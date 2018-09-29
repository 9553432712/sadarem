<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%
String personcode=(String)session.getAttribute("personcode");
String name=(String)session.getAttribute("Name");
%>

 <LINK REL="stylesheet" HREF="./DisabilityUITG/css/printeformatecss.css">
<html>
    <head>

       <script language="javascript" >
    function goBack()
{
            document.forms[0].action="showCalculationsAction.do";
            document.forms[0].submit();
}
</script>

    </head>
    <BODY ><br>
<form>
        <logic:present name="vsmsBean" scope="request">
    <html:form action="/vsmsscreeningtestaction.do?MRVsmsscreeningTestdetailsinsert=MRVsmsscreeningTestdetailsinsert" styleId="form" >

        <table  align="center" cellspacing="1" cellpadding="8" class="innerTable" width="85%">
            <tr><td colspan="3"  align="right" class="label">ID No:&nbsp;<font color="blue"><%=personcode%></font></td></tr>
            <tr><td colspan="3"  align="right" class="label">Name:&nbsp;<font color="blue"><%=name%></font></td></tr>

        <tr>
            <td align="center" class="heading">VINELAND SOCIAL MATURITY SCALE RECORD SHEET</td>
        </tr></table>


                <logic:equal name="vsmsBean" property="years0_3" value="true">
                <table  align="center" cellspacing="1" cellpadding="8" class="innerTable1" width="85%">


                    <logic:equal name="vsmsBean" property="years0_1_2_3" value="true">
                    <td>





                 <logic:notEqual name="vsmsBean" property="count0to1" value="0">

                 <table  border="1">
                     <tr>   <td class="labelBlue">0-1 YEAR</td>  </tr>
                    <logic:equal name="vsmsBean" property="vsms_0to1_CoolsLaughs" value="1">
                   <tr>

                        <td class="label"> &nbsp;&nbsp;&nbsp;&nbsp;Coos Laughs</td>



                   </tr> </logic:equal>
                     <logic:equal name="vsmsBean" property="vsms_0to1_Balenceshead" value="1">
                    <tr>

                        <td class="label"> &nbsp;&nbsp;&nbsp;&nbsp;Balances head</td>


                    </tr></logic:equal>
                    <logic:equal name="vsmsBean" property="vsms_0to1_Graphsobject" value="1">
                    <tr>

                        <td class="label"> &nbsp;&nbsp;&nbsp;&nbsp;Grasps objects within reach</td>


                    </tr></logic:equal>
                     <logic:equal name="vsmsBean" property="vsms_0to1_Reachesforfamiliarpersons" value="1">
                    <tr>

                        <td class="label"> &nbsp;&nbsp;&nbsp;&nbsp;Reaches for familiar persons</td>


                    </tr></logic:equal>
                    <logic:equal name="vsmsBean" property="vsms_0to1_Rolls" value="1">
                    <tr>

                        <td class="label"> &nbsp;&nbsp;&nbsp;&nbsp;Rolls over(unassisted)</td>


                    </tr></logic:equal>
                    <logic:equal name="vsmsBean" property="vsms_0to1_Reachesforobjects" value="1">
                    <tr>

                        <td class="label"> &nbsp;&nbsp;&nbsp;&nbsp;Reaches for neraby objects</td>


                    </tr></logic:equal>
                    <logic:equal name="vsmsBean" property="vsms_0to1_Occupies" value="1">
                    <tr>

                        <td class="label"> &nbsp;&nbsp;&nbsp;&nbsp;Occupies self unattened</td>


                    </tr></logic:equal>
                <logic:equal name="vsmsBean" property="vsms_0to1_Sits" value="1">
                    <tr >

                        <td class="label"> &nbsp;&nbsp;&nbsp;&nbsp;Sits unsupported</td>


                    </tr></logic:equal>
                    <logic:equal name="vsmsBean" property="vsms_0to1_Pulls" value="1">
                    <tr>

                        <td class="label"> &nbsp;&nbsp;&nbsp;&nbsp;Pulls self upright</td>


                    </tr></logic:equal>
                    <logic:equal name="vsmsBean" property="vsms_0to1_Talks" value="1">
                    <tr>

                        <td class="label"> &nbsp;&nbsp;&nbsp;&nbsp;Talks imitates sounds</td>


                    </tr></logic:equal>
                    <logic:equal name="vsmsBean" property="vsms_0to1_Drinks" value="1">
                    <tr>

                        <td class="label"> &nbsp;&nbsp;&nbsp;&nbsp;Drinks from cup or glass assisted</td>


                    </tr></logic:equal>
                    <logic:equal name="vsmsBean" property="vsms_0to1_Moves" value="1">
                    <tr>

                        <td class="label"> &nbsp;&nbsp;&nbsp;&nbsp;Moves about on floor(Creeping,crawling)</td>


                    </tr></logic:equal>
                    <logic:equal name="vsmsBean" property="vsms_0to1_Grasps" value="1">

                    <tr>

                        <td class="label"> &nbsp;&nbsp;&nbsp;&nbsp;Grasps with thumb and finger</td>


                    </tr></logic:equal>
                    <logic:equal name="vsmsBean" property="vsms_0to1_Demands" value="1">
                    <tr>

                        <td class="label"> &nbsp;&nbsp;&nbsp;&nbsp;Demands personal attention</td>


                    </tr></logic:equal>
                    <logic:equal name="vsmsBean" property="vsms_0to1_Stands" value="1">
                    <tr>

                        <td class="label"> &nbsp;&nbsp;&nbsp;&nbsp;Stands alone</td>


                    </tr></logic:equal>
                    <logic:equal name="vsmsBean" property="vsms_0to1_Doesnotdrool" value="1">
                    <tr>

                        <td class="label"> &nbsp;&nbsp;&nbsp;&nbsp;Does not drool</td>


                    </tr></logic:equal>
                    <logic:equal name="vsmsBean" property="vsms_0to1_Follows" value="1">
                    <tr>

                        <td class="label"> &nbsp;&nbsp;&nbsp;&nbsp;Follows simple instructions</td>



                    </tr>
                    </logic:equal>

                </table><%-- end of first block --%>
                     </logic:notEqual>


                 <logic:notEqual  name="vsmsBean" property="count2to3" scope="request" value="0">
                 <table border="1">


                    <tr>

                        <td class="labelBlue">2-3 YEARS</td>


                    </tr><logic:equal name="vsmsBean" property="vsms_2to3_Signals" value="1">
                    <tr>

                        <td class="label"> &nbsp;&nbsp;&nbsp;&nbsp;Signals to go to toilet</td>


                    </tr></logic:equal>
                    <logic:equal name="vsmsBean" property="vsms_2to3_Initiates" value="1">
                    <tr>

                        <td class="label"> &nbsp;&nbsp;&nbsp;&nbsp;Initiates own play activities</td>


                    </tr></logic:equal>
                    <logic:equal name="vsmsBean" property="vsms_2to3_Removesshirt" value="1">
                    <tr>

                        <td class="label"> &nbsp;&nbsp;&nbsp;&nbsp;Removes shirt or frock if unbuttoned</td>



                    </tr></logic:equal>
                    <logic:equal name="vsmsBean" property="vsms_2to3_Eatswithspoon" value="1">
                    <tr>

                        <td class="label"> &nbsp;&nbsp;&nbsp;&nbsp;Eats with spoon/hands(food)</td>


                    </tr></logic:equal>
                    <logic:equal name="vsmsBean" property="vsms_2to3_Getsdrink" value="1">
                    <tr>

                        <td class="label"> &nbsp;&nbsp;&nbsp;&nbsp;Gets drink(water) unassisted</td>


                    </tr></logic:equal>
                    <logic:equal name="vsmsBean" property="vsms_2to3_Dries" value="1">
                    <tr>

                        <td class="label"> &nbsp;&nbsp;&nbsp;&nbsp;Dries own hands</td>


                    </tr></logic:equal>
                    <logic:equal name="vsmsBean" property="vsms_2to3_Avoids" value="1">
                    <tr>

                        <td class="label"> &nbsp;&nbsp;&nbsp;&nbsp;Avoids simple hazards</td>


                    </tr></logic:equal>
                    <logic:equal name="vsmsBean" property="vsms_2to3_Putsonshirt" value="1">
                    <tr>

                        <td class="label"> &nbsp;&nbsp;&nbsp;&nbsp;Puts on shirt or frock unassisted</td>


                    </tr></logic:equal>
                    <logic:equal name="vsmsBean" property="vsms_2to3_Candopaperfolding" value="1">
                    <tr>

                        <td class="label"> &nbsp;&nbsp;&nbsp;&nbsp;Can do paper folding</td>


                    </tr></logic:equal>
                    <logic:equal name="vsmsBean" property="vsms_2to3_Relates" value="1">
                    <tr>

                        <td class="label"> &nbsp;&nbsp;&nbsp;&nbsp;Relates experiences</td>


                    </tr></logic:equal>


                 </table>  </logic:notEqual> <%-- end of 2nd block --%>
                    </td>
                </logic:equal>
                     <logic:notEqual  name="vsmsBean" property="count1to2" value="0">
                    <td>

                        <table border="1" align="top">

                            <tr valign="top">

                                <td class="labelBlue">1-2 YEARS</td>


                            </tr>
                            <logic:equal name="vsmsBean" property="vsms_1to2_Walks" value="1">
                            <tr>

                                <td class="label"> &nbsp;&nbsp;&nbsp;&nbsp;Walks about room unattened</td>


                            </tr></logic:equal>
                            <logic:equal name="vsmsBean" property="vsms_1to2_Marks" value="1">
                            <tr>

                                <td class="label"> &nbsp;&nbsp;&nbsp;&nbsp;Marks with pencil or crayon or chalk </td>



                            </tr></logic:equal>
                            <logic:equal name="vsmsBean" property="vsms_1to2_Masticates" value="1">
                            <tr>

                                <td class="label"> &nbsp;&nbsp;&nbsp;&nbsp;Masticates (chews) solid or semi-solid food</td>


                            </tr></logic:equal>
                            <logic:equal name="vsmsBean" property="vsms_1to2_Pulls" value="1">
                            <tr>

                                <td class="label"> &nbsp;&nbsp;&nbsp;&nbsp;Pulls off clothes</td>


                            </tr></logic:equal>
                            <logic:equal name="vsmsBean" property="vsms_1to2_Transfers" value="1">
                            <tr>

                                <td class="label"> &nbsp;&nbsp;&nbsp;&nbsp;Transfers objects</td>



                            </tr></logic:equal>
                            <logic:equal name="vsmsBean" property="vsms_1to2_Overcomessimpleobstacles" value="1">
                            <tr>

                                <td class="label"> &nbsp;&nbsp;&nbsp;&nbsp;Overcomes simple obstacles</td>


                            </tr></logic:equal>
                            <logic:equal name="vsmsBean" property="vsms_1to2_Fetches" value="1">
                            <tr>

                                <td class="label"> &nbsp;&nbsp;&nbsp;&nbsp;Fetches or carries familiar objects</td>


                            </tr></logic:equal>
                            <logic:equal name="vsmsBean" property="vsms_1to2_Drinks" value="1">
                            <tr>

                                <td class="label"> &nbsp;&nbsp;&nbsp;&nbsp; Drinks from cup or glass unassisted</td>


                            </tr></logic:equal>
                            <logic:equal name="vsmsBean" property="vsms_1to2_Walkswithoutsupport" value="1">
                            <tr>

                                <td class="label"> &nbsp;&nbsp;&nbsp;&nbsp;Walks without support</td>


                            </tr></logic:equal>
                            <logic:equal name="vsmsBean" property="vsms_1to2_Plays" value="1">
                            <tr>

                                <td class="label"> &nbsp;&nbsp;&nbsp;&nbsp; Plays with other children</td>



                            </tr></logic:equal>
                           <logic:equal name="vsmsBean" property="vsms_1to2_Eats" value="1">
                            <tr>

                                <td class="label"> &nbsp;&nbsp;&nbsp;&nbsp;Eats with own hands(biscuits,bread etc)</td>


                            </tr></logic:equal>
                            <logic:equal name="vsmsBean" property="vsms_1to2_Goes" value="1">
                            <tr>

                                <td class="label"> &nbsp;&nbsp;&nbsp;&nbsp;Goes about house or yard</td>



                            </tr></logic:equal>
                            <logic:equal name="vsmsBean" property="vsms_1to2_Discriminates" value="1">
                            <tr>

                                <td class="label"> &nbsp;&nbsp;&nbsp;&nbsp;Discriminates edible substances from non-edibles</td>




                            </tr></logic:equal>
                            <logic:equal name="vsmsBean" property="vsms_1to2_Usesnames" value="1">
                            <tr>

                                <td class="label"> &nbsp;&nbsp;&nbsp;&nbsp;Uses names of familiar objects</td>


                            </tr></logic:equal>
                            <logic:equal name="vsmsBean" property="vsms_1to2_Walksupstairs" value="1">
                            <tr>

                                <td class="label"> &nbsp;&nbsp;&nbsp;&nbsp;Walks upstairs unassisted</td>


                            </tr></logic:equal>
                            <logic:equal name="vsmsBean" property="vsms_1to2_Unwarps" value="1">
                            <tr>

                                <td class="label"> &nbsp;&nbsp;&nbsp;&nbsp;Unwraps sweets,chocolates</td>


                            </tr></logic:equal>
                            <logic:equal name="vsmsBean" property="vsms_1to2_Talks" value="1">
                            <tr>

                                <td class="label"> &nbsp;&nbsp;&nbsp;&nbsp;Talks in short sentences</td>


                            </tr></logic:equal>



                        </table></td> </logic:notEqual>

                </tr>
                </table>  <%-- end of first tab   --%>
                </logic:equal>

           <logic:equal name="vsmsBean" property="years4_6" value="true">
                <table  align="center" cellspacing="1" cellpadding="8" class="innerTable1" width="85%">

                    <tr>
                    <logic:equal name="vsmsBean" property="years3_4_5" value="true">
                    <td>


                     <logic:notEqual  name="vsmsBean" property="count3to4" value="0">
                    <TABLE  border="1">


                    <tr>

                        <td class="labelBlue">3-4 YEARS</td>


                    </tr>
                    <logic:equal name="vsmsBean" property="vsms_3to4_Walksdownsstairs" value="1">
                    <tr>

                        <td class="label"> &nbsp;&nbsp;&nbsp;Walks downsstairs one step at a time</td>


                    </tr></logic:equal>
                    <logic:equal name="vsmsBean" property="vsms_3to4_Playscooperatively" value="1">
                    <tr>

                        <td class="label"> &nbsp;&nbsp;&nbsp;&nbsp;Plays cooperatively at kindergarten level</td>


                    </tr></logic:equal>
                    <logic:equal name="vsmsBean" property="vsms_3to4_Buttons" value="1">
                    <tr>

                        <td class="label"> &nbsp;&nbsp;&nbsp;&nbsp;Buttons shirt or frock</td>


                    </tr></logic:equal>
                    <logic:equal name="vsmsBean" property="vsms_3to4_Helps" value="1">
                    <tr>

                        <td class="label"> &nbsp;&nbsp;&nbsp;&nbsp;Helps at little household tasks</td>


                    </tr></logic:equal>
                    <logic:equal name="vsmsBean" property="vsms_3to4_Performs" value="1">
                    <tr>

                        <td class="label"> &nbsp;&nbsp;&nbsp;&nbsp;Performs for others</td>


                    </tr></logic:equal>
                    <logic:equal name="vsmsBean" property="vsms_3to4_Washes" value="1">
                    <tr>

                        <td class="label"> &nbsp;&nbsp;&nbsp;&nbsp;Washes hands unaided</td>



                    </tr></logic:equal>

                    <tr>
                      <td>  <hr>   </td>
                    </tr>
                </table>
                     </logic:notEqual><%-- end of 4th block --%>



 <logic:notEqual  name="vsmsBean" property="count4to5" value="0">
                     <table border="1">



                        <tr>
                        <td class="labelBlue">4-5 YEARS</td>
                        </tr>


                    <logic:equal name="vsmsBean" property="vsms_4to5_Cares" value="1">
                    <tr>

                        <td class="label"> &nbsp;&nbsp;&nbsp;&nbsp;Cares for self at toilet</td>


                    </tr></logic:equal>
                    <logic:equal name="vsmsBean" property="vsms_4to5_Prints" value="1">

                    <tr>

                        <td class="label"> &nbsp;&nbsp;&nbsp;&nbsp;Washes face unassisted</td>

                    </tr></logic:equal>
                    <logic:equal name="vsmsBean" property="vsms_4to5_Goesaboutneighbourhood" value="1">

                    <tr>

                        <td class="label"> &nbsp;&nbsp;&nbsp;&nbsp;Goes about neighbourhood unattened</td>


                    </tr></logic:equal>
                  <logic:equal name="vsmsBean" property="vsms_4to5_Dresses" value="1">
                    <tr>

                        <td class="label"> &nbsp;&nbsp;&nbsp;&nbsp;Dresses self except for tying</td>


                    </tr></logic:equal>
                    <logic:equal name="vsmsBean" property="vsms_4to5_Usespencil" value="1">
                    <tr>

                        <td class="label"> &nbsp;&nbsp;&nbsp;&nbsp;Uses pencil or crayon or chalk for drawing</td>


                    </tr></logic:equal>
                    <logic:equal name="vsmsBean" property="vsms_4to5_Playscompetitive" value="1">
                    <tr>

                        <td class="label"> &nbsp;&nbsp;&nbsp;&nbsp;Plays competitive exercise games</td>


                    </tr></logic:equal>



                </table></logic:notEqual>
                    </td>
           </logic:equal>


 <logic:notEqual  name="vsmsBean" property="count5to6" value="0">
                           <td>

                        <table border="1">


                            <tr>

                                <td class="labelBlue">5-6 YEARS</td>

                            </tr>
                            <logic:equal name="vsmsBean" property="vsms_5to6_Useshoops" value="1">
                            <tr>

                                <td class="label"> &nbsp;&nbsp;&nbsp;&nbsp;Uses hoops,files,kites or uses knife</td>

                            </tr></logic:equal>
                            <logic:equal name="vsmsBean" property="vsms_5to6_Printssimplewords" value="1">
                            <tr>

                                <td class="label"> &nbsp;&nbsp;&nbsp;&nbsp;Prints(writes) simple words</td>


                            </tr></logic:equal>
                            <logic:equal name="vsmsBean" property="vsms_5to6_Playssimplegames" value="1">
                            <tr>

                                <td class="label"> &nbsp;&nbsp;&nbsp;&nbsp;Plays simple games which require talking turns</td>


                            </tr></logic:equal>
                            <logic:equal name="vsmsBean" property="vsms_5to6_trusted" value="1">
                            <tr>

                                <td class="label"> &nbsp;&nbsp;&nbsp;&nbsp;Is trusted with money</td>


                            </tr></logic:equal>
                            <logic:equal name="vsmsBean" property="vsms_5to6_Goestoschool" value="1">
                            <tr>

                                <td class="label"> &nbsp;&nbsp;&nbsp;&nbsp;Goes to school unattened</td>


                            </tr></logic:equal>


                        </table>

                    </td>
 </logic:notEqual>
                </tr>
                </table>
           </logic:equal>


       <logic:equal name="vsmsBean" property="years7_9" value="true">
                <table  align="center" cellspacing="1" cellpadding="8" class="innerTable" width="85%">
                <caption class="labelBlue">    (7-9)YEARS  </caption>
                    <tr>
                   <logic:equal name="vsmsBean" property="years6_7_8" value="true">
                        <td>
                         <logic:notEqual  name="vsmsBean" property="count6to7" value="0">
                            <table border="1" >

                                <tr>

                                    <td class="labelBlue">6-7 YEARS</td>


                                </tr>
                                <logic:equal name="vsmsBean" property="vsms_6to7_Mixes" value="1">
                                <tr>

                                    <td class="label"> &nbsp;&nbsp;Mixes rice properly unassisted</td>


                                </tr></logic:equal>
                                <logic:equal name="vsmsBean" property="vsms_6to7_Usespencilorchalk" value="1">
                                <tr>

                                    <td class="label"> &nbsp;&nbsp;Uses pencil or chalk for writing</td>


                                </tr></logic:equal>
                                <logic:equal name="vsmsBean" property="vsms_6to7_Batches" value="1">
                                <tr>

                                    <td class="label"> &nbsp;&nbsp;Bathes self assisted</td>


                                </tr></logic:equal>
                                <logic:equal name="vsmsBean" property="vsms_6to7_Goestobed" value="1">
                                <tr>

                                    <td class="label"> &nbsp;&nbsp;Goes to bed unassisted</td>


                                </tr></logic:equal>
                                <tr><td><hr></td></tr>
                            </table>

                         </logic:notEqual>


                          <logic:notEqual  name="vsmsBean" property="count7to8" value="0">
                              <table border="1">


                                <tr>

                                    <td class="labelBlue">7-8 YEARS</td>


                                </tr>
                                <logic:equal name="vsmsBean" property="vsms_7to8_Candifferentiatebetween" value="1">
                                <tr>

                                    <td class="label"> &nbsp;&nbsp;Can differentiate between AM & PM</td>


                                </tr></logic:equal>
                                <logic:equal name="vsmsBean" property="vsms_7to8_Helps" value="1">
                                <tr>

                                    <td class="label"> &nbsp;&nbsp;Helps himself during meals</td>


                                </tr></logic:equal>
                                <logic:equal name="vsmsBean" property="vsms_7to8_Understands" value="1">
                                <tr>

                                    <td class="label"> &nbsp;&nbsp;Understands and keeps family secrets</td>

                                </tr></logic:equal>
                               <logic:equal name="vsmsBean" property="vsms_7to8_Participates" value="1">
                                <tr>

                                    <td class="label"> &nbsp;&nbsp;Participates in pre-adolescent play</td>

                                </tr></logic:equal>
                                <logic:equal name="vsmsBean" property="vsms_7to8_Combs" value="1">
                                <tr>

                                    <td class="label"> &nbsp;&nbsp;Combs or brushes hair</td>


                                </tr></logic:equal>


                              </table></logic:notEqual>
                        </td>
                   </logic:equal>


                     <logic:notEqual  name="vsmsBean" property="count8to9" value="0">


                        <td>
                            <table border="1">


                                <tr>

                                    <td class="labelBlue">8-9 YEARS</td>

                                </tr>
                                <logic:equal name="vsmsBean" property="vsms_8to9_Usestools" value="1">
                                <tr>

                                    <td class="label"> &nbsp;&nbsp; Uses tools or utensils</td>

                                </tr></logic:equal>
                                <logic:equal name="vsmsBean" property="vsms_8to9_routinehouseholdtasks" value="1">
                                <tr>

                                    <td class="label"> &nbsp;&nbsp; Does routine household tasks</td>

                                </tr></logic:equal>
                                <logic:equal name="vsmsBean" property="vsms_8to9_Readsonowninitiative" value="1">
                                <tr>

                                    <td class="label">&nbsp;&nbsp;Reads on own initiative</td>

                                </tr></logic:equal>
                                <logic:equal name="vsmsBean" property="vsms_8to9_Batchesselfunaided" value="1">
                                <tr>

                                    <td class="label">&nbsp;&nbsp;Bathes self unaided</td>

                                </tr></logic:equal>



                            </table>
                        </td>
                     </logic:notEqual>

       </logic:equal>

        <logic:equal name="vsmsBean" property="years10_15" value="true">
               <table  align="center" cellspacing="1" cellpadding="8" class="innerTable" width="85%">
                <caption class="labelBlue">    (10-15)YEARS    </caption>
                    <tr>
                 <logic:equal name="vsmsBean" property="years9_10_11" value="true">
                        <td><logic:notEqual name="vsmsBean" property="count9to10" value="0">
                            <table border="1">


                                <tr>

                                    <td class="labelBlue">9-10 YEARS</td>

                                </tr>
                                <logic:equal name="vsmsBean" property="vsms_9to10_Caresforself" value="1">
                                <tr>

                                    <td class="label">&nbsp;&nbsp;Cares for self at meals</td>

                                </tr></logic:equal>
                                <logic:equal name="vsmsBean" property="vsms_9to10_Makesminorpurchases" value="1">
                                <tr >

                                    <td class="label">&nbsp;&nbsp;Makes minor purchases</td>

                                </tr></logic:equal>
                                <logic:equal name="vsmsBean" property="vsms_9to10_Goesabouthome" value="1">
                                <tr >

                                    <td class="label">&nbsp;&nbsp;Goes about home town freely</td>

                                </tr></logic:equal>


                            </table>
                        </logic:notEqual>
                       <logic:notEqual name="vsmsBean" property="count10to11" value="0">
                             <table border="1">

                                <tr>

                                    <td class="labelblue">10-11 YEARS</td>

                                </tr>
                                <logic:equal name="vsmsBean" property="vsms_10to11_Distinguishes" value="1">
                                <tr>

                                    <td class="label">&nbsp;&nbsp;Distinguishes between friends and play mates</td>


                                </tr></logic:equal>
                                <logic:equal name="vsmsBean" property="vsms_10to11_Makesindependentchoice" value="1">
                                <tr>

                                    <td class="label">&nbsp;&nbsp;Makes independent choice of shops</td>


                                </tr></logic:equal>
                                <logic:equal name="vsmsBean" property="vsms_10to11_smallremunerativework" value="1">
                                <tr>

                                    <td class="label">&nbsp;&nbsp;Does small remunerative work:makes articles</td>



                                </tr></logic:equal>
                               <logic:equal name="vsmsBean" property="vsms_10to11_Follows" value="1">
                                <tr>

                                    <td class="label">&nbsp;&nbsp;Follows local current events</td>

                                </tr></logic:equal>




                            </table>
                       </logic:notEqual>
                        </td>
                        </logic:equal>
                         <logic:equal name="vsmsBean" property="years11_12_15" value="true">
                        <td>
                        <logic:notEqual name="vsmsBean" property="count11to12" value="0">
                            <table border="1">

                                <tr>

                                    <td class="labelBlue">11-12 YEARS</td>

                                </tr>

                                <logic:equal name="vsmsBean" property="vsms_11to12_simplecreative" value="1">
                                <tr>

                                    <td class="label">&nbsp;&nbsp;Does simple creative work</td>

                                </tr></logic:equal>
                                <logic:equal name="vsmsBean" property="vsms_11to12_lefttocare" value="1">
                                <tr>

                                    <td class="label">&nbsp;&nbsp;Is left to care for self or others</td>


                                </tr></logic:equal>
                                <logic:equal name="vsmsBean" property="vsms_11to12_Enjoys" value="1">
                                <tr>

                                    <td class="label">&nbsp;&nbsp;Enjoys reading, books, newspapers, magazines</td>

                                </tr></logic:equal>
                            <tr><td><hr></td></tr>
                            </table>
                             </logic:notEqual>
                        <logic:notEqual name="vsmsBean" property="count12to15" value="0">
                            <table border="1">


                                <tr>

                                    <td class="labelBlue">12-15 YEARS</td>

                                </tr>
                                <logic:equal name="vsmsBean" property="vsms_12to15_Playsdifficult" value="1">
                                <tr>

                                    <td class="label">&nbsp;&nbsp;Plays difficult games</td>


                                </tr></logic:equal>
                               <logic:equal name="vsmsBean" property="vsms_12to15_Exercisescomplete" value="1">
                                <tr>

                                    <td class="label">&nbsp;&nbsp;Exercises complete care of dress</td>


                                </tr></logic:equal>
                                <logic:equal name="vsmsBean" property="vsms_12to15_Buys" value="1">
                                <tr>

                                    <td class="label">&nbsp;&nbsp;Buys own clothing accessories</td>


                                </tr></logic:equal>
                               <logic:equal name="vsmsBean" property="vsms_12to15_Engages" value="1">
                                <tr>

                                    <td class="label">&nbsp;&nbsp;Engages in adolescent group activities</td>

                                </tr></logic:equal>
                                <logic:equal name="vsmsBean" property="vsms_12to15_Performs" value="1">
                                <tr>

                                    <td class="label">&nbsp;&nbsp;Performs responsible routine chores</td>


                                </tr></logic:equal>



                            </table>
                        </logic:notEqual>
                        </td>
                        </logic:equal>
                    </tr>
                </table>
        </logic:equal>

        <table  align="center" cellspacing="1" cellpadding="8" border="0" class="innerTable1" width="85%">
            <logic:notEqual name="vsmsBean" property="totalcount" value="0">
                                <tr>

                                    <td class="labelBlue"> &nbsp;&nbsp; Number Of Fields Selected</td>


                                    <td><bean:write name="vsmsBean" property="totalcount"/></td>
                                </tr></logic:notEqual>
                                <tr><td class="labelBlue">&nbsp;&nbsp; Age Of Person Is
                                <td><bean:write name="vsmsBean" property="year"/>&nbsp;Years&nbsp;&nbsp;:&nbsp;&nbsp;<bean:write name="vsmsBean" property="month"/>&nbsp;Months

        </table>


       <table  align="center" cellspacing="1" cellpadding="8" border="1" class="innerTable1" width="85%">
           <tr><td colspan="2" class="labelBlue">Calculation of mental age</td></tr>

            <tr><td class="label">The total no. of months   </td><td><bean:write name="vsmsBean" property="month" scope="request"/></td></tr>
            <tr><td class="label">The total no. of Years   </td><td><bean:write name="vsmsBean" property="year" scope="request"/></td></tr>
            <tr><td class="label">The mental age is   </td><td><bean:write name="vsmsBean" property="mentalage" scope="request"/></td></tr>
            <tr><td colspan="2" class="labelBlue">Calculation of  chronological age</td></tr>
            <tr><td class="label">Date of birth  </td><td><bean:write name="vsmsBean" property="dateofbirth" scope="request"/></td></tr>
            <tr><td class="label">Todays date </td><td><bean:write name="vsmsBean" property="todaysdate" scope="request"/></td></tr>
            <tr><td class="label">Chronological Age (todays date-date of birth)  </td><td><bean:write name="vsmsBean" property="chronologicalage" scope="request"/></td></tr>
            <tr><td class="label">IQ </td><td class="label"><pre><bean:write name="vsmsBean" property="iq" scope="request"/></pre></td></tr>

         </table>

       </html:form>
        </logic:present>



     <table align="center">
            <tr>
                <html:button property="" value="Back" onclick="goBack()"  styleClass="button" />  </tr>
           <tr></tr><tr></tr>
          <tr><a href="showCalc.do?typeofDisabilityFlag=12&flagPrint=print" target="_blank">
                                Print<img src="DisabilityUITG/images/print.gif"  height="25" width="35"/></a></tr>

</table>

</form>

    </body>

</html>