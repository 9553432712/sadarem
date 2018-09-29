<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<html:html>
    <head>
        
        <script type="text/javascript" src="./DisabilityUITG/js/tabber.js"></script>
        <link rel="stylesheet" HREF="./DisabilityUITG/css/tabber.css" TYPE="text/css" MEDIA="screen">
        <script type="text/javascript">
            document.write('<style type="text/css">.tabber{display:none;}<\/style>');

        </script>
        <script language="javascript">
            function goBack()
            {
                document.forms[0].action="MentalRetardationForwdAction.do";
                document.forms[0].submit();
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
                    enable(1,7);
                else
                    disable(1,7);
            }

            function enableOrDisable6MonthsFields()
            {
                if( document.getElementById("6MSelectAll").checked==true)
                    enable(8,13);
                else
                    disable(8,13);
            }

            function enableOrDisable9MonthsFields()
            {
                if( document.getElementById("9MSelectAll").checked==true)
                    enable(14,17);
                else
                    disable(14,17);
            }

            function enableOrDisable1YearFields()
            {
                if( document.getElementById("1YSelectAll").checked==true)
                    enable(18,21);
                else
                    disable(18,21);

            }




            function enableOrDisable1andHalfYearFields()
            {
                if( document.getElementById("1andHalfYSelectAll").checked==true)
                    enable(22,25);
                else
                    disable(22,25);
            }





            function enableOrDisable2YearsFields()
            {
                if( document.getElementById("2YSelectAll").checked==true)
                    enable(26,29);
                else
                    disable(26,29);
            }

            function enableOrDisable3YearsFields()
            {
                if( document.getElementById("3YSelectAll").checked==true)
                    enable(30,35);
                else
                    disable(30,35);
            }

            function enableOrDisable4YearsFields()
            {
                if( document.getElementById("4YSelectAll").checked==true)
                    enable(36,40);
                else
                    disable(36,40);
            }

            function enableOrDisable5YearsFields()
            {
                if( document.getElementById("5YSelectAll").checked==true)
                    enable(41,46);
                else
                    disable(41,46);
            }

            function enableOrDisable6YearsFields()
            {
                if( document.getElementById("6YSelectAll").checked==true)
                    enable(47,51);
                else
                    disable(47,51);
            }

            function enableOrDisable7YearsFields()
            {
                if( document.getElementById("7YSelectAll").checked==true)
                    enable(52,56);
                else
                    disable(52,56);
            }

            function enableOrDisable8YearsFields()
            {
                if( document.getElementById("8ySelectAll").checked==true)
                    enable(57,60);
                else
                    disable(57,60);
            }

            function enableOrDisable9YearsFields()
            {
                if( document.getElementById("9YSelectAll").checked==true)
                    enable(61,66);
                else
                    disable(61,66);
            }


        </script>
        <script language="javascript">

            function enableOrDisable10YearsFields()
            {
                if( document.getElementById("10YSelectAll").checked==true)
                    enable(67,71);
                else
                    disable(67,71);
            }

            function enableOrDisable11YearsFields()
            {
                if( document.getElementById("11YSelectAll").checked==true)
                    enable(72,75);
                else
                    disable(72,75);
            }

            function enableOrDisable12YearsFields()
            {
                if( document.getElementById("12YSelectAll").checked==true)
                    enable(76,78);
                else
                    disable(76,78);
            }

            function enableOrDisable13YearsFields()
            {
                if( document.getElementById("13YSelectAll").checked==true)
                    enable(79,83);
                else
                    disable(79,83);

            }

            function enableOrDisable15YearsFields()
            {
                if( document.getElementById("15YSelectAll").checked==true)
                    enable(84,88);
                else
                    disable(84,88);
            }


        </script>
        <script language="javascript">
            function checkSelectAllBasedonOnload()
            {
                subfunctionforSelectAll(1,7,"3MSelectAll");
                subfunctionforSelectAll(8,13,"6MSelectAll");
                subfunctionforSelectAll(14,17,"9MSelectAll");
                subfunctionforSelectAll(18,21,"1ySelectAll");
                subfunctionforSelectAll(22,25,"1andHalfYSelectAll");
                subfunctionforSelectAll(26,29,"2YSelectAll");
                subfunctionforSelectAll(30,35,"3YSelectAll");
                subfunctionforSelectAll(36,40,"4YSelectAll");
                subfunctionforSelectAll(41,46,"5YSelectAll");
                subfunctionforSelectAll(47,51,"6YSelectAll");
                subfunctionforSelectAll(52,56,"7YSelectAll");
                subfunctionforSelectAll(57,60,"8ySelectAll");
                subfunctionforSelectAll(61,66,"9YSelectAll");
                subfunctionforSelectAll(67,71,"10YSelectAll");
                subfunctionforSelectAll(72,75,"11YSelectAll");
                subfunctionforSelectAll(76,78,"12YSelectAll");
                subfunctionforSelectAll(79,83,"13YSelectAll");
                subfunctionforSelectAll(84,88,"15YSelectAll");


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
                if (thisform.getAttribute('submitted')){
                    return false;
                }else{
                     thisform.setAttribute('submitted','true');
                      document.getElementById('hearButton').disabled = true;
                     return true;
                }

            }
        </script>


    </head>

    <body>


        <html:form action="MRDevelopmentalScreeningTestAction.do?parameter=insertMRDevelopmentalScreeningTest" method="post"
                   onsubmit="return avoidDuplicateForm(this)">

            <table  align="center" cellspacing="1" cellpadding="0" class="inputform" width="90%">
                <tr>
                    <th colspan="26">ADD DEVELOPMENTAL SCREENING TEST</th>
                </tr></table>



            <div class="tabber">
                <div class="tabbertab">
                    <h2>3M - (1-1/2)Y</h2>
                   <table border="0" class="inputform" cellpadding="0" cellspacing="1" width="90%" >
                        <tr>
                            <td valign="top">
                               <table border="0" class="inputform" cellpadding="0" cellspacing="1" width="100%">
                                    <tr>
                                        <th>&nbsp;</th>
                                        <th>&nbsp;</th>
                                       <th>&nbsp;</th>
                                        <th>Months</th>
                                        <th>Days</th>
                                   
                                    </tr>
                                    <tr >
                                        <th>&nbsp;</th>
                                        <th>&nbsp;</th>
                                        <th>Select All <input type="checkbox" id="3MSelectAll" onclick="enableOrDisable3MonthsFields();" value="true"  style="width:25px;height:15px"/></th>
                                        <th>&nbsp;</th>
                                        <th>&nbsp;</th>
                                    </tr>
                                    <tr >
                                        <td>&nbsp;</td>
                                        <td>Birth cry present </td>
                                        <td><html:checkbox styleId="1" property="dst_birth_cry_present" onclick="checkSelectAllBasedonOnload()" value="1"  style="width:25px;height:15px"/></td>
                                        <td>&nbsp;</td>
                                        <td>13</td>
                                    </tr>
                                    <tr >
                                        <td>&nbsp;</td>
                                        <td>Equal bilateral movements </td>
                                        <td><html:checkbox styleId="2" property="dst_equal_bilateral_movements" onclick="checkSelectAllBasedonOnload()" value="1"  style="width:25px;height:15px"/></td>
                                        <td>&nbsp;</td>
                                        <td>26</td>
                                    </tr>
                                    <tr >
                                        <td>&nbsp;</td>
                                        <td>Responds to bell </td>
                                        <td><html:checkbox styleId="3" property="dst_responds_to_bell" onclick="checkSelectAllBasedonOnload()" value="1"  style="width:25px;height:15px"/></td>
                                        <td>&nbsp;</td>
                                        <td>39</td>
                                    </tr>
                                    <tr >
                                        <td>&nbsp;</td>
                                        <td>Vocalises sounds </td>
                                        <td><html:checkbox  styleId="4" property="dst_vocalises_sounds" onclick="checkSelectAllBasedonOnload()" value="1"  style="width:25px;height:15px"/></td>
                                        <td>&nbsp;</td>
                                        <td>52</td>
                                    </tr>
                                    <tr >
                                        <td>&nbsp;</td>
                                        <td>Smiles spontaneously </td>
                                        <td><html:checkbox styleId="5" property="dst_smiles_spontaneously" onclick="checkSelectAllBasedonOnload()" value="1"  style="width:25px;height:15px"/></td>
                                        <td>&nbsp;</td>
                                        <td>65</td>
                                    </tr>
                                    <tr >
                                        <td>&nbsp;</td>
                                        <td>Eyes follow moving object </td>
                                        <td><html:checkbox styleId="6" property="dst_eyes_following_moving_object" onclick="checkSelectAllBasedonOnload()" value="1"  style="width:25px;height:15px"/></td>
                                        <td>&nbsp;</td>
                                        <td>78</td>
                                    </tr>
                                    <tr >
                                        <td>3M</td>
                                        <td>Head steady </td>
                                        <td><html:checkbox styleId="7" property="dst_head_steady" onclick="checkSelectAllBasedonOnload()" value="1"  style="width:25px;height:15px"/></td>
                                        <td>&nbsp;</td>
                                        <td>90</td>
                                    </tr>

                                    <tr ><td colspan="5"><hr></td></tr>

                                    <tr >
                                        <th>&nbsp;</th>
                                         <th>&nbsp;</th>
                                        <th width="25%">Select All <input type="checkbox" id="6MSelectAll" onclick="enableOrDisable6MonthsFields()" value="true"  style="width:25px;height:15px"/></th>
                                         <th>&nbsp;</th>
                                         <th>&nbsp;</th>
                                    </tr>

                                    <tr >
                                        <td>&nbsp;</td>
                                        <td>Reaches for objects </td>
                                        <td><html:checkbox styleId="8" property="dst_reaches_for_objects" onclick="checkSelectAllBasedonOnload()" value="1"  style="width:25px;height:15px"/></td>
                                        <td>&nbsp;</td>
                                        <td>15</td>
                                    </tr>
                                    <tr >
                                        <td>&nbsp;</td>
                                        <td>Laughs aloud </td>
                                        <td><html:checkbox styleId="9" property="dst_laughs_loud" onclick="checkSelectAllBasedonOnload()" value="1"  style="width:25px;height:15px"/></td>
                                        <td>&nbsp;</td>
                                        <td>30</td>
                                    </tr>
                                    <tr >
                                        <td>&nbsp;</td>
                                        <td>Recognises mother </td>
                                        <td><html:checkbox styleId="10" property="dst_recognises_mother" onclick="checkSelectAllBasedonOnload()" value="1"  style="width:25px;height:15px"/></td>
                                        <td>&nbsp;</td>
                                        <td>45</td>
                                    </tr>
                                    <tr >
                                        <td>&nbsp;</td>
                                        <td>Vocalises for pleasure/babble </td>
                                        <td><html:checkbox styleId="11" property="dst_vocalises_for_pleasure_or_babble" onclick="checkSelectAllBasedonOnload()" value="1"  style="width:25px;height:15px"/></td>
                                        <td>&nbsp;</td>
                                        <td>60</td>
                                    </tr>
                                    <tr >
                                        <td>&nbsp;</td>
                                        <td>Carries objects to mouth </td>
                                        <td><html:checkbox styleId="12" property="dst_carries_objects_to_mouth" onclick="checkSelectAllBasedonOnload()" value="1"  style="width:25px;height:15px"/></td>
                                        <td>&nbsp;</td>
                                        <td>75</td>
                                    </tr>
                                    <tr >
                                        <td>6M</td>
                                        <td>Rolls over </td>
                                        <td><html:checkbox styleId="13" property="dst_rolls_over" onclick="checkSelectAllBasedonOnload()" value="1"  style="width:25px;height:15px"/></td>
                                        <td>&nbsp;</td>
                                        <td>90</td>
                                    </tr>

                                </table>
                            </td>

                            <td>
                                 <table border="0" class="inputform" cellpadding="0" cellspacing="1" width="100%">
                                    <tr >
                                       <th>&nbsp;</th>
                                        <th>&nbsp;</th>
                                       <th>&nbsp;</th>
                                        <th>Months</th>
                                        <th>Days</th>
                                      <%--  <th>&nbsp;</th>
                                        <th>&nbsp;</th>--%>
                                    </tr>
                                    <tr >
                                        <th>&nbsp;</th>
                                         <th>&nbsp;</th>
                                        <th>Select All <input type="checkbox" id="9MSelectAll" onclick="enableOrDisable9MonthsFields()" value="true"  style="width:25px;height:15px"/></th>
                                         <th>&nbsp;</th>
                                         <th>&nbsp;</th>
                                    </tr>
                                    <tr >
                                        <td>&nbsp;</td>
                                        <td>Imitates speech sounds </td>
                                        <td><html:checkbox styleId="14" property="dst_imitates_speech_sounds" onclick="checkSelectAllBasedonOnload()" value="1"  style="width:25px;height:15px"/></td>
                                        <td>&nbsp;</td>
                                        <td>23</td>
                                    </tr>
                                    <tr >
                                        <td>&nbsp;</td>
                                        <td>Sits by self </td>
                                        <td><html:checkbox styleId="15" property="dst_sits_by_self" onclick="checkSelectAllBasedonOnload()" value="1"  style="width:25px;height:15px"/></td>
                                        <td>&nbsp;</td>
                                        <td>46</td>
                                    </tr>
                                    <tr >
                                        <td>&nbsp;</td>
                                        <td>Thumb finger grasp </td>
                                        <td><html:checkbox styleId="16" property="dst_thumb_finger_grasp" onclick="checkSelectAllBasedonOnload()" value="1"  style="width:25px;height:15px"/></td>
                                        <td>&nbsp;</td>
                                        <td>68</td>
                                    </tr>
                                    <tr >
                                        <td>9M</td>
                                        <td>Shows curiousity </td>
                                        <td><html:checkbox styleId="17" property="dst_shows_curiousity" onclick="checkSelectAllBasedonOnload()" value="1"  style="width:25px;height:15px"/></td>
                                        <td>&nbsp;</td>
                                        <td>90</td>
                                    </tr>
                                    <tr ><td colspan="5"><hr></td></tr>

                                    <tr >
                                         <th>&nbsp;</th>
                                         <th>&nbsp;</th>
                                        <th width="25%">Select All <input type="checkbox" id="1ySelectAll" onclick="enableOrDisable1YearFields()" value="true"  style="width:25px;height:15px"/></th>
                                         <th>&nbsp;</th>
                                         <th>&nbsp;</th>
                                    </tr>
                                    <tr >
                                        <td>&nbsp;</td>
                                        <td>Says 3 words 'dada', 'mama', etc </td>
                                        <td><html:checkbox styleId="18" property="dst_says_three_words" onclick="checkSelectAllBasedonOnload()" value="1"  style="width:25px;height:15px"/></td>
                                        <td>&nbsp;</td>
                                        <td>23</td>
                                    </tr>
                                    <tr >
                                        <td>&nbsp;</td>
                                        <td>Stands alone well </td>
                                        <td><html:checkbox styleId="19" property="dst_stands_alone_well" onclick="checkSelectAllBasedonOnload()" value="1"  style="width:25px;height:15px"/></td>
                                        <td>&nbsp;</td>
                                        <td>46</td>
                                    </tr>
                                    <tr >
                                        <td>&nbsp;</td>
                                        <td>Follows simple instructions </td>
                                        <td><html:checkbox styleId="20" property="dst_follows_simple_instructions" onclick="checkSelectAllBasedonOnload()" value="1"  style="width:25px;height:15px"/></td>
                                        <td>&nbsp;</td>
                                        <td>68</td>
                                    </tr>
                                    <tr >
                                        <td>1Y</td>
                                        <td>Co-operates for dressing </td>
                                        <td><html:checkbox styleId="21" property="dst_cooperates_for_dressing" onclick="checkSelectAllBasedonOnload()" value="1"  style="width:25px;height:15px"/></td>
                                        <td>&nbsp;</td>
                                        <td>90</td>
                                    </tr>
                                    <tr ><td colspan="5"><hr></td></tr>

                                    <tr >
                                         <th>&nbsp;</th>
                                         <th>&nbsp;</th>
                                        <th width="25%">Select All <input type="checkbox" id="1andHalfYSelectAll" onclick="enableOrDisable1andHalfYearFields()" value="true"  style="width:25px;height:15px"/></th>
                                         <th>&nbsp;</th>
                                         <th>&nbsp;</th>
                                    </tr>
                                    <tr >
                                        <td>&nbsp;</td>
                                        <td>Many intelligible words </td>
                                        <td><html:checkbox styleId="22" property="dst_many_intelligible_words" onclick="checkSelectAllBasedonOnload()" value="1"  style="width:25px;height:15px"/></td>
                                        <td>1</td>
                                        <td>15</td>
                                    </tr>
                                    <tr >
                                        <td>&nbsp;</td>
                                        <td>Walks, runs well </td>
                                        <td><html:checkbox styleId="23" property="dst_walks_runs_well" onclick="checkSelectAllBasedonOnload()" value="1"  style="width:25px;height:15px"/></td>
                                        <td>3</td>
                                        <td>&nbsp;</td>
                                    </tr>
                                    <tr >
                                        <td>&nbsp;</td>
                                        <td>Indicates wants </td>
                                        <td><html:checkbox styleId="24" property="dst_indicates_wants" onclick="checkSelectAllBasedonOnload()" value="1"  style="width:25px;height:15px"/></td>
                                        <td>4</td>
                                        <td>15</td>
                                    </tr>
                                    <tr >
                                        <td>[1-1/2]</td>
                                        <td>Scribbles spontaneously </td>
                                        <td><html:checkbox styleId="25" property="dst_scribbles_spontaneously" onclick="checkSelectAllBasedonOnload()" value="1"  style="width:25px;height:15px"/></td>
                                        <td>6</td>
                                        <td>&nbsp;</td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </table>
                </div>
                <div class="tabbertab">
                    <h2>2Y - 6Y</h2>
                 <table border="0" class="inputform" cellpadding="0" cellspacing="1" width="90%">
                        <tr>
                            <td>
                                <table border="0" class="inputform" cellpadding="0" cellspacing="1" width="100%">
                                    <tr >
                                        <th>&nbsp;</th>
                                        <th>&nbsp;</th>
                                       <th>&nbsp;</th>
                                        <th>Months</th>
                                        <th>Days</th>
                                       <%-- <th>&nbsp;</th>
                                        <th>&nbsp;</th>--%>
                                    </tr>
                                    <tr >
                                         <th>&nbsp;</th>
                                        <th>&nbsp;</th>
                                        <th width="25%">Select All <input type="checkbox" id="2YSelectAll" onclick="enableOrDisable2YearsFields()" value="true"  style="width:25px;height:15px"  /></th>
                                          <th>&nbsp;</th>
                                        <th>&nbsp;</th>
                                    </tr>
                                    <tr >
                                        <td>&nbsp;</td>
                                        <td>Says sentences of 2/3 words </td>
                                        <td><html:checkbox styleId="26" property="dst_says_sentences_of_twobythree_words" onclick="checkSelectAllBasedonOnload()" value="1"  style="width:25px;height:15px"/></td>
                                        <td>1</td>
                                        <td>15</td>
                                    </tr>
                                    <tr >
                                        <td>&nbsp;</td>
                                        <td>Points out objects in pictures </td>
                                        <td><html:checkbox styleId="27" property="dst_points_out_objects_in_pictures" onclick="checkSelectAllBasedonOnload()" value="1"  style="width:25px;height:15px"/></td>
                                        <td>3</td>
                                        <td>&nbsp;</td>
                                    </tr>
                                    <tr >
                                        <td>&nbsp;</td>
                                        <td>Shows body parts </td>
                                        <td><html:checkbox styleId="28" property="dst_shows_body_parts" onclick="checkSelectAllBasedonOnload()" value="1"  style="width:25px;height:15px"/></td>
                                        <td>4</td>
                                        <td>15</td>
                                    </tr>
                                    <tr >
                                        <td>2Y</td>
                                        <td>Participates in play </td>
                                        <td><html:checkbox styleId="29" property="dst_participates_in_play" onclick="checkSelectAllBasedonOnload()" value="1"  style="width:25px;height:15px"/></td>
                                        <td>6</td>
                                        <td>&nbsp;</td>
                                    </tr>
                                    <tr ><td colspan="5"><hr></td></tr>

                                    <tr >
                                        <th>&nbsp;</th>
                                         <th>&nbsp;</th>
                                        <th width="25%">Select All <input type="checkbox" id="3ySelectAll" onclick="enableOrDisable3YearsFields()" value="true"  style="width:25px;height:15px"  /></th>
                                        <th>&nbsp;</th>
                                         <th>&nbsp;</th>
                                    </tr>
                                    <tr >
                                        <td>&nbsp;</td>
                                        <td>Copies O </td>
                                        <td><html:checkbox styleId="30" property="dst_copies_o" onclick="checkSelectAllBasedonOnload()" value="1"  style="width:25px;height:15px"/></td>
                                        <td>2</td>
                                        <td>&nbsp;</td>
                                    </tr>
                                    <tr >
                                        <td>&nbsp;</td>
                                        <td>Relates experiences </td>
                                        <td><html:checkbox styleId="31" property="dst_relates_experiences" onclick="checkSelectAllBasedonOnload()" value="1"  style="width:25px;height:15px"/></td>
                                        <td>4</td>
                                        <td>&nbsp;</td>
                                    </tr>
                                    <tr >
                                        <td>&nbsp;</td>
                                        <td>Knows names, uses of common objects </td>
                                        <td><html:checkbox styleId="32" property="dst_knows_names_uses_of_common_objects" onclick="checkSelectAllBasedonOnload()" value="1"  style="width:25px;height:15px"/></td>
                                        <td>6</td>
                                        <td>&nbsp;</td>
                                    </tr>
                                    <tr >
                                        <td>&nbsp;</td>
                                        <td>Begins to ask 'Why?' </td>
                                        <td><html:checkbox styleId="33" property="dst_begins_to_ask_why" onclick="checkSelectAllBasedonOnload()" value="1"  style="width:25px;height:15px"/></td>
                                        <td>8</td>
                                        <td>&nbsp;</td>
                                    </tr>
                                    <tr >
                                        <td>&nbsp;</td>
                                        <td>Takes food by self </td>
                                        <td><html:checkbox styleId="34" property="dst_takes_food_by_self" onclick="checkSelectAllBasedonOnload()" value="1"  style="width:25px;height:15px"/></td>
                                        <td>10</td>
                                        <td>&nbsp;</td>
                                    </tr>
                                    <tr >
                                        <td>3Y</td>
                                        <td>Toilet control present </td>
                                        <td><html:checkbox styleId="35" property="dst_toilet_control_present" onclick="checkSelectAllBasedonOnload()" value="1"  style="width:25px;height:15px"/></td>
                                        <td>12</td>
                                        <td>&nbsp;</td>
                                    </tr>


                                    <tr ><td colspan="5"><hr></td></tr>

                                    <tr >
                                          <th>&nbsp;</th>
                                         <th>&nbsp;</th>
                                        <th width="25%">Select All <input type="checkbox" id="4YSelectAll" onclick="enableOrDisable4YearsFields()" value="true"  style="width:25px;height:15px"  /></th>
                                          <th>&nbsp;</th>
                                         <th>&nbsp;</th>
                                    </tr>
                                    <tr >
                                        <td>&nbsp;</td>
                                        <td>Buttons up </td>
                                        <td><html:checkbox styleId="36" property="dst_buttons_up" onclick="checkSelectAllBasedonOnload()" value="1"  style="width:25px;height:15px"/></td>
                                        <td>2</td>
                                        <td>12</td>
                                    </tr>
                                    <tr >
                                        <td>&nbsp;</td>
                                        <td>Comprehends 'hunger', 'cold' </td>
                                        <td><html:checkbox styleId="37" property="dst_comprehends_hunger_cold" onclick="checkSelectAllBasedonOnload()" value="1"  style="width:25px;height:15px"/></td>
                                        <td>4</td>
                                        <td>24</td>
                                    </tr>
                                    <tr >
                                        <td>&nbsp;</td>
                                        <td>Plays cooperatively with children </td>
                                        <td><html:checkbox styleId="38" property="dst_plays_cooperatively_with_children" onclick="checkSelectAllBasedonOnload()" value="1"  style="width:25px;height:15px"/></td>
                                        <td>7</td>
                                        <td>6</td>
                                    </tr>
                                    <tr >
                                        <td>&nbsp;</td>
                                        <td>Repeats 3 digits </td>
                                        <td><html:checkbox styleId="39" property="dst_repeats_three_digits" onclick="checkSelectAllBasedonOnload()" value="1"  style="width:25px;height:15px"/></td>
                                        <td>9</td>
                                        <td>18</td>
                                    </tr>
                                    <tr >
                                        <td>4Y</td>
                                        <td>Tells stories </td>
                                        <td><html:checkbox styleId="40" property="dst_tells_stories" onclick="checkSelectAllBasedonOnload()" value="1"  style="width:25px;height:15px"/></td>
                                        <td>12</td>
                                        <td>&nbsp;</td>
                                    </tr>
                                </table>
                            </td>
                            <td valign="top">
                                 <table border="0" class="inputform" cellpadding="0" cellspacing="1" width="100%">
                                    <tr >
                                        <th>&nbsp;</th>
                                        <th>&nbsp;</th>
                                       <th>&nbsp;</th>
                                        <th>Months</th>
                                        <th>Days</th>
                                      <%--  <th>&nbsp;</th>
                                        <th>&nbsp;</th>--%>
                                    </tr>
                                    <tr >
                                         <th>&nbsp;</th>
                                         <th>&nbsp;</th>
                                          <th width="25%">Select All<input type="checkbox" id="5YSelectAll" onclick="enableOrDisable5YearsFields()" value="true"  style="width:25px;height:15px"/></th>
                                         <th>&nbsp;</th>
                                         <th>&nbsp;</th>
                                    </tr>
                                    <tr >
                                        <td>&nbsp;</td>
                                        <td>Defines words </td>
                                        <td><html:checkbox styleId="41" property="dst_defines_words" onclick="checkSelectAllBasedonOnload()" value="1"  style="width:25px;height:15px"/></td>
                                        <td>2</td>
                                        <td>&nbsp;</td>
                                    </tr>
                                    <tr >
                                        <td>&nbsp;</td>
                                        <td>Makes simple drawing </td>
                                        <td><html:checkbox styleId="42" property="dst_makes_simple_drawing" onclick="checkSelectAllBasedonOnload()" value="1"  style="width:25px;height:15px"/></td>
                                        <td>4</td>
                                        <td>&nbsp;</td>
                                    </tr>
                                    <tr >
                                        <td>&nbsp;</td>
                                        <td>Dresses with no supervision </td>
                                        <td><html:checkbox styleId="43" property="dst_dresses_with_no_supervision" onclick="checkSelectAllBasedonOnload()" value="1"  style="width:25px;height:15px"/></td>
                                        <td>6</td>
                                        <td>&nbsp;</td>
                                    </tr>
                                    <tr >
                                        <td>&nbsp;</td>
                                        <td>Describes actions in pictures </td>
                                        <td><html:checkbox styleId="44" property="dst_describes_actions_in_pictures" onclick="checkSelectAllBasedonOnload()" value="1"  style="width:25px;height:15px"/></td>
                                        <td>8</td>
                                        <td>&nbsp;</td>
                                    </tr>
                                    <tr >
                                        <td>&nbsp;</td>
                                        <td>Gives sensible answers to questions </td>
                                        <td><html:checkbox styleId="45" property="dst_gives_sensible_answers_to_questions" onclick="checkSelectAllBasedonOnload()" value="1"  style="width:25px;height:15px"/></td>
                                        <td>10</td>
                                        <td>&nbsp;</td>
                                    </tr>
                                    <tr >
                                        <td>5Y</td>
                                        <td>Goes about neighbourhood </td>
                                        <td><html:checkbox styleId="46" property="dst_goes_about_neighbourhood" onclick="checkSelectAllBasedonOnload()" value="1"  style="width:25px;height:15px"/></td>
                                        <td>12</td>
                                        <td>&nbsp;</td>
                                    </tr>
                                    <tr ><td colspan="5"><hr></td></tr>

                                    <tr >
                                         <th>&nbsp;</th>
                                         <th>&nbsp;</th>
                                         <th width="25%">Select All<input type="checkbox" id="6ySelectAll" onclick="enableOrDisable6YearsFields()" value="true"  style="width:25px;height:15px"/></th>
                                        <th>&nbsp;</th>
                                         <th>&nbsp;</th>
                                    </tr>
                                    <tr >
                                        <td>&nbsp;</td>
                                        <td>Can name primary colours </td>
                                        <td><html:checkbox styleId="47" property="dst_can_name_primary_colours" onclick="checkSelectAllBasedonOnload()" value="1"  style="width:25px;height:15px"/></td>
                                        <td>2</td>
                                        <td>12</td>
                                    </tr>
                                    <tr >
                                        <td>&nbsp;</td>
                                        <td>Plays games governed by rules </td>
                                        <td><html:checkbox styleId="48" property="dst_plays_games_governed_by_rules" onclick="checkSelectAllBasedonOnload()" value="1"  style="width:25px;height:15px"/></td>
                                        <td>4</td>
                                        <td>24</td>
                                    </tr>
                                    <tr >
                                        <td>&nbsp;</td>
                                        <td>Writes simple words </td>
                                        <td><html:checkbox styleId="49" property="dst_writes_simple_words" onclick="checkSelectAllBasedonOnload()" value="1"  style="width:25px;height:15px"/></td>
                                        <td>7</td>
                                        <td>6</td>
                                    </tr>
                                    <tr >
                                        <td>&nbsp;</td>
                                        <td>Gains admission to school </td>
                                        <td><html:checkbox styleId="50" property="dst_gains_admission_to_school" onclick="checkSelectAllBasedonOnload()" value="1"  style="width:25px;height:15px"/></td>
                                        <td>9</td>
                                        <td>18</td>
                                    </tr>

                                    <tr >
                                        <td>6Y</td>
                                        <td>Enjoys constructive play </td>
                                        <td><html:checkbox styleId="51" property="dst_enjoys_constructive_play" onclick="checkSelectAllBasedonOnload()" value="1"  style="width:25px;height:15px"/></td>
                                        <td>12</td>
                                        <td>&nbsp;</td>
                                    </tr>
                                </table>

                            </td>
                        </tr>
                    </table>
                </div>
                <div class="tabbertab">
                    <h2>7Y - 12Y</h2>
                     <table border="0" class="inputform" cellpadding="0" cellspacing="1" width="90%">
                        <tr>
                            <td>
                                <table border="0" class="inputform" cellpadding="0" cellspacing="1" width="100%">
                                    <tr >
                                        <th>&nbsp;</th>
                                        <th>&nbsp;</th>
                                       <th>&nbsp;</th>
                                        <th>Months</th>
                                        <th>Days</th>

                                    </tr>
                                    <tr >
                                      <th>&nbsp;</th>
                                        <th>&nbsp;</th>
                                        <th width="25%">Select All <input type="checkbox" id="7YSelectAll" onclick="enableOrDisable7YearsFields()" value="true" style="width:25px;height:15px"/></th>
                                       <th>&nbsp;</th>
                                        <th>&nbsp;</th>
                                    </tr>
                                    <tr >
                                        <td>&nbsp;</td>
                                        <td>Adapts to home, school</td>
                                        <td><html:checkbox styleId="52" property="dst_adapts_to_home_school" onclick="checkSelectAllBasedonOnload()" value="1" style="width:25px;height:15px"/></td>
                                        <td>2</td>
                                        <td>12</td>
                                    </tr>
                                    <tr >
                                        <td>&nbsp;</td>
                                        <td>Tells differences of objects </td>
                                        <td><html:checkbox styleId="53" property="dst_tells_differences_of_objects" onclick="checkSelectAllBasedonOnload()" value="1" style="width:25px;height:15px"/></td>
                                        <td>4</td>
                                        <td>24</td>
                                    </tr>
                                    <tr >
                                        <td>&nbsp;</td>
                                        <td>Spells, reads, writes simple words </td>
                                        <td><html:checkbox styleId="54" property="dst_spells_reads_writes_simple_words" onclick="checkSelectAllBasedonOnload()" value="1" style="width:25px;height:15px"/></td>
                                        <td>7</td>
                                        <td>6</td>
                                    </tr>
                                    <tr >
                                        <td>&nbsp;</td>
                                        <td>Enjoys group play </td>
                                        <td><html:checkbox styleId="55" property="dst_enjoys_group_play" onclick="checkSelectAllBasedonOnload()" value="1"  style="width:25px;height:15px"/></td>
                                        <td>9</td>
                                        <td>18</td>
                                    </tr>
                                    <tr >
                                        <td>7Y</td>
                                        <td>Knows comparative value of coins </td>
                                        <td><html:checkbox styleId="56" property="dst_knows_comparative_value_of_coins" onclick="checkSelectAllBasedonOnload()" value="1"  style="width:25px;height:15px"/></td>
                                        <td>12</td>
                                        <td>&nbsp;</td>
                                    </tr>
                                    <tr ><td colspan="5"><hr></td></tr>

                                    <tr >
                                        <th>&nbsp;</th>
                                        <th>&nbsp;</th>
                                        <th width="25%">Select All <input type="checkbox" id="8ySelectAll" onclick="enableOrDisable8YearsFields()" value="true"  style="width:25px;height:15px"/></th>
                                         <th>&nbsp;</th>
                                        <th>&nbsp;</th>
                                    </tr>
                                    <tr >
                                        <td>&nbsp;</td>
                                        <td>Combs hair by self </td>
                                        <td><html:checkbox styleId="57" property="dst_combs_hair_by_self" onclick="checkSelectAllBasedonOnload()" value="1"  style="width:25px;height:15px"/></td>
                                        <td>3</td>
                                        <td>&nbsp;</td>
                                    </tr>
                                    <tr >
                                        <td>&nbsp;</td>
                                        <td>Makes small purchases </td>
                                        <td><html:checkbox styleId="58" property="dst_makes_small_purchases" onclick="checkSelectAllBasedonOnload()" value="1"  style="width:25px;height:15px"/></td>
                                        <td>6</td>
                                        <td>&nbsp;</td>
                                    </tr>
                                    <tr >
                                        <td>&nbsp;</td>
                                        <td>Competition in school/play </td>
                                        <td><html:checkbox styleId="59" property="dst_competition_in_school_or_play" onclick="checkSelectAllBasedonOnload()" value="1"  style="width:25px;height:15px"/></td>
                                        <td>9</td>
                                        <td>&nbsp;</td>
                                    </tr>
                                    <tr >
                                        <td>8Y</td>
                                        <td>Tells time </td>
                                        <td><html:checkbox styleId="60" property="dst_tells_time" onclick="checkSelectAllBasedonOnload()" value="1"  style="width:25px;height:15px"/></td>
                                        <td>12</td>
                                        <td>&nbsp;</td>
                                    </tr>


                                    <tr ><td colspan="5"><hr></td></tr>

                                    <tr >
                                        <th>&nbsp;</th>
                                         <th>&nbsp;</th>
                                        <th>Select All <input type="checkbox" id="9YSelectAll" onclick="enableOrDisable9YearsFields()" value="true"  style="width:25px;height:15px"  /></th>
                                          <th>&nbsp;</th>
                                         <th>&nbsp;</th>
                                    </tr>
                                    <tr >
                                        <td>&nbsp;</td>
                                        <td>Tells day, month, year </td>
                                        <td><html:checkbox styleId="61" property="dst_tells_day_month_year" onclick="checkSelectAllBasedonOnload()" value="1" style="width:25px;height:15px"/></td>
                                        <td>2</td>
                                        <td>&nbsp;</td>
                                    </tr>
                                    <tr >
                                        <td>&nbsp;</td>
                                        <td>Reads on own initiative </td>
                                        <td><html:checkbox styleId="62" property="dst_reads_on_own_initiative" onclick="checkSelectAllBasedonOnload()" value="1" style="width:25px;height:15px"/></td>
                                        <td>4</td>
                                        <td>&nbsp;</td>
                                    </tr>
                                    <tr >
                                        <td>&nbsp;</td>
                                        <td>Recoginises property rights </td>
                                        <td><html:checkbox styleId="63" property="dst_recognises_property_rights" onclick="checkSelectAllBasedonOnload()" value="1" style="width:25px;height:15px"/></td>
                                        <td>6</td>
                                        <td>&nbsp;</td>
                                    </tr>
                                    <tr >
                                        <td>&nbsp;</td>
                                        <td>Favourite of fairy tales </td>
                                        <td><html:checkbox styleId="64" property="dst_favourite_of_fairy_tales" onclick="checkSelectAllBasedonOnload()" value="1" style="width:25px;height:15px"/></td>
                                        <td>8</td>
                                        <td>&nbsp;</td>
                                    </tr>
                                    <tr >
                                        <td>&nbsp;</td>
                                        <td>Muscle coordination games (marbles) </td>
                                        <td><html:checkbox styleId="65" property="dst_muscle_coordination_games" onclick="checkSelectAllBasedonOnload()" value="1" style="width:25px;height:15px"/></td>
                                        <td>10</td>
                                        <td>&nbsp;</td>
                                    </tr>
                                    <tr >
                                        <td >9Y</td>
                                        <td>Bathes self unaided </td>
                                        <td><html:checkbox styleId="66" property="dst_bathes_self_unaided" onclick="checkSelectAllBasedonOnload()" value="1" style="width:25px;height"/></td>
                                        <td>12</td>
                                        <td>&nbsp;</td>
                                    </tr>
                                </table>
                            </td>
                            <td valign="top">
                                <table border="0" class="inputform" cellpadding="0" cellspacing="1" width="100%">
                                    <tr>
                                        <th>&nbsp;</th>
                                        <th>&nbsp;</th>
                                       <th>&nbsp;</th>
                                        <th>Months</th>
                                        <th>Days</th>
                                   
                                    </tr>
                                    <tr >
                                        <th>&nbsp;</th>
                                        <th>&nbsp;</th>
                                        <th>Select All <input type="checkbox" id="10YSelectAll" onclick="enableOrDisable10YearsFields()" value="true"  style="width:25px;height:15px"  /></th>
                                        <th>&nbsp;</th>
                                        <th>&nbsp;</th>
                                    </tr>
                                    <tr >
                                        <td>&nbsp;</td>
                                        <td>Cooperates keenly with companions </td>
                                        <td><html:checkbox styleId="67" property="dst_cooperates_keenly_with_companions" onclick="checkSelectAllBasedonOnload()" value="1"  style="width:25px;height:15px"/></td>
                                        <td>2</td>
                                        <td>12</td>
                                    </tr>
                                    <tr >
                                        <td>&nbsp;</td>
                                        <td>Has various hobbies, collections </td>
                                        <td><html:checkbox styleId="68" property="dst_has_various_hobbies_collections" onclick="checkSelectAllBasedonOnload()" value="1"  style="width:25px;height:15px"/></td>
                                        <td>4</td>
                                        <td>24</td>
                                    </tr>
                                    <tr >
                                        <td>&nbsp;</td>
                                        <td>goes about town freely </td>
                                        <td><html:checkbox styleId="69" property="dst_goes_about_town_freely" onclick="checkSelectAllBasedonOnload()" value="1"  style="width:25px;height:15px"/></td>
                                        <td>7</td>
                                        <td>6</td>
                                    </tr>
                                    <tr >
                                        <td>&nbsp;</td>
                                        <td>Sex differences in play become marked </td>
                                        <td><html:checkbox styleId="70" property="dst_sex_differences_in_play_become_marked" onclick="checkSelectAllBasedonOnload()" value="1"  style="width:25px;height:15px"/></td>
                                        <td>9</td>
                                        <td>18</td>
                                    </tr>
                                    <tr >
                                        <td>10Y</td>
                                        <td>Can stay away from home (camps) </td>
                                        <td><html:checkbox styleId="71" property="dst_can_stay_away_from_home" onclick="checkSelectAllBasedonOnload()" value="1"  style="width:25px;height:15px"/></td>
                                        <td>12</td>
                                        <td>&nbsp;</td>
                                    </tr>
                                    <tr ><td colspan="5"><hr></td></tr>

                                    <tr >
                                       <th>&nbsp;</th>
                                        <th>&nbsp;</th>
                                        <th width="25%">Select All <input type="checkbox" id="11ySelectAll" onclick="enableOrDisable11YearsFields()" value="true"  style="width:25px;height:15px"  /></th>
                                       <th>&nbsp;</th>
                                        <th>&nbsp;</th>
                                    </tr>
                                    <tr >
                                        <td>&nbsp;</td>
                                        <td>Writes occasional short letters </td>
                                        <td><html:checkbox styleId="72" property="dst_writes_occasional_short_letters" onclick="checkSelectAllBasedonOnload()" value="1"  style="width:25px;height:15px"/></td>
                                        <td>3</td>
                                        <td>&nbsp;</td>
                                    </tr>
                                    <tr >
                                        <td>&nbsp;</td>
                                        <td>Comprehends social situations </td>
                                        <td><html:checkbox styleId="73" property="dst_comprehends_social_situations" onclick="checkSelectAllBasedonOnload()" value="1"  style="width:25px;height:15px"/></td>
                                        <td>6</td>
                                        <td>&nbsp;</td>
                                    </tr>
                                    <tr >
                                        <td>&nbsp;</td>
                                        <td>Physical feats liked </td>
                                        <td><html:checkbox styleId="74" property="dst_physical_feets_liked" onclick="checkSelectAllBasedonOnload()" value="1"  style="width:25px;height:15px"/></td>
                                        <td>9</td>
                                        <td>&nbsp;</td>
                                    </tr>
                                    <tr >
                                        <td>11Y</td>
                                        <td>Able to discuss problems </td>
                                        <td><html:checkbox styleId="75" property="dst_able_to_discuss_problems" onclick="checkSelectAllBasedonOnload()" value="1"  style="width:25px;height:15px"/></td>
                                        <td>12</td>
                                        <td>&nbsp;</td>
                                    </tr>


                                    <tr ><td colspan="5"><hr></td></tr>

                                    <tr >
                                        <th>&nbsp;</th>
                                        <th>&nbsp;</th>
                                        <th width="25%">Select All <input type="checkbox" id="12YSelectAll" onclick="enableOrDisable12YearsFields()" value="true"  style="width:25px;height:15px" /></th>
                                        <th>&nbsp;</th>
                                        <th>&nbsp;</th>
                                    </tr>
                                    <tr >
                                        <td>&nbsp;</td>
                                        <td>Enjoys books, newspapers, magazines </td>
                                        <td><html:checkbox styleId="76" property="dst_enjoys_books_newspapers_magazines" onclick="checkSelectAllBasedonOnload()" value="1"  style="width:25px;height:15px"/></td>
                                        <td>4</td>
                                        <td>&nbsp;</td>
                                    </tr>
                                    <tr >
                                        <td>&nbsp;</td>
                                        <td>More independent in spending </td>
                                        <td><html:checkbox styleId="77" property="dst_more_independent_in_spending" onclick="checkSelectAllBasedonOnload()" value="1"  style="width:25px;height:15px"/></td>
                                        <td>8</td>
                                        <td>&nbsp;</td>
                                    </tr>

                                    <tr >
                                        <td>12Y</td>
                                        <td>Capable of self criticism </td>
                                        <td><html:checkbox styleId="78" property="dst_capable_of_self_criticism" onclick="checkSelectAllBasedonOnload()" value="1"  style="width:25px;height:15px"/></td>
                                        <td>12</td>
                                        <td>&nbsp;</td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </table>
                </div>
                <div class="tabbertab">
                    <h2>13Y - 15Y</h2>
                    <table border="0" class="inputform" cellpadding="0" cellspacing="1" width="90%">
                        <tr>
                            <td>
                             <table border="0" class="inputform" cellpadding="0" cellspacing="1" width="100%">
                                    <tr >
                                         <th>&nbsp;</th>
                                        <th>&nbsp;</th>
                                       <th>&nbsp;</th>
                                        <th>Months</th>
                                        <th>Days</th>

                                    </tr>
                                    <tr>
                                         <th>&nbsp;</th>
                                        <th>&nbsp;</th>
                                        <th width="25%">Select All <input type="checkbox" id="13YSelectAll" onclick="enableOrDisable13YearsFields()" value="true"  style="width:25px;height:15px" /></th>
                                         <th>&nbsp;</th>
                                        <th>&nbsp;</th>
                                    </tr>
                                    <tr >
                                        <td>&nbsp;</td>
                                        <td>Shows foresight, planning, judgement </td>
                                        <td><html:checkbox styleId="79" property="dst_shows_foresight_planning_judgement" onclick="checkSelectAllBasedonOnload()" value="1" style="width:25px;height:15px"/></td>
                                        <td>2</td>
                                        <td>12</td>
                                    </tr>
                                    <tr >
                                        <td>&nbsp;</td>
                                        <td>Learns from experience </td>
                                        <td><html:checkbox styleId="80" property="dst_learns_from_experience" onclick="checkSelectAllBasedonOnload()" value="1" style="width:25px;height:15px"/></td>
                                        <td>4</td>
                                        <td>24</td>
                                    </tr>
                                    <tr >
                                        <td>&nbsp;</td>
                                        <td>Plays difficult games </td>
                                        <td><html:checkbox styleId="81" property="dst_plays_difficult_games" onclick="checkSelectAllBasedonOnload()" value="1" style="width:25px;height:15px"/></td>
                                        <td>7</td>
                                        <td>6</td>
                                    </tr>
                                    <tr >
                                        <td>&nbsp;</td>
                                        <td>Interested in dressing up </td>
                                        <td><html:checkbox styleId="82" property="dst_interested_in_dressing_up" onclick="checkSelectAllBasedonOnload()" value="1" style="width:25px;height:15px"/></td>
                                        <td>9</td>
                                        <td>18</td>
                                    </tr>
                                    <tr >
                                        <td>13Y</td>
                                        <td>Understands abstract ideas (justice) </td>
                                        <td><html:checkbox styleId="83" property="dst_understands_abstract_ideas" onclick="checkSelectAllBasedonOnload()" value="1" style="width:25px;height:15px"/></td>
                                        <td>12</td>
                                        <td>&nbsp;</td>
                                    </tr>
                                </table>
                            </td>
                            <td>
                              <table border="0" class="inputform" cellpadding="0" cellspacing="1" width="100%">
                                    <tr >
                                        <th>&nbsp;</th>
                                        <th>&nbsp;</th>
                                       <th>&nbsp;</th>
                                        <th>Months</th>
                                        <th>Days</th>
                                    </tr>
                                    <tr >
                                        <th>&nbsp;</th>
                                         <th>&nbsp;</th>
                                         <th width="25%">Select All <input type="checkbox" id="15ySelectAll" onclick="enableOrDisable15YearsFields()"value="true"  style="width:25px;height:15px"/></th>
                                       <th>&nbsp;</th>
                                       <th>&nbsp;</th>
                                    </tr>
                                    <tr >
                                        <td>&nbsp;</td>
                                        <td>Makes sensible plans for future (job) </td>
                                        <td><html:checkbox styleId="84" property="dst_makes_sensible_plans_for_future" onclick="checkSelectAllBasedonOnload()" value="true"  style="width:25px;height:15px"/></td>
                                        <td>4</td>
                                        <td>24</td>
                                    </tr>
                                    <tr >
                                        <td>&nbsp;</td>
                                        <td>Follows current events </td>
                                        <td><html:checkbox styleId="85" property="dst_follows_current_events" onclick="checkSelectAllBasedonOnload()" value="1"  style="width:25px;height:15px"/></td>
                                        <td>9</td>
                                        <td>18</td>
                                    </tr>
                                    <tr >
                                        <td>&nbsp;</td>
                                        <td>Buys own clothing </td>
                                        <td><html:checkbox styleId="86" property="dst_buys_own_clothing" onclick="checkSelectAllBasedonOnload()" value="1"  style="width:25px;height:15px"/></td>
                                        <td>1 year 2m</td>
                                        <td>12</td>
                                    </tr>
                                    <tr >
                                        <td>&nbsp;</td>
                                        <td>Systematises own work </td>
                                        <td><html:checkbox styleId="87" property="dst_systematises_own_work" onclick="checkSelectAllBasedonOnload()" value="1"  style="width:25px;height:15px"/></td>
                                        <td>1 year 7m</td>
                                        <td>6</td>
                                    </tr>
                                    <tr >
                                        <td>15Y</td>
                                        <td>Purchases for others </td>
                                        <td><html:checkbox styleId="88" property="dst_purchases_for_others" onclick="checkSelectAllBasedonOnload()" value="1"  style="width:25px;height:15px"/></td>
                                        <td>2 years</td>
                                        <td>&nbsp;</td>
                                    </tr>



                                </table>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
            <table border="0" class="inputform" cellpadding="0" cellspacing="1" width="90%">
                <tr>
                    <th colspan="20"><html:button property=""  value="Back" onclick="goBack()"  styleClass="button"/>


              <html:submit  value="Add" styleId="hearButton" styleClass="button"/>


                <html:button property="" value="Reset" onclick="goReset()" styleClass="button"/></th>

            </table>
        </html:form>
    </body>
</html:html>
