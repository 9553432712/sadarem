<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%> 
<%@taglib  uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib  uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<html:html>
  <script language="javascript" >
 function goBack()
{
            document.forms[0].action="LocomotorSublinkForwdAction.do?getDisabilityPercentages=getDisabilityPercentages";
            document.forms[0].submit();
}


function addition(form)
{
var a=0;
var b=0;
var c=0;
var d=0;
var e=0;
  if(form.selfcare.value!="")
    {
      if(!isNaN(parseInt(form.selfcare.value)))
      {
        a=parseInt(form.selfcare.value)
      }
    }  
    
  if(form.personalactivities.value!="")
    {
      if(!isNaN(parseInt(form.personalactivities.value)))
       {
        b=parseInt(form.personalactivities.value)
       }
    }
  if(form.communication.value!="")
   {
     if(!isNaN(parseInt(form.communication.value)))
      {
        c=parseInt(form.communication.value)
      }
   }  
 
if(form.work.value!="")  
  {
    if(!isNaN(parseInt(form.work.value)))
     {
        d=parseInt(form.work.value)
     }
  } 
 if(form.duration.value!="") 
   {
     if(!isNaN(parseInt(form.duration.value)))
      {
        e=parseInt(form.duration.value)
      }
    }
    

  form.globalscore.value= a + b + c + d + e;   
     
}

 function avoidDuplicateForm(thisform){
        if (thisform.getAttribute('submitted'))
            return false;
        else{
        thisform.setAttribute('submitted','true');        
            document.getElementById('mentButton').disabled = true;
        }
    }

