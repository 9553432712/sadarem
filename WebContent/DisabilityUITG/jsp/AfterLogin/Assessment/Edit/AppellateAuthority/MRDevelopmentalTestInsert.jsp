<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%
Integer year= (Integer)request.getAttribute("dstinsertyears");
Double month= (Double)request.getAttribute("dstinsertmonths");



%>


     <html:html>
     <HEAD>
 
     <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        

      </HEAD>
     <script language="javascript" >
   
 
</script>
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
            if (validate_required(dstyear,"Enter Years")==false)
            {
            dstyear.focus();
            return false;
            }
            if (validate_required(dstmonth,"Enter Months")==false)
            {
            dstmonth.focus();
            return false;
            }
               else{
       document.getElementById('addButton').disabled = true;
       return true;
        }
                             
            }
            }
   
           
        </script>
     
       
      <body>
       <html:errors/>
      <html:form action="mrdevelopmenttestaction.do?insertDevelopmentalTestScreening=insertDevelopmentalTestScreening" onsubmit="return validate_form(this)"  styleId="mentalretardationactiontestform">
          <table  align="center"  border="0" cellspacing="1" cellpadding="0" class="inputform" width="50%">
       <tr>
           <th colspan="4">1.&nbsp;&nbsp;Add Developmental Screening Test (D.S.T)</th>
       </tr>
      
       <tr> 
           <td align="CENTER" ><br>Developmental Age</td>
     
       </tr>
       <tr>
           <td align="CENTER" ><br><b>Years:</b><html:text property="dstyear" value="<%=String.valueOf(year)%>" size="5" readonly="true" />
               &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              <b>Months:</b><html:text property="dstmonth" value="<%=String.valueOf(month)%>"  readonly="true"/></td>
      
       </tr>
    <tr>
        <th colspan="4"><html:submit  value="Add" styleId="addButton" styleClass="button"/></th>
	
    </tr>
        </table>
      </html:form>
      </body>
   
</html:html>
   

