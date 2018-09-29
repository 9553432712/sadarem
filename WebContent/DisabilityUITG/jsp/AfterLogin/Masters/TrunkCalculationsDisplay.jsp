<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%
String personcode=(String)session.getAttribute("personcode");
String name=(String)session.getAttribute("Name");
%>
<html:html>

     <head>

<script language="javascript" >
    function goBack()
{
            document.forms[0].action="showCalculationsAction.do";
            document.forms[0].submit();
}
</script>
          <LINK REL="stylesheet" HREF="./DisabilityUITG/css/printeformatecss.css">
    </head>

    <body><br>
<form>

      <logic:notEmpty name="trunkbean"  scope="request">
          <table  border="1" align="center"  width="85%" class="innerTable">
               <tr><td colspan="3"  align="right" class="labelBlue">ID No.&nbsp;<%=personcode%></td></tr>
        <tr><td colspan="3"  align="right" class="labelBlue">Name.&nbsp;<%=name%></td></tr>
       </table>
       <table  border="1" align="center"  width="85%" class="innerTable1">
              <tr>
                  <td class="heading1" colspan="3"><center>5.Trunk</center></td>

              </tr>

              <tr >
                  <td class="labelBlue" colspan="3">5.1.Traumatic Lesions:</td>
              </tr>


              <logic:equal name="trunkbean" property="cervicalspine" value="true">

                  <tr >
                      <td class="labelBlue" colspan="3">5.1.1&nbsp;&nbsp;Cervical Spine injuries (Fracture)</td>
                  </tr>
                  <logic:equal name="trunkbean" property="compression" value="20">
                      <tr>
                          <td class="label">&nbsp;I.Compression of one or two adjacent vertebral bodies with no involvement of posterior
                              elements. No nerve root involvement, moderate neck rigidity and persistent soreness</td>
                          <td class="label">
                              <bean:write name="trunkbean" property="compression"/>
                          </td>
                      </tr><tr></tr>
                  </logic:equal>
                  <%--end of compressiom%---%>
                  <logic:equal name="trunkbean" property="posterior" value="true">
                      <tr><td class="label" colspan="3">II.&nbsp;Posterior element damage with radiological evidence of moderate partial
                              dislocation/subluxation including whiplash injury</td></tr>

                      <logic:equal name="trunkbean" property="posterior_fusion" value="10" >
                          <tr><td class="label"> a)&nbsp;with fusion healed no permanent motor or sensory changes</td>
                              <td class="label"><bean:write name="trunkbean" property="posterior_fusion"/> </td></tr>
                      </logic:equal>

                      <logic:equal name="trunkbean" property="posterior_persistent" value="25">
                      <tr> <td class="label">  b)&nbsp;Persistent pain with radiological demonstrable instability </td>
                          <td class="label"><bean:write name="trunkbean" property="posterior_persistent"/></td> </tr>
                      <tr></tr></logic:equal>

                  </logic:equal>

                  <%--end of posterior--%>

                  <logic:equal name="trunkbean" property="severe" value="true">

                      <tr><td class="label" colspan="2">&nbsp;III. Severe dislocation</td> </tr>
                      <logic:equal name="trunkbean" property="severe_fire" value="10">
                      <tr>
                      <td class="label">a)&nbsp; Fair to good reduction with or without fusion with no residual motor or sensory involvement</td>
                          <td class="label"><bean:write name="trunkbean" property="severe_fire"/> </td> </tr>
                      <tr></tr></logic:equal>


                      <logic:equal name="trunkbean" property="severe_inadequate" value="15">
                      <tr><td class="label">&nbsp; Inadequate reduction with fusion and persistent radicular pain</td>
                          <td class="label"><bean:write name="trunkbean" property="severe_inadequate"/> </td></tr>
                      <tr></tr></logic:equal>

                  </logic:equal>

              </logic:equal>
              <%--end of severe--%>

              <logic:equal name="trunkbean" property="cervical" value="true">
                  <tr></tr>
                  <tr><td class="labelBlue" colspan="3">&nbsp;5.1.2&nbsp;Cervical Intervertebral Disc</td> </tr>
                  <logic:equal name="trunkbean" property="cervical_disc" value="10">
                      <tr><td class="label">1) &nbsp;Treated case of disc lesion with persistent pain and no </td><td class="label">
                      <bean:write name="trunkbean" property="cervical_disc"/> </td></tr>
                  </logic:equal>

                  <logic:equal name="trunkbean" property="cervical_pain" value="15">
                      <tr><td class="label">  2) &nbsp;Treated case with pain and instability</td><td class="label">
                              <bean:write name="trunkbean" property="cervical_pain"/> </td></tr>
                  </logic:equal>
              </logic:equal>

              <%--end of cervical--%>


              <logic:equal name="trunkbean" property="thoracic" value="true"><tr></tr>
                  <tr><td class="labelBlue" colspan="3">5.1.3&nbsp; Thoracic and Thoracolumbar &nbsp;Spine Injuries</td> </tr>

                  <logic:equal name="trunkbean" property="thoracic_less" value="10">
               <tr><td class="label">1) &nbsp;Compression of less than 50% involving one vertebral body with no neurological manifestation</td>
                       <td class="label"><bean:write name="trunkbean" property="thoracic_less"/> </td></tr>
                  </logic:equal>



                  <logic:equal name="trunkbean" property="thoracic_more" value="20">
                      <tr><td class="label">2)&nbsp; Compression of more than 50% involving single vertebra or more with involvement of
                              posterior  elements,healed,no neurological manifestations persistent pain,fusion indicated </td>
                          <td class="label"><bean:write name="trunkbean" property="thoracic_more"/> </td></tr>
                  </logic:equal>



                  <logic:equal name="trunkbean" property="thoracic_fusion" value="15">
                      <tr><td class="label">   3) Same as (2) with fusion ,pain only heavy use of back  </td><td class="label">
                      <bean:write name="trunkbean" property="thoracic_fusion"/> </td></tr>
                  </logic:equal>



                  <logic:equal name="trunkbean" property="thoracic_radiologically" value="30">
                      <tr><td class="label"> 4) Radiologically demonstrable instability with fracture or fracture dislocation with persistent
                              pain  </td>
                          <td class="label"><bean:write name="trunkbean" property="thoracic_radiologically"/> </td></tr>
                  </logic:equal>

              </logic:equal>


              <logic:equal name="trunkbean" property="fracture" value="true">
                  <tr></tr>
                  <tr><td class="labelBlue">5.1.4 Lumbar and lumbosacral</td></tr><tr></tr>
                  <logic:equal name="trunkbean" property="fracture_less" value="15">
                      <tr><td class="label">   1)&nbsp; Compression of 25% or less of one or two adjacent vertebra bodies no definite pattern
                              or neurological deficit</td>
                      <td class="label"><bean:write name="trunkbean" property="fracture_less"/> </td></tr>
                  </logic:equal>

                  <logic:equal name="trunkbean" property="fracture_more" value="30">
                      <tr><td class="label">2)Compression of 25% with disruption of posterior elements,persistent pain and stiffness,
                              healed with or with out fusion,inability to lift more than 10 kgs</td>
                          <td class="label"><bean:write name="trunkbean" property="fracture_more"/> </td></tr>
                  </logic:equal>

                  <logic:equal name="trunkbean" property="fracture_radiologically" value="35">
                      <tr><td class="label"> 3)&nbsp; Radiologically demonstrable instability in low lumber or lumbosacral spine with pain </td>
                          <td class="label"><bean:write name="trunkbean" property="fracture_radiologically"/> </td></tr>
                  </logic:equal>
              </logic:equal>


              <logic:equal name="trunkbean" property="disc" value="true"><tr></tr>

                  <tr><td class="labelBlue">5.1.5 Lumbar and lumbosacral spine :(Disc Lesion)</td></tr>
                  <logic:equal name="trunkbean" property="disc_persistent" value="15">
                      <tr><td class="label"> 1)Treated cases with persistent pain </td>
                          <td class="label"><bean:write name="trunkbean" property="disc_persistent"/> </td></tr>
                  </logic:equal>

                  <logic:equal name="trunkbean" property="disc_pain" value="20">
                      <tr><td class="label">2)&nbsp; Treated cases with pain and instability</td>
                          <td class="label"><bean:write name="trunkbean" property="disc_pain"/> </td></tr>

                  </logic:equal>

                  <logic:equal name="trunkbean" property="disc_diseases" value="25">
                      <tr><td class="label"> 3)Treated cases of disc diseases with pain activities or lifting</td>
                          <td class="label"><bean:write name="trunkbean" property="disc_diseases"/></td></tr>

                  </logic:equal>

                  <logic:equal name="trunkbean" property="disc_stifness" value="30">
                      <tr><td class="label">4)Treated cases of disc diseases with persistent pain and stiffness,aggravated by
                              heavy lifting necessitating modifications of all activities requiring heavy weight lifting</td>
                          <td class="label"><bean:write name="trunkbean" property="disc_stifness"/></td></tr>
                  </logic:equal>
              </logic:equal>

              <tr>
              <td class="label" colspan="3">Total Traumatic&nbsp;=&nbsp;compression+posterior_fusion+posterior_persistent+<br>
                  severe_fire+severe_inadequate+cervical_disc+cervical_pain+thoracic_less+<br>
                  thoracic_more+thoracic_fusion+thoracic_radiologically+fracture_less+fracture_more+ <br>
                  fracture_radiologically+disc_persistent+disc_pain+disc_diseases+disc_stifness</td></tr>
              <tr></tr>

              <logic:equal name="trunkbean" property="traumaticflag1" value="true">
                  <tr>


                      <td class="label" colspan="3">Total traumatic=<bean:write name="trunkbean" property="motortraumatic"/></td> </tr>
              </logic:equal>
              <tr><td class="label">
              <logic:equal name="trunkbean" property="traumaticflag" value="true">
                  **We are making the value of Traumatic to 100 evem it is greater than 100 <tr><td>
              </logic:equal>
          </table>


              <%-- End of traumatic -----%>

              <%-- start of non-traumatic --%>
              <table  border="1" align="center" width="85%" class="innerTable1">

              <tr>
              <td align="center" class="heading1">5.2 NON-TRAUMATIC LESIONS</td>

              </tr>
              </table>
              <%-- **** This is scoliosis Table **** ---%>
              <table align="center" width="85%" class="innerTable1">
              <tr><td class="label">


              <logic:equal name="trunkbean" property="scoliosis" value="true" >
                  <tr><td class="label">5.2.1 SCOLIOSIS</td> </tr>

                  <logic:equal name="trunkbean" property="scoliosis_measure" value="0">
                      <tr><td class="label">5.2.1.1 Measurement of spine deformity (Scoliosis)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </td><td align="right" class="label">&nbsp;<bean:write name="trunkbean" property="scoliosis_measure"/> </td></tr>
                  </logic:equal>
                  <logic:equal name="trunkbean" property="scoliosis_measure" value="10">
                      <tr><td class="label">5.2.1.1 Measurement of spine deformity (Scoliosis)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </td><td align="right" class="label">&nbsp;<bean:write name="trunkbean" property="scoliosis_measure"/> </td></tr>
                  </logic:equal>
                  <logic:equal name="trunkbean" property="scoliosis_measure" value="20">
                      <tr><td class="label">5.2.1.1 Measurement of spine deformity (Scoliosis)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </td><td align="right" class="label">&nbsp; <bean:write name="trunkbean" property="scoliosis_measure"/> </td></tr>
                  </logic:equal>
                  <logic:equal name="trunkbean" property="scoliosis_measure" value="30">
                      <tr><td class="label">5.2.1.1 Measurement of spine deformity (Scoliosis) &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td><td align="right" class="label">&nbsp;<bean:write name="trunkbean" property="scoliosis_measure"/> </td></tr>
                  </logic:equal>


                  <logic:equal name="trunkbean" property="scoliosis_torso" value="4">
                      <tr><td class="label"> 5.2.1.2 Torso imbalance (Scoliosis)</td><td align="right" class="label"><bean:write name="trunkbean" property="scoliosis_torso"/> </td></tr>
                  </logic:equal>
                  <logic:equal name="trunkbean" property="scoliosis_torso" value="8">
                      <tr><td class="label"> 5.2.1.2 Torso imbalance (Scoliosis)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td><td align="right" class="label"> <bean:write name="trunkbean" property="scoliosis_torso"/> </td></tr>
                  </logic:equal>
                  <logic:equal name="trunkbean" property="scoliosis_torso" value="16">
                      <tr><td class="label"> 5.2.1.2 Torso imbalance (Scoliosis)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td><td class="label"><bean:write name="trunkbean" property="scoliosis_torso"/> </td></tr>
                  </logic:equal>
                  <logic:equal name="trunkbean" property="scoliosis_torso" value="32">
                      <tr><td class="label"> 5.2.1.2 Torso imbalance (Scoliosis)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td><td align="right" class="label"><bean:write name="trunkbean" property="scoliosis_torso"/>  </td></tr>
                  </logic:equal>

              </logic:equal>

              <logic:equal name="trunkbean" property="scoliosis" value="true" >
                  <tr><td class="label">Total Scoliosis=Measure+Torso</td></tr>
                  <tr><td class="label">Total Scoliosis
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
                      <td class="label">= <bean:write name="trunkbean" property="scoliosis_measure"/>
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



              <%-- **** This is kyphosis Table **** ---%>



              <logic:equal name="trunkbean" property="kyphosis" value="true" >
              <tr> <td class="label">5.2.2 KYPHOSIS</td> </tr>
              <tr> <td class="label">
              <logic:equal name="trunkbean" property="kyphosis_measure" value="0">
                  5.2.2.1 Measurement of spine deformity (Kyphosis)</td><td align="right" class="label"> <bean:write name="trunkbean" property="kyphosis_measure"/> </td>
              </logic:equal>

              <logic:equal name="trunkbean" property="kyphosis_measure" value="10">
                  5.2.2.1 Measurement of spine deformity (Kyphosis)</td><td align="right" class="label"> <bean:write name="trunkbean" property="kyphosis_measure"/> </td>
              </logic:equal>
              <logic:equal name="trunkbean" property="kyphosis_measure" value="20">
                  5.2.2.1 Measurement of spine deformity (Kyphosis)</td><td align="right" class="label"><bean:write name="trunkbean" property="kyphosis_measure"/>  </td>
              </logic:equal>
              <logic:equal name="trunkbean" property="kyphosis_measure" value="30">
                  5.2.2.1 Measurement of spine deformity (Kyphosis)</td><td align="right" class="label"><bean:write name="trunkbean" property="kyphosis_measure"/>  </td>
              </logic:equal>


              <tr><td class="label">  <logic:equal name="trunkbean" property="kyphosis_torso" value="4">

                  5.2.2.2 Torso imbalance (Kyphosis)
                  Deviation of plumb line (in cm in front of ankle)  </td><td align="right"> <bean:write name="trunkbean" property="kyphosis_torso"/> </td>
              </logic:equal>
            <logic:equal name="trunkbean" property="kyphosis_torso" value="8">
                  5.2.2.2 Torso imbalance (Kyphosis)
                  Deviation of plumb line (in cm in front of ankle)</td><td align="right"> <bean:write name="trunkbean" property="kyphosis_torso"/> </td>
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
              <tr><td class="label">Total kyphosis=Measure+Torso </td></tr>
              <tr><td class="label"> Total kyphosis=<logic:equal name="trunkbean" property="kyphosis_measure" value="0">
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
          </td></tr>
          </table>


          <%-- **** This is Head tilt Table **** ---%>

          <table align="center"   width="85%" class="innerTable1">

          <tr>
          <td class="label">

          <logic:equal name="trunkbean" property="head" value="4">
              <td class="label">5.2.3 Head tilt over C7 spine:</td>
              <td align="right" class="label">  <bean:write name="trunkbean" property="head"/> </td>

          </logic:equal>
          <logic:equal name="trunkbean" property="head" value="10">
              <td class="label">5.2.3 Head tilt over C7 spine:</td>
              <td align="right" class="label"> <bean:write name="trunkbean" property="head"/> </td>
          </logic:equal> </td> </tr>


          </table>


          <table border="1" align="center"  width="85%" class="innerTable1">


          <tr><td class="labelBlue" colspan="3">5.2.4 Cardio-pulmonary test</td></tr>
          <tr><td class="label">5.2.4.1 Chest Expansion    </td>

          <td align="right" class="label"><logic:equal name="trunkbean" property="cardio_chest" value="0">
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

          <tr><td class="label">5.2.4.2 Counting in one breath  </td>
          <td align="right" class="label">     <logic:equal name="trunkbean" property="cardio_counting" value="0">
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


          <tr><td class="label" colspan="3">5.2.4.3 Associated problem </td></tr>




          <logic:equal name="trunkbean" property="cardio_associatepain" value="4">
              <tr><td class="label">A)Pain  </td>  <td align="right" class="label"><bean:write name="trunkbean" property="cardio_associatepain"/>
          </logic:equal>

          <logic:equal name="trunkbean" property="cardio_associatepain" value="6">
              <tr><td class="label">A)Pain</td><td align="right" class="label">      <bean:write name="trunkbean" property="cardio_associatepain"/>
          </logic:equal>

          <logic:equal name="trunkbean" property="cardio_associatepain" value="10">
              <tr><td class="label">A)Pain </td><td align="right" class="label">     <bean:write name="trunkbean" property="cardio_associatepain"/>
          </logic:equal>   </td></tr>



          <logic:equal name="trunkbean" property="cardio_associatecosmetic" value="0">
              <tr><td class="label">            B)Cosmetic appearance  </td>  <td align="right" class="label">     <bean:write name="trunkbean" property="cardio_associatecosmetic"/>
          </logic:equal>
          <logic:equal name="trunkbean" property="cardio_associatecosmetic" value="2">
              <tr><td class="label">            B)Cosmetic appearance  </td><td align="right" class="label">  <bean:write name="trunkbean" property="cardio_associatecosmetic"/>
          </logic:equal>
          <logic:equal name="trunkbean" property="cardio_associatecosmetic" value="4">
              <tr><td class="label">            B)Cosmetic appearance  </td> <td align="right" class="label">  <bean:write name="trunkbean" property="cardio_associatecosmetic"/>
          </logic:equal>  </td></tr>



          <logic:notEmpty name="trunkbean" property="cardio_associateleg">
              <tr><td class="label"> C)Leg length discrepancy </td><td align="right" class="label"> <bean:write name="trunkbean" property="cardio_associateleg"/>
          </logic:notEmpty>
          </td> </tr>
          <tr><td class="label" colspan="3">Extra=Chest Expansion+Counting in one breath+Associated problem(pain)+Associated problem(Cosmetic)+((Leg length discrepancy-0.5)*8)+head </td></tr>
          <tr><td class="label" colspan="3">Extra= <logic:equal name="trunkbean" property="cardio_chest" value="0">
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

          <tr><td class="label" colspan="3">Scoliosisextra(totalscoliosis,extra)&nbsp;&nbsp;&nbsp;=&nbsp;&nbsp;&nbsp;a+(90-a)/90*b where a is greater(totalscoliosis,extra)
              where b is lesser(totalscoliosis,extra)
          </td></tr>&nbsp;&nbsp;
          <tr><td class="label" colspan="3"><logic:notEqual  name="trunkbean" property="scoliosisextra" value="0"><logic:notEqual  name="trunkbean" property="scoliosis_extra" value="null">
              <bean:write name="trunkbean" property="scoliosis_extra"/>
          </logic:notEqual>&nbsp;&nbsp;=&nbsp;&nbsp;
              <bean:write name="trunkbean" property="scoliosisextraformat"/></logic:notEqual>
              <logic:equal  name="trunkbean" property="scoliosisextra" value="0">
              scoliosisextra is 0 </logic:equal>
          </td></tr>


          <tr><td class="label" colspan="3">Khyphosisextra&nbsp;&nbsp;&nbsp;=&nbsp;&nbsp;&nbsp;a+(90-a)/90*b where a is greater(totalKhyphosis,extra)
              where b is lesser(totalKhyphosis,extra)
          </td></tr><tr><td class="label" colspan="3"><logic:notEqual  name="trunkbean" property="kyphosisextra" value="0"><logic:notEqual  name="trunkbean" property="kyphosis_extra" value="null">
              <bean:write name="trunkbean" property="kyphosis_extra"/>
          </logic:notEqual>&nbsp;&nbsp;=&nbsp;&nbsp;
              <bean:write name="trunkbean" property="kyphosisextraformat"/> </logic:notEqual>
              <logic:equal  name="trunkbean" property="kyphosisextra" value="0">
              kyphosisextra is 0 </logic:equal>
          </td></tr>



          <tr><td class="label" colspan="3">Total Nontraumatic &nbsp;&nbsp;&nbsp;=&nbsp;&nbsp;&nbsp;a+(90-a)/90*b where a is greater(scoliosisextra,kyphosisextra)
              where b is lesser(scoliosisextra,kyphosisextra)
          </td></tr> <tr><td class="label" colspan="3"><logic:notEqual  name="trunkbean" property="non_traumatic" value="null">
              <bean:write name="trunkbean" property="non_traumatic"/>
          </logic:notEqual>&nbsp;&nbsp;=&nbsp;&nbsp;
          <bean:write name="trunkbean" property="nontraumaticformat"/></td></tr>


          <tr><td>
          <tr></tr><tr></tr>
          <tr><td class="label" colspan="3">Trunk Total value&nbsp;&nbsp;&nbsp;&nbsp;=&nbsp;&nbsp;&nbsp;&nbsp;a+(90-a)/90*b where a is greater(Traumatic,Non-Traumatic)
              where b is lesser(Traumatic,Non-Traumatic)
          </td></tr>
          <tr><td class="label" colspan="3"><logic:notEqual  name="trunkbean" property="totaltrunk" value="null">
              <bean:write name="trunkbean" property="totaltrunk"/>
          </logic:notEqual>&nbsp;&nbsp;=&nbsp;&nbsp;
          <bean:write name="trunkbean" property="traumaticformat"/></td></tr>


      </logic:notEmpty>

     <table align="center">
            <tr>
          <br> <html:button property="" value="Back" onclick="goBack()" styleClass="button"/>  </tr>
           <tr></tr><tr></tr>
          <br><tr><a href="showCalc.do?typeofDisabilityFlag=5&flagPrint=print" target="_blank">
                                Print<img src="DisabilityUITG/images/print.gif"  height="25" width="35"/></a></tr>

</table>
        </form>

</body>
</html:html>


 