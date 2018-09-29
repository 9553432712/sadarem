<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ page import="java.util.*" %>
<%--
The taglib directive below imports the JSTL library. If you uncomment it,
you must also add the JSTL library to the project. The Add Library... action
on Libraries node in Projects view can be used to add the JSTL 1.1 library.
--%>
<%--
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html:html>
    <head>
        <script>
            function validate_required(field,alerttxt)
            {
            with (field)
            {
            if (value==null||value=="")
            {
            alert(alerttxt);
            return false
            }
            else
            {
            return true
            }
            }
            }
            function validate_form(thisform)
            {
            with (thisform)
            {
            if (validate_required(fromdate,"From Date must be selected!")==false)
            {
            fromdate.focus();
            return false
            }
            if (validate_required(todate,"To Date must be selected!")==false)
            {
            todate.focus();
            return false
            }
            
            }
            //  }
            //   function comparedate(fromDate,toDate)
          //  {
	
	
            var fromDate= document.forms[0].fromdate.value;
            var toDate= document.forms[0].todate.value;
		
            //Validate Meeting Date
	
            var yye=fromDate.substr(6,4);
            var mme=fromDate.substr(3,2);
            var dde=fromDate.substr(0,2);
        
            var yyb=toDate.substr(6,4);
            var mmb=toDate.substr(3,2);
            var ddb=toDate.substr(0,2);
         
            var dob = new  Date();
            var etd = new  Date();
            var today=new Date();
        
            etd.setFullYear(yye,mme-1,dde);
            dob.setFullYear(yyb,mmb-1,ddb)
        
            var y1=today.getYear();
            var y2= dob.getYear();
        
              
        
            if (today < etd)
            {
            alert("From date is after Todays Date");
            document.forms[0].fromdate.value="";
            return false;
            }
        
            if (today < dob)
            {
            alert("To date is after Todays Date");
            document.forms[0].todate.value="";
            return false;
            }
        
            if (dob < etd)
            {
            alert("From date is greater than To date");
            document.forms[0].fromdate.value="";
            return false;
            }
            }
            
        </script>
        
        <script language="JavaScript" src="./DisabilityUITG/js/date-picker"></script>
    </head>

    <BODY bgcolor="#F2f0e6">

        <html:form action="getAssistiveDate.do" 
            styleId="surgeryreportform" onsubmit="return validate_form(this)">
            <table  border="0" align="center" cellspacing="1" cellpadding="5" valign="middle"
                class="tbl_bg2_content"  ><br>
            <tr  align="center" bgcolor="white"> 
                <td bgcolor="white"><font face="vardana" color="#660000" size="3">
                    <b>Select The Date For Reports&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b> </font>
                </td>
                <td  bgcolor="white">
                <font color="red"><b>*
                </b></font><font size="2"face="vardana" color="#660000"><b>From Date :    
                </td><td  bgcolor="white"> </font>
                        <html:text property="fromdate"  readonly="true"  name="surgeryreportform" 
                        size="10" />
                        <a  href="javascript:show_calendar('forms[0].fromdate');"
                        onmouseover="window.status='Date Picker';return true;"
                        onmouseout="window.status='';return true;">
                        <img src="./DisabilityUITG/images/calendar.png" border="0"></a>
                    </td>
    
    
                    <td bgcolor="white"><font color="red"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;*</b></font>
                        <font size="2" face="vardana" color="#660000">
                        <b>To Date :    
                    </td><td  bgcolor="white"> </font>
                        <html:text property="todate"  readonly="true"  name="surgeryreportform" size="10" />
                        <a  href="javascript:show_calendar('forms[0].todate');"
                        onmouseover="window.status='Date Picker';return true;" 
                        onmouseout="window.status='';return true;">
                        <img src="./DisabilityUITG/images/calendar.png" border="0"></a>
                    </td>
                <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<html:submit property="button"  value="Generate Reports"/></td></tr></table>
   
        </html:form>
    </BODY>
</html:html>
