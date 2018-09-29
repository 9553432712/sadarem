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


  <body onLoad="disableForm(form4);">
        <html:form action="getmentalillnessPrint.do" styleId="form4" method="post">

        <html:errors/>

         <% if(request.getAttribute("msg")!=null) { %>
         <font color="red"><center><%=request.getAttribute("msg")%></center></font> <% } %>
         <input type="hidden" name="<%= Constants.TOKEN_KEY %>"
            value="<%= session.getAttribute(Globals.TRANSACTION_TOKEN_KEY) %>" >
          <table align="center" cellspacing="0" cellpadding="0" width="45%">
            <tr>
                <td align="center"><font color="blue"><b>SSDAREM ID : <%=session.getAttribute("personcode")%></b></font></td>
            </tr>
       </table><br>
<table width="100%" border="0" cellspacing="2" cellpadding="0">
  <tr>
    <td colspan="2"><table  align="center"  class="innerTable" width="100%">
      <tr>
        <th align="center" class="gridhdbg">12.&nbsp;UPDATE MENTAL ILLNESS</th>
      </tr>
    </table></td>
    </tr>
  <tr>
    <td colspan="2"><table  align="center" cellspacing="1" cellpadding="1" class="innerTable1" width="100%">
      <tr>
        <td class="gridhdbg">12.1 &nbsp;&nbsp;Evaluation of Mental Illness</td>
      </tr>
      <tr>
        <td class="gridhdbg">&nbsp;&nbsp;&nbsp;Indian Disability Evaluation and assessment Scale (IDEAS)<br>
          &nbsp;&nbsp;&nbsp;Developed by Rehabilitation committee of Indian psychiatry association (IPA)<br>
          &nbsp;&nbsp(Facility to fill up numeric value in the boxes)</td>
      </tr>
    </table></td>
    </tr>
  <tr>
    <td colspan="2"><table  align="center"  border="0" cellpadding="0" cellspacing="0" class="gridtb" width="100%">
      <tr>
        <td class="gridhdbg" align="center" colspan="3" >Write Scores from Table</td>
      </tr>
      <tr>
        <td class="gridhdbg" align="center"  >&nbsp;</td>
        <td class="gridhdbg" align="center"  >Old</td>
        <td class="gridhdbg" align="center"  >New</td>
      </tr>
      <tr>
        <td width="50%" class="gridbg1">1)&nbsp;&nbsp; Self care <br>
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Taking care of body (hygiene,gromming,bathing,toileting,dressing,eating,taking care of one's health)
          </UL></td>
        <td  class="gridbg1"><CENTER>
          (0-4)&nbsp; &nbsp;
          <html:text property="selfcare" size="3" maxlength="1" onkeyup="addition(this.form)"/>
        </CENTER></td>
        <td  class="gridbg1"><CENTER>
          (0-4)&nbsp; &nbsp;
          <input type="text" name="rekha" size="3" maxlength="1" onKeyUp="addition(this.form)"/>
        </CENTER></td>
      </tr>
      <tr>
        <td width="50%" class="gridbg1">2)&nbsp; &nbsp; Inter personal Activities(Social relationships) <br>
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Initiating and maintaing interaction with others in contextual and social appropriate manner
          </UL></td>
        <td  class="gridbg1"><CENTER>
          (0-4)&nbsp; &nbsp;
          <html:text property="personalactivities" size="3" maxlength="1" onkeyup="addition(this.form)"/>
        </CENTER></td>
        <td  class="gridbg1"><CENTER>
          (0-4)&nbsp; &nbsp;
          <input type="text" name="rekha"size="3" maxlength="1" onKeyUp="addition(this.form)"/>
        </CENTER></td>
      </tr>
      <tr>
        <td width="50%" class="gridbg1">3)&nbsp; &nbsp;Communication and Understanding <br>
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Including communication and conversions with others by producing and comprehending spoken/written/non-verbal messages
          </UL></td>
        <td class="gridbg1"><CENTER>
          (0-4)&nbsp; &nbsp;
          <html:text property="communication" size="3" maxlength="1" onkeyup="addition(this.form)"/>
        </CENTER></td>
        <td  class="gridbg1"><CENTER>
          (0-4)&nbsp; &nbsp;
          <input type="text"name="rekha" size="3" maxlength="1" onKeyUp="addition(this.form)"/>
        </CENTER></td>
      </tr>
      <tr>
        <td width="50%" class="gridbg1">4)&nbsp; &nbsp;Work<br>
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Ability to perform tasks at employee,family,house hold and at school,Completly and efficienty and in proper time
          </UL></td>
        <td  class="gridbg1"><CENTER>
          (0-4)&nbsp; &nbsp;
          <html:text property="work" size="3" maxlength="1" onkeyup="addition(this.form)"/>
        </CENTER></td>
        <td  class="gridbg1"><CENTER>
          (0-4)&nbsp; &nbsp;
          <input type="text" name="rekha"size="3" maxlength="1" onKeyUp="addition(this.form)"/>
        </CENTER></td>
      </tr>
      <tr>
        <td width="50%" class="gridbg1">12.2)&nbsp;&nbsp; Duration of Illness</td>
        <td  class="gridbg1"><CENTER>
          (0-4)&nbsp; &nbsp;
          <html:text property="duration" size="3" maxlength="1" onkeyup="addition(this.form)"/>
        </CENTER></td>
        <td  class="gridbg1"><CENTER>
          (0-4)&nbsp; &nbsp;
          <input type="text" name="rekha"size="3" maxlength="1" onKeyUp="addition(this.form)"/>
        </CENTER></td>
      </tr>
      <tr>
        <td width="50%" class="gridbg1">&nbsp;&nbsp; Global Disability Score (Total)</td>
        <td  class="gridbg1"><CENTER>
          (0-20)&nbsp; &nbsp;
          <html:text property="globalscore" size="3" readonly="true"/>
        </CENTER></td>
        <td  class="gridbg1"><CENTER>
          (0-20)&nbsp; &nbsp;
          <input type="text" name="rekha" size="3" readonly="true"/>
        </CENTER></td>
      </tr>
    </table></td>
    </tr>
  <tr>
    <td width="50%" valign="top"><table width="100%" border="0" cellspacing="2" cellpadding="0">
      <tr>
        <td><table  align="center"  border="0" class="gridtb" cellpadding="0" cellspacing="0" width="100%">
          <tr>
            <td colspan=4 class="gridhdbg">&nbsp; Scoring for each items:(Reference Tables)</td>
          </tr>
        </table></td>
      </tr>
      <tr>
        <td><table  align="center" cellspacing="0" cellpadding="0" border="0" class="gridtb" width="100%">
          <tr>
            <td class="gridhdbg" width="20%">Score</td>
            <td class="gridhdbg" width="60%">Degree of Performance</td>
            <td class="gridhdbg">Duration of Illness</td>
          </tr>
          <tr>
            <td class="gridbg1">0</td>
            <td class="gridbg1">No Disability(None,Absent,Negligible)</td>
            <td class="gridbg1"><br></td>
          </tr>
          <tr>
            <td class="gridbg1">1</td>
            <td class="gridbg1">MILD disability(Slight,Low)</td>
            <td class="gridbg1">< 2years </td>
          </tr>
          <tr>
            <td class="gridbg1">2</td>
            <td class="gridbg1">MODERATE disability(Medium,Fair)</td>
            <td class="gridbg1">2-5 years </td>
          </tr>
          <tr>
            <td class="gridbg1">3</td>
            <td class="gridbg1">SEVERE disability(High,Extreme)</td>
            <td class="gridbg1">6-10 years </td>
          </tr>
          <tr>
            <td class="gridbg1">4</td>
            <td class="gridbg1">PROFOUND disability(Total,can't do)</td>
            <td class="gridbg1">>10 years </td>
          </tr>
        </table></td>
      </tr>
      <tr>
        <td><table  align="center" border="0" cellpadding="0" cellspacing="0" class="gridtb" width="100%">
          <tr>
            <td class="gridhdbg" width="20%">Score</td>
            <td class="gridhdbg" width="60%">Degree of Disability</td>
            <td class="gridhdbg">% of disability</td>
          </tr>
          <tr>
            <td class="gridbg1">0</td>
            <td class="gridbg1">NO </td>
            <td class="gridbg1">0 </td>
          </tr>
          <tr>
            <td class="gridbg1">1-6</td>
            <td class="gridbg1">MILD </td>
            <td class="gridbg1">< 40 </td>
          </tr>
          <tr>
            <td class="gridbg1">7-13</td>
            <td class="gridbg1">MODERATE</td>
            <td class="gridbg1">40-70 </td>
          </tr>
          <tr>
            <td class="gridbg1">14-19</td>
            <td class="gridbg1">SEVERE </td>
            <td class="gridbg1">71-99 </td>
          </tr>
          <tr>
            <td class="gridbg1">20</td>
            <td class="gridbg1">PROFOUND </td>
            <td class="gridbg1">100 </td>
          </tr>
        </table></td>
      </tr>
    </table></td>
    <td width="50%" valign="top"><table width="100%" border="0" cellspacing="2" cellpadding="0">
      <tr>
        <td><table  align="center"  class="innerTable" width="100%">
          <tr>
            <td colspan="3" class="gridhdbg" style="border:1px #000 solid;">Need Assessment/Referred/Recommended</td>
          </tr>
        </table></td>
      </tr>
      <tr>
        <td><table  align="center"  border="0" cellpadding="0" cellspacing="0" class="gridtb" width="100%">
          <tr>
            <td class="gridhdbg">&nbsp;</td>
            <td class="gridhdbg" >Old </td>
            <td class="gridhdbg" > New </td>
          </tr>
          <tr>
            <td  colspan="3" class="gridbg1"> 1. Early Intervention Services &nbsp;(For Children Below 3 Years)</td>
          </tr>
          <tr>
            <td  width="60%"class="gridbg1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; i.Behavior Modification</td>
            <td class="gridbg1"><html:checkbox property="behaviormodification" value="Behavior Modification"> </html:checkbox></td>
            <td class="gridbg1"><input type="checkbox"name="rekha2" value="Behavior Modification"/></td>
          </tr>
          <tr>
            <td   width="60%"class="gridbg1">2. Surgery</td>
            <td class="gridbg1" ><html:text property="surgery" maxlength="50"></html:text></td>
            <td class="gridbg1" ><input type="checkbox"name="rekha2" maxlength="50"></td>
          </tr>
          <tr>
            <td  width="60%"class="gridbg1">3. Psycotherapy/Behaviour Modification</td>
            <td class="gridbg1"><html:checkbox property="psycotherapybehaviour" value="Psycotherapy/Behaviour Modification"></html:checkbox></td>
            <td class="gridbg1"><input type="checkbox"name="rekha2" value="Psycotherapy/Behaviour Modification"></td>
          </tr>
          <tr>
            <td  width="60%"class="gridbg1"> 4. Manager To Take Care Of The Properties (For Persons With Mental Illness)</td>
            <td class="gridbg1"><html:checkbox property="managerproperties" value="Manager To Take Care"> </html:checkbox></td>
            <td class="gridbg1"><input type="checkbox" name="rekha2" value="Manager To Take Care"></td>
          </tr>
          <tr>
            <td width="60%" class="gridbg1">5. Admission In Psychiatric Hospitals/ Nursing Homes (For Persons With Mental Illness)</td>
            <td class="gridbg1"><html:checkbox property="psychiatrichospital" value="Psychiatric Hospitals/ Nursing Homes" > </html:checkbox></td>
            <td class="gridbg1"><input type="checkbox"name="rekha2" value="Psychiatric Hospitals/ Nursing Homes" ></td>
          </tr>
          <tr>
            <td width="60%" class="gridbg1">Any Other Mental Illness Needs</td>
            <td class="gridbg1"><html:text property="anyotherneed" maxlength="50" size="50"></html:text></td>
            <td class="gridbg1"><input type="text"name="rekha2" maxlength="50" size="30"></td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
</table>



    </html:form>

        <form action="">
               <table align="center">
            <tr>
                <td><html:button property=""  value="Next" onclick="goURL()" styleClass="button" /></td>

             <td><html:button  property="" value="Print" onclick="window.print();" styleClass="button" /></td>
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
</html:html>
