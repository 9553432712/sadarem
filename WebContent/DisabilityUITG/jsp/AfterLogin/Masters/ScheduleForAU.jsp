<%--
    Document   : AppletAuthorityForPartB
    Created on : Jul 29, 2011, 10:45:05 AM
    Author     : 484898
--%>

<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>


<html:html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>SADAREM</title>
    </head>

    <script >
       
        function getData() {
            document.forms[0].mode.value="";
            document.forms[0].submit();
        }

        function insertDetails() {

            var dt = new Date();
            var str1 = document.forms[0].fromdate.value;

            var dt1  = parseInt(str1.substring(0,2),10);
            var mon1 = parseInt(str1.substring(3,5),10);
            var yr1  = parseInt(str1.substring(6,10),10);
            var dt2  = dt.getDate();
            var mon2 = dt.getMonth()+1;
            var yr2  = dt.getFullYear();
            var date1 = new Date(yr1, mon1, dt1);
            var date2 = new Date(yr2, mon2, dt2);
      
           if(date2 > date1) {
                alert("Selected Date Cannot be less than the ToDay's Date");
                document.forms[0].elements['fromdate'].focus();
                document.forms[0].elements['fromdate'].value="";
                return false;
            }

            if(document.forms[0].elements['mandal_id'].value=="0") {
                alert("Please Select Mandal");
                document.forms[0].elements['mandal_id'].focus();
                return false;
            }else if(document.forms[0].elements['fromdate'].value=="") {
                alert("Please Select Schedule Date");
                document.forms[0].elements['fromdate'].focus();
                return false;
            }
            document.forms[0].mode.value="insertDetails";
            document.forms[0].submit();
        }
        function ShowDate()
        {
            var d;
            var dt = new Date();
            if(dt.getMonth() <= "9" || dt.getDate()<= "9" ) {
              d ="0"+dt.getDate()+"/0"+(dt.getMonth()+1) +"/" +dt.getFullYear();
            }else {
                d =dt.getDate()+"/"+(dt.getMonth()+1) +"/" +dt.getFullYear();
            }

            document.forms[0].elements['fromdate'].value= d;

        }

     
    </script>



    <body>

    <script language="JavaScript" src="./DisabilityUITG/js/date-picker"></script>
    <html:form action="/ScheduleForAu" focus="mandal_id">

        <input type="hidden" name="mode"/>
        <logic:present name="msgSuccess">
            <center><font color="green" size="2"><bean:write name="msgSuccess"/></font></center>
        </logic:present>
        <logic:present name="msgFailure">
            <center><font color="red" size="2"><bean:write name="msgFailure"/></font></center>
        </logic:present>
      
        <table  align="center" cellspacing="1" cellpadding="0" width="90%" class="inputform">
            <tr>
                <th align="center" colspan="5">
                  Second Time Re-Assessment Schedule
                </th>
            </tr>
            <tr>
                <td align="left" valign="middle" class="label">
                    Mandal :<font color="red">*</font>  <html:select styleId="2" property="mandal_id" onchange="getData()" style="height:25px;">
                        <html:option  value="0">-- Select --</html:option>
                        <html:optionsCollection   property="mandallist" label="mandal_name" value="mandal_id"  />
                    </html:select>

                </td>

                <td align="left" valign="middle" class="label">
                    Village :  <html:select styleId="3" property="village_id" style="height:25px;">
                        <html:option  value="0">ALL</html:option>
                        <html:optionsCollection property="villagelist" label="village_name" value="village_id"  />
                    </html:select>

                </td>


                <td class="label" colspan="2">Schedule Date :<font color="red">*</font>
                    <html:text property="fromdate" readonly="true" size="12" />
                    <a  href="javascript:show_calendar('forms[0].fromdate');"
                        onmouseover="window.status='Date Picker';return true;"
                        onmouseout="window.status='';return true;">
                        <img src="./DisabilityUITG/images/calendar.png" border="0"></a>
                </td>

            </tr>

            <tr>
                <th colspan="6" align="center">  <html:button property="but" onclick="insertDetails()" value="Submit"/>
                </th>
            </tr>
        </table>


    </html:form>
</body>
</html:html>


