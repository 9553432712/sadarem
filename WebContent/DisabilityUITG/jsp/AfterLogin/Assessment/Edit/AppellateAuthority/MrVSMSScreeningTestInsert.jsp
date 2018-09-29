<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
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
                document.forms[0].action="MentalRetardationForwdAction.do";
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
                else{
                    thisform.setAttribute('submitted','true');
                    document.getElementById('addBut').disabled = true;
                }
            }
        </script> 
    </head>
    <BODY >


        <html:form action="/vsmsscreeningtestaction.do?MRVsmsscreeningTestdetailsinsert=MRVsmsscreeningTestdetailsinsert" styleId="form"
                   onsubmit="return avoidDuplicateForm(this)">
        

        <table  align="center" cellspacing="1" cellpadding="0" class="inputform" width="100%">
            <tr>
                <th colspan="12">ADD VINELAND SOCIAL MATURITY SCALE RECORD SHEET</th>
            </tr></table>
        <div class="tabber">
            <div class="tabbertab">
                <h2>(0-3)YEARS</h2>
                <table  align="center" cellspacing="1" cellpadding="0" class="inputform" width="90%">
                    <tr >
                        <td valign="top">
                            <table  align="center" cellspacing="1" cellpadding="0" class="inputform" width="100%">
                                <tr >
                                    <th>&nbsp;</th>
                                    <th>&nbsp;</th>
                                    <th>&nbsp;</th>
                                    <th>Years</th>
                                    <th>Months</th>
                                </tr>
                                <tr >
                                    <th>&nbsp;</th>
                                    <th>0-1 YEAR</th>
                                                <th>Select All <input type="checkbox" id="3MSelectAll" value="true" onclick="enableOrDisable3MonthsFields()" style="width:25px"/></th>
                                                <th>&nbsp;</th>
                                                <th>&nbsp;</th>
                                                </tr>
                                                <tr >
                                                    <td>1.&nbsp;</td>
                                                    <td align="left">Coos Laughs</td>
                                                    <td align="left"> <html:checkbox styleId="1" property="vsms_0to1_CoolsLaughs"  value="1" style="width:25px" onclick="checkSelectAllBasedonOnload()" /></td>

                                                    <td>&nbsp;</td>
                                                    <td>0.7 </td>
                                                </tr>
                                                <tr >
                                                    <td>2.&nbsp;</td>
                                                    <td align="left"> Balences head</td>
                                                    <td align="left"> <html:checkbox  styleId="2" property="vsms_0to1_Balenceshead"  value="1"  style="width:25px" onclick="checkSelectAllBasedonOnload()" /></td>
                                                    <td>&nbsp;</td>
                                                    <td>1.4</td>
                                                </tr>
                                                <tr >
                                                    <td>3.&nbsp;</td>
                                                    <td align="left">Graphs object within reach</td>
                                                    <td align="left"> <html:checkbox styleId="3" property="vsms_0to1_Graphsobject"  value="1" style="width:25px" onclick="checkSelectAllBasedonOnload()" /></td>

                                                    <td>&nbsp;</td>
                                                    <td>2.1</td>
                                                </tr>
                                                <tr >
                                                    <td>4.&nbsp;</td>
                                                    <td align="left">Reaches for familiar persons</td>
                                                    <td align="left"> <html:checkbox styleId="4" property="vsms_0to1_Reachesforfamiliarpersons"  value="1" style="width:25px" onclick="checkSelectAllBasedonOnload()" /></td>
                                                    <td>&nbsp;</td>
                                                    <td>2.8</td>
                                                </tr>
                                                <tr >
                                                    <td>5.&nbsp;</td>
                                                    <td align="left"> Rolls over(unassisted)</td>
                                                    <td align="left"> <html:checkbox styleId="5" property="vsms_0to1_Rolls"  value="1" style="width:25px" onclick="checkSelectAllBasedonOnload()" /></td>

                                                    <td>&nbsp;</td>
                                                    <td>3.5</td>
                                                </tr>
                                                <tr >
                                                    <td>6.&nbsp;</td>
                                                    <td align="left"> Reaches for neraby objects</td>
                                                    <td align="left"> <html:checkbox styleId="6" property="vsms_0to1_Reachesforobjects"  value="1" style="width:25px" onclick="checkSelectAllBasedonOnload()" /></td>
                                                    <td>&nbsp;</td>
                                                    <td>4.2</td>
                                                </tr>
                                                <tr >
                                                    <td>7.&nbsp;</td>
                                                    <td align="left">Occupies self unattened</td>
                                                    <td align="left"> <html:checkbox styleId="7" property="vsms_0to1_Occupies"  value="1" style="width:25px" onclick="checkSelectAllBasedonOnload()" /></td>
                                                    <td>&nbsp;</td>
                                                    <td>4.9</td>
                                                </tr>

                                                <tr >
                                                    <td>8.&nbsp;</td>
                                                    <td align="left"> Sits unsupported</td>
                                                    <td align="left"> <html:checkbox styleId="8" property="vsms_0to1_Sits"  value="1"  style="width:25px" onclick="checkSelectAllBasedonOnload()" /></td>
                                                    <td>&nbsp;</td>
                                                    <td>5.6</td>
                                                </tr>
                                                <tr >
                                                    <td>9.&nbsp;</td>
                                                    <td align="left"> Pulls self upright</td>
                                                    <td align="left"> <html:checkbox  styleId="9" property="vsms_0to1_Pulls"  value="1" style="width:25px"  onclick="checkSelectAllBasedonOnload()"/></td>
                                                    <td>&nbsp;</td>
                                                    <td>6.3</td>
                                                </tr>
                                                <tr >
                                                    <td>10.&nbsp;</td>
                                                    <td align="left"> Talks imitates sounds</td>
                                                    <td align="left"> <html:checkbox styleId="10" property="vsms_0to1_Talks"  value="1" style="width:25px" onclick="checkSelectAllBasedonOnload()" /></td>
                                                    <td>&nbsp;</td>
                                                    <td>7.0</td>
                                                </tr>
                                                <tr >
                                                    <td>11.&nbsp;</td>
                                                    <td align="left">Drinks from cup or glass assisted</td>
                                                    <td align="left"> <html:checkbox  styleId="11" property="vsms_0to1_Drinks"  value="1"  style="width:25px" onclick="checkSelectAllBasedonOnload()" /></td>

                                                    <td>&nbsp;</td>
                                                    <td>7.7</td>
                                                </tr>
                                                <tr >
                                                    <td>12.&nbsp;</td>
                                                    <td align="left"> Moves about on floor (Creeping,crawling)</td>
                                                    <td align="left"> <html:checkbox styleId="12" property="vsms_0to1_Moves"  value="1" style="width:25px"  onclick="checkSelectAllBasedonOnload()" /></td>
                                                    <td>&nbsp;</td>
                                                    <td>8.4</td>
                                                </tr>

                                                <tr >
                                                    <td>13.&nbsp;</td>
                                                    <td align="left">Grasps with thumb and finger</td>
                                                    <td align="left"> <html:checkbox styleId="13" property="vsms_0to1_Grasps"  value="1" style="width:25px" onclick="checkSelectAllBasedonOnload()"  /></td>
                                                    <td>&nbsp;</td>
                                                    <td>9.1</td>
                                                </tr>
                                                <tr >
                                                    <td>14.&nbsp;</td>
                                                    <td align="left">Demands personal attention</td>
                                                    <td align="left"> <html:checkbox styleId="14" property="vsms_0to1_Demands"  value="1" style="width:25px" onclick="checkSelectAllBasedonOnload()" /></td>
                                                    <td>&nbsp;</td>
                                                    <td>9.8</td>
                                                </tr>
                                                <tr >
                                                    <td>15.&nbsp;</td>
                                                    <td align="left">Stands alone</td>
                                                    <td align="left"> <html:checkbox styleId="15" property="vsms_0to1_Stands"  value="1"  style="width:25px" onclick="checkSelectAllBasedonOnload()" /></td>
                                                    <td>&nbsp;</td>
                                                    <td>10.6</td>
                                                </tr>
                                                <tr >
                                                    <td>16.&nbsp;</td>
                                                    <td align="left">Does not drool</td>
                                                    <td align="left"> <html:checkbox styleId="16" property="vsms_0to1_Doesnotdrool"  value="1" style="width:25px" onclick="checkSelectAllBasedonOnload()"  /></td>
                                                    <td>&nbsp;</td>
                                                    <td>11.3</td>
                                                </tr>
                                                <tr >
                                                    <td>17.&nbsp;</td>
                                                    <td align="left">Follows simple instructions</td>
                                                    <td align="left"> <html:checkbox styleId="17" property="vsms_0to1_Follows"  value="1" style="width:25px" onclick="checkSelectAllBasedonOnload()" /></td>

                                                    <td>&nbsp;</td>
                                                    <td>12.0</td>
                                                </tr>


                                                <tr >
                                                    <td>&nbsp;</td>
                                                    <td>&nbsp;</td>
                                                    <td>&nbsp;</td>
                                                    <td></td>
                                                    <td></td>
                                                </tr>


                                                </table>
                                                </td>
                                                <td>
                                                    <table  align="center" cellspacing="1" cellpadding="0" class="inputform" width="100%">
                                                        <tr >
                                                            <th>&nbsp;</th>
                                                            <th>&nbsp;</th>
                                                            <th>&nbsp;</th>
                                                            <th>Years</th>
                                                            <th>Months</th>
                                                        </tr>
                                                        <tr >
                                                            <th>&nbsp;</th>
                                                            <th><b>1-2 YEARS</b></th>
                                                            <th>Select All <input type="checkbox" id="1YSelectAll" value="true" style="width:25px" onclick="enableOrDisable1to2yearsFields()"/></th>
                                                            <th>&nbsp;</th>
                                                            <th>&nbsp;</th>
                                                        </tr>

                                                        <tr >
                                                            <td>18.&nbsp;</td>
                                                            <td align="left"> Walks about room unattened</td>
                                                            <td align="left"> <html:checkbox styleId="18" property="vsms_1to2_Walks"  value="1" style="width:25px" onclick="checkSelectAllBasedonOnload()" /></td>
                                                            <td>1</td>
                                                            <td>0.7</td>
                                                        </tr>
                                                        <tr >
                                                            <td>19.&nbsp;</td>
                                                            <td align="left">Marks with pencil or crayon or chalk </td>
                                                            <td align="left"> <html:checkbox styleId="19" property="vsms_1to2_Marks"  value="1" style="width:25px" onclick="checkSelectAllBasedonOnload()" /></td>

                                                            <td>1</td>
                                                            <td>1.4</td>
                                                        </tr>
                                                        <tr >
                                                            <td>20.&nbsp;</td>
                                                            <td align="left">Masticates (chews) solid or semi-solid food</td>
                                                            <td align="left"> <html:checkbox styleId="20" property="vsms_1to2_Masticates"  value="1" style="width:25px" onclick="checkSelectAllBasedonOnload()" /></td>
                                                            <td>1</td>
                                                            <td>2.1</td>
                                                        </tr>
                                                        <tr >
                                                            <td>21.&nbsp;</td>
                                                            <td align="left"> Pulls off clothes</td>
                                                            <td align="left"> <html:checkbox styleId="21" property="vsms_1to2_Pulls"  value="1" style="width:25px" onclick="checkSelectAllBasedonOnload()" /></td>
                                                            <td>1</td>
                                                            <td>2.8</td>
                                                        </tr>
                                                        <tr >
                                                            <td>22.&nbsp;</td>
                                                            <td align="left"> Transfers objects</td>
                                                            <td align="left"> <html:checkbox styleId="22" property="vsms_1to2_Transfers"  value="1" style="width:25px" onclick="checkSelectAllBasedonOnload()" /></td>

                                                            <td>1</td>
                                                            <td>3.5</td>
                                                        </tr>
                                                        <tr >
                                                            <td>23.&nbsp;</td>
                                                            <td align="left"> Overcomes simple obstacles</td>
                                                            <td align="left"> <html:checkbox styleId="23" property="vsms_1to2_Overcomessimpleobstacles"  value="1" style="width:25px" onclick="checkSelectAllBasedonOnload()" /></td>
                                                            <td>1</td>
                                                            <td>4.2</td>
                                                        </tr>
                                                        <tr >
                                                            <td>24.&nbsp;</td>
                                                            <td align="left">Fetches or carries familiar objects</td>
                                                            <td align="left"> <html:checkbox styleId="24" property="vsms_1to2_Fetches"  value="1" style="width:25px" onclick="checkSelectAllBasedonOnload()" /></td>
                                                            <td>1</td>
                                                            <td>4.9</td>
                                                        </tr>
                                                        <tr >
                                                            <td>25.&nbsp;</td>
                                                            <td align="left"> Drinks from cup or glass unassisted</td>
                                                            <td align="left"> <html:checkbox styleId="25" property="vsms_1to2_Drinks"  value="1" style="width:25px" onclick="checkSelectAllBasedonOnload()" /></td>
                                                            <td>1</td>
                                                            <td>5.6</td>
                                                        </tr>
                                                        <tr >
                                                            <td>26.&nbsp;</td>
                                                            <td align="left"> Walks without support</td>
                                                            <td align="left"> <html:checkbox styleId="26" property="vsms_1to2_Walkswithoutsupport"  value="1" style="width:25px"  onclick="checkSelectAllBasedonOnload()"/></td>
                                                            <td>1</td>
                                                            <td>6.3</td>
                                                        </tr>
                                                        <tr >
                                                            <td>27.&nbsp;</td>
                                                            <td align="left">  Plays with other children</td>
                                                            <td align="left"> <html:checkbox styleId="27" property="vsms_1to2_Plays"  value="1" style="width:25px" onclick="checkSelectAllBasedonOnload()" /></td>

                                                            <td>1</td>
                                                            <td>7.0</td>
                                                        </tr>
                                                        <tr >
                                                            <td>28.&nbsp;</td>
                                                            <td align="left"> Eats with own hands(biscuits,bread etc)</td>
                                                            <td align="left"> <html:checkbox styleId="28"property="vsms_1to2_Eats"  value="1" style="width:25px" onclick="checkSelectAllBasedonOnload()" /></td>
                                                            <td>1</td>
                                                            <td>7.7</td>
                                                        </tr>
                                                        <tr >
                                                            <td>29.&nbsp;</td>
                                                            <td align="left"> Goes about house or yard</td>
                                                            <td align="left"> <html:checkbox styleId="29"property="vsms_1to2_Goes"  value="1" style="width:25px" onclick="checkSelectAllBasedonOnload()" /></td>

                                                            <td>1</td>
                                                            <td>8.4</td>
                                                        </tr>
                                                        <tr >
                                                            <td>30.&nbsp;</td>
                                                            <td align="left"> Discriminates edible substances from non-edibles</td>

                                                            <td align="left"> <html:checkbox styleId="30" property="vsms_1to2_Discriminates" style="width:25px" value="1" onclick="checkSelectAllBasedonOnload()" /></td>

                                                            <td>1</td>
                                                            <td>9.2</td>
                                                        </tr>
                                                        <tr >
                                                            <td>31.&nbsp;</td>
                                                            <td align="left"> Uses names of familiar objects</td>
                                                            <td align="left"> <html:checkbox styleId="31" property="vsms_1to2_Usesnames"  value="1" style="width:25px" onclick="checkSelectAllBasedonOnload()" /></td>
                                                            <td>1</td>
                                                            <td>9.8</td>
                                                        </tr>
                                                        <tr >
                                                            <td>32.&nbsp;</td>
                                                            <td align="left"> Walks upstairs unassisted</td>
                                                            <td align="left"> <html:checkbox styleId="32" property="vsms_1to2_Walksupstairs"  value="1" style="width:25px" onclick="checkSelectAllBasedonOnload()" /></td>
                                                            <td>1</td>
                                                            <td>10.6</td>
                                                        </tr>
                                                        <tr >
                                                            <td>33.&nbsp;</td>
                                                            <td align="left">Unwraps sweets,chocolates</td>
                                                            <td align="left"> <html:checkbox styleId="33" property="vsms_1to2_Unwarps"  value="1" style="width:25px" onclick="checkSelectAllBasedonOnload()" /></td>
                                                            <td>1</td>
                                                            <td>11.3</td>
                                                        </tr>
                                                        <tr >
                                                            <td>34.&nbsp;</td>
                                                            <td align="left"> Talks in short sentences</td>
                                                            <td align="left"> <html:checkbox styleId="34" property="vsms_1to2_Talks"  value="1"  style="width:25px" onclick="checkSelectAllBasedonOnload()" /></td>
                                                            <td>1</td>
                                                            <td>12.0</td>
                                                        </tr>
                                                        <tr >
                                                            <th>&nbsp;</th>
                                                            <th><b>2-3 YEARS</b></th>
                                                            <th>Select All <input type="checkbox" id="2YSelectAll" value="true" style="width:25px"  onclick="enableOrDisable2to3yearsFields()"/></th>
                                                            <th>&nbsp;</th>
                                                            <th>&nbsp;</th>
                                                        </tr>
                                                        <tr >
                                                            <td>35.&nbsp;</td>
                                                            <td align="left"> Signals to go to toilet</td>
                                                            <td align="left"> <html:checkbox styleId="35" property="vsms_2to3_Signals"  value="1" style="width:25px" onclick="checkSelectAllBasedonOnload()" /></td>
                                                            <td>2</td>
                                                            <td>1.2</td>
                                                        </tr>
                                                        <tr >
                                                            <td>36.&nbsp;</td>
                                                            <td align="left"> Initiates own play activities</td>
                                                            <td align="left"> <html:checkbox styleId="36" property="vsms_2to3_Initiates"  value="1" style="width:25px" onclick="checkSelectAllBasedonOnload()" /></td>
                                                            <td>2</td>
                                                            <td>2.4</td>
                                                        </tr>
                                                        <tr >
                                                            <td>37.&nbsp;</td>
                                                            <td align="left"> Removes shirt or frock if unbuttoned</td>
                                                            <td align="left"> <html:checkbox styleId="37" property="vsms_2to3_Removesshirt"  value="1" style="width:25px" onclick="checkSelectAllBasedonOnload()"/></td>

                                                            <td>2</td>
                                                            <td>3.6</td>
                                                        </tr>
                                                        <tr >
                                                            <td>38.&nbsp;</td>
                                                            <td align="left"> Eats with spoon/hands (food)</td>
                                                            <td align="left"> <html:checkbox styleId="38" property="vsms_2to3_Eatswithspoon"  value="1"  style="width:25px" onclick="checkSelectAllBasedonOnload()" /></td>
                                                            <td>2</td>
                                                            <td>4.8</td>
                                                        </tr>
                                                        <tr >
                                                            <td>39.&nbsp;</td>
                                                            <td align="left"> Gets drink(water)unassisted</td>
                                                            <td align="left"> <html:checkbox styleId="39" property="vsms_2to3_Getsdrink"  value="1"  style="width:25px" onclick="checkSelectAllBasedonOnload()" /></td>
                                                            <td>2</td>
                                                            <td>6.0</td>
                                                        </tr>
                                                        <tr >
                                                            <td>40.&nbsp;</td>
                                                            <td align="left"> Dries own hands</td>
                                                            <td align="left"> <html:checkbox styleId="40" property="vsms_2to3_Dries"  value="1" style="width:25px" onclick="checkSelectAllBasedonOnload()" /></td>
                                                            <td>2</td>
                                                            <td>7.2</td>
                                                        </tr>
                                                        <tr >
                                                            <td>41.&nbsp;</td>
                                                            <td align="left"> Avoids simple hazards</td>
                                                            <td align="left"> <html:checkbox styleId="41" property="vsms_2to3_Avoids"  value="1" style="width:25px" onclick="checkSelectAllBasedonOnload()" /></td>
                                                            <td>2</td>
                                                            <td>8.4</td>
                                                        </tr>
                                                        <tr >
                                                            <td>42.&nbsp;</td>
                                                            <td align="left"> Puts on shirt or frock<br>unassisted</td>
                                                            <td align="left"> <html:checkbox styleId="42" property="vsms_2to3_Putsonshirt"  value="1"  style="width:25px" onclick="checkSelectAllBasedonOnload()" /></td>
                                                            <td>2</td>
                                                            <td>9.6</td>
                                                        </tr>
                                                        <tr >
                                                            <td>43.&nbsp;</td>
                                                            <td align="left"> Can do paper folding</td>
                                                            <td align="left"> <html:checkbox styleId="43" property="vsms_2to3_Candopaperfolding"  value="1" style="width:25px" onclick="checkSelectAllBasedonOnload()" /></td>
                                                            <td>2</td>
                                                            <td>10.8</td>
                                                        </tr>
                                                        <tr >
                                                            <td>44.&nbsp;</td>
                                                            <td align="left"> Relates experiences</td>
                                                            <td align="left"> <html:checkbox styleId="44" property="vsms_2to3_Relates"  value="1" style="width:25px" onclick="checkSelectAllBasedonOnload()" /></td>
                                                            <td>2</td>
                                                            <td>12.0</td>
                                                        </tr>
                                                    </table>
                                                </td>
                                                </tr>
                                                </table>
                                                </div>
                                                <div class="tabbertab">
                                                    <h2>(4-6)YEARS</h2>
                                                    <table  align="center" cellspacing="1" cellpadding="0" class="inputform" width="90%">
                                                        <tr >
                                                            <td>
                                                                <table  align="center" cellspacing="1" cellpadding="0" class="inputform" width="100%">
                                                                    <tr >
                                                                        <th>&nbsp;</th>
                                                                        <th>&nbsp;</th>
                                                                        <th>&nbsp;</th>
                                                                        <th>Years</th>
                                                                        <th>Months</th>
                                                                    </tr>
                                                                    <tr >
                                                                        <th>&nbsp;</th>
                                                                        <th><b>3-4 YEARS</b></th>
                                                                        <th >Select All <input type="checkbox" id="3YSelectAll" value="true" style="width:25px" onclick="enableOrDisable3to4yearsFields()"/></th>
                                                                        <th>&nbsp;</th>
                                                                        <th>&nbsp;</th>
                                                                    </tr>
                                                                    <tr >
                                                                        <td>45.&nbsp;</td>
                                                                        <td align="left"> Walks downsstairs one  step at a time</td>
                                                                        <td align="left"> <html:checkbox styleId="45" property="vsms_3to4_Walksdownsstairs"  value="1" style="width:25px" onclick="checkSelectAllBasedonOnload()" /></td>
                                                                        <td>3</td>
                                                                        <td>2</td>
                                                                    </tr>
                                                                    <tr >
                                                                        <td>46.&nbsp;</td>
                                                                        <td align="left"> Plays cooperatively at kindergarten level</td>
                                                                        <td align="left"> <html:checkbox styleId="46" property="vsms_3to4_Playscooperatively"  value="1" style="width:25px" onclick="checkSelectAllBasedonOnload()" /></td>
                                                                        <td>3</td>
                                                                        <td>4</td>
                                                                    </tr>
                                                                    <tr >
                                                                        <td>47.&nbsp;</td>
                                                                        <td align="left"> Buttons shirt or frock</td>
                                                                        <td align="left"> <html:checkbox styleId="47" property="vsms_3to4_Buttons"  value="1" style="width:25px" onclick="checkSelectAllBasedonOnload()" /></td>
                                                                        <td>3</td>
                                                                        <td>6</td>
                                                                    </tr>
                                                                    <tr >
                                                                        <td>48.&nbsp;</td>
                                                                        <td align="left"> Helps at little household tasks</td>
                                                                        <td align="left"> <html:checkbox styleId="48" property="vsms_3to4_Helps"  value="1" style="width:25px" onclick="checkSelectAllBasedonOnload()" /></td>
                                                                        <td>3</td>
                                                                        <td>8</td>
                                                                    </tr>
                                                                    <tr >
                                                                        <td>49.&nbsp;</td>
                                                                        <td align="left"> Performs for others</td>
                                                                        <td align="left"> <html:checkbox styleId="49" property="vsms_3to4_Performs"  value="1" style="width:25px" onclick="checkSelectAllBasedonOnload()" /></td>
                                                                        <td>3</td>
                                                                        <td>10</td>
                                                                    </tr>
                                                                    <tr >
                                                                        <td>50.&nbsp;</td>
                                                                        <td align="left"> Washes hands unaided</td>
                                                                        <td align="left"> <html:checkbox styleId="50" property="vsms_3to4_Washes"  value="1" style="width:25px" onclick="checkSelectAllBasedonOnload()" /></td>

                                                                        <td>3</td>
                                                                        <td>12</td>
                                                                    </tr>

                                                                    <tr >
                                                                        <td>&nbsp;</td>
                                                                        <td>&nbsp;</td>
                                                                        <td>&nbsp;</td>
                                                                        <td></td>
                                                                        <td></td>
                                                                    </tr>

                                                                    <tr >
                                                                        <th>&nbsp;</th>
                                                                        <th><b>4-5 YEARS</b></th>
                                                                        <th>Select All <input type="checkbox" id="4YSelectAll" value="true" style="width:25px" onclick="enableOrDisable4to5yearsFields()"/></th>
                                                                       <th>&nbsp;</th>
                                                                       <th>&nbsp;</th>
                                                                    </tr>
                                                                    <tr >
                                                                        <td>51.&nbsp;</td>
                                                                        <td align="left"> Cares for self at toilet</td>
                                                                        <td align="left"> <html:checkbox styleId="51" property="vsms_4to5_Cares"  value="1" style="width:25px" onclick="checkSelectAllBasedonOnload()" /></td>

                                                                        <td>4</td>
                                                                        <td>2</td>
                                                                    </tr>

                                                                    <tr >
                                                                        <td>52.&nbsp;</td>
                                                                        <td align="left"> Washes face unassisted</td>
                                                                        <td align="left"> <html:checkbox styleId="52" property="vsms_4to5_Prints"  value="1" style="width:25px" onclick="checkSelectAllBasedonOnload()" /></td>


                                                                        <td>4</td>
                                                                        <td>4</td>
                                                                    </tr>

                                                                    <tr >
                                                                        <td>53.&nbsp;</td>
                                                                        <td align="left"> Goes about neighbourhood unattened</td>
                                                                        <td align="left"> <html:checkbox styleId="53" property="vsms_4to5_Goesaboutneighbourhood"  value="1" style="width:25px" onclick="checkSelectAllBasedonOnload()" /></td>


                                                                        <td>4</td>
                                                                        <td>6</td>
                                                                    </tr>

                                                                    <tr >
                                                                        <td>54.&nbsp;</td>
                                                                        <td align="left"> Dresses self except for tying</td>
                                                                        <td align="left"> <html:checkbox styleId="54" property="vsms_4to5_Dresses"  value="1" style="width:25px" onclick="checkSelectAllBasedonOnload()" /></td>

                                                                        <td>4</td>
                                                                        <td>8</td>
                                                                    </tr>
                                                                    <tr >
                                                                        <td>55.&nbsp;</td>
                                                                        <td align="left"> Uses pencil or crayon or chalk for drawing</td>
                                                                        <td align="left"> <html:checkbox styleId="55" property="vsms_4to5_Usespencil"  value="1" style="width:25px" onclick="checkSelectAllBasedonOnload()" /></td>

                                                                        <td>4</td>
                                                                        <td>10</td>
                                                                    </tr>
                                                                    <tr >
                                                                        <td>56.&nbsp;</td>
                                                                        <td align="left"> Plays competitive exercise games</td>
                                                                        <td align="left"> <html:checkbox styleId="56" property="vsms_4to5_Playscompetitive"  value="1" style="width:25px" onclick="checkSelectAllBasedonOnload()" /></td>

                                                                        <td>4</td>
                                                                        <td>12</td>
                                                                    </tr>



                                                                </table>
                                                            </td>
                                                            <td valign="top">
                                                                <table  align="center" cellspacing="1" cellpadding="0" class="inputform" width="100%">
                                                                    <tr >
                                                                        <th>&nbsp;</th>
                                                                        <th>&nbsp;</th>
                                                                        <th>&nbsp;</th>
                                                                        <th>Years</th>
                                                                        <th>Months</th>
                                                                    </tr>
                                                                    <tr >
                                                                        <th>&nbsp;</th>
                                                                        <th><b>5-6 YEARS</b></th>
                                                                        <th width="25%">Select All<input type="checkbox" id="5YSelectAll" value="true" style="width:25px" onclick="enableOrDisable5to6yearsFields()"/></th>
                                                                        <th>&nbsp;</th>
                                                                        <th>&nbsp;</th>
                                                                    </tr>
                                                                    <tr >
                                                                        <td>57</td>
                                                                        <td align="left"> Uses hoops,files,kites or uses knife</td>
                                                                        <td align="left"> <html:checkbox styleId="57" property="vsms_5to6_Useshoops"  value="1" style="width:25px" onclick="checkSelectAllBasedonOnload()" /></td>

                                                                        <td>5</td>
                                                                        <td>2.4</td>
                                                                    </tr>
                                                                    <tr >
                                                                        <td>58</td>
                                                                        <td align="left"> Prints(writes) simple words</td>
                                                                        <td align="left"> <html:checkbox styleId="58" property="vsms_5to6_Printssimplewords"  value="1" style="width:25px" onclick="checkSelectAllBasedonOnload()" /></td>
                                                                        <td>5</td>
                                                                        <td>4.8</td>
                                                                    </tr>
                                                                    <tr >
                                                                        <td>59.&nbsp;</td>
                                                                        <td align="left"> Plays simple games which require talking turns</td>
                                                                        <td align="left"> <html:checkbox styleId="59" property="vsms_5to6_Playssimplegames"  value="1" style="width:25px" onclick="checkSelectAllBasedonOnload()" /></td>
                                                                        <td>5</td>
                                                                        <td>7.2</td>
                                                                    </tr>
                                                                    <tr >
                                                                        <td>60.&nbsp;</td>
                                                                        <td align="left"> Is trusted with money</td>
                                                                        <td align="left"> <html:checkbox styleId="60" property="vsms_5to6_trusted"  value="1" style="width:25px" onclick="checkSelectAllBasedonOnload()" /></td>
                                                                        <td>5</td>
                                                                        <td>9.6</td>
                                                                    </tr>
                                                                    <tr >
                                                                        <td>61.&nbsp;</td>
                                                                        <td align="left"> Goes to school unattened</td>
                                                                        <td align="left"> <html:checkbox styleId="61" property="vsms_5to6_Goestoschool"  value="1" style="width:25px" onclick="checkSelectAllBasedonOnload()" /></td>
                                                                        <td>5</td>
                                                                        <td>12.0</td>
                                                                    </tr>


                                                                </table>

                                                            </td>
                                                        </tr>
                                                    </table>
                                                </div>
                                                <div class="tabbertab">
                                                    <h2>(7-9)YEARS</h2>
                                                    <table  align="center" cellspacing="1" cellpadding="0" class="inputform" width="90%">
                                                        <tr>
                                                            <td>
                                                                <table  align="center" cellspacing="1" cellpadding="0" class="inputform" width="100%">
                                                                    <tr>
                                                                        <th>&nbsp;</th>
                                                                        <th>&nbsp;</th>
                                                                        <th>&nbsp;</th>
                                                                        <th>Years</th>
                                                                        <th>Months</th>
                                                                    </tr>
                                                                    <tr >
                                                                        <th>&nbsp;</th>
                                                                        <th><b>6-7 YEARS</b></th>
                                                                        <th>Select All <input type="checkbox" id="6YSelectAll" value="true" style="width:25px" onclick="enableOrDisable6to7yearsFields()"/></th>
                                                                        <th>&nbsp;</th>
                                                                        <th>&nbsp;</th>
                                                                    </tr>
                                                                    <tr >
                                                                        <td>62.</td>
                                                                        <td align="left"> Mixes rice properly unassisted</td>
                                                                        <td align="left"> <html:checkbox styleId="62" property="vsms_6to7_Mixes"  value="1" style="width:25px" onclick="checkSelectAllBasedonOnload()" /></td>
                                                                        <td>6</td>
                                                                        <td>3</td>
                                                                    </tr>
                                                                    <tr >
                                                                        <td>63.</td>
                                                                        <td align="left"> Uses pencil or chalk for writing</td>
                                                                        <td align="left"> <html:checkbox styleId="63" property="vsms_6to7_Usespencilorchalk"  value="1" style="width:25px" onclick="checkSelectAllBasedonOnload()" /></td>

                                                                        <td>6</td>
                                                                        <td>6</td>
                                                                    </tr>
                                                                    <tr >
                                                                        <td>64.</td>
                                                                        <td align="left"> Bathes self assisted</td>
                                                                        <td align="left"> <html:checkbox styleId="64" property="vsms_6to7_Batches"  value="1" style="width:25px" onclick="checkSelectAllBasedonOnload()" /></td>
                                                                        <td>6</td>
                                                                        <td>9</td>
                                                                    </tr>
                                                                    <tr >
                                                                        <td>65.</td>
                                                                        <td align="left"> Goes to bed unassisted</td>
                                                                        <td align="left"> <html:checkbox styleId="65" property="vsms_6to7_Goestobed"  value="1" style="width:25px" onclick="checkSelectAllBasedonOnload()" /></td>
                                                                        <td>6</td>
                                                                        <td>12</td>
                                                                    </tr>

                                                                    <tr><td colspan="5"><hr></td></tr>

                                                                    <tr>
                                                                        <th>&nbsp;</th>
                                                                        <th><b>7-8 YEARS</b></th>
                                                                        <th>Select All <input type="checkbox" id="7YSelectAll" value="true" style="width:25px" onclick="enableOrDisable7to8yearsFields()"/></th>
                                                                        <th>&nbsp;</th>
                                                                        <th>&nbsp;</th>
                                                                    </tr>
                                                                    <tr>
                                                                        <td>66.</td>
                                                                        <td align="left"> Can differentiate between AM & PM</td>
                                                                        <td align="left"> <html:checkbox styleId="66" property="vsms_7to8_Candifferentiatebetween"  value="1" style="width:25px"  onclick="checkSelectAllBasedonOnload()"/></td>
                                                                        <td>7</td>
                                                                        <td>2.4</td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td>67.</td>
                                                                        <td align="left"> Helps himself during meals</td>
                                                                        <td align="left"> <html:checkbox styleId="67" property="vsms_7to8_Helps"  value="1" style="width:25px" onclick="checkSelectAllBasedonOnload()" /></td>
                                                                        <td>7</td>
                                                                        <td>4.8</td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td>68.&nbsp;</td>
                                                                        <td align="left"> Understands and keeps family secrets</td>
                                                                        <td align="left"> <html:checkbox styleId="68" property="vsms_7to8_Understands"  value="1" style="width:25px" onclick="checkSelectAllBasedonOnload()"  /></td>
                                                                        <td>7</td>
                                                                        <td>7.2</td>
                                                                    </tr>

                                                                    <tr>
                                                                        <td>69.&nbsp;</td>
                                                                        <td align="left"> Participates in pre-adolescent play</td>
                                                                        <td align="left"> <html:checkbox styleId="69" property="vsms_7to8_Participates"  value="1" style="width:25px" onclick="checkSelectAllBasedonOnload()" /></td>
                                                                        <td>7</td>
                                                                        <td>9.6</td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td>70.&nbsp;</td>
                                                                        <td align="left"> Combs or brushes hair</td>
                                                                        <td align="left"> <html:checkbox styleId="70" property="vsms_7to8_Combs"  value="1" style="width:25px" onclick="checkSelectAllBasedonOnload()" /></td>
                                                                        <td>7</td>
                                                                        <td>12.0</td>
                                                                    </tr>


                                                                </table>
                                                            </td>
                                                            <td valign="top">
                                                                <table  align="center" cellspacing="1" cellpadding="0" class="inputform" width="100%">
                                                                    <tr>
                                                                        <th>&nbsp;</th>
                                                                        <th>&nbsp;</th>
                                                                        <th>&nbsp;</th>
                                                                        <th>Years</th>
                                                                        <th>Months</th>
                                                                    </tr>
                                                                    <tr>
                                                                        <th>&nbsp;</th>
                                                                        <th><b>8-9 YEARS</b></th>
                                                                        <th>Select All <input type="checkbox" id="8YSelectAll" value="true" style="width:25px" onclick="enableOrDisable8to9yearsFields()"/></th>
                                                                        <th>&nbsp;</th>
                                                                        <th>&nbsp;</th>
                                                                    </tr>
                                                                    <tr >
                                                                        <td>71.&nbsp;</td>
                                                                        <td align="left"> Uses tools or utensils</td>
                                                                        <td align="left"> <html:checkbox styleId="71" property="vsms_8to9_Usestools"  value="1" style="width:25px" onclick="checkSelectAllBasedonOnload()" /></td>
                                                                        <td>8</td>
                                                                        <td>3</td>
                                                                    </tr>
                                                                    <tr >
                                                                        <td>72.&nbsp;</td>
                                                                        <td align="left"> Does routine household tasks</td>
                                                                        <td align="left"> <html:checkbox styleId="72" property="vsms_8to9_routinehouseholdtasks"  value="1" style="width:25px" onclick="checkSelectAllBasedonOnload()" /></td>
                                                                        <td>8</td>
                                                                        <td>6</td>
                                                                    </tr>
                                                                    <tr >
                                                                        <td>73.&nbsp;</td>
                                                                        <td align="left"> Reads on own initiative</td>
                                                                        <td align="left"> <html:checkbox styleId="73" property="vsms_8to9_Readsonowninitiative"  value="1" style="width:25px" onclick="checkSelectAllBasedonOnload()" /></td>

                                                                        <td>8</td>
                                                                        <td>9</td>
                                                                    </tr>
                                                                    <tr >
                                                                        <td>74.&nbsp;</td>
                                                                        <td align="left"> Bathes self unaided</td>
                                                                        <td align="left"> <html:checkbox styleId="74" property="vsms_8to9_Batchesselfunaided"  value="1" style="width:25px" onclick="checkSelectAllBasedonOnload()" /></td>
                                                                        <td>8</td>
                                                                        <td>12</td>
                                                                    </tr>



                                                                </table>
                                                            </td>
                                                        </tr>
                                                    </table>
                                                </div>
                                                <div class="tabbertab">
                                                    <h2>(10-15)YEARS </h2>
                                                    <table  align="center" cellspacing="1" cellpadding="0" class="inputform" width="90%">
                                                        <tr >
                                                            <td>
                                                               <table  align="center" cellspacing="1" cellpadding="0" class="inputform" width="100%">
                                                                    <tr >
                                                                        <th>&nbsp;</th>
                                                                        <th>&nbsp;</th>
                                                                        <th>&nbsp;</th>
                                                                        <th>Years</th>
                                                                        <th>Months</th>
                                                                    </tr>
                                                                    <tr >
                                                                        <th>&nbsp;</th>
                                                                        <th><b>9-10 YEARS</b></th>
                                                                        <th>Select All <input type="checkbox" id="9YSelectAll" value="true" style="width:25px" onclick="enableOrDisable10to11yearsFields()" style="width:25px"/></th>
                                                                       <th>&nbsp;</th>
                                                                        <th>&nbsp;</th>
                                                                    </tr>
                                                                    <tr >
                                                                        <td>75.&nbsp;</td>
                                                                        <td align="left"> Cares for self at meals</td>
                                                                        <td align="left"> <html:checkbox styleId="75" property="vsms_9to10_Caresforself"  value="1" style="width:25px" style="width:25px" onclick="checkSelectAllBasedonOnload()" /></td>
                                                                        <td>9</td>
                                                                        <td>4</td>
                                                                    </tr>
                                                                    <tr >
                                                                        <td>76.&nbsp;</td>11-12 YEARS
                                                                        <td align="left"> Makes minor purchases</td>
                                                                        <td align="left"> <html:checkbox styleId="76" property="vsms_9to10_Makesminorpurchases"  value="1" style="width:25px" style="width:25px" onclick="checkSelectAllBasedonOnload()" /></td>
                                                                        <td>9</td>
                                                                        <td>8</td>
                                                                    </tr>
                                                                    <tr >
                                                                        <td>77.&nbsp;</td>
                                                                        <td align="left"> Goes about home town freely</td>
                                                                        <td align="left"> <html:checkbox styleId="77" property="vsms_9to10_Goesabouthome"  value="1" style="width:25px" onclick="checkSelectAllBasedonOnload()" /></td>
                                                                        <td>9</td>
                                                                        <td>12</td>
                                                                    </tr>
                                                                    <tr ><td colspan="5"><hr></td></tr>

                                                                    <tr >
                                                                        <th>&nbsp;</th>
                                                                        <th>10-11 YEARS</th>
                                                                                    <th>Select All <input type="checkbox" id="10YSelectAll" value="true" style="width:25px" onclick="enableOrDisable11to12yearsFields()"/></th>
                                                                                    <th>&nbsp;</th>
                                                                                    <th>&nbsp;</th>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td>78.&nbsp;</td>
                                                                                        <td align="left"> Distinguishes between friends and play matesnt</td>
                                                                                        <td align="left"> <html:checkbox styleId="78" property="vsms_10to11_Distinguishes"  value="1" style="width:25px" onclick="checkSelectAllBasedonOnload()" /></td>
                                                                                        <td>10</td>
                                                                                        <td>3</td>
                                                                                    </tr>
                                                                                    <tr >
                                                                                        <td>79.&nbsp;</td>
                                                                                        <td align="left"> Makes independent choice of shops</td>
                                                                                        <td align="left"> <html:checkbox styleId="79" property="vsms_10to11_Makesindependentchoice"  value="1" style="width:25px" onclick="checkSelectAllBasedonOnload()" /></td>
                                                                                        <td>10</td>
                                                                                        <td>6</td>
                                                                                    </tr>

                                                                                    <tr>
                                                                                        <td>80.&nbsp;</td>
                                                                                        <td align="left"> Does small remunerative work:makes articles</td>
                                                                                        <td align="left"> <html:checkbox styleId="80" property="vsms_10to11_smallremunerativework"  value="1" style="width:25px" onclick="checkSelectAllBasedonOnload()" /></td>
                                                                                        <td>10</td>
                                                                                        <td>9</td>
                                                                                    </tr>

                                                                                    <tr >
                                                                                        <td>81.&nbsp;</td>
                                                                                        <td align="left"> Follows local current events</td>
                                                                                        <td align="left"> <html:checkbox styleId="81" property="vsms_10to11_Follows"  value="1" style="width:25px" onclick="checkSelectAllBasedonOnload()" /></td>

                                                                                        <td>10</td>
                                                                                        <td>12</td>
                                                                                    </tr>




                                                                                    </table>
                                                                                    </td>
                                                                                    <td>
                                                                                        <table  align="center" cellspacing="1" cellpadding="0" class="inputform" width="100%">
                                                                                            <tr >
                                                                                                <th>&nbsp;</th>
                                                                                                <th>&nbsp;</th>
                                                                                                <th>&nbsp;</th>
                                                                                                <th>Years</th>
                                                                                                <th>Months</th>
                                                                                            </tr>
                                                                                            <tr >
                                                                                                  <th>&nbsp;</th>
                                                                                                <th>11-12 YEARS</th>
                                                                                                <th>Select All <input type="checkbox" id="11YSelectAll" value="true" style="width:25px" onclick="enableOrDisable12to15yearsFields()"/></th>
                                                                                                              <th>&nbsp;</th>
                                                                                                       <th>&nbsp;</th>
                                                                                                            </tr>
                                                                                                            <tr >
                                                                                                                <td>82.&nbsp;</td>
                                                                                                                <td align="left"> Does simple creative work</td>
                                                                                                                <td align="left"> <html:checkbox styleId="82" property="vsms_11to12_simplecreative"  value="1" style="width:25px" style="width:25px" onclick="checkSelectAllBasedonOnload()" /></td>
                                                                                                                <td>11</td>
                                                                                                                <td>4</td>
                                                                                                            </tr>
                                                                                                            <tr >
                                                                                                                <td>83.&nbsp;</td>
                                                                                                                <td align="left"> Is left to care for self or others</td>
                                                                                                                <td align="left"> <html:checkbox styleId="83" property="vsms_11to12_lefttocare"  value="1" style="width:25px" onclick="checkSelectAllBasedonOnload()" /></td>
                                                                                                                <td>11</td>
                                                                                                                <td>8</td>
                                                                                                            </tr>
                                                                                                            <tr >
                                                                                                                <td>84.&nbsp;</td>
                                                                                                                <td align="left"> Enjoys reading,books,newspapers,magazines</td>
                                                                                                                <td align="left"> <html:checkbox styleId="84" property="vsms_11to12_Enjoys"  value="1" style="width:25px" onclick="checkSelectAllBasedonOnload()" /></td>
                                                                                                                <td>11</td>
                                                                                                                <td>12</td>
                                                                                                            </tr>


                                                                                                            <tr><td colspan="5"><hr></td></tr>

                                                                                                            <tr>
                                                                                                                 <th>&nbsp;</th>
                                                                                                                <th><b>12-15 YEARS</b></th>
                                                                                                                <th>Select All <input type="checkbox" id="12YSelectAll" value="true" style="width:25px" onclick="enableOrDisable15yearsFields()"/></th>
                                                                                                                <th>&nbsp;</th>
                                                                                                               <th>&nbsp;</th>
                                                                                                            </tr>
                                                                                                            <tr>
                                                                                                                <td>85.&nbsp;</td>
                                                                                                                <td align="left"> Plays difficult games</td>
                                                                                                                <td align="left"> <html:checkbox styleId="85" property="vsms_12to15_Playsdifficult"  value="1" style="width:25px" onclick="checkSelectAllBasedonOnload()" /></td>

                                                                                                                <td>12</td>
                                                                                                                <td>7.2</td>
                                                                                                            </tr>

                                                                                                            <tr>
                                                                                                                <td>86.&nbsp;</td>
                                                                                                                <td align="left"> Exercises complete care of dress</td>
                                                                                                                <td align="left"> <html:checkbox styleId="86" property="vsms_12to15_Exercisescomplete"  value="1" style="width:25px" onclick="checkSelectAllBasedonOnload()" /></td>


                                                                                                                <td>12</td>
                                                                                                                <td>14.4</td>
                                                                                                            </tr>

                                                                                                            <tr>
                                                                                                                <td>87.&nbsp;</td>
                                                                                                                <td align="left"> Buys own clothing accessories</td>
                                                                                                                <td align="left"> <html:checkbox styleId="87" property="vsms_12to15_Buys"  value="1" style="width:25px" onclick="checkSelectAllBasedonOnload()" /></td>


                                                                                                                <td>12</td>
                                                                                                                <td>21.6</td>
                                                                                                            </tr>

                                                                                                            <tr>
                                                                                                                <td>88.&nbsp;</td>
                                                                                                                <td align="left"> Engages in adolescent group activities</td>
                                                                                                                <td align="left"> <html:checkbox styleId="88" property="vsms_12to15_Engages"  value="1" style="width:25px" onclick="checkSelectAllBasedonOnload()" /></td>
                                                                                                                <td>12</td>
                                                                                                                <td>28.8</td>
                                                                                                            </tr>
                                                                                                            <tr>
                                                                                                                <td>89.&nbsp;</td>
                                                                                                                <td align="left"> Performs responsible routine chores</td>
                                                                                                                <td align="left"> <html:checkbox styleId="89" property="vsms_12to15_Performs"  value="1" style="width:25px" onclick="checkSelectAllBasedonOnload()" /></td>

                                                                                                                <td>12</td>
                                                                                                                <td>36.0</td>
                                                                                                            </tr>



                                                                                                            </table>
                                                                                                            </td>
                                                                                                            </tr>
                                                                                                            </table>
                                                                                                            </div>
                                                                                                            </div><br>

                                                                                                            <table  align="center" cellspacing="1" cellpadding="0" class="inputform" width="100%">

                                                                                                                <tr >
                                                                                                                    <th colspan="12">
                                                                                                                        <html:button property="" value="Back" onclick="goBack()" styleClass="button"/>
                                                                                                                  
                                                                                                                        <html:submit value="Add" styleClass="button" styleId="addBut"/>
                                                                                                                  
                                                                                                                        <html:button property=""   value="Reset" onclick="goReset()"  styleClass="button"/></th>
                                                                                                                </tr>
                                                                                                            </table>

                                                                                                        </html:form>
                                                                                                        </body>

                                                                                                        </html>