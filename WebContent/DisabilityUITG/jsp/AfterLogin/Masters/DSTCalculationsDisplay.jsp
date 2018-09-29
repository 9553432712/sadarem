<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%
            String personcode = (String) session.getAttribute("personcode");
            String name = (String) session.getAttribute("Name");
%>
<LINK REL="stylesheet" HREF="./DisabilityUITG/css/printeformatecss.css">

<html:html>

    <head>
        <script language="javascript" >
            function goBack()
            {

                document.forms[0].action="showCalculationsAction.do";
                document.forms[0].submit();
            }
        </script>
    </head>

    <body>

        <logic:present name="dstBean" scope="request">
            <html:form action="MRDevelopmentalScreeningTestAction.do?parameter=insertMRDevelopmentalScreeningTest" method="post">
                <table  align="center" cellspacing="1" cellpadding="8" class="innerTable" width="85%">
                    <tr><td class="labelBlue" align="right">ID No.&nbsp;<%=personcode%></td></tr>
                    <tr><td class="labelBlue" align="right">Name.&nbsp;<%=name%></td></tr>
                    <tr><td class="heading" align="center">Developmental Screening Test</td></tr>
                </table>

                <logic:notEqual name="dstBean" property="threemonthstooneandhalfyear" scope="request" value="true">
                    <table  align="center" cellspacing="1" cellpadding="8" class="innerTable1" width="85%">

                        <tr>
                            <logic:equal name="dstBean" property="threetosixmonths" scope="request" value="true">
                                <td>

                                    <table  align="center" cellspacing="1" cellpadding="8" class="innerTable" width="85%">
                                        <logic:notEqual name="dstBean" property="threemonths" scope="request" value="0">
                                            <tr><td colspan="2"><center>Three Months</center></td></tr>


                                            <logic:equal name="dstBean" property="dst_birth_cry_present" scope="request" value="1">
                                                <tr><td>&nbsp;</td>
                                                    <td><li>Birth cry present </td>
                                                    </tr>
                                                </logic:equal>

                                                <logic:equal name="dstBean" property="dst_equal_bilateral_movements" scope="request" value="1">
                                                <tr><td>&nbsp;</td>
                                                    <td><li>Equal bilateral movements </td>
                                                    </tr>
                                                </logic:equal>

                                                <logic:equal name="dstBean" property="dst_responds_to_bell" scope="request" value="1">
                                                <tr><td>&nbsp;</td>
                                                    <td><li>Responds to bell </td>
                                                    </tr>
                                                </logic:equal>

                                                <logic:equal name="dstBean" property="dst_vocalises_sounds" scope="request" value="1">
                                                <tr><td>&nbsp;</td>
                                                    <td><li>Vocalises sounds </td>
                                                    </tr>
                                                </logic:equal>

                                                <logic:equal name="dstBean" property="dst_smiles_spontaneously" scope="request" value="1">
                                                <tr><td>&nbsp;</td>
                                                    <td><li>Smiles spontaneously </td>
                                                    </tr>
                                                </logic:equal>

                                                <logic:equal name="dstBean" property="dst_eyes_following_moving_object" scope="request" value="1">
                                                <tr><td>&nbsp;</td>
                                                    <td><li>Eyes follow moving object </td>
                                                    </tr>
                                                </logic:equal>

                                                <logic:equal name="dstBean" property="dst_head_steady" scope="request" value="1">
                                                <tr><td>&nbsp;</td>
                                                    <td><li>Head steady </td>
                                                    </tr>
                                                </logic:equal>

                                            <tr >

                                                <td valign="top"><br>&nbsp;Total</td>
                                                <td ><br>&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="dstBean" property="threemonths"/> days<br>&nbsp;</td>
                                            </tr>

                                            <tr >
                                                <td >Ref:</td>
                                                <td colspan="2">
                                                    <table border=1 style="border-collapse: collapse" >
                                                        <tr>
                                                            <td ><pre ><p>1->13</p></pre></td>
                                                            <td><pre>2->26</pre></td>
                                                            <td ><pre>3->39</pre></td>
                                                            <td><pre>4->52</pre></td>
                                                        </tr>
                                                        <tr>

                                                            <td><pre>5->65</pre></td>
                                                            <td><pre>6->78</pre></td>
                                                            <td colspan="2"><pre>7->90</pre></td>
                                                        </tr>
                                                    </table>
                                            </tr>


                                            <tr ><td colspan="5"><br></td></tr>
                                                </logic:notEqual>


                                        <logic:notEqual name="dstBean" property="sixmonths" scope="request" value="0">
                                            <logic:equal name="dstBean" property="dst_reaches_for_objects" scope="request" value="1">
                                                <tr><td colspan="2"><center>Six Months</center></td></tr>
                                                <tr >
                                                    <td>&nbsp;</td>
                                                    <td><li>Reaches for objects </td>
                                                    </tr>
                                                </logic:equal>

                                                <logic:equal name="dstBean" property="dst_laughs_loud" scope="request" value="1">
                                                <tr >
                                                    <td>&nbsp;</td>
                                                    <td><li>Laughs aloud </td>
                                                    </tr>
                                                </logic:equal>
                                                <logic:equal name="dstBean" property="dst_recognises_mother" scope="request" value="1">
                                                <tr >
                                                    <td>&nbsp;</td>
                                                    <td><li>Recognises mother </td>
                                                    </tr>
                                                </logic:equal>
                                                <logic:equal name="dstBean" property="dst_vocalises_for_pleasure_or_babble" scope="request" value="1">
                                                <tr >
                                                    <td>&nbsp;</td>
                                                    <td><li>Vocalises for pleasure/babble </td>
                                                    </tr>
                                                </logic:equal>
                                                <logic:equal name="dstBean" property="dst_carries_objects_to_mouth" scope="request" value="1">
                                                <tr >
                                                    <td>&nbsp;</td>
                                                    <td><li>Carries objects to mouth </td>
                                                    </tr>
                                                </logic:equal>
                                                <logic:equal name="dstBean" property="dst_rolls_over" scope="request" value="1">
                                                <tr >
                                                    <td>&nbsp;</td>
                                                    <td><li>Rolls over </td>
                                                    </tr>
                                                </logic:equal>

                                            <tr >

                                                <td valign="top"><br>&nbsp;Total</td>
                                                <td ><br>&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="dstBean" property="sixmonths"/> days<br>&nbsp;</td>
                                            </tr>

                                            <tr >
                                                <td >Ref:</td>
                                                <td colspan="2">
                                                    <table border=1 style="border-collapse: collapse" >
                                                        <tr>
                                                            <td ><pre>1->15</pre></td>
                                                            <td><pre>2->30</pre></td>
                                                            <td ><pre>3->45</pre></td>

                                                        </tr>
                                                        <tr>
                                                            <td><pre>4->60</pre></td>
                                                            <td><pre>5->75</pre></td>
                                                            <td><pre>6->90</pre></td>

                                                        </tr>
                                                    </table>
                                                </logic:notEqual>
                                        </tr>

                                    </table>

                                </td>
                            </logic:equal>
                            <logic:equal name="dstBean" property="ninemonthstooneandhalfyear" scope="request" value="true">
                                <td>

                                    <table  align="center" cellspacing="1" cellpadding="8" class="innerTable" width="85%">

                                        <logic:notEqual name="dstBean" property="ninemonths" scope="request" value="0">

                                            <logic:equal name="dstBean" property="dst_imitates_speech_sounds" scope="request" value="1">
                                                <tr><td colspan="2"><center>Nine Months</center></td></tr>
                                                <tr >
                                                    <td>&nbsp;</td>
                                                    <td><li>Imitates speech sounds </td>
                                                    </tr>
                                                </logic:equal>
                                                <logic:equal name="dstBean" property="dst_sits_by_self" scope="request" value="1">
                                                <tr >
                                                    <td>&nbsp;</td>
                                                    <td><li>Sits by self </td>
                                                    </tr>
                                                </logic:equal>
                                                <logic:equal name="dstBean" property="dst_thumb_finger_grasp" scope="request" value="1">
                                                <tr >
                                                    <td>&nbsp;</td>
                                                    <td><li>Thumb finger grasp </td>
                                                    </tr>
                                                </logic:equal>
                                                <logic:equal name="dstBean" property="dst_shows_curiousity" scope="request" value="1">
                                                <tr >
                                                    <td>&nbsp;</td>
                                                    <td><li>Shows curiousity </td>
                                                    </tr>
                                                </logic:equal>


                                            <tr >

                                                <td valign="top"><br>&nbsp;Total</td>
                                                <td ><br>&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="dstBean" property="ninemonths"/> days<br>&nbsp;</td>
                                            </tr>


                                            <tr >
                                                <td >Ref:</td>
                                                <td colspan="2">
                                                    <table border=1 style="border-collapse: collapse" >
                                                        <tr>
                                                            <td ><pre>1->23</pre></td>
                                                            <td><pre>2->46</pre></td>


                                                        </tr>
                                                        <tr>
                                                            <td ><pre>3->68</pre></td>
                                                            <td><pre>4->90</pre></td>


                                                        </tr>
                                                    </table></td>
                                            </tr>

                                            <tr ><td colspan="5"><br></td></tr>
                                                </logic:notEqual>

                                        <logic:notEqual name="dstBean" property="oneyear" scope="request" value="0">

                                            <logic:equal name="dstBean" property="dst_says_three_words" scope="request" value="1">
                                                <tr><td colspan="2"><center>One Year</center></td></tr>
                                                <tr >
                                                    <td>&nbsp;</td>
                                                    <td><li>Says 3 words 'dada', 'mama', etc </td>
                                                    </tr>
                                                </logic:equal>
                                                <logic:equal name="dstBean" property="dst_stands_alone_well" scope="request" value="1">
                                                <tr >
                                                    <td>&nbsp;</td>
                                                    <td><li>Stands alone well </td>
                                                    </tr>
                                                </logic:equal>
                                                <logic:equal name="dstBean" property="dst_follows_simple_instructions" scope="request" value="1">
                                                <tr >
                                                    <td>&nbsp;</td>
                                                    <td><li>Follows simple instructions </td>
                                                    </tr>
                                                </logic:equal>
                                                <logic:equal name="dstBean" property="dst_cooperates_for_dressing" scope="request" value="1">
                                                <tr >
                                                    <td>&nbsp;</td>
                                                    <td><li>Co-operates for dressing </td>
                                                    </tr>
                                                </logic:equal>


                                            <tr >

                                                <td valign="top"><br>&nbsp;Total</td>
                                                <td ><br>&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="dstBean" property="oneyear"/> days<br>&nbsp;</td>
                                            </tr>


                                            <tr >
                                                <td >Ref:</td>
                                                <td colspan="2">
                                                    <table border=1 style="border-collapse: collapse" >
                                                        <tr>
                                                            <td ><pre>1->23</pre></td>
                                                            <td><pre>2->46</pre></td>


                                                        </tr>
                                                        <tr>
                                                            <td ><pre>3->68</pre></td>
                                                            <td><pre>4->90</pre></td>


                                                        </tr>
                                                    </table>
                                            </tr>

                                            <tr ><td colspan="5"><br></td></tr>
                                                </logic:notEqual>

                                        <logic:notEqual name="dstBean" property="oneandhalfyear" scope="request" value="0">

                                            <logic:equal name="dstBean" property="dst_many_intelligible_words" scope="request" value="1">
                                                <tr><td colspan="2"><center>One and Half  Year</center></td></tr>
                                                <tr >
                                                    <td>&nbsp;</td>
                                                    <td><li>Many intelligible words </td>
                                                    </tr>
                                                </logic:equal>

                                                <logic:equal name="dstBean" property="dst_walks_runs_well" scope="request" value="1">
                                                <tr >
                                                    <td>&nbsp;</td>
                                                    <td><li>Walks, runs well </td>
                                                    </tr>
                                                </logic:equal>
                                                <logic:equal name="dstBean" property="dst_indicates_wants" scope="request" value="1">
                                                <tr >
                                                    <td>&nbsp;</td>
                                                    <td><li>Indicates wants </td>
                                                    </tr>
                                                </logic:equal>
                                                <logic:equal name="dstBean" property="dst_scribbles_spontaneously" scope="request" value="1">
                                                <tr >
                                                    <td>&nbsp;</td>
                                                    <td><li>Scribbles spontaneously </td>
                                                    </tr>
                                                </logic:equal>


                                            <tr >

                                                <td valign="top"><br>&nbsp;Total</td>
                                                <td ><br>&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="dstBean" property="oneandhalfyear"/> days<br>&nbsp;</td>
                                            </tr>

                                            <tr >
                                                <td >Ref:</td>
                                                <td colspan="2">
                                                    <table border=1 style="border-collapse: collapse" >
                                                        <tr>
                                                            <td ><pre>1->45</pre></td>
                                                            <td><pre>2->90</pre></td>


                                                        </tr>
                                                        <tr>
                                                            <td ><pre>3->135</pre></td>
                                                            <td><pre>4->180</pre></td>


                                                        </tr>
                                                    </table>
                                            </tr>
                                        </logic:notEqual>
                                    </table>

                                </td>
                            </logic:equal>
                        </tr>
                    </table>
                </logic:notEqual>


                <table  align="center" cellspacing="1" cellpadding="8" class="innerTable" width="85%">

                    <tr>
                        <logic:equal name="dstBean" property="twoyearstofouryears" scope="request" value="true">
                            <td>
                                <table  align="center" cellspacing="1" cellpadding="8" class="innerTable" width="85%">
                                    <logic:notEqual name="dstBean" property="twoyears" scope="request" value="0">

                                        <tr><td colspan="2"><center>Two Years</center></td></tr>

                                        <logic:equal name="dstBean" property="dst_says_sentences_of_twobythree_words" scope="request" value="1">
                                            <tr >
                                                <td>&nbsp;</td>
                                                <td><li>Says sentences of 2/3 words </td>
                                                </tr>
                                            </logic:equal>
                                            <logic:equal name="dstBean" property="dst_points_out_objects_in_pictures" scope="request" value="1">
                                            <tr >
                                                <td>&nbsp;</td>
                                                <td><li>Points out objects in pictures </td>
                                                </tr>
                                            </logic:equal>
                                            <logic:equal name="dstBean" property="dst_shows_body_parts" scope="request" value="1">
                                            <tr >
                                                <td>&nbsp;</td>
                                                <td><li>Shows body parts </td>
                                                </tr>
                                            </logic:equal>
                                            <logic:equal name="dstBean" property="dst_participates_in_play" scope="request" value="1">
                                            <tr >
                                                <td>&nbsp;</td>
                                                <td><li>Participates in play </td>
                                                </tr>
                                            </logic:equal>


                                        <tr >

                                            <td valign="top"><br>&nbsp;Total</td>
                                            <td ><br>&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="dstBean" property="twoyears"/> days<br>&nbsp;</td>
                                        </tr>


                                        <tr >
                                            <td >Ref:</td>
                                            <td colspan="2">
                                                <table border=1 style="border-collapse: collapse" >
                                                    <tr>
                                                        <td ><pre>1->45</pre></td>
                                                        <td><pre>2->90</pre></td>


                                                    </tr>
                                                    <tr>
                                                        <td ><pre>3->135</pre></td>
                                                        <td><pre>4->180</pre></td>


                                                    </tr>
                                                </table></td>
                                        </tr>

                                        <tr ><td colspan="5"><br></td></tr>
                                            </logic:notEqual>

                                    <logic:notEqual name="dstBean" property="threeyears" scope="request" value="0">

                                        <tr><td colspan="2"><center>Three Years</center></td></tr>
                                        <logic:equal name="dstBean" property="dst_copies_o" scope="request" value="1">
                                            <tr >
                                                <td>&nbsp;</td>
                                                <td><li>Copies O </td>
                                                </tr>
                                            </logic:equal>
                                            <logic:equal name="dstBean" property="dst_relates_experiences" scope="request" value="1">
                                            <tr >
                                                <td>&nbsp;</td>
                                                <td><li>Relates experiences </td>
                                                </tr>
                                            </logic:equal>
                                            <logic:equal name="dstBean" property="dst_knows_names_uses_of_common_objects" scope="request" value="1">
                                            <tr >
                                                <td>&nbsp;</td>
                                                <td><li>Knows names, uses of common objects </td>
                                                </tr>
                                            </logic:equal>

                                            <logic:equal name="dstBean" property="dst_begins_to_ask_why" scope="request" value="1">
                                            <tr >
                                                <td>&nbsp;</td>
                                                <td><li>Begins to ask 'Why?' </td>
                                                </tr>
                                            </logic:equal>

                                            <logic:equal name="dstBean" property="dst_takes_food_by_self" scope="request" value="1">
                                            <tr >
                                                <td>&nbsp;</td>
                                                <td><li>Takes food by self </td>
                                                </tr>
                                            </logic:equal>
                                            <logic:equal name="dstBean" property="dst_toilet_control_present" scope="request" value="1">
                                            <tr >
                                                <td>&nbsp;</td>
                                                <td><li>Toilet control present </td>
                                                </tr>
                                            </logic:equal>


                                        <tr >

                                            <td valign="top"><br>&nbsp;Total</td>
                                            <td ><br>&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="dstBean" property="threeyears"/> days<br>&nbsp;</td>
                                        </tr>


                                        <tr >
                                            <td >Ref:</td>
                                            <td colspan="2">
                                                <table border=1 style="border-collapse: collapse" >
                                                    <tr>
                                                        <td ><pre>1->60</pre></td>
                                                        <td><pre>2->120</pre></td>
                                                        <td ><pre>3->180</pre></td>

                                                    </tr>
                                                    <tr>

                                                        <td><pre>4->240</pre></td>
                                                        <td><pre>5->300</pre></td>
                                                        <td><pre>6->360</pre></td>
                                                    </tr>
                                                </table></td>
                                        </tr>

                                        <tr ><td colspan="5"><br></td></tr>
                                            </logic:notEqual>

                                    <logic:notEqual name="dstBean" property="fouryears" scope="request" value="0">

                                        <logic:equal name="dstBean" property="dst_buttons_up" scope="request" value="1">
                                            <tr><td colspan="2"><center>Four Years</center></td></tr>
                                            <tr >
                                                <td>&nbsp;</td>
                                                <td><li>Buttons up </td>
                                                </tr>
                                            </logic:equal>
                                            <logic:equal name="dstBean" property="dst_comprehends_hunger_cold" scope="request" value="1">
                                            <tr >
                                                <td>&nbsp;</td>
                                                <td><li>Comprehends 'hunger', 'cold' </td>
                                                </tr>
                                            </logic:equal>
                                            <logic:equal name="dstBean" property="dst_plays_cooperatively_with_children" scope="request" value="1">
                                            <tr >
                                                <td>&nbsp;</td>
                                                <td><li>Plays cooperatively with children </td>
                                                </tr>
                                            </logic:equal>
                                            <logic:equal name="dstBean" property="dst_repeats_three_digits" scope="request" value="1">
                                            <tr >
                                                <td>&nbsp;</td>
                                                <td><li>Repeats 3 digits </td>
                                                </tr>
                                            </logic:equal>
                                            <logic:equal name="dstBean" property="dst_tells_stories" scope="request" value="1">
                                            <tr >
                                                <td>&nbsp;</td>
                                                <td><li>Tells stories </td>
                                                </tr>
                                            </logic:equal>


                                        <tr >

                                            <td valign="top"><br>&nbsp;Total</td>
                                            <td ><br>&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="dstBean" property="fouryears"/> days<br>&nbsp;</td>
                                        </tr>


                                        <tr >
                                            <td >Ref:</td>
                                            <td colspan="2">
                                                <table border=1 style="border-collapse: collapse" >
                                                    <tr>
                                                        <td ><pre>1->72</pre></td>
                                                        <td><pre>2->144</pre></td>
                                                        <td ><pre>3->216</pre></td>

                                                    </tr>
                                                    <tr>

                                                        <td><pre>4->288</pre></td>
                                                        <td colspan="2"><pre>5->360</pre></td>

                                                    </tr>
                                                </table></td>
                                        </tr>
                                    </logic:notEqual>

                                </table></td>
                            </td>
                        </logic:equal>

                        <logic:equal name="dstBean" property="fiveyearstosixyears" scope="request" value="true">
                            <td>
                                <table  align="center" cellspacing="1" cellpadding="8" class="innerTable" width="85%">
                                    <logic:notEqual name="dstBean" property="fiveyears" scope="request" value="0">

                                        <tr><td colspan="2"><center>Five Years</center></td></tr>
                                        <logic:equal name="dstBean" property="dst_defines_words" scope="request" value="1">
                                            <tr >
                                                <td>&nbsp;</td>
                                                <td>Defines words </td>
                                            </tr>
                                        </logic:equal>
                                        <logic:equal name="dstBean" property="dst_makes_simple_drawing" scope="request" value="1">
                                            <tr >
                                                <td>&nbsp;</td>
                                                <td>Makes simple drawing </td>
                                            </tr>
                                        </logic:equal>
                                        <logic:equal name="dstBean" property="dst_dresses_with_no_supervision" scope="request" value="1">
                                            <tr >
                                                <td>&nbsp;</td>
                                                <td>Dresses with no supervision </td>
                                            </tr>
                                        </logic:equal>
                                        <logic:equal name="dstBean" property="dst_describes_actions_in_pictures" scope="request" value="1">
                                            <tr >
                                                <td>&nbsp;</td>
                                                <td>Describes actions in pictures </td>
                                            </tr>
                                        </logic:equal>
                                        <logic:equal name="dstBean" property="dst_gives_sensible_answers_to_questions" scope="request" value="1">
                                            <tr >
                                                <td>&nbsp;</td>
                                                <td>Gives sensible answers to questions </td>
                                            </tr>
                                        </logic:equal>
                                        <logic:equal name="dstBean" property="dst_goes_about_neighbourhood" scope="request" value="1">
                                            <tr >
                                                <td>&nbsp;</td>
                                                <td>Goes about neighbourhood </td>
                                            </tr>
                                        </logic:equal>


                                        <tr >

                                            <td valign="top"><br>&nbsp;Total</td>
                                            <td ><br>&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="dstBean" property="fiveyears"/> days<br>&nbsp;</td>
                                        </tr>


                                        <tr >
                                            <td >Ref:</td>
                                            <td colspan="2">
                                                <table border=1 style="border-collapse: collapse" >
                                                    <tr>
                                                        <td ><pre>1->60</pre></td>
                                                        <td><pre>2->120</pre></td>
                                                        <td ><pre>3->180</pre></td>

                                                    </tr>
                                                    <tr>

                                                        <td><pre>4->240</pre></td>
                                                        <td><pre>5->300</pre></td>
                                                        <td><pre>6->360</pre></td>
                                                    </tr>
                                                </table></td>
                                        </tr>

                                        <tr ><td colspan="5"><br></td></tr>
                                            </logic:notEqual>

                                    <logic:notEqual name="dstBean" property="sixyears" scope="request" value="0">

                                        <logic:equal name="dstBean" property="dst_can_name_primary_colours" scope="request" value="1">
                                            <tr><td colspan="2"><center>Six Years</center></td></tr>
                                            <tr >
                                                <td>&nbsp;</td>
                                                <td><li>Can name primary colours </td>
                                                </tr>
                                            </logic:equal>

                                            <logic:equal name="dstBean" property="dst_plays_games_governed_by_rules" scope="request" value="1">
                                            <tr >
                                                <td>&nbsp;</td>
                                                <td><li>Plays games governed by rules </td>
                                                </tr>
                                            </logic:equal>
                                            <logic:equal name="dstBean" property="dst_writes_simple_words" scope="request" value="1">
                                            <tr >
                                                <td>&nbsp;</td>
                                                <td><li>Writes simple words </td>
                                                </tr>
                                            </logic:equal>
                                            <logic:equal name="dstBean" property="dst_gains_admission_to_school" scope="request" value="1">
                                            <tr >
                                                <td>&nbsp;</td>
                                                <td><li>Gains admission to school </td>
                                                </tr>
                                            </logic:equal>
                                            <logic:equal name="dstBean" property="dst_enjoys_constructive_play" scope="request" value="1">
                                            <tr >
                                                <td>&nbsp;</td>
                                                <td><li>Enjoys constructive play </td>
                                                </tr>
                                            </logic:equal>


                                        <tr >

                                            <td valign="top"><br>&nbsp;Total</td>
                                            <td ><br>&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="dstBean" property="sixyears"/> days<br>&nbsp;</td>
                                        </tr>


                                        <tr >
                                            <td >Ref:</td>
                                            <td colspan="2">
                                                <table border=1 style="border-collapse: collapse" >
                                                    <tr>
                                                        <td ><pre>1->72</pre></td>
                                                        <td><pre>2->144</pre></td>
                                                        <td ><pre>3->216</pre></td>

                                                    </tr>
                                                    <tr>

                                                        <td><pre>4->288</pre></td>
                                                        <td colspan="2"><pre>5->360</pre></td>

                                                    </tr>
                                                </table></td>
                                        </tr>
                                    </logic:notEqual>
                                </table>

                            </td>
                        </logic:equal>
                    </tr>
                </table>


                <table  align="center" cellspacing="1" cellpadding="8" class="innerTable" width="85%">

                    <tr>
                        <logic:equal name="dstBean" property="seventonineyears" scope="request" value="true">
                            <td>
                                <table  align="center" cellspacing="1" cellpadding="8" class="innerTable" width="85%">
                                    <logic:notEqual name="dstBean" property="sevenyears" scope="request" value="0">

                                        <tr><td colspan="2"><center>Seven Years</center></td></tr>


                                        <logic:equal name="dstBean" property="dst_adapts_to_home_school" scope="request" value="1">
                                            <tr >
                                                <td>&nbsp;</td>
                                                <td><li>Adapts to home, school </td>
                                                </tr>
                                            </logic:equal>
                                            <logic:equal name="dstBean" property="dst_tells_differences_of_objects" scope="request" value="1">
                                            <tr >
                                                <td>&nbsp;</td>
                                                <td><li>Tells differences of objects </td>
                                                </tr>
                                            </logic:equal>
                                            <logic:equal name="dstBean" property="dst_spells_reads_writes_simple_words" scope="request" value="1">
                                            <tr >
                                                <td>&nbsp;</td>
                                                <td><li>Spells, reads, writes simple words </td>
                                                </tr>
                                            </logic:equal>
                                            <logic:equal name="dstBean" property="dst_enjoys_group_play" scope="request" value="1">
                                            <tr >
                                                <td>&nbsp;</td>
                                                <td><li>Enjoys group play </td>
                                                </tr>
                                            </logic:equal>
                                            <logic:equal name="dstBean" property="dst_knows_comparative_value_of_coins" scope="request" value="1">
                                            <tr >
                                                <td>&nbsp;</td>
                                                <td><li>Knows comparative value of coins </td>
                                                </tr>
                                            </logic:equal>


                                        <tr >

                                            <td valign="top"><br>&nbsp;Total</td>
                                            <td ><br>&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="dstBean" property="sevenyears"/> days<br>&nbsp;</td>
                                        </tr>


                                        <tr >
                                            <td >Ref:</td>
                                            <td colspan="2">
                                                <table border=1 style="border-collapse: collapse" >
                                                    <tr>
                                                        <td ><pre>1->72</pre></td>
                                                        <td><pre>2->144</pre></td>
                                                        <td ><pre>3->216</pre></td>

                                                    </tr>
                                                    <tr>

                                                        <td><pre>4->288</pre></td>
                                                        <td colspan="2"><pre>5->360</pre></td>

                                                    </tr>
                                                </table>
                                        </tr>

                                        <tr ><td colspan="5"><br></td></tr>

                                    </logic:notEqual>

                                    <logic:notEqual name="dstBean" property="eightyears" scope="request" value="0">

                                        <tr><td colspan="2"><center>Eight Years</center></td></tr>

                                        <logic:equal name="dstBean" property="dst_combs_hair_by_self" scope="request" value="1">
                                            <tr >
                                                <td>&nbsp;</td>
                                                <td><li>Combs hair by self </td>
                                                </tr>
                                            </logic:equal>
                                            <logic:equal name="dstBean" property="dst_makes_small_purchases" scope="request" value="1">
                                            <tr >
                                                <td>&nbsp;</td>
                                                <td><li>Makes small purchases </td>
                                                </tr>
                                            </logic:equal>
                                            <logic:equal name="dstBean" property="dst_competition_in_school_or_play" scope="request" value="1">
                                            <tr >
                                                <td>&nbsp;</td>
                                                <td><li>Competition in school/play </td>
                                                </tr>
                                            </logic:equal>
                                            <logic:equal name="dstBean" property="dst_tells_time" scope="request" value="1">
                                            <tr >
                                                <td>&nbsp;</td>
                                                <td><li>Tells time </td>
                                                </tr>
                                            </logic:equal>


                                        <tr >

                                            <td valign="top"><br>&nbsp;Total</td>
                                            <td ><br>&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="dstBean" property="eightyears"/> days<br>&nbsp;</td>
                                        </tr>


                                        <tr >
                                            <td >Ref:</td>
                                            <td colspan="2">
                                                <table border=1 style="border-collapse: collapse" >
                                                    <tr>
                                                        <td ><pre>1->90</pre></td>
                                                        <td><pre>2->180</pre></td>


                                                    </tr>
                                                    <tr>
                                                        <td ><pre>3->270</pre></td>
                                                        <td><pre>4->360</pre></td>


                                                    </tr>
                                                </table>
                                        </tr>

                                        <tr ><td colspan="5"><br></td></tr>

                                    </logic:notEqual>

                                    <logic:notEqual name="dstBean" property="nineyears" scope="request" value="0">

                                        <tr><td colspan="2"><center>Nine Years</center></td></tr>

                                        <logic:equal name="dstBean" property="dst_tells_day_month_year" scope="request" value="1">
                                            <tr >
                                                <td>&nbsp;</td>
                                                <td><li>Tells day, month, year </td>
                                                </tr>
                                            </logic:equal>

                                            <logic:equal name="dstBean" property="dst_reads_on_own_initiative" scope="request" value="1">
                                            <tr >
                                                <td>&nbsp;</td>
                                                <td><li>Reads on own initiative </td>
                                                </tr>
                                            </logic:equal>
                                            <logic:equal name="dstBean" property="dst_recognises_property_rights" scope="request" value="1">
                                            <tr >
                                                <td>&nbsp;</td>
                                                <td><li>Recoginises property rights </td>
                                                </tr>
                                            </logic:equal>
                                            <logic:equal name="dstBean" property="dst_favourite_of_fairy_tales" scope="request" value="1">
                                            <tr >
                                                <td>&nbsp;</td>
                                                <td><li>Favourite of fairy tales </td>
                                                </tr>
                                            </logic:equal>
                                            <logic:equal name="dstBean" property="dst_muscle_coordination_games" scope="request" value="1">
                                            <tr >
                                                <td>&nbsp;</td>
                                                <td><li>Muscle coordination games (marbles) </td>
                                                </tr>
                                            </logic:equal>
                                            <logic:equal name="dstBean" property="dst_bathes_self_unaided" scope="request" value="1">
                                            <tr >
                                                <td>&nbsp;</td>
                                                <td><li>Bathes self unaided </td>
                                                </tr>
                                            </logic:equal>


                                        <tr >

                                            <td valign="top"><br>&nbsp;Total</td>
                                            <td ><br>&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="dstBean" property="nineyears"/> days<br>&nbsp;</td>
                                        </tr>


                                        <tr >
                                            <td >Ref:</td>
                                            <td colspan="2">
                                                <table border=1 style="border-collapse: collapse" >
                                                    <tr>
                                                        <td ><pre>1->60</pre></td>
                                                        <td><pre>2->120</pre></td>
                                                        <td ><pre>3->180</pre></td>

                                                    </tr>
                                                    <tr>

                                                        <td><pre>4->240</pre></td>
                                                        <td><pre>5->300</pre></td>
                                                        <td><pre>6->360</pre></td>
                                                    </tr>
                                                </table>
                                        </tr>
                                    </logic:notEqual>
                                </table>
                            </td>
                        </logic:equal>
                        <logic:equal name="dstBean" property="tenyearstotwelveyears" value="true" scope="request">
                            <td>
                                <table  align="center" cellspacing="1" cellpadding="8" class="innerTable" width="85%">

                                    <logic:notEqual name="dstBean" property="tenyears" scope="request" value="0">

                                        <tr><td colspan="2"><center>Ten Years</center></td></tr>

                                        <logic:equal name="dstBean" property="dst_cooperates_keenly_with_companions" scope="request" value="1">

                                            <tr >
                                                <td>&nbsp;</td>
                                                <td><li>Cooperates keenly with companions </td>
                                                </tr>
                                            </logic:equal>
                                            <logic:equal name="dstBean" property="dst_has_various_hobbies_collections" scope="request" value="1">
                                            <tr >
                                                <td>&nbsp;</td>
                                                <td><li>Has various hobbies, collections </td>
                                                </tr>
                                            </logic:equal>
                                            <logic:equal name="dstBean" property="dst_goes_about_town_freely" scope="request" value="1">
                                            <tr >
                                                <td>&nbsp;</td>
                                                <td><li>goes about town freely </td>
                                                </tr>
                                            </logic:equal>
                                            <logic:equal name="dstBean" property="dst_sex_differences_in_play_become_marked" scope="request" value="1">
                                            <tr >
                                                <td>&nbsp;</td>
                                                <td><li>Sex differences in play become marked </td>
                                                </tr>
                                            </logic:equal>
                                            <logic:equal name="dstBean" property="dst_can_stay_away_from_home" scope="request" value="1">
                                            <tr >
                                                <td>&nbsp;</td>
                                                <td><li>Can stay away from home (camps) </td>
                                                </tr>
                                            </logic:equal>


                                        <tr >

                                            <td valign="top"><br>&nbsp;Total</td>
                                            <td ><br>&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="dstBean" property="tenyears"/> days<br>&nbsp;</td>
                                        </tr>


                                        <tr >
                                            <td >Ref:</td>
                                            <td colspan="2">
                                                <table border=1 style="border-collapse: collapse" >
                                                    <tr>
                                                        <td ><pre>1->72</pre></td>
                                                        <td><pre>2->144</pre></td>
                                                        <td ><pre>3->216</pre></td>

                                                    </tr>
                                                    <tr>

                                                        <td><pre>4->288</pre></td>
                                                        <td colspan="2"><pre>5->360</pre></td>

                                                    </tr>
                                                </table>
                                        </tr>

                                        <tr ><td colspan="5"><br></td></tr>

                                    </logic:notEqual>

                                    <logic:notEqual name="dstBean" property="elevenyears" scope="request" value="0">

                                        <tr><td colspan="2"><center>Eleven Years</center></td></tr>

                                        <logic:equal name="dstBean" property="dst_writes_occasional_short_letters" scope="request" value="1">
                                            <tr >
                                                <td>&nbsp;</td>
                                                <td><li>Writes occasional short letters </td>
                                                </tr>
                                            </logic:equal>
                                            <logic:equal name="dstBean" property="dst_comprehends_social_situations" scope="request" value="1">
                                            <tr >
                                                <td>&nbsp;</td>
                                                <td><li>Comprehends social situations </td>
                                                </tr>
                                            </logic:equal>
                                            <logic:equal name="dstBean" property="dst_physical_feets_liked" scope="request" value="1">
                                            <tr >
                                                <td>&nbsp;</td>
                                                <td><li>Physical feats liked </td>
                                                </tr>
                                            </logic:equal>
                                            <logic:equal name="dstBean" property="dst_able_to_discuss_problems" scope="request" value="1">
                                            <tr >
                                                <td>&nbsp;</td>
                                                <td><li>Able to discuss problems </td>
                                                </tr>
                                            </logic:equal>


                                        <tr >

                                            <td valign="top"><br>&nbsp;Total</td>
                                            <td ><br>&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="dstBean" property="elevenyears"/> days<br>&nbsp;</td>
                                        </tr>


                                        <tr >
                                            <td >Ref:</td>
                                            <td colspan="2">
                                                <table border=1 style="border-collapse: collapse" >
                                                    <tr>
                                                        <td ><pre>1->90</pre></td>
                                                        <td><pre>2->180</pre></td>


                                                    </tr>
                                                    <tr>
                                                        <td ><pre>3->270</pre></td>
                                                        <td><pre>4->360</pre></td>


                                                    </tr>
                                                </table>
                                        </tr>

                                        <tr ><td colspan="5"><br></td></tr>

                                    </logic:notEqual>

                                    <logic:notEqual name="dstBean" property="tweleveyears" scope="request" value="0">

                                        <tr><td colspan="2"><center>Twelve Years</center></td></tr>

                                        <logic:equal name="dstBean" property="dst_enjoys_books_newspapers_magazines" scope="request" value="1">
                                            <tr >
                                                <td>&nbsp;</td>
                                                <td><li>Enjoys books, newspapers, magazines </td>
                                                </tr>
                                            </logic:equal>
                                            <logic:equal name="dstBean" property="dst_more_independent_in_spending" scope="request" value="1">
                                            <tr >
                                                <td>&nbsp;</td>
                                                <td><li>More independent in spending </td>
                                                </tr>
                                            </logic:equal>
                                            <logic:equal name="dstBean" property="dst_capable_of_self_criticism" scope="request" value="1">
                                            <tr >
                                                <td>&nbsp;</td>
                                                <td><li>Capable of self criticism </td>
                                                </tr>
                                            </logic:equal>


                                        <tr >

                                            <td valign="top"><br>&nbsp;Total</td>
                                            <td ><br>&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="dstBean" property="tweleveyears"/> days<br>&nbsp;</td>
                                        </tr>


                                        <tr >
                                            <td >Ref:</td>
                                            <td colspan="2">
                                                <table border=1 style="border-collapse: collapse" >
                                                    <tr>
                                                        <td ><pre>1->120</pre></td>
                                                        <td><pre>2->240</pre></td>
                                                        <td ><pre>3->360</pre></td>

                                                    </tr>

                                                </table>
                                        </tr>
                                    </logic:notEqual>
                                </table>
                            </td>
                        </logic:equal>
                    </tr>
                </table>


                <table  align="center" cellspacing="1" cellpadding="8" class="innerTable" width="85%">

                    <tr>
                        <logic:notEqual name="dstBean" property="thirteenyears" scope="request" value="0">
                            <td>
                                <table  align="center" cellspacing="1" cellpadding="8" class="innerTable" width="85%">
                                    <tr><td colspan="2"><center>Thirteen Years</center></td></tr>

                                    <logic:equal name="dstBean" property="dst_shows_foresight_planning_judgement" scope="request" value="1">
                                        <tr >
                                            <td>&nbsp;</td>
                                            <td><li>Shows foresight, planning, judgement </td>
                                            </tr>
                                        </logic:equal>
                                        <logic:equal name="dstBean" property="dst_learns_from_experience" scope="request" value="1">
                                        <tr >
                                            <td>&nbsp;</td>
                                            <td><li>Learns from experience </td>
                                            </tr>
                                        </logic:equal>
                                        <logic:equal name="dstBean" property="dst_plays_difficult_games" scope="request" value="1">
                                        <tr >
                                            <td>&nbsp;</td>
                                            <td><li>Plays difficult games </td>
                                            </tr>
                                        </logic:equal>
                                        <logic:equal name="dstBean" property="dst_interested_in_dressing_up" scope="request" value="1">
                                        <tr >
                                            <td>&nbsp;</td>
                                            <td><li>Interested in dressing up </td>
                                            </tr>
                                        </logic:equal>
                                        <logic:equal name="dstBean" property="dst_understands_abstract_ideas" scope="request" value="1">
                                        <tr >
                                            <td>&nbsp;</td>
                                            <td><li>Understands abstract ideas (justice) </td>
                                            </tr>
                                        </logic:equal>


                                    <tr >

                                        <td valign="top"><br>&nbsp;Total</td>
                                        <td ><br>&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="dstBean" property="thirteenyears"/> days<br>&nbsp;</td>
                                    </tr>


                                    <tr >
                                        <td >Ref:</td>
                                        <td colspan="2">
                                            <table border=1 style="border-collapse: collapse" >
                                                <tr>
                                                    <td ><pre>1->72</pre></td>
                                                    <td><pre>2->144</pre></td>
                                                    <td ><pre>3->216</pre></td>

                                                </tr>
                                                <tr>

                                                    <td><pre>4->288</pre></td>
                                                    <td colspan="2"><pre>5->360</pre></td>

                                                </tr>
                                            </table>
                                    </tr>

                                </table>
                            </td>

                        </logic:notEqual>

                        <logic:notEqual name="dstBean" property="fifteenyears" scope="request" value="0">

                            <td>
                                <table  align="center" cellspacing="1" cellpadding="8" class="innerTable" width="85%">
                                    <tr><td colspan="2"><center>Fifteen Years</center></td></tr>

                                    <logic:equal name="dstBean" property="dst_makes_sensible_plans_for_future" scope="request" value="1">
                                        <tr >
                                            <td>&nbsp;</td>
                                            <td><li>Makes sensible plans for future (job) </td>
                                            </tr>
                                        </logic:equal>
                                        <logic:equal name="dstBean" property="dst_follows_current_events" scope="request" value="1">
                                        <tr >
                                            <td>&nbsp;</td>
                                            <td><li>Follows current events </td>
                                            </tr>
                                        </logic:equal>
                                        <logic:equal name="dstBean" property="dst_buys_own_clothing" scope="request" value="1">
                                        <tr >
                                            <td>&nbsp;</td>
                                            <td><li>Buys own clothing </td>
                                            </tr>
                                        </logic:equal>
                                        <logic:equal name="dstBean" property="dst_systematises_own_work" scope="request" value="1">
                                        <tr >
                                            <td>&nbsp;</td>
                                            <td><li>Systematises own work </td>
                                            </tr>
                                        </logic:equal>
                                        <logic:equal name="dstBean" property="dst_purchases_for_others" scope="request" value="1">
                                        <tr >
                                            <td>&nbsp;</td>
                                            <td><li>Purchases for others </td>
                                            </tr>
                                        </logic:equal>


                                    <tr >

                                        <td valign="top"><br>&nbsp;Total</td>
                                        <td ><br>&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="dstBean" property="fifteenyears"/> days<br>&nbsp;</td>
                                    </tr>






                                    <tr >
                                        <td >Ref:</td>
                                        <td colspan="2">
                                            <table border=1 style="border-collapse: collapse" >
                                                <tr>
                                                    <td ><pre>1->144</pre></td>
                                                    <td><pre>2->288</pre></td>
                                                    <td ><pre>3->426</pre></td>

                                                </tr>
                                                <tr>

                                                    <td><pre>4->576</pre></td>
                                                    <td colspan="2"><pre>5->720</pre></td>

                                                </tr>
                                            </table>
                                    </tr>

                                </table>
                            </td>
                        </logic:notEqual>
                    </tr>
                </table>
                <br><br>

                <table  align="center" cellspacing="1" cellpadding="8" class="innerTable" width="85%">
                    <tr><td colspan="2" class="labelBlue">Calculation of mental age</td></tr>

                    <tr><td width="25%" class="label">Total no. of days are =  </td><td class="label"><bean:write name="dstBean" property="totaldst" scope="request" /></td></tr>

                    <tr><td class="label">Total no. of months =  </td><td class="label"><bean:write name="dstBean" property="noofmonths" scope="request"/></td></tr>
                    <tr><td class="label">Total no. of Years =  </td><td class="label"><bean:write name="dstBean" property="noofyears" scope="request"/></td></tr>
                    <tr><td class="label">Mental age =  </td><td class="label"><bean:write name="dstBean" property="mentalage" scope="request"/></td></tr>
                    <tr><td colspan="2" class="labelBlue">Calculation of  chronological age</td></tr>
                    <tr><td class="label">Date of birth = </td><td class="label"><bean:write name="dstBean" property="dateofbirth" scope="request"/></td></tr>
                    <tr><td class="label">Todays date = </td><td class="label"><bean:write name="dstBean" property="todaysdate" scope="request"/></td></tr>
                    <tr><td class="label">Chronological Age= (todays date - date of birth)  </td><td class="label"><bean:write name="dstBean" property="chronologicalage" scope="request"/></td></tr>
                    <tr><td width="15%" class="label">IQ =  </td><td><pre><br><div style="font-family:verdana;"><bean:write name="dstBean" property="iq" scope="request"/></div></pre></td></tr>

                </table><br>

                <table align="center">
                    <tr>
                        <html:button property="" value="Back" onclick="goBack()" styleClass="button"/>  </tr>
                    <tr></tr><tr></tr>
                    <tr><a href="showCalc.do?typeofDisabilityFlag=11&flagPrint=print" target="_blank">
                        Print<img src="DisabilityUITG/images/print.gif"  height="25" width="35"/></a></tr>

            </table>



        </html:form>
    </logic:present>
</body>



</html:html>