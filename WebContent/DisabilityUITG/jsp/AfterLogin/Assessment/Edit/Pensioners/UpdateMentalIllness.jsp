<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%> 
<%@taglib  uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib  uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page import="org.apache.struts.Globals"%>
<%@ page import="org.apache.struts.taglib.html.Constants"%>

<html:html>
    <script language="javascript" >
        function goBack()
        {
            document.forms[0].action="LocomotorSublinkUpdateForwdAction.do?getDisabilityPercentages=getDisabilityPercentages";
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

        function resetbutton(form)
        {
 
            document.form4.selfcare.value="";
            document.form4.personalactivities.value="";
            document.form4.communication.value="";
            document.form4.work.value="";

            document.form4.duration.value="";
            document.form4.globalscore.value="0";

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


    <body><br>
        <html:form action="updatementalillness.do" styleId="form4" method="post" onsubmit="return avoidDuplicateForm(this)">

            <html:errors/>
        
        <% if (request.getAttribute("msg") != null) {%>
        <font color="red"><center><%=request.getAttribute("msg")%></center></font> <% }%>
        <input type="hidden" name="<%= Constants.TOKEN_KEY%>"
               value="<%= session.getAttribute(Globals.TRANSACTION_TOKEN_KEY)%>" >
        <table  align="center" cellspacing="1" cellpadding="0" class="inputform" width="90%">
            <tr>
                <th colspan="10">12.&nbsp;UPDATE MENTAL ILLNESS</th>
            </tr>

            <tr>
                <td colspan="10">12.1 &nbsp;&nbsp;Evaluation of Mental Illness</td>
            </tr>

            <tr>
                <td colspan="10">&nbsp;&nbsp;&nbsp;Indian Disability Evaluation and assessment Scale (IDEAS)
                    &nbsp;&nbsp;&nbsp;Developed by Rehabilitation committee of Indian psychiatry association (IPA)
                    &nbsp;&nbsp(Facility to fill up numeric value in the boxes)</td>
            </tr>

            <tr>
                <td  align="center" colspan="10" >Write Scores from Table</td>
            </tr>

            <tr>
                <td  width="60%">1)&nbsp;&nbsp; Self care
                    <UL>Taking care of body (hygiene,gromming,bathing,toileting,dressing,eating,taking care of one's health)</UL></td>
                <td colspan="4" width="20%">
                    (0-4)&nbsp; &nbsp;<html:text property="selfcare"  maxlength="1" onkeyup="addition(this.form)"/>
                </td>
            </tr>
            <tr>
                <td width="60%">2)&nbsp; &nbsp; Inter personal Activities(Social relationships)
                    <UL>Initiating and maintaing interaction with others in contextual and social appropriate manner</UL></td>
                <td colspan="4" width="20%">
                    (0-4)&nbsp; &nbsp;<html:text property="personalactivities"  maxlength="1" onkeyup="addition(this.form)"/>
                </td>
            </tr>

            <tr>
                <td width="60%">3)&nbsp; &nbsp;Communication and Understanding

                    <UL>Including communication and conversions with others by producing and comprehending spoken/written/non-verbal messages</UL></td>
                <td  colspan="4" width="20%">
                    (0-4)&nbsp; &nbsp;<html:text property="communication"  maxlength="1" onkeyup="addition(this.form)"/>
                </td>
            </tr>

            <tr>
                <td width="60%">4)&nbsp; &nbsp;Work


                    <UL>Ability to perform tasks at employee,family,house hold and at school,Completly and efficienty and in proper time</UL></td>
                <td colspan="4" width="20%">
                    (0-4)&nbsp; &nbsp;<html:text property="work"  maxlength="1" onkeyup="addition(this.form)"/>
                </td>
            </tr>

            <tr>
                <td width="60%">12.2)&nbsp;&nbsp; Duration of Illness</td>
                <td colspan="4" width="20%">
                    (0-4)&nbsp; &nbsp;<html:text property="duration"  maxlength="1" onkeyup="addition(this.form)"/>
                </td>
            </tr>

            <tr>
                <td width="60%" >&nbsp;&nbsp; Global Disability Score (Total)</td>
                <td  colspan="4" width="20%">
                    (0-20)&nbsp;<html:text property="globalscore"  readonly="true"/>
                </td>
            </tr>
               </table>
                  <table  align="center" cellspacing="1" cellpadding="0" class="inputform" width="90%">
            <tr>
                <th colspan="10">&nbsp; Scoring for each items:(Reference Tables)</th>
            </tr>
  
 
            <tr>
                <th  width="10%"><br>Score</th>
                <th  width="35%"><br>Degree of Performance</th>
                <th  width="35%"><br>Duration of Illness</th>
            </tr>
            <tr>
                <td ><br>0</td>
                <td ><br>No Disability(None,Absent,Negligible)</td>
                <td ><br>  </td>
            </tr>
            <tr>
                <td ><br>1</td>
                <td ><br>MILD disability(Slight,Low)</td>
                <td ><br>< 2years </td>
            </tr>

            <tr>
                <td ><br>2</td>
                <td ><br>MODERATE disability(Medium,Fair)</td>
                <td ><br>2-5 years </td>
            </tr>
            <tr>
                <td ><br>3</td>
                <td ><br>SEVERE disability(High,Extreme)</td>
                <td ><br>6-10 years </td>
            </tr>
            <tr>
                <td ><br>4</td>
                <td ><br>PROFOUND disability(Total,can't do)</td>
                <td ><br> >10 years </td>
            </tr>



            <tr>
                <th  ><br>Score</th>
                <th><br>Degree of Disability</th>
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
                <td ><br>< 40  </td>
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
                  </table>
                <table  align="center" cellspacing="1" cellpadding="0" class="inputform" width="90%">
            <tr>
                <th colspan="12" >Need Assessment/Referred/Recommended</th>
            </tr>

            <tr>
                <td  colspan="12" > 1. Early Intervention Services &nbsp;(For Children Below 3 Years)</td>
            </tr>
            <tr>
                <td  >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; i.Behavior Modification</td>
                <td colspan="4"><html:checkbox property="behaviormodification" value="Behavior Modification" style="width:25px"> </html:checkbox></td>
            </tr>
            <tr>
                <td   >2. Surgery</td>
                <td ><html:text property="surgery" maxlength="50"></html:text> </td>
            </tr>
            <tr>
                <td  >3. Psycotherapy/Behaviour Modification</td>
                <td><html:checkbox property="psycotherapybehaviour" value="Psycotherapy/Behaviour Modification" style="width:25px"></html:checkbox></td>
            </tr>
            <tr>
                <td  > 4. Manager To Take Care Of The Properties (For Persons With Mental Illness)</td>
                <td><html:checkbox property="managerproperties" value="Manager To Take Care" style="width:25px"> </html:checkbox></td>
            </tr>
            <tr>
                <td >5. Admission In Psychiatric Hospitals/ Nursing Homes (For Persons With Mental Illness)</td>
                <td><html:checkbox property="psychiatrichospital" value="Psychiatric Hospitals/ Nursing Homes"  style="width:25px"> </html:checkbox></td>
            </tr>
            <tr>
                <td >Any Other Mental Illness Needs</td>
                <td><html:text property="anyotherneed" maxlength="50" size="50"></html:text></td></tr>

            <tr>
                <th colspan="8"><html:button property=""  value="Back" onclick="goBack()" styleClass="button" />
                    <html:submit value="Update" styleClass="button" styleId="mentButton"/>
                    <html:button  property="" value="Reset" onclick="resetbutton()" styleClass="button" /></th>
            </tr>
        </table>

    </html:form>
</body> 
</html:html>
