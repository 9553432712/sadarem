<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@page import="java.util.*" %>


<%

            Double developmental = (Double) request.getAttribute("developmentalresult");
            Double vineland = (Double) request.getAttribute("vinelandresult");
            Double binetkamal = (Double) request.getAttribute("binetkamalresult");
            Double patDouble = (Double) request.getAttribute("pat");
            Double bhatiaDouble = (Double) request.getAttribute("bhatia");
            Double sfbiqresult = (Double) request.getAttribute("sfbiq");
           Double malinsresult = (Double) request.getAttribute("misiciq");

           /* Double developmental = 0.0;
            Double vineland = 0.0;
            Double binetkamal = 0.0;
            Double patDouble = 0.0;
            Double bhatiaDouble = 0.0;
            Double sfbiqresult = 0.0;
            Double malinsresult = 0.0;*/
            

            double malinsformresult = Math.round(malinsresult.doubleValue());
            double seguinformresult = Math.round(sfbiqresult.doubleValue());
            double developmentalresult = Math.round(developmental.doubleValue());
            double vinelandresult = Math.round(vineland.doubleValue());
            double binetkamalresult = Math.round(binetkamal.doubleValue());
            double pat = Math.round(patDouble.doubleValue());
            double bhatia = Math.round(bhatiaDouble.doubleValue());

%>

     <html:html>
     <HEAD>
 
     </HEAD>
     <script language="javascript" >
    function goBack()
{
            document.forms[0].action="LocomotorSublinkForwdAction.do?getDisabilityPercentages=getDisabilityPercentages";
            document.forms[0].method = "post";
            document.forms[0].submit();
}
</script>

     


 <body>
   
<form action="" >
<%--    --%>
     <table  align="center" cellspacing="1" cellpadding="0" class="inputform" width="90%">
        <tr> 
            <th class="heading" align="center">ADD PSYCHOLOGICAL ASSESSMENT</th>
        </tr>
     </table>
          
           
    <table  align="center" cellspacing="1" cellpadding="0" class="inputform" width="90%">

        <tr>
            <td><font size="2"color=#FF3300><b>Click below  to Conduct Test</b></font></td>
         
            <td><font size="2"color=#FF3300><b>Please ask to doctor which test to click for final IQ.</b></font></td>
        </tr>    


                
 <tr>
                <td width="40%"><ul><font size="3"color=#FF3300><b><li><a href="mrDevelopmentalScreeningTestAddJsp.do"><font size="2"> Developmental Screening Test (D.S.T)</li></a></b></font>
                </td>
               <td width="40%"> <a href="InsertMentalretardationForwdAction.do?iqforall=<%=developmentalresult%>&testname=D.S.T"><font size="4"> <font size="3" color="red"><b><%=developmentalresult%></b></font></a><br><br>
               </td>
 </tr>
           <tr> 
               <td width="40%"><ul><font size="3"color=#FF3300><b><li><a href="vsmsscreeningtsetinsert.do"><font size="2"> Vineland Social Maturity Scale (V.S.M.S)</li></a></b></font>
               </td>
         <td width="40%"> <a href="InsertMentalretardationForwdAction.do?iqforall=<%=vinelandresult%>&testname=V.S.M.S"><font size="4"> <font size="3" color="red"><b><%=vinelandresult%></b></font></a><br><br>  
         </td>
           </tr>
                <tr> 
<td width="40%"><ul> <font size="3"color=#FF3300><b><li><a href="MRBinetKamatDetailedTestDetailsInsertForwdAction.do"><font size="2"> Binet Kamat Test of Intelligence (B.K.T)</li></a></b></font>
</td>
<td width="40%"> <a href="InsertMentalretardationForwdAction.do?iqforall=<%=binetkamalresult%>&testname=B.K.T"><font size="4"> <font size="3" color="red"><b><%=binetkamalresult%></b></font></a><br><br>  
</td>
                </tr>
                 <tr>
<td width="40%"><ul><font size="3"color=#FF3300><b><li><a href="Malinstest.do"><font size="2"> Malins Intelligence Scale for Indian Children (M.I.S.I.C)</li></a></b></font>
</td>
<td width="40%"> <a href="InsertMentalretardationForwdAction.do?iqforall=<%=malinsformresult%>&testname=M.I.S.I.C"><font size="4"> <font size="3" color="red"><b><%=malinsformresult%></b></font></a><br><br>  
</td>
                 </tr>
                <tr> 
<td width="40%"><ul><font size="3"color=#FF3300><b><li><a href="seguintest.do"><font size="2"> Seguin Form Board (S.F.B)</li></a></b></font>
</td>
<td width="40%"> <a href="InsertMentalretardationForwdAction.do?iqforall=<%=seguinformresult%>&testname=S.F.B"><font size="4"> <font size="3" color="red"><b><%=seguinformresult%></b></font></a><br><br>  
</td>
                </tr>
                 <tr><td width="40%"><ul><font size="3"color=#FF3300><b><li><a href="alexandertest.do"><font size="2"> Alexander's Pass Along Test (P.A.T)</li></a></b></font>
                 </td>
<td width="40%"> <a href="InsertMentalretardationForwdAction.do?iqforall=<%=pat%>&testname=P.A.T"><font size="4"> <font size="3" color="red"><b> <%=pat%> </b></font></a><br><br> 
</td>
                 </tr>
               <tr>  <td width="40%"><ul><font size="3"color=#FF3300><b><li><a href="bhatiastest.do"><font size="2"> Bhatias Battery of Intelligence Tests</li></a></b></font>
               </td>
<td width="40%"> <a href="InsertMentalretardationForwdAction.do?iqforall=<%=bhatia%>&testname=B.B.I.T"><font size="4"> <font size="3" color="red"><b><%=bhatia%></b></font></a><br><br>  
</td>
               </tr>
               </TABLE>
      

</form>

      </body>
</html:html>
   