</script>  
    
    <body>
    
        <html:form action="mentalillness.do" method="post" onsubmit="return avoidDuplicateForm(this)">
        <html:errors/>

   <p>     </p>   
 <table  align="center" cellspacing="1" cellpadding="0" class="inputform" width="90%">
            <tr>
                <th align="center" colspan="4">12. &nbsp;ADD &nbsp;MENTAL&nbsp; ILLNESS</th>
            </tr>
 </table>

   <table  align="center" cellspacing="1" cellpadding="0" class="inputform" width="90%">
            <tr align="left">
                <td  align="left">12.1 &nbsp;&nbsp;Evaluation of Mental Illness<br></td>
            </tr>
        </table>

   <table  align="center" cellspacing="1" cellpadding="0" border="0" class="inputform" width="90%">
            <tr>
                <td >&nbsp;&nbsp;&nbsp;Indian Disability Evaluation and Assessment Scale (IDEAS)<br>
                    &nbsp;&nbsp;&nbsp;Developed by Rehabilitation committee of Indian Psychiatry Association (IPA)<br><br>
                (Facility to fill up numeric value in the boxes)
                </td>
                <td  ><br><br>(Write scores from table)</td>
            </tr>
        <tr>
        <td width="80%" >1)&nbsp;&nbsp; Self care:
            <UL>Taking care of body (hygiene,gromming,bathing,toileting,dressing,eating,taking care of one's health)</UL></td>
        <td width="30%" ><CENTER>
            (0-4)<html:text property="selfcare"  maxlength="2" onkeyup="addition(this.form)"/>
        </CENTER></td>
        </tr>

        <tr> 
        <td width="80%" >2)&nbsp; &nbsp; Inter personal Activities (Social relationships):
            <UL>Initiating and maintaing interaction with others in contextual and social appropriate manner</UL></td>
        <td width="30%" ><CENTER>(0-4)
           <html:text property="personalactivities"  maxlength="1"  onkeyup="addition(this.form)"/>
        </CENTER></td>
        </tr>


        <tr> 
        <td width="80%" >3)&nbsp; &nbsp;Communication and Understanding:
            <UL>Including communication and conversions with others by producing and comprehending spoken/written/non-verbal messages</UL></td>
        <td width="30%" ><CENTER><BR><BR>(0-4)
         <html:text property="communication"  maxlength="1" onkeyup="addition(this.form)"/>
        </CENTER></td>
        </tr>


        
        <tr> 
        <td width="80%" >4)&nbsp; &nbsp;Work:
            <UL>Ability to perform tasks at employee,family,house hold and at school,Completly and efficienty and in proper time</UL></td>
        <td width="30%" ><CENTER><BR><BR>(0-4)
                <html:text property="work"  maxlength="1" onkeyup="addition(this.form)"/>
        </CENTER></td>
        </tr>
        </table>
        
        <table  align="center" cellspacing="1" cellpadding="0" border="0" class="inputform" width="90%">
        <tr> 
            <td width="80%" >12.2 &nbsp;&nbsp;Duration of Illness:</td>
        <td width="30%" ><CENTER><BR><BR>(0-4)
                <html:text property="duration"  maxlength="1" onkeyup="addition(this.form)"/>
        </CENTER></td>
        </tr>


          <tr>
            <td width="80%" >Global Disability Score (Total):</td>
        <td width="30%" ><CENTER><BR><BR>(0-2)
                <html:text property="globalscore"  maxlength="1" onkeyup="addition(this.form)"/>
        </CENTER></td>
        </tr>
         </table>

        <table  align="center" cellspacing="1" cellpadding="0" border="0" class="inputform" width="90%">
            <tr>
            <th colspan=3 >Tables for References&nbsp; Scoring for each items:</th>
            </tr>
            <tr>
                <th  width="20%"><br>Score</th>
                <th  width="60%"><br>Degree of Performance</th>
                <th width="15%"><br>Duration of Illness</th>
            </tr>

            <tr>
                <td ><br>0</td>
                <td ><br>NO disability (None,Absent,Negligible)</td>
                <td ><br>  </td>
            </tr>
            <tr>
                <td ><br>1</td>
                <td ><br>MILD disability (Slight,Low)</td>
                <td ><br>< 2years </td>
            </tr>
        
            <tr>
                <td ><br>2</td>
                <td ><br>MODERATE disability (Medium,Fair)</td>
                <td ><br>2-5 years </td>
            </tr>

            <tr>
                <td ><br>3</td>
                <td ><br>SEVERE disability (High,Extreme)</td>
                <td ><br>6-10 years </td>
            </tr>

            <tr>
                <td ><br>4</td>
                <td ><br>PROFOUND disability (Total,can't do)</td>
                <td ><br> >10  years </td>
            </tr>
			
      <%--  </table>
        
        <table  align="center" cellspacing="1" cellpadding="0" border="0" class="inputform" width="90%">--%>
            
            <tr>
                <th  width="20%"><br>Score</th>
                <th  width="60%"><br>Degree of Disability</th>
                <th ><br>% of disability</th>
            </tr>
            <tr>
                <td ><br>0</td>
                <td ><br>NO </td>
                <td ><br>0  </td>
            </tr>
            <tr>
                <td ><br>1-6</td>
                <td ><br>MILD </td>
                <td ><br>< 40 </td>
            </tr>
        
            <tr>
                <td ><br>7-13</td>
                <td ><br>MODERATE</td>
                <td ><br>40-70 </td>
            </tr>

            <tr>
                <td ><br>14-19</td>
                <td ><br>SEVERE </td>
                <td ><br>71-99 </td>
            </tr>
            <tr>
                <td ><br>20</td>
                <td ><br>PROFOUND </td>
                <td ><br>100 </td>
            </tr>
       

              <tr> <th colspan="4" align="center">Need Assessment/Referred/Recommended</th>
                </tr>
        
              <%
                        int caAU = 0, ca = 0,caPer=0;
                        if (session.getAttribute("ageAU") != null) {
                            caAU = Integer.parseInt((String) session.getAttribute("ageAU"));
                        }else if(session.getAttribute("agePersonalInsert") != null) {
                               caPer = Integer.parseInt((String) session.getAttribute("agePersonalInsert"));
                         }else {
                            ca = Integer.parseInt((String) session.getAttribute("agePersonal"));
                        }
                        if (caAU <= 3 && ca <= 3 && caPer <= 3) {
            %>
        <tr>
            <td  colspan="3" >1. Early Intervention Services (For Children Below 3 Years)</td>
        </tr>
        <tr>
            <td colspan="2" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; i.Behavior Modification</td>
            <td><html:checkbox property="behaviormodification" value="Behavior Modification" style="width:25px"> </html:checkbox></td>
        </tr>
      <tr>
          <td  colspan="2" >2. Surgery</td>
         <td   ><html:text property="surgery" maxlength="50" style="width:200px"></html:text> </td>
      </tr>
     <tr>
         <td colspan="2" >3. Psycotherapy/Behaviour Modification</td>
         <td ><html:checkbox property="psycotherapybehaviour" value="Psycotherapy/Behaviour Modification" disabled="true" style="width:25px"></html:checkbox></td>
     </tr>
       <tr>
           <td colspan="2" >4. Manager To Take Care Of The Properties (For Persons With Mental Illness)</td>
           <td ><html:checkbox property="managerproperties" value="Manager To Take Care" disabled="true" style="width:25px"> </html:checkbox></td>
       </tr>
         <%} else {%>
         <tr>
            <td  colspan="3" >1. Early Intervention Services (For Children Below 3 Years)</td>
        </tr>
        <tr>
            <td colspan="2" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; i.Behavior Modification</td>
            <td ><html:checkbox property="behaviormodification" value="Behavior Modification" disabled="true" style="width:25px"> </html:checkbox></td>
        </tr>
      <tr>
          <td  colspan="2" >2. Surgery</td>
         <td   ><html:text property="surgery" maxlength="50" style="width:200px"></html:text> </td>
      </tr>
     <tr>
         <td colspan="2" >3. Psycotherapy/Behaviour Modification</td>
         <td ><html:checkbox property="psycotherapybehaviour" value="Psycotherapy/Behaviour Modification" style="width:25px"></html:checkbox></td>
     </tr>
       <tr>
           <td colspan="2" >4. Manager To Take Care Of The Properties (For Persons With Mental Illness)</td>
           <td ><html:checkbox property="managerproperties" value="Manager To Take Care" style="width:25px"> </html:checkbox></td>
       </tr>
         <%}%>
    <tr>
        <td colspan="2" >5. Admission In Psychiatric Hospitals/ Nursing Homes (For Persons With Mental Illness)</td>
        <td ><html:checkbox property="psychiatrichospital" value="Psychiatric Hospitals/ Nursing Homes" style="width:25px"> </html:checkbox></td>
    </tr>
   <tr>
        <td colspan="2" >Any Other Mental Illness Needs</td>
        <td><html:text property="anyotherneed" maxlength="50" style="width:200px"> </html:text></td>
    </tr> 
        
            <tr>
                <th colspan="4"><html:button property=""  value="Back" onclick="goBack()" styleClass="button"/>
               <html:submit value="Add" styleClass="button" styleId="mentButton"/>
               <html:reset value="Reset" styleClass="button"/></th>
            </tr>
        </table>

    </html:form>
   </body> 
</html:html>
