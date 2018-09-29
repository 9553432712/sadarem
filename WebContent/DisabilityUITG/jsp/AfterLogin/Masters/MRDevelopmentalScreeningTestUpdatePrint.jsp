<%@page contentType="text/html"%>
<%@ page import="org.apache.struts.Globals"%>
<%@ page import="org.apache.struts.taglib.html.Constants"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%><head>

<html:html>
    
    <script type="text/javascript" src="./DisabilityUITG/js/tabber.js"></script>

    <link rel="stylesheet" HREF="./DisabilityUITG/css/tabber.css" TYPE="text/css" MEDIA="screen">
    <script type="text/javascript">
        document.write('<style type="text/css">.tabber{display:none;}</style>');

    </script>
    <script language="javascript">
        function resetAllFields()
        {
            for(var i=1;i<=106;i++)
                document.getElementById(i).checked=false;
        }
        function goBack()
        {
            document.forms[0].action="MentalRetardationForwdActionPrint.do";
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

<body  onload="disableForm(mentalRed);">


    <html:form action="MRDevelopmentalScreeningTestSelectActionPrint.do" method="post" styleId="mentalRed">

        <table align="center" cellspacing="0" cellpadding="0" width="45%">
            <tr>
                <td align="center"><font color="blue"><b>SADAREM ID : <%=session.getAttribute("personcode")%></b></font></td>
            </tr>
        </table><br/>

        <table  align="center" cellspacing="1" cellpadding="8" class="innerTable" width="85%">
            <tr>
                <th align="center" class="heading">DEVELOPMENTAL SCREENING TEST</th>
            </tr>
        </table>
      <table width="100%" border="0" cellspacing="2" cellpadding="0">
  <tr>
    <td colspan="2"><table border="0" width="100%"  cellpadding="0" cellspacing="2">
      <tr>
        <td valign="top" width="50%"><table border=0 class="gridtb" cellpadding="0" cellspacing="0" width="100%">
          <tr class="tbl_bg2_content">
            <td class="gridhdbg">&nbsp;</td>
            <td class="gridhdbg">&nbsp;</td>
            <td class="gridhdbg">Old Values</td>
            <td class="gridhdbg">New Values</td>
            <td class="gridhdbg">Months</td>
            <td class="gridhdbg">Days</td>
          </tr>
          <tr class="tbl_bg2_content">
            <td class="gridbg1">&nbsp;</td>
            <td class="gridbg1">&nbsp;</td>
            <td class="gridbg1">Select All
              <input type="checkbox" id="3MSelectAll" name="89" onClick="enableOrDisable3MonthsFields();" disabled="true"/></td>
            <td class="gridbg1">&nbsp;</td>
            <td class="gridbg1">&nbsp;</td>
            <td class="gridbg1">&nbsp;</td>
          </tr>
          <tr class="tbl_bg2_content">
            <td class="gridbg1">&nbsp;</td>
            <td class="gridbg1">Birth cry present </td>
            <td class="gridbg1"><html:checkbox styleId="1" property="dst_birth_cry_present" onclick="checkSelectAllBasedonOnload()" value="1" disabled="true"/></td>
            <td class="gridbg1"><input type="checkbox" name="mohan" value="4" disabled="true"/></td>
            <td class="gridbg1">&nbsp;</td>
            <td class="gridbg1">13</td>
          </tr>
          <tr class="tbl_bg2_content">
            <td class="gridbg1">&nbsp;</td>
            <td class="gridbg1">Equal bilateral movements </td>
            <td class="gridbg1"><html:checkbox styleId="2" property="dst_equal_bilateral_movements" onclick="checkSelectAllBasedonOnload()" value="1" disabled="true"/></td>
            <td class="gridbg1"><input type="checkbox" name="mohan" value="4" disabled="true"/></td>
            <td class="gridbg1">&nbsp;</td>
            <td class="gridbg1">26</td>
          </tr>
          <tr class="tbl_bg2_content">
            <td class="gridbg1">&nbsp;</td>
            <td class="gridbg1">Responds to bell </td>
            <td class="gridbg1"><html:checkbox styleId="3" property="dst_responds_to_bell" onclick="checkSelectAllBasedonOnload()" value="1" disabled="true"/></td>
            <td class="gridbg1"><input type="checkbox" name="mohan" value="4" disabled="true"/></td>
            <td class="gridbg1">&nbsp;</td>
            <td class="gridbg1">39</td>
          </tr>
          <tr class="tbl_bg2_content">
            <td class="gridbg1">&nbsp;</td>
            <td class="gridbg1">Vocalises sounds </td>
            <td class="gridbg1"><html:checkbox  styleId="4" property="dst_vocalises_sounds" onclick="checkSelectAllBasedonOnload()" value="1" disabled="true"/></td>
            <td class="gridbg1"><input type="checkbox" name="mohan" value="4" disabled="true"/></td>
            <td class="gridbg1">&nbsp;</td>
            <td class="gridbg1">52</td>
          </tr>
          <tr class="tbl_bg2_content">
            <td class="gridbg1">&nbsp;</td>
            <td class="gridbg1">Smiles spontaneously </td>
            <td class="gridbg1"><html:checkbox styleId="5" property="dst_smiles_spontaneously" onclick="checkSelectAllBasedonOnload()" value="1" disabled="true"/></td>
            <td class="gridbg1"><input type="checkbox" name="mohan" value="4" disabled="true"/></td>
            <td class="gridbg1">&nbsp;</td>
            <td class="gridbg1">65</td>
          </tr>
          <tr class="tbl_bg2_content">
            <td class="gridbg1">&nbsp;</td>
            <td class="gridbg1">Eyes follow moving object </td>
            <td class="gridbg1"><html:checkbox styleId="6" property="dst_eyes_following_moving_object" onclick="checkSelectAllBasedonOnload()" value="1" disabled="true"/></td>
            <td class="gridbg1"><input type="checkbox" name="mohan" value="4" disabled="true"/></td>
            <td class="gridbg1">&nbsp;</td>
            <td class="gridbg1">78</td>
          </tr>
          <tr class="tbl_bg2_content">
            <td class="gridbg1">3M</td>
            <td class="gridbg1">Head steady </td>
            <td class="gridbg1"><html:checkbox styleId="7" property="dst_head_steady" onclick="checkSelectAllBasedonOnload()" value="1" disabled="true"/></td>
            <td class="gridbg1"><input type="checkbox" name="mohan" value="4" disabled="true"/></td>
            <td class="gridbg1">&nbsp;</td>
            <td class="gridbg1">90</td>
          </tr>
          <tr class="tbl_bg2_content">
            <td colspan="6"><hr></td>
          </tr>
          <tr class="tbl_bg2_content">
            <td class="gridhdbg">&nbsp;</td>
            <td class="gridhdbg">&nbsp;</td>
            <td class="gridhdbg">Select All
              <input type="checkbox" id="6MSelectAll" name="90" onClick="enableOrDisable6MonthsFields()" value="true" disabled="true"/>
            </td>
            <td class="gridhdbg">&nbsp;</td>
            <td class="gridhdbg">&nbsp;</td>
            <td class="gridhdbg">&nbsp;</td>
          </tr>
          <tr class="tbl_bg2_content">
            <td class="gridbg1">&nbsp;</td>
            <td class="gridbg1">Reaches for objects </td>
            <td class="gridbg1"><html:checkbox styleId="8" property="dst_reaches_for_objects" onclick="checkSelectAllBasedonOnload()" value="1" disabled="true"/></td>
            <td class="gridbg1"><input type="checkbox" name="mohan" value="4" disabled="true"/></td>
            <td class="gridbg1">&nbsp;</td>
            <td class="gridbg1">15</td>
          </tr>
          <tr class="tbl_bg2_content">
            <td class="gridbg1">&nbsp;</td>
            <td class="gridbg1">Laughs aloud </td>
            <td class="gridbg1"><html:checkbox styleId="9" property="dst_laughs_loud" onclick="checkSelectAllBasedonOnload()" value="1" disabled="true"/></td>
            <td class="gridbg1"><input type="checkbox" name="mohan" value="4" disabled="true"/></td>
            <td class="gridbg1">&nbsp;</td>
            <td class="gridbg1">30</td>
          </tr>
          <tr class="tbl_bg2_content">
            <td class="gridbg1">&nbsp;</td>
            <td class="gridbg1">Recognises mother </td>
            <td class="gridbg1"><html:checkbox styleId="10" property="dst_recognises_mother" onclick="checkSelectAllBasedonOnload()" value="1" disabled="true"/></td>
            <td class="gridbg1"><input type="checkbox" name="mohan" value="4" disabled="true"/></td>
            <td class="gridbg1">&nbsp;</td>
            <td class="gridbg1">45</td>
          </tr>
          <tr class="tbl_bg2_content">
            <td class="gridbg1">&nbsp;</td>
            <td class="gridbg1">Vocalises for pleasure/babble </td>
            <td class="gridbg1"><html:checkbox styleId="11" property="dst_vocalises_for_pleasure_or_babble" onclick="checkSelectAllBasedonOnload()" value="1" disabled="true"/></td>
            <td class="gridbg1"><input type="checkbox" name="mohan" value="4" disabled="true"/></td>
            <td class="gridbg1">&nbsp;</td>
            <td class="gridbg1">60</td>
          </tr>
          <tr class="tbl_bg2_content">
            <td class="gridbg1">&nbsp;</td>
            <td class="gridbg1">Carries objects to mouth </td>
            <td class="gridbg1"><html:checkbox styleId="12" property="dst_carries_objects_to_mouth" onclick="checkSelectAllBasedonOnload()" value="1" disabled="true"/></td>
            <td class="gridbg1"><input type="checkbox" name="mohan" value="4" disabled="true"/></td>
            <td class="gridbg1">&nbsp;</td>
            <td class="gridbg1">75</td>
          </tr>
          <tr class="tbl_bg2_content">
            <td class="gridhdbg">6M</td>
            <td class="gridhdbg">Rolls over </td>
            <td class="gridhdbg">
              <html:checkbox styleId="13" property="dst_rolls_over" onclick="checkSelectAllBasedonOnload()" value="1" disabled="true"/>
            </td>
            <td class="gridhdbg">
              <input type="checkbox" name="mohan" value="4" disabled="true"/>
            </td>
            <td class="gridbg1">&nbsp;</td>
            <td class="gridhdbg">90</td>
          </tr>
        </table></td>
        <td width="50%" valign="top"><table border=0 class="gridtb" width="100%" cellpadding="0" cellspacing="0">
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
            <td class="gridhdbg">&nbsp;</td>
            <td class="gridhdbg">Select All
              <input type="checkbox" id="9MSelectAll" name="91" onClick="enableOrDisable9MonthsFields()" value="true" disabled="true"/></td>
            <td class="gridhdbg">&nbsp;</td>
            <td class="gridhdbg">&nbsp;</td>
            <td class="gridhdbg">&nbsp;</td>
          </tr>
          <tr class="tbl_bg2_content">
            <td class="gridbg1">&nbsp;</td>
            <td class="gridbg1">Imitates speech sounds </td>
            <td class="gridbg1"><html:checkbox styleId="14" property="dst_imitates_speech_sounds" onclick="checkSelectAllBasedonOnload()" value="1" disabled="true"/></td>
            <td class="gridbg1"><input type="checkbox" name="mohan" value="4" disabled="true"/></td>
            <td class="gridbg1">&nbsp;</td>
            <td class="gridbg1">23</td>
          </tr>
          <tr class="tbl_bg2_content">
            <td class="gridbg1">&nbsp;</td>
            <td class="gridbg1">Sits by self </td>
            <td class="gridbg1"><html:checkbox styleId="15" property="dst_sits_by_self" onclick="checkSelectAllBasedonOnload()" value="1" disabled="true"/></td>
            <td class="gridbg1"><input type="checkbox" name="mohan" value="4" disabled="true"/></td>
            <td class="gridbg1">&nbsp;</td>
            <td class="gridbg1">46</td>
          </tr>
          <tr class="tbl_bg2_content">
            <td class="gridbg1">&nbsp;</td>
            <td class="gridbg1">Thumb finger grasp </td>
            <td class="gridbg1"><html:checkbox styleId="16" property="dst_thumb_finger_grasp" onclick="checkSelectAllBasedonOnload()" value="1" disabled="true"/></td>
            <td class="gridbg1"><input type="checkbox" name="mohan" value="4" disabled="true"/></td>
            <td class="gridbg1">&nbsp;</td>
            <td class="gridbg1">68</td>
          </tr>
          <tr class="tbl_bg2_content">
            <td class="gridbg1">9M</td>
            <td class="gridbg1">Shows curiousity </td>
            <td class="gridbg1"><html:checkbox styleId="17" property="dst_shows_curiousity" onclick="checkSelectAllBasedonOnload()" value="1" disabled="true"/></td>
            <td class="gridbg1"><input type="checkbox" name="mohan" value="4" disabled="true"/></td>
            <td class="gridbg1">&nbsp;</td>
            <td class="gridbg1">90</td>
          </tr>
          <tr class="tbl_bg2_content">
            <td colspan="6"><hr></td>
          </tr>
          <tr class="tbl_bg2_content">
            <td class="gridhdbg">&nbsp;</td>
            <td class="gridhdbg">&nbsp;</td>
            <td class="gridhdbg">Select All
              <input type="checkbox" id="1ySelectAll" name="92" onClick="enableOrDisable1YearFields()" value="true" disabled="true"/>
            </td>
            <td class="gridhdbg">&nbsp;</td>
            <td class="gridhdbg">&nbsp;</td>
            <td class="gridhdbg">&nbsp;</td>
          </tr>
          <tr class="tbl_bg2_content">
            <td class="gridbg1">&nbsp;</td>
            <td class="gridbg1">Says 3 words 'dada', 'mama', etc </td>
            <td class="gridbg1"><html:checkbox styleId="18" property="dst_says_three_words" onclick="checkSelectAllBasedonOnload()" value="1" disabled="true"/></td>
            <td class="gridbg1"><input type="checkbox" name="mohan" value="4" disabled="true"/></td>
            <td class="gridbg1">&nbsp;</td>
            <td class="gridbg1">23</td>
          </tr>
          <tr class="tbl_bg2_content">
            <td class="gridbg1">&nbsp;</td>
            <td class="gridbg1">Stands alone well </td>
            <td class="gridbg1"><html:checkbox styleId="19" property="dst_stands_alone_well" onclick="checkSelectAllBasedonOnload()" value="1" disabled="true"/></td>
            <td class="gridbg1"><input type="checkbox" name="mohan" value="4" disabled="true"/></td>
            <td class="gridbg1">&nbsp;</td>
            <td class="gridbg1">46</td>
          </tr>
          <tr class="tbl_bg2_content">
            <td class="gridbg1">&nbsp;</td>
            <td class="gridbg1">Follows simple instructions </td>
            <td class="gridbg1"><html:checkbox styleId="20" property="dst_follows_simple_instructions" onclick="checkSelectAllBasedonOnload()" value="1" disabled="true"/></td>
            <td class="gridbg1"><input type="checkbox" name="mohan" value="4" disabled="true"/></td>
            <td class="gridbg1">&nbsp;</td>
            <td class="gridbg1">68</td>
          </tr>
          <tr class="tbl_bg2_content">
            <td class="gridhdbg">1Y</td>
            <td class="gridhdbg">Co-operates for dressing </td>
            <td class="gridhdbg">
              <html:checkbox styleId="21" property="dst_cooperates_for_dressing" onclick="checkSelectAllBasedonOnload()" value="1" disabled="true"/>
            </td>
            <td class="gridhdbg">
              <input type="checkbox" name="mohan" value="4" disabled="true"/>
            </td>
            <td class="gridhdbg">&nbsp;</td>
            <td class="gridhdbg">90</td>
          </tr>
          <tr class="tbl_bg2_content">
            <td colspan="6"><hr></td>
          </tr>
          <tr class="tbl_bg2_content">
            <td class="gridhdbg">&nbsp;</td>
            <td class="gridhdbg">&nbsp;</td>
            <td class="gridhdbg">Select All
              <input type="checkbox" id="1andHalfYSelectAll" name="93" onClick="enableOrDisable1andHalfYearFields()" value="true" disabled="true"/>
            </td>
            <td class="gridhdbg">&nbsp;</td>
            <td class="gridhdbg">&nbsp;</td>
            <td class="gridhdbg">&nbsp;</td>
          </tr>
          <tr class="tbl_bg2_content">
            <td class="gridbg1">&nbsp;</td>
            <td class="gridbg1">Many intelligible words </td>
            <td class="gridbg1"><html:checkbox styleId="22" property="dst_many_intelligible_words" onclick="checkSelectAllBasedonOnload()" value="1" disabled="true"/></td>
            <td class="gridbg1"><input type="checkbox" name="mohan" value="4" disabled="true"/></td>
            <td class="gridbg1">1</td>
            <td class="gridbg1">15</td>
          </tr>
          <tr class="tbl_bg2_content">
            <td class="gridbg1">&nbsp;</td>
            <td class="gridbg1">Walks, runs well </td>
            <td class="gridbg1"><html:checkbox styleId="23" property="dst_walks_runs_well" onclick="checkSelectAllBasedonOnload()" value="1" disabled="true"/></td>
            <td class="gridbg1"><input type="checkbox" name="mohan" value="4" disabled="true"/></td>
            <td class="gridbg1">3</td>
            <td class="gridbg1">&nbsp;</td>
          </tr>
          <tr class="tbl_bg2_content">
            <td class="gridbg1">&nbsp;</td>
            <td class="gridbg1">Indicates wants </td>
            <td class="gridbg1"><html:checkbox styleId="24" property="dst_indicates_wants" onclick="checkSelectAllBasedonOnload()" value="1" disabled="true"/></td>
            <td class="gridbg1"><input type="checkbox" name="mohan" value="4" disabled="true"/></td>
            <td class="gridbg1">4</td>
            <td class="gridbg1">15</td>
          </tr>
          <tr class="tbl_bg2_content">
            <td class="gridhdbg">[1-1/2]</td>
            <td class="gridhdbg">Scribbles spontaneously </td>
            <td class="gridhdbg">
              <html:checkbox styleId="25" property="dst_scribbles_spontaneously" onclick="checkSelectAllBasedonOnload()" value="1" disabled="true"/>
            </td>
            <td class="gridhdbg">
              <input type="checkbox" name="mohan" value="4" disabled="true"/>
            </td>
            <td class="gridhdbg">6</td>
            <td class="gridhdbg">&nbsp;</td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td width="50%" align="center" valign="top"><table border="0" class="gridtb" width="100%" cellpadding="0" cellspacing="0">
      <tr>
        <td class="gridhdbg">&nbsp;</td>
        <td class="gridhdbg">&nbsp;</td>
        <td class="gridhdbg">Old Values</td>
        <td class="gridhdbg">New Values</td>
        <td class="gridhdbg">Months</td>
        <td class="gridhdbg">Days</td>
      </tr>
      <tr class="tbl_bg2_content">
        <td class="gridbg1">&nbsp;</td>
        <td class="gridbg1">&nbsp;</td>
        <td class="gridbg1">Select All
          <input type="checkbox" id="7YSelectAll" name="99" onClick="enableOrDisable7YearsFields()" value="true" disabled="true"/></td>
        <td class="gridbg1">&nbsp;</td>
        <td class="gridbg1">&nbsp;</td>
        <td class="gridbg1">&nbsp;</td>
      </tr>
      <tr class="tbl_bg2_content">
        <td class="gridbg1">&nbsp;</td>
        <td class="gridbg1">Adapts to home, school </td>
        <td class="gridbg1"><html:checkbox styleId="52" property="dst_adapts_to_home_school" onclick="checkSelectAllBasedonOnload()" value="1" disabled="true"/></td>
        <td class="gridbg1"><input type="checkbox" name="mohan2" value="4" disabled="true"/></td>
        <td class="gridbg1">2</td>
        <td class="gridbg1">12</td>
      </tr>
      <tr class="tbl_bg2_content">
        <td class="gridbg1">&nbsp;</td>
        <td class="gridbg1">Tells differences of objects </td>
        <td class="gridbg1"><html:checkbox styleId="53" property="dst_tells_differences_of_objects" onclick="checkSelectAllBasedonOnload()" value="1" disabled="true"/></td>
        <td class="gridbg1"><input type="checkbox" name="mohan2" value="4" disabled="true"/></td>
        <td class="gridbg1">4</td>
        <td class="gridbg1">24</td>
      </tr>
      <tr class="tbl_bg2_content">
        <td class="gridbg1">&nbsp;</td>
        <td class="gridbg1">Spells, reads, writes simple words </td>
        <td class="gridbg1"><html:checkbox styleId="54" property="dst_spells_reads_writes_simple_words" onclick="checkSelectAllBasedonOnload()" value="1" disabled="true"/></td>
        <td class="gridbg1"><input type="checkbox" name="mohan2" value="4" disabled="true"/></td>
        <td class="gridbg1">7</td>
        <td class="gridbg1">6</td>
      </tr>
      <tr class="tbl_bg2_content">
        <td class="gridbg1">&nbsp;</td>
        <td class="gridbg1">Enjoys group play </td>
        <td class="gridbg1"><html:checkbox styleId="55" property="dst_enjoys_group_play" onclick="checkSelectAllBasedonOnload()" value="1" disabled="true"/></td>
        <td class="gridbg1"><input type="checkbox" name="mohan2" value="4" disabled="true"/></td>
        <td class="gridbg1">9</td>
        <td class="gridbg1">18</td>
      </tr>
      <tr class="tbl_bg2_content">
        <td class="gridhdbg">7Y</td>
        <td class="gridhdbg">Knows comparative value of coins </td>
        <td class="gridhdbg">
          <html:checkbox styleId="56" property="dst_knows_comparative_value_of_coins" onclick="checkSelectAllBasedonOnload()" value="1" disabled="true"/>
        </td>
        <td class="gridhdbg">
          <input type="checkbox" name="mohan2" value="4" disabled="true"/>
        </td>
        <td class="gridhdbg">12</td>
        <td class="gridhdbg">&nbsp;</td>
      </tr>
      <tr class="gridbg1">
        <td colspan="6"><hr></td>
      </tr>
      <tr class="tbl_bg2_content">
        <td class="gridbg1">&nbsp;</td>
        <td class="gridbg1">&nbsp;</td>
        <td class="gridbg1">Select All
          <input type="checkbox" id="8ySelectAll" name="100" onClick="enableOrDisable8YearsFields()" value="true" disabled="true"/></td>
        <td class="gridbg1">&nbsp;</td>
        <td class="gridbg1">&nbsp;</td>
        <td class="gridbg1">&nbsp;</td>
      </tr>
      <tr class="tbl_bg2_content">
        <td class="gridbg1">&nbsp;</td>
        <td class="gridbg1">Combs hair by self </td>
        <td class="gridbg1"><html:checkbox styleId="57" property="dst_combs_hair_by_self" onclick="checkSelectAllBasedonOnload()" value="1" disabled="true"/></td>
        <td class="gridbg1"><input type="checkbox" name="mohan2" value="4" disabled="true"/></td>
        <td class="gridbg1">3</td>
        <td class="gridbg1">&nbsp;</td>
      </tr>
      <tr class="tbl_bg2_content">
        <td class="gridbg1">&nbsp;</td>
        <td class="gridbg1">Makes small purchases </td>
        <td class="gridbg1"><html:checkbox styleId="58" property="dst_makes_small_purchases" onclick="checkSelectAllBasedonOnload()" value="1" disabled="true"/></td>
        <td class="gridbg1"><input type="checkbox" name="mohan2" value="4" disabled="true"/></td>
        <td class="gridbg1">6</td>
        <td class="gridbg1">&nbsp;</td>
      </tr>
      <tr class="tbl_bg2_content">
        <td class="gridbg1">&nbsp;</td>
        <td class="gridbg1">Competition in school/play </td>
        <td class="gridbg1"><html:checkbox styleId="59" property="dst_competition_in_school_or_play" onclick="checkSelectAllBasedonOnload()" value="1" disabled="true"/></td>
        <td class="gridbg1"><input type="checkbox" name="mohan2" value="4" disabled="true"/></td>
        <td class="gridbg1">9</td>
        <td class="gridbg1">&nbsp;</td>
      </tr>
      <tr class="tbl_bg2_content">
        <td class="gridhdbg">8Y</td>
        <td class="gridhdbg">Tells time </td>
        <td class="gridhdbg">
          <html:checkbox styleId="60" property="dst_tells_time" onclick="checkSelectAllBasedonOnload()" value="1" disabled="true"/>
        </td>
        <td class="gridhdbg">
          <input type="checkbox" name="mohan2" value="4" disabled="true"/>
        </td>
        <td class="gridhdbg">12</td>
        <td class="gridhdbg">&nbsp;</td>
      </tr>
      <tr class="gridbg1">
        <td colspan="6"><hr></td>
      </tr>
      <tr class="tbl_bg2_content">
        <td class="gridbg1">&nbsp;</td>
        <td class="gridbg1">&nbsp;</td>
        <td class="gridbg1">Select All
          <input type="checkbox" id="9YSelectAll" name="101" onClick="enableOrDisable9YearsFields()" value="true" disabled="true"/></td>
        <td class="gridbg1">&nbsp;</td>
        <td class="gridbg1">&nbsp;</td>
        <td class="gridbg1">&nbsp;</td>
      </tr>
      <tr class="tbl_bg2_content">
        <td class="gridbg1">&nbsp;</td>
        <td class="gridbg1">Tells day, month, year </td>
        <td class="gridbg1"><html:checkbox styleId="61" property="dst_tells_day_month_year" onclick="checkSelectAllBasedonOnload()" value="1" disabled="true"/></td>
        <td class="gridbg1"><input type="checkbox" name="mohan2" value="4" disabled="true"/></td>
        <td class="gridbg1">2</td>
        <td class="gridbg1">&nbsp;</td>
      </tr>
      <tr class="tbl_bg2_content">
        <td class="gridbg1">&nbsp;</td>
        <td class="gridbg1">Reads on own initiative </td>
        <td class="gridbg1"><html:checkbox styleId="62" property="dst_reads_on_own_initiative" onclick="checkSelectAllBasedonOnload()" value="1" disabled="true"/></td>
        <td class="gridbg1"><input type="checkbox" name="mohan2" value="4" disabled="true"/></td>
        <td class="gridbg1">4</td>
        <td class="gridbg1">&nbsp;</td>
      </tr>
      <tr class="tbl_bg2_content">
        <td class="gridbg1">&nbsp;</td>
        <td class="gridbg1">Recoginises property rights </td>
        <td class="gridbg1"><html:checkbox styleId="63" property="dst_recognises_property_rights" onclick="checkSelectAllBasedonOnload()" value="1" disabled="true"/></td>
        <td class="gridbg1"><input type="checkbox" name="mohan2" value="4" disabled="true"/></td>
        <td class="gridbg1">6</td>
        <td class="gridbg1">&nbsp;</td>
      </tr>
      <tr class="tbl_bg2_content">
        <td class="gridbg1">&nbsp;</td>
        <td class="gridbg1">Favourite of fairy tales </td>
        <td class="gridbg1"><html:checkbox styleId="64" property="dst_favourite_of_fairy_tales" onclick="checkSelectAllBasedonOnload()" value="1" disabled="true"/></td>
        <td class="gridbg1"><input type="checkbox" name="mohan2" value="4" disabled="true"/></td>
        <td class="gridbg1">8</td>
        <td class="gridbg1">&nbsp;</td>
      </tr>
      <tr class="tbl_bg2_content">
        <td class="gridbg1">&nbsp;</td>
        <td class="gridbg1">Muscle coordination games (marbles) </td>
        <td class="gridbg1"><html:checkbox styleId="65" property="dst_muscle_coordination_games" onclick="checkSelectAllBasedonOnload()" value="1" disabled="true"/></td>
        <td class="gridbg1"><input type="checkbox" name="mohan2" value="4" disabled="true"/></td>
        <td class="gridbg1">10</td>
        <td class="gridbg1">&nbsp;</td>
      </tr>
      <tr class="tbl_bg2_content">
        <td class="gridhdbg">9Y</td>
        <td class="gridhdbg">Bathes self unaided </td>
        <td class="gridhdbg">
          <html:checkbox styleId="66" property="dst_bathes_self_unaided" onclick="checkSelectAllBasedonOnload()" value="1" disabled="true"/>
        </td>
        <td class="gridhdbg">
          <input type="checkbox" name="mohan2" value="4" disabled="true"/>
        </td>
        <td class="gridhdbg">12</td>
        <td class="gridhdbg">&nbsp;</td>
      </tr>
    </table>
      <table border="0" class="gridtb" width="100%" cellpadding="0" cellspacing="0">
        <tr >
          <td class="gridhdbg">&nbsp;</td>
          <td class="gridhdbg">&nbsp;</td>
          <td class="gridhdbg">Old Values</td>
          <td class="gridhdbg">New Values</td>
          <td class="gridhdbg">Months</td>
          <td class="gridhdbg">Days</td>
        </tr>
        <tr class="tbl_bg2_content">
          <td class="gridhdbg">&nbsp;</td>
          <td class="gridhdbg">&nbsp;</td>
          <td class="gridhdbg">Select All
            <input type="checkbox" id="13YSelectAll" name="105" onClick="enableOrDisable13YearsFields()" value="true" disabled="true"/></td>
          <td class="gridhdbg">&nbsp;</td>
          <td class="gridhdbg">&nbsp;</td>
          <td class="gridhdbg">&nbsp;</td>
        </tr>
        <tr class="tbl_bg2_content">
          <td class="gridbg1">&nbsp;</td>
          <td class="gridbg1">Shows foresight, planning, judgement </td>
          <td class="gridbg1"><html:checkbox styleId="79" property="dst_shows_foresight_planning_judgement" onclick="checkSelectAllBasedonOnload()" value="1" disabled="true"/></td>
          <td class="gridbg1"><input type="checkbox" name="mohan5" value="4" disabled="true"/></td>
          <td class="gridbg1">2</td>
          <td class="gridbg1">12</td>
        </tr>
        <tr class="tbl_bg2_content">
          <td class="gridbg1">&nbsp;</td>
          <td class="gridbg1">Learns from experience </td>
          <td class="gridbg1"><html:checkbox styleId="80" property="dst_learns_from_experience" onclick="checkSelectAllBasedonOnload()" value="1" disabled="true"/></td>
          <td class="gridbg1"><input type="checkbox" name="mohan5" value="4" disabled="true"/></td>
          <td class="gridbg1">4</td>
          <td class="gridbg1">24</td>
        </tr>
        <tr class="tbl_bg2_content">
          <td class="gridbg1">&nbsp;</td>
          <td class="gridbg1">Plays difficult games </td>
          <td class="gridbg1"><html:checkbox styleId="81" property="dst_plays_difficult_games" onclick="checkSelectAllBasedonOnload()" value="1" disabled="true"/></td>
          <td class="gridbg1"><input type="checkbox" name="mohan5" value="4" disabled="true"/></td>
          <td class="gridbg1">7</td>
          <td class="gridbg1">6</td>
        </tr>
        <tr class="tbl_bg2_content">
          <td class="gridbg1">&nbsp;</td>
          <td class="gridbg1">Interested in dressing up </td>
          <td class="gridbg1"><html:checkbox styleId="82" property="dst_interested_in_dressing_up" onclick="checkSelectAllBasedonOnload()" value="1" disabled="true"/></td>
          <td class="gridbg1"><input type="checkbox" name="mohan5" value="4" disabled="true"/></td>
          <td class="gridbg1">9</td>
          <td class="gridbg1">18</td>
        </tr>
        <tr class="tbl_bg2_content">
          <td class="gridhdbg">13Y</td>
          <td class="gridhdbg">Understands abstract ideas (justice) </td>
          <td class="gridhdbg">
            <html:checkbox styleId="83" property="dst_understands_abstract_ideas" onclick="checkSelectAllBasedonOnload()" value="1" disabled="true"/>
          </td>
          <td class="gridhdbg">
            <input type="checkbox" name="mohan5" value="4" disabled="true"/>
          </td>
          <td class="gridhdbg">12</td>
          <td class="gridhdbg">&nbsp;</td>
        </tr>
      </table></td>
    <td width="50%" align="center" valign="top"><table border="0" class="gridtb" width="100%" cellpadding="0" cellspacing="0">
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
        <td class="gridhdbg">&nbsp;</td>
        <td class="gridhdbg">Select All
          <input type="checkbox" id="10YSelectAll" name="102" onClick="enableOrDisable10YearsFields()" value="true" disabled="true"/></td>
        <td class="gridhdbg">&nbsp;</td>
        <td class="gridhdbg">&nbsp;</td>
        <td class="gridhdbg">&nbsp;</td>
      </tr>
      <tr class="tbl_bg2_content">
        <td class="gridbg1">&nbsp;</td>
        <td class="gridbg1">Cooperates keenly with companions </td>
        <td class="gridbg1"><html:checkbox styleId="67" property="dst_cooperates_keenly_with_companions" onclick="checkSelectAllBasedonOnload()" value="1" disabled="true"/></td>
        <td class="gridbg1"><input type="checkbox" name="mohan3" value="4" disabled="true"/></td>
        <td class="gridbg1">2</td>
        <td class="gridbg1">12</td>
      </tr>
      <tr class="tbl_bg2_content">
        <td class="gridbg1">&nbsp;</td>
        <td class="gridbg1">Has various hobbies, collections </td>
        <td class="gridbg1"><html:checkbox styleId="68" property="dst_has_various_hobbies_collections" onclick="checkSelectAllBasedonOnload()" value="1" disabled="true"/></td>
        <td class="gridbg1"><input type="checkbox" name="mohan3" value="4" disabled="true"/></td>
        <td class="gridbg1">4</td>
        <td class="gridbg1">24</td>
      </tr>
      <tr class="tbl_bg2_content">
        <td class="gridbg1">&nbsp;</td>
        <td class="gridbg1">goes about town freely </td>
        <td class="gridbg1"><html:checkbox styleId="69" property="dst_goes_about_town_freely" onclick="checkSelectAllBasedonOnload()" value="1" disabled="true"/></td>
        <td class="gridbg1"><input type="checkbox" name="mohan3" value="4" disabled="true"/></td>
        <td class="gridbg1">7</td>
        <td class="gridbg1">6</td>
      </tr>
      <tr class="tbl_bg2_content">
        <td class="gridbg1">&nbsp;</td>
        <td class="gridbg1">Sex differences in play become marked </td>
        <td class="gridbg1"><html:checkbox styleId="70" property="dst_sex_differences_in_play_become_marked" onclick="checkSelectAllBasedonOnload()" value="1" disabled="true"/></td>
        <td class="gridbg1"><input type="checkbox" name="mohan3" value="4" disabled="true"/></td>
        <td class="gridbg1">9</td>
        <td class="gridbg1">18</td>
      </tr>
      <tr class="tbl_bg2_content">
        <td class="gridhdbg">10Y</td>
        <td class="gridhdbg">Can stay away from home (camps) </td>
        <td class="gridhdbg">
          <html:checkbox styleId="71" property="dst_can_stay_away_from_home" onclick="checkSelectAllBasedonOnload()" value="1" disabled="true"/>
        </td>
        <td class="gridhdbg">
          <input type="checkbox" name="mohan3" value="4" disabled="true"/>
        </td>
        <td class="gridhdbg">12</td>
        <td class="gridhdbg">&nbsp;</td>
      </tr>
      <tr class="tbl_bg2_content">
        <td colspan="6"><hr></td>
      </tr>
      <tr class="tbl_bg2_content">
        <td class="gridhdbg">&nbsp;</td>
        <td class="gridhdbg">&nbsp;</td>
        <td class="gridhdbg">Select All
          <input type="checkbox" id="11ySelectAll" name="103" onClick="enableOrDisable11YearsFields()" value="true" disabled="true"/>
        </td>
        <td class="gridhdbg">&nbsp;</td>
        <td class="gridhdbg">&nbsp;</td>
        <td class="gridhdbg">&nbsp;</td>
      </tr>
      <tr class="tbl_bg2_content">
        <td class="gridbg1">&nbsp;</td>
        <td class="gridbg1">Writes occasional short letters </td>
        <td class="gridbg1"><html:checkbox styleId="72" property="dst_writes_occasional_short_letters" onclick="checkSelectAllBasedonOnload()" value="1" disabled="true"/></td>
        <td class="gridbg1"><input type="checkbox" name="mohan3" value="4" disabled="true"/></td>
        <td class="gridbg1">3</td>
        <td class="gridbg1">&nbsp;</td>
      </tr>
      <tr class="tbl_bg2_content">
        <td class="gridbg1">&nbsp;</td>
        <td class="gridbg1">Comprehends social situations </td>
        <td class="gridbg1"><html:checkbox styleId="73" property="dst_comprehends_social_situations" onclick="checkSelectAllBasedonOnload()" value="1" disabled="true"/></td>
        <td class="gridbg1"><input type="checkbox" name="mohan3" value="4" disabled="true"/></td>
        <td class="gridbg1">6</td>
        <td class="gridbg1">&nbsp;</td>
      </tr>
      <tr class="tbl_bg2_content">
        <td class="gridbg1">&nbsp;</td>
        <td class="gridbg1">Physical feats liked </td>
        <td class="gridbg1"><html:checkbox styleId="74" property="dst_physical_feets_liked" onclick="checkSelectAllBasedonOnload()" value="1" disabled="true"/></td>
        <td class="gridbg1"><input type="checkbox" name="mohan3" value="4" disabled="true"/></td>
        <td class="gridbg1">9</td>
        <td class="gridbg1">&nbsp;</td>
      </tr>
      <tr class="tbl_bg2_content">
        <td class="gridhdbg">11Y</td>
        <td class="gridhdbg">Able to discuss problems </td>
        <td class="gridhdbg">
          <html:checkbox styleId="75" property="dst_able_to_discuss_problems" onclick="checkSelectAllBasedonOnload()" value="1" disabled="true"/>
        </td>
        <td class="gridhdbg">
          <input type="checkbox" name="mohan3" value="4" disabled="true"/>
        </td>
        <td class="gridhdbg">12</td>
        <td class="gridhdbg">&nbsp;</td>
      </tr>
      <tr class="tbl_bg2_content">
        <td colspan="6"><hr></td>
      </tr>
      <tr>
        <td class="gridhdbg">&nbsp;</td>
        <td class="gridhdbg">&nbsp;</td>
        <td class="gridhdbg">Select All
          <input type="checkbox" id="12YSelectAll" name="104" onClick="enableOrDisable12YearsFields()" value="true" disabled="true"/></td>
        <td class="gridhdbg">&nbsp;</td>
        <td class="gridhdbg">&nbsp;</td>
        <td class="gridhdbg">&nbsp;</td>
      </tr>
      <tr class="tbl_bg2_content">
        <td class="gridbg1">&nbsp;</td>
        <td class="gridbg1">Enjoys books, newspapers, magazines </td>
        <td class="gridbg1"><html:checkbox styleId="76" property="dst_enjoys_books_newspapers_magazines" onclick="checkSelectAllBasedonOnload()" value="1" disabled="true"/></td>
        <td class="gridbg1"><input type="checkbox" name="mohan3" value="4" disabled="true"/></td>
        <td class="gridbg1">4</td>
        <td class="gridbg1">&nbsp;</td>
      </tr>
      <tr class="tbl_bg2_content">
        <td class="gridbg1">&nbsp;</td>
        <td class="gridbg1">More independent in spending </td>
        <td class="gridbg1"><html:checkbox styleId="77" property="dst_more_independent_in_spending" onclick="checkSelectAllBasedonOnload()" value="1" disabled="true"/></td>
        <td class="gridbg1"><input type="checkbox" name="mohan3" value="4" disabled="true"/></td>
        <td class="gridbg1">8</td>
        <td class="gridbg1">&nbsp;</td>
      </tr>
      <tr class="tbl_bg2_content">
        <td class="gridhdbg">12Y</td>
        <td class="gridhdbg">Capable of self criticism </td>
        <td class="gridhdbg">
          <html:checkbox styleId="78" property="dst_capable_of_self_criticism" onclick="checkSelectAllBasedonOnload()" value="1" disabled="true"/>
        </td>
        <td class="gridhdbg">
          <input type="checkbox" name="mohan3" value="4" disabled="true"/>
        </td>
        <td class="gridhdbg">12</td>
        <td class="gridhdbg">&nbsp;</td>
      </tr>
    </table>
      <table border="0" class="gridtb" width="100%" cellpadding="0" cellspacing="0">
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
          <td class="gridhdbg">&nbsp;</td>
          <td class="gridhdbg">Select All
            <input type="checkbox" id="15ySelectAll" name="106" onClick="enableOrDisable15YearsFields()"value="true" disabled="true"/></td>
          <td class="gridhdbg">&nbsp;</td>
          <td class="gridhdbg">&nbsp;</td>
          <td class="gridhdbg">&nbsp;</td>
        </tr>
        <tr class="tbl_bg2_content">
          <td class="gridbg1">&nbsp;</td>
          <td class="gridbg1">Makes sensible plans for future (job) </td>
          <td class="gridbg1"><html:checkbox styleId="84" property="dst_makes_sensible_plans_for_future" onclick="checkSelectAllBasedonOnload()" value="1" disabled="true"/></td>
          <td class="gridbg1"><input type="checkbox" name="mohan4" value="4" disabled="true"/></td>
          <td class="gridbg1">4</td>
          <td class="gridbg1">24</td>
        </tr>
        <tr class="tbl_bg2_content">
          <td class="gridbg1">&nbsp;</td>
          <td class="gridbg1">Follows current events </td>
          <td class="gridbg1"><html:checkbox styleId="85" property="dst_follows_current_events" onclick="checkSelectAllBasedonOnload()" value="1" disabled="true"/></td>
          <td class="gridbg1"><input type="checkbox" name="mohan4" value="4" disabled="true"/></td>
          <td class="gridbg1">9</td>
          <td class="gridbg1">18</td>
        </tr>
        <tr class="tbl_bg2_content">
          <td class="gridbg1">&nbsp;</td>
          <td class="gridbg1">Buys own clothing </td>
          <td class="gridbg1"><html:checkbox styleId="86" property="dst_buys_own_clothing" onclick="checkSelectAllBasedonOnload()" value="1" disabled="true"/></td>
          <td class="gridbg1"><input type="checkbox" name="mohan4" value="4" disabled="true"/></td>
          <td class="gridbg1">1 year 2m</td>
          <td class="gridbg1">12</td>
        </tr>
        <tr class="tbl_bg2_content">
          <td class="gridbg1">&nbsp;</td>
          <td class="gridbg1">Systematises own work </td>
          <td class="gridbg1"><html:checkbox styleId="87" property="dst_systematises_own_work" onclick="checkSelectAllBasedonOnload()" value="1" disabled="true"/></td>
          <td class="gridbg1"><input type="checkbox" name="mohan4" value="4" disabled="true"/></td>
          <td class="gridbg1">1 year 7m</td>
          <td class="gridbg1">6</td>
        </tr>
        <tr class="tbl_bg2_content">
          <td class="gridhdbg">15Y</td>
          <td class="gridhdbg">Purchases for others </td>
          <td class="gridhdbg">
            <html:checkbox styleId="88" property="dst_purchases_for_others" onclick="checkSelectAllBasedonOnload()" value="1" disabled="true"/>
          </td>
          <td class="gridhdbg">
            <input type="checkbox" name="mohan4" value="4" disabled="true"/>
          </td>
          <td class="gridhdbg">2 years</td>
          <td class="gridhdbg">&nbsp;</td>
        </tr>
      </table>
      <p>&nbsp;</p></td>
  </tr>
  <tr>
    <td colspan="2" align="center" valign="top">&nbsp;</td>
  </tr>
  <tr>
    <td colspan="2" align="center" valign="top">&nbsp;</td>
  </tr>
</table>


  </html:form>
        <form action="">
             <TABLE align="center">
            <tr class="tbl_bg2_content">
                <td><html:button property=""  value="Back" onclick="goBack()" styleClass="button"/></td>
                <td><html:button property="" value="Print" onclick="window.print()" styleClass="button"/></td>

        </TABLE>
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
