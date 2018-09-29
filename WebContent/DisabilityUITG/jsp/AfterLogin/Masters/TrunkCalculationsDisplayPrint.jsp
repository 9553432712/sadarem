<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%
String personcode=(String)session.getAttribute("personcode");
String name=(String)session.getAttribute("Name");
%>
<html:html>


        <body  onload="window.print()">
        <form>
<LINK REL="stylesheet" HREF="./DisabilityUITG/css/printeformatecss.css">
      <logic:notEmpty name="trunkbean"  scope="request">
          <table  border="0" align="center"  width="100%">
               <tr><td colspan="3"  align="right"><b>ID No.&nbsp;<%=personcode%></b></td></tr>
        <tr><td colspan="3"  align="right"><b>Name.&nbsp;<%=name%></b></td></tr>


              <tr >
                  <b><td><center><font size="4">5.Trunk</center></td></b>
              </tr>

              <tr >
                  <td ><b><font size="3">5.1.Traumatic Lesions:</font></b></td>
              </tr>


              <logic:equal name="trunkbean" property="cervicalspine" value="true">

                  <tr >
                      <font size="3"> <td><b>5.1.1&nbsp;&nbsp;Cervical Spine injuries (Fracture)</font></b></td>
                  </tr>
                  <logic:equal name="trunkbean" property="compression" value="20">
                      <tr>
                          <td ><b>&nbsp;I.Compression of one or two adjacent vertebral bodies with no<br>&nbsp;&nbsp;&nbsp;&nbsp;involvement of posterior elements. No nerve root involvement,<br>&nbsp;&nbsp;&nbsp;&nbsp;moderate neck rigidity and persistent soreness</b></td>
                          <td>
                              <bean:write name="trunkbean" property="compression"/>
                          </td>
                      </tr><tr></tr>
                  </logic:equal>
                  <%--end of compressiom%---%>
                  <logic:equal name="trunkbean" property="posterior" value="true">
                      <tr><td><b>II.&nbsp;Posterior element damage with radiological evidence of moderate partial <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;dislocation/subluxation including whiplash injury</b></td></tr>

                      <logic:equal name="trunkbean" property="posterior_fusion" value="10" >
                          <tr><td> a)&nbsp;with fusion healed no permanent motor or sensory &nbsp;&nbsp;&nbsp; changes</td><td><bean:write name="trunkbean" property="posterior_fusion"/> </td></tr>
                      </logic:equal>

                      <logic:equal name="trunkbean" property="posterior_persistent" value="25">
                      <tr> <td>  b)&nbsp;Persistent pain with radiological demonstrable instability </td><td><bean:write name="trunkbean" property="posterior_persistent"/></td> </tr>
                      <tr></tr></logic:equal>

                  </logic:equal>

                  <%--end of posterior--%>

                  <logic:equal name="trunkbean" property="severe" value="true">

                      <tr><td>&nbsp;<b>III. Severe dislocation</b></td> </tr>
                      <logic:equal name="trunkbean" property="severe_fire" value="10">
                      <tr><td>a)&nbsp; Fair to good reduction with or without fusion with no residual motor or sensory involvement</td><td><bean:write name="trunkbean" property="severe_fire"/> </td> </tr>
                      <tr></tr></logic:equal>


                      <logic:equal name="trunkbean" property="severe_inadequate" value="15">
                      <tr><td> b)&nbsp; Inadequate reduction with fusion and persistent radicular pain</td><td><bean:write name="trunkbean" property="severe_inadequate"/> </td></tr>
                      <tr></tr></logic:equal>

                  </logic:equal>

              </logic:equal>
              <%--end of severe--%>

              <logic:equal name="trunkbean" property="cervical" value="true">
                  <tr></tr>
                  <tr><td>&nbsp;<b>5.1.2&nbsp;Cervical Intervertebral Disc </b></td> </tr>
                  <logic:equal name="trunkbean" property="cervical_disc" value="10">
                      <tr><td>1) &nbsp;Treated case of disc lesion with persistent pain and no </td><td>
                      <bean:write name="trunkbean" property="cervical_disc"/> </td></tr>
                  </logic:equal>

                  <logic:equal name="trunkbean" property="cervical_pain" value="15">
                      <tr><td>  2) &nbsp;Treated case with pain and instability</td><td><bean:write name="trunkbean" property="cervical_pain"/> </td></tr>
                  </logic:equal>
              </logic:equal>

              <%--end of cervical--%>


              <logic:equal name="trunkbean" property="thoracic" value="true"><tr></tr>
                  <tr><td>  <b>5.1.3&nbsp; Thoracic and Thoracolumbar &nbsp;Spine Injuries  </b></td> </tr>

                  <logic:equal name="trunkbean" property="thoracic_less" value="10">
                      <tr><td>1) &nbsp;Compression of less than 50% involving one vertebra body with no neurological manifestation  </td><td>

                      <bean:write name="trunkbean" property="thoracic_less"/> </td></tr>
                  </logic:equal>



                  <logic:equal name="trunkbean" property="thoracic_more" value="20">
                      <tr><td>2)&nbsp; Compression of more than 50% involving single vertebra or more with<br>&nbsp;&nbsp;&nbsp;&nbsp; involvement of posterior
                      elements,healed,no neurological manifestations persistent pain,fusion indicated </td><td>
                      <bean:write name="trunkbean" property="thoracic_more"/> </td></tr>
                  </logic:equal>



                  <logic:equal name="trunkbean" property="thoracic_fusion" value="15">
                      <tr><td>   3) Same as (2) with fusion ,pain only heavy use of back  </td><td>
                      <bean:write name="trunkbean" property="thoracic_fusion"/> </td></tr>
                  </logic:equal>



                  <logic:equal name="trunkbean" property="thoracic_radiologically" value="30">
                      <tr><td> 4) Radiologically demonstrable instability with fracture or fracture dislocation with persistent pain  </td><td>
                      <bean:write name="trunkbean" property="thoracic_radiologically"/> </td></tr>
                  </logic:equal>

              </logic:equal>


              <logic:equal name="trunkbean" property="fracture" value="true">
                  <tr></tr>
                  <tr><td><b>5.1.4 Lumbar and lumbosacral</b> </tr></td><tr></tr>
                  <logic:equal name="trunkbean" property="fracture_less" value="15">
                      <tr><td>   1)&nbsp; Compression of 25% or less of one or two adjacent vertebra bodies no definite pattern or neurological deficit</td>
                      <td><bean:write name="trunkbean" property="fracture_less"/> </tr></td>
                  </logic:equal>

                  <logic:equal name="trunkbean" property="fracture_more" value="30">
                      <tr><td>2)Compression of 25% with disruption of posterior elements,persistent pain and stiffness,<br>&nbsp;&nbsp;healed with or with out fusion,inability to lift more than 10 kgs</td><td><bean:write name="trunkbean" property="fracture_more"/> </td>
                  </logic:equal>

                  <logic:equal name="trunkbean" property="fracture_radiologically" value="35">
                      <tr><td> 3)&nbsp; Radiologically demonstrable instability in low lumber or lumbosacral spine with pain </td><td><bean:write name="trunkbean" property="fracture_radiologically"/> </td></tr>
                  </logic:equal>
              </logic:equal>


              <logic:equal name="trunkbean" property="disc" value="true"><tr></tr>

                  <tr><td><b>5.1.5 Lumbar and lumbosacral spine :(Disc Lesion)   </b> </tr></td>
                  <logic:equal name="trunkbean" property="disc_persistent" value="15">
                      <tr><td> 1)Treated cases with persistent pain </td><td><bean:write name="trunkbean" property="disc_persistent"/> </td></tr> <tr></tr>
                  </logic:equal>

                  <logic:equal name="trunkbean" property="disc_pain" value="20">
                      <tr><td>2)&nbsp; Treated cases with pain and instability<td><bean:write name="trunkbean" property="disc_pain"/> </td></tr> <tr></tr>
                  </logic:equal>

                  <logic:equal name="trunkbean" property="disc_diseases" value="25">
                      <tr><td> 3)Treated cases of disc diseases with pain activities or lifting<td><bean:write name="trunkbean" property="disc_diseases"/></td></tr> <tr></tr>
                  </logic:equal>

                  <logic:equal name="trunkbean" property="disc_stifness" value="30">
                      <tr><td>4)Treated cases of disc diseases with persistent pain and stiffness,aggravated by <br>&nbsp;&nbsp;heavy
                      lifting necessitating modifications of all activities requiring heavy weight lifting</td><td><bean:write name="trunkbean" property="disc_stifness"/></td></tr> <tr></tr><tr></tr>
                  </logic:equal>
              </logic:equal>

              <tr>
              <td><b>Total Traumatic&nbsp;=&nbsp;compression+posterior_fusion+posterior_persistent+<br>severe_fire+severe_inadequate+cervical_disc+cervical_pain+thoracic_less+<br>thoracic_more+thoracic_fusion+thoracic_radiologically+fracture_less+fracture_more+ <br>fracture_radiologically+disc_persistent+disc_pain+disc_diseases+disc_stifness</b></td></tr>
              <tr></tr>

              <logic:equal name="trunkbean" property="traumaticflag1" value="true">
                  <tr>  <td><b>Total traumatic</b>=<bean:write name="trunkbean" property="motortraumatic"/></td> </tr>
              </logic:equal>
              <tr><td>
              <logic:equal name="trunkbean" property="traumaticflag" value="true">
                  <b>**We are making the value of Traumatic to 100 evem it is greater than 100</b> <tr><td>
              </logic:equal>
          </table><br><br>

              <br><br>
              <%-- End of traumatic -----%>

              <%-- start of non-traumatic --%>
              <TABLE  border="0" align="center" width="100%">
              <tr>



                  <td align="center" ><font size="3"><b>5.2 NON-TRAUMATIC LESIONS</font></b></td>
              </tr>
              </table>
              <%-- **** This is scoliosis Table **** ---%>
              <TABLE  border="0" align="center" width="100%">
              <tr>

              <logic:equal name="trunkbean" property="scoliosis" value="true" >
                  <tr><td><b><font size="2">5.2.1 SCOLIOSIS</td> </tr>
                  <logic:equal name="trunkbean" property="scoliosis_measure" value="0">
                      <tr><td>5.2.1.1 Measurement of spine deformity (Scoliosis)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </td><td align="right">&nbsp;<bean:write name="trunkbean" property="scoliosis_measure"/> </td></tr>
                  </logic:equal>
                  <logic:equal name="trunkbean" property="scoliosis_measure" value="10">
                      <tr><td>5.2.1.1 Measurement of spine deformity (Scoliosis)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </td><td align="right">&nbsp;<bean:write name="trunkbean" property="scoliosis_measure"/> </td></tr>
                  </logic:equal>
                  <logic:equal name="trunkbean" property="scoliosis_measure" value="20">
                      <tr><td>5.2.1.1 Measurement of spine deformity (Scoliosis)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </td><td align="right">&nbsp; <bean:write name="trunkbean" property="scoliosis_measure"/> </td></tr>
                  </logic:equal>
                  <logic:equal name="trunkbean" property="scoliosis_measure" value="30">
                      <tr><td>5.2.1.1 Measurement of spine deformity (Scoliosis) &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td><td align="right">&nbsp;<bean:write name="trunkbean" property="scoliosis_measure"/> </td></tr>
                  </logic:equal>


                  <logic:equal name="trunkbean" property="scoliosis_torso" value="4">
                      <tr><td> 5.2.1.2 Torso imbalance (Scoliosis)</td><td align="right"><bean:write name="trunkbean" property="scoliosis_torso"/> </td></tr>
                  </logic:equal>
                  <logic:equal name="trunkbean" property="scoliosis_torso" value="8">
                      <tr><td> 5.2.1.2 Torso imbalance (Scoliosis)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td><td align="right">  <bean:write name="trunkbean" property="scoliosis_torso"/> </td></tr>
                  </logic:equal>
                  <logic:equal name="trunkbean" property="scoliosis_torso" value="16">
                      <tr><td> 5.2.1.2 Torso imbalance (Scoliosis)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td><td><bean:write name="trunkbean" property="scoliosis_torso"/> </td></tr>
                  </logic:equal>
                  <logic:equal name="trunkbean" property="scoliosis_torso" value="32">
                      <tr><td> 5.2.1.2 Torso imbalance (Scoliosis)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td><td align="right"><bean:write name="trunkbean" property="scoliosis_torso"/>  </td></tr>
                  </logic:equal>

              </logic:equal>

              <logic:equal name="trunkbean" property="scoliosis" value="true" >
                  <tr><td><b>Total Scoliosis=Measure+Torso</b> </td></tr>
                  <tr><td><b>Total Scoliosis</b>
                  <logic:equal name="trunkbean" property="scoliosis_measure" value="0">
                      =<bean:write name="trunkbean" property="scoliosis_measure"/>
                  </logic:equal>
                  <logic:equal name="trunkbean" property="scoliosis_measure" value="10">
                      =<bean:write name="trunkbean" property="scoliosis_measure"/>
                  </logic:equal>
                  <logic:equal name="trunkbean" property="scoliosis_measure" value="20">
                      =<bean:write name="trunkbean" property="scoliosis_measure"/>
                  </logic:equal>
                  <logic:equal name="trunkbean" property="scoliosis_measure" value="30">
                      <td>= <bean:write name="trunkbean" property="scoliosis_measure"/>
                  </logic:equal>
                      <logic:equal name="trunkbean" property="scoliosis_torso" value="4">
                          +<bean:write name="trunkbean" property="scoliosis_torso"/>
                      </logic:equal>
                      <logic:equal name="trunkbean" property="scoliosis_torso" value="8">
                          +<bean:write name="trunkbean" property="scoliosis_torso"/>
                      </logic:equal>
                      <logic:equal name="trunkbean" property="scoliosis_torso" value="16">
                          +<bean:write name="trunkbean" property="scoliosis_torso"/>
                      </logic:equal>
                      <logic:equal name="trunkbean" property="scoliosis_torso" value="32">
                          +<bean:write name="trunkbean" property="scoliosis_torso"/>
                      </logic:equal>&nbsp;&nbsp;=<bean:write name="trunkbean" property="totalscoliosis"/>
                  </td></tr>
              </logic:equal>


              <br>

              <%-- **** This is kyphosis Table **** ---%>



              <logic:equal name="trunkbean" property="kyphosis" value="true" >
              <tr> <td><b><font size="2">5.2.2 KYPHOSIS</font></b></td> </tr>
              <tr> <td>
              <logic:equal name="trunkbean" property="kyphosis_measure" value="0">
                  5.2.2.1 Measurement of spine deformity (Kyphosis)</td><td align="right"> <bean:write name="trunkbean" property="kyphosis_measure"/> </td>
              </logic:equal>
              <logic:equal name="trunkbean" property="kyphosis_measure" value="10">
                  5.2.2.1 Measurement of spine deformity (Kyphosis)</td><td align="right"> <bean:write name="trunkbean" property="kyphosis_measure"/>  </td>
              </logic:equal>
              <logic:equal name="trunkbean" property="kyphosis_measure" value="20">
                  5.2.2.1 Measurement of spine deformity (Kyphosis)</td><td align="right"><bean:write name="trunkbean" property="kyphosis_measure"/>  </td>
              </logic:equal>
              <logic:equal name="trunkbean" property="kyphosis_measure" value="30">
                  5.2.2.1 Measurement of spine deformity (Kyphosis)</td><td align="right"><bean:write name="trunkbean" property="kyphosis_measure"/>  </td>
              </logic:equal>

          </td></tr>
              <tr><td>  <logic:equal name="trunkbean" property="kyphosis_torso" value="4">
                  5.2.2.2 Torso imbalance (Kyphosis)
                  Deviation of plumb line (in cm in front of ankle)  </td><td align="right"> <bean:write name="trunkbean" property="kyphosis_torso"/> </td>
              </logic:equal>
              <logic:equal name="trunkbean" property="kyphosis_torso" value="8">
                  5.2.2.2 Torso imbalance (Kyphosis)
                  Deviation of plumb line (in cm in front of ankle)  </td><td align="right"> <bean:write name="trunkbean" property="kyphosis_torso"/> </td>
              </logic:equal>
              <logic:equal name="trunkbean" property="kyphosis_torso" value="16">
                  5.2.2.2 Torso imbalance (Kyphosis)
                  Deviation of plumb line (in cm in front of ankle)</td><td align="right">  <bean:write name="trunkbean" property="kyphosis_torso"/> </td>
              </logic:equal>
              <logic:equal name="trunkbean" property="kyphosis_torso" value="32">
                  5.2.2.2 Torso imbalance (Kyphosis)
                  Deviation of plumb line (in cm in front of ankle)</td><td align="right">   <bean:write name="trunkbean" property="kyphosis_torso"/> </td>
              </logic:equal>
          </logic:equal>
          </td></tr>
          <logic:equal name="trunkbean" property="kyphosis" value="true">
              <tr><td><b>Total kyphosis=Measure+Torso </tr></td>
              <tr><td> Total kyphosis= </b><logic:equal name="trunkbean" property="kyphosis_measure" value="0">
                  <bean:write name="trunkbean" property="kyphosis_measure"/>
              </logic:equal>
              <logic:equal name="trunkbean" property="kyphosis_measure" value="10">
                  <bean:write name="trunkbean" property="kyphosis_measure"/>
              </logic:equal>
              <logic:equal name="trunkbean" property="kyphosis_measure" value="20">
                  <bean:write name="trunkbean" property="kyphosis_measure"/>
              </logic:equal>
              <logic:equal name="trunkbean" property="kyphosis_measure" value="30">
                  <bean:write name="trunkbean" property="kyphosis_measure"/>
              </logic:equal>

              <logic:equal name="trunkbean" property="kyphosis_torso" value="4">
                  +<bean:write name="trunkbean" property="kyphosis_torso"/>
              </logic:equal>
              <logic:equal name="trunkbean" property="kyphosis_torso" value="8">
                  +<bean:write name="trunkbean" property="kyphosis_torso"/>
              </logic:equal>
              <logic:equal name="trunkbean" property="kyphosis_torso" value="16">
                  +<bean:write name="trunkbean" property="kyphosis_torso"/>
              </logic:equal>
              <logic:equal name="trunkbean" property="kyphosis_torso" value="32">
                  +<bean:write name="trunkbean" property="kyphosis_torso"/>
              </logic:equal>
              =<bean:write name="trunkbean" property="totalkyphosis"/></td></tr>
          </logic:equal>
          </table>

          <br>
          <%-- **** This is Head tilt Table **** ---%>

          <TABLE  border="0" align="center"   width="100%">
          <tr>





          <logic:equal name="trunkbean" property="head" value="4">
              <td > <b>   5.2.3 Head tilt over C7 spine:</b> </td>
              <td align="right">  <bean:write name="trunkbean" property="head"/> </td>
          </logic:equal>
          <logic:equal name="trunkbean" property="head" value="10">
              <td> <b>   5.2.3 Head tilt over C7 spine:</b> </td>
              <td align="right"> <bean:write name="trunkbean" property="head"/> </td>
          </logic:equal> </td> </tr>


          </table>
          <br>

          <TABLE  border="0" align="center"  width="100%">

          <tr><td>      <b>5.2.4 Cardio-pulmonary test</b> </td></tr>
          <tr><td>5.2.4.1 Chest Expansion    </td>

          <td align="right">   <logic:equal name="trunkbean" property="cardio_chest" value="0">
              <bean:write name="trunkbean" property="cardio_chest"/>
          </logic:equal>
          <logic:equal name="trunkbean" property="cardio_chest" value="5">
              <bean:write name="trunkbean" property="cardio_chest"/>
          </logic:equal>
          <logic:equal name="trunkbean" property="cardio_chest" value="10">
              <bean:write name="trunkbean" property="cardio_chest"/>
          </logic:equal>
          <logic:equal name="trunkbean" property="cardio_chest" value="15">
              <bean:write name="trunkbean" property="cardio_chest"/>
          </logic:equal>
          <logic:equal name="trunkbean" property="cardio_chest" value="25">
              <bean:write name="trunkbean" property="cardio_chest"/>
          </logic:equal>  </td></tr>

          <tr><td>        <br>5.2.4.2 Counting in one breath  </td>
          <td align="right">     <logic:equal name="trunkbean" property="cardio_counting" value="0">
              <bean:write name="trunkbean" property="cardio_counting"/>
          </logic:equal>
          <logic:equal name="trunkbean" property="cardio_counting" value="5">
              <bean:write name="trunkbean" property="cardio_counting"/>
          </logic:equal>
          <logic:equal name="trunkbean" property="cardio_counting" value="10">
              <bean:write name="trunkbean" property="cardio_counting"/>
          </logic:equal>
          <logic:equal name="trunkbean" property="cardio_counting" value="15">
              <bean:write name="trunkbean" property="cardio_counting"/>
          </logic:equal>
          <logic:equal name="trunkbean" property="cardio_counting" value="20">
              <bean:write name="trunkbean" property="cardio_counting"/>
          </logic:equal>
          <logic:equal name="trunkbean" property="cardio_counting" value="25">
              <bean:write name="trunkbean" property="cardio_counting"/>
          </logic:equal>  </td></tr>


          <tr><td>     <br><b>5.2.4.3 Associated problem </b></tr> </td>




          <logic:equal name="trunkbean" property="cardio_associatepain" value="4">
              <tr><td>          A)Pain  </td>  <td align="right">     <bean:write name="trunkbean" property="cardio_associatepain"/>
          </logic:equal>

          <logic:equal name="trunkbean" property="cardio_associatepain" value="6">
              <tr><td>          A)Pain  </td>   <td align="right">      <bean:write name="trunkbean" property="cardio_associatepain"/>
          </logic:equal>

          <logic:equal name="trunkbean" property="cardio_associatepain" value="10">
              <tr><td>          A)Pain  </td>  <td td align="right">     <bean:write name="trunkbean" property="cardio_associatepain"/>
          </logic:equal>   </td></tr>



          <logic:equal name="trunkbean" property="cardio_associatecosmetic" value="0">
              <tr><td>            B)Cosmetic appearance  </td>  <td align="right">     <bean:write name="trunkbean" property="cardio_associatecosmetic"/>
          </logic:equal>
          <logic:equal name="trunkbean" property="cardio_associatecosmetic" value="2">
              <tr><td>            B)Cosmetic appearance  </td><td align="right">  <bean:write name="trunkbean" property="cardio_associatecosmetic"/>
          </logic:equal>
          <logic:equal name="trunkbean" property="cardio_associatecosmetic" value="4">
              <tr><td>            B)Cosmetic appearance  </td> <td align="right">  <bean:write name="trunkbean" property="cardio_associatecosmetic"/>
          </logic:equal>  </td></tr>



          <logic:notEmpty name="trunkbean" property="cardio_associateleg">
              <tr><td>       C)Leg length discrepancy  </td> <td td align="right"> <bean:write name="trunkbean" property="cardio_associateleg"/>
          </logic:notEmpty>
          </td> </tr>
          <tr><td><b>Extra=Chest Expansion+Counting in one breath+Associated problem(pain)+Associated problem(Cosmetic)+((Leg length discrepancy-0.5)*8)+head </b></td></tr>
          <tr><td><b>Extra=</b> <logic:equal name="trunkbean" property="cardio_chest" value="0">
              <bean:write name="trunkbean" property="cardio_chest"/>
          </logic:equal>
          <logic:equal name="trunkbean" property="cardio_chest" value="5">
              <bean:write name="trunkbean" property="cardio_chest"/>
          </logic:equal>
          <logic:equal name="trunkbean" property="cardio_chest" value="10">
              <bean:write name="trunkbean" property="cardio_chest"/>
          </logic:equal>
          <logic:equal name="trunkbean" property="cardio_chest" value="15">
              <bean:write name="trunkbean" property="cardio_chest"/>
          </logic:equal>
          <logic:equal name="trunkbean" property="cardio_chest" value="25">
              <bean:write name="trunkbean" property="cardio_chest"/>
          </logic:equal>

          <logic:equal name="trunkbean" property="cardio_counting" value="0">
              +<bean:write name="trunkbean" property="cardio_counting"/>
          </logic:equal>
          <logic:equal name="trunkbean" property="cardio_counting" value="5">
              +<bean:write name="trunkbean" property="cardio_counting"/>
          </logic:equal>
          <logic:equal name="trunkbean" property="cardio_counting" value="10">
              +<bean:write name="trunkbean" property="cardio_counting"/>
          </logic:equal>
          <logic:equal name="trunkbean" property="cardio_counting" value="15">
              +<bean:write name="trunkbean" property="cardio_counting"/>
          </logic:equal>
          <logic:equal name="trunkbean" property="cardio_counting" value="20">
              +<bean:write name="trunkbean" property="cardio_counting"/>
          </logic:equal>
          <logic:equal name="trunkbean" property="cardio_counting" value="25">
              +<bean:write name="trunkbean" property="cardio_counting"/>
          </logic:equal>

          <logic:equal name="trunkbean" property="cardio_associatepain" value="4">
              +<bean:write name="trunkbean" property="cardio_associatepain"/>
          </logic:equal>

          <logic:equal name="trunkbean" property="cardio_associatepain" value="6">
              +<bean:write name="trunkbean" property="cardio_associatepain"/>
          </logic:equal>

          <logic:equal name="trunkbean" property="cardio_associatepain" value="10">
              +<bean:write name="trunkbean" property="cardio_associatepain"/>
          </logic:equal>

          <logic:equal name="trunkbean" property="cardio_associatecosmetic" value="0">
              +<bean:write name="trunkbean" property="cardio_associatecosmetic"/>
          </logic:equal>
          <logic:equal name="trunkbean" property="cardio_associatecosmetic" value="2">
              +<bean:write name="trunkbean" property="cardio_associatecosmetic"/>
          </logic:equal>
          <logic:equal name="trunkbean" property="cardio_associatecosmetic" value="4">
              +<bean:write name="trunkbean" property="cardio_associatecosmetic"/>
          </logic:equal>

          <logic:notEmpty name="trunkbean" property="cardio_leg">
              +<bean:write name="trunkbean" property="cardio_leg"/>
          </logic:notEmpty>

          <logic:equal name="trunkbean" property="head" value="4">
              +<bean:write name="trunkbean" property="head"/>
          </logic:equal>
          <logic:equal name="trunkbean" property="head" value="10">
              +<bean:write name="trunkbean" property="head"/>
          </logic:equal>
          =<bean:write name="trunkbean" property="extra"/>  </td></tr>

          <tr><td><b>Scoliosisextra(totalscoliosis,extra)&nbsp;&nbsp;&nbsp;=&nbsp;&nbsp;&nbsp;a+(90-a)/90*b where a is greater(totalscoliosis,extra)
              where b is lesser(totalscoliosis,extra)</b>
          </td></tr>&nbsp;&nbsp;
          <tr><td><logic:notEqual  name="trunkbean" property="scoliosisextra" value="0"><logic:notEqual  name="trunkbean" property="scoliosis_extra" value="null">
              <bean:write name="trunkbean" property="scoliosis_extra"/>
          </logic:notEqual>&nbsp;&nbsp;=&nbsp;&nbsp;
              <bean:write name="trunkbean" property="scoliosisextraformat"/></logic:notEqual>
              <logic:equal  name="trunkbean" property="scoliosisextra" value="0">
              scoliosisextra is 0 </logic:equal>
          </td></tr>


          <tr><td><b>Khyphosisextra&nbsp;&nbsp;&nbsp;=&nbsp;&nbsp;&nbsp;a+(90-a)/90*b where a is greater(totalKhyphosis,extra)
              where b is lesser(totalKhyphosis,extra)</b>
          </td></tr><tr><td><logic:notEqual  name="trunkbean" property="kyphosisextra" value="0"><logic:notEqual  name="trunkbean" property="kyphosis_extra" value="null">
              <bean:write name="trunkbean" property="kyphosis_extra"/>
          </logic:notEqual>&nbsp;&nbsp;=&nbsp;&nbsp;
              <bean:write name="trunkbean" property="kyphosisextraformat"/> </logic:notEqual>
              <logic:equal  name="trunkbean" property="kyphosisextra" value="0">
              kyphosisextra is 0 </logic:equal>
          </td></tr>



          <tr><td><b> Total Nontraumatic </b>&nbsp;&nbsp;&nbsp;=&nbsp;&nbsp;&nbsp;</b>a+(90-a)/90*b where a is greater(scoliosisextra,kyphosisextra)
              where b is lesser(scoliosisextra,kyphosisextra)
          </td></tr> <tr><td><logic:notEqual  name="trunkbean" property="non_traumatic" value="null">
              <bean:write name="trunkbean" property="non_traumatic"/>
          </logic:notEqual>&nbsp;&nbsp;=&nbsp;&nbsp;
          <bean:write name="trunkbean" property="nontraumaticformat"/></td></tr>


          <tr><td>
          <tr></tr><tr></tr>
          <tr><td><b>Trunk Total value&nbsp;&nbsp;&nbsp;&nbsp;=&nbsp;&nbsp;&nbsp;&nbsp;a+(90-a)/90*b where a is greater(Traumatic,Non-Traumatic)
              where b is lesser(Traumatic,Non-Traumatic)</b>
          </td></tr>
          <tr><td><logic:notEqual  name="trunkbean" property="totaltrunk" value="null">
              <bean:write name="trunkbean" property="totaltrunk"/>
          </logic:notEqual>&nbsp;&nbsp;=&nbsp;&nbsp;
          <b><bean:write name="trunkbean" property="traumaticformat"/></b></td></tr>


      </logic:notEmpty>

        </form>
</body>
</html:html>


 