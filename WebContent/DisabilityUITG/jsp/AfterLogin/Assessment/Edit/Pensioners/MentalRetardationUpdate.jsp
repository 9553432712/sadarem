
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@page import="java.util.*" %>


<%


    Double patDouble= (Double)request.getAttribute("pat");
    Double bhatiaDouble= (Double)request.getAttribute("bhatia");


    Double  developmental= (Double)request.getAttribute("developmentalresult");
    Double vineland= (Double)request.getAttribute("vinelandresult");
    Double binetkamal= (Double)request.getAttribute("binetkamalresult");


    double developmentalresult =Math.round(developmental.doubleValue());
    double vinelandresult =Math.round(vineland.doubleValue());
    double binetkamalresult =Math.round(binetkamal.doubleValue());
    Double  sfbiqresult= (Double)request.getAttribute("sfbiq");
    Double  malinsresult= (Double)request.getAttribute("misiciq");

    double seguinformresult =Math.round(sfbiqresult.doubleValue());
    double malinsformresult =Math.round(malinsresult.doubleValue());



    double pat =Math.round(patDouble.doubleValue());

    double bhatia =Math.round(bhatiaDouble.doubleValue());
    String iqvalue=(String)request.getAttribute("iqvalue");
    String iqtestname= (String)request.getAttribute("iqtestname");


%>
     <html:html>
         <HEAD><br><br>
 
         </HEAD>
 
         <script language="javascript" >
             function goBack()
{
            document.forms[0].action="LocomotorSublinkUpdateForwdAction.do?getDisabilityPercentages=getDisabilityPercentages";
            document.forms[0].method = "post";
            document.forms[0].submit();
}
         </script>
     


         <body>
             <form action="" >

             
                 <table  align="center" cellspacing="0" cellpadding="0" class="inputform" width="85%">
                 <tr> 
                     <th class="heading"><center>UPDATE PSYCHOLOGICAL ASSESSMENT</center></th>
                 </tr>
                 </table>
          
           
                 <table  align="center" cellspacing="0" cellpadding="0" class="inputform" width="85%">
                     
                     <% if(iqtestname!=null) { %>
                     <tr>
                         <td width="40%">
                             <font size="2"color=#FF3300><b>You have selected<u><font color="blue"> <%=iqtestname%></font></u>
                                     test and its Iq is <u><font color="blue"><%=iqvalue%></font></u></b></font>
                         </td>
                         <td width="40%">&nbsp;
                         </td>
                     </tr>
                     <%}%>
                 </table>
                        <table  align="center" cellspacing="1" cellpadding="0" class="inputform" width="85%">
                     <tr>
                         <td width="40%">
                             <font   color="red">Click below  to Conduct Test</font>
                         </td>
                         <td width="40%"><font color="red">&nbsp;&nbsp;&nbsp;&nbsp;Please ask to doctor which test to click for final IQ</font></td>
                     </tr>
                 
                 <tr>   

                     <td width="40%" class="labelBlue"><ul><font size="3"color=#FF3300><b><li><a href="MRDevelopmentalScreeningTestSelectAction.do?parameter=selectMRDevelopmentalScreeningTest"><font size="2"> Developmental Screening Test (D.S.T)</li></a></b></font>
                     </td>
                     <td width="40%"><a href="mentalretarselect.do?selectMentalRetardation=selectMentalRetardation&iqforall=<%=developmentalresult%>&testname=D.S.T"><font size="4"><font size="3" color="red"><b><%=developmentalresult%></b></font></a><br><br>  
                     </td>
                 </tr>
                 <tr>
                     <td width="40%"><ul><font size="3"color=#FF3300><b><li><a href="vsmsscreeningtestselect.do?getVsmsScreeningTestDetails=getVsmsScreeningTestDetails"><font size="2"> Vineland Social Maturity Scale (V.S.M.S)</li></a></b></font>
                     </td>
                     <td width="40%"> <a href="mentalretarselect.do?selectMentalRetardation=selectMentalRetardation&iqforall=<%=vinelandresult%>&testname=V.S.M.S"><font size="4"> <font size="3" color="red"><b><%=vinelandresult%></b></font></a><br><br>
                     </td>
                 </tr>
                 <tr> 
                     <td width="40%"><ul><font size="3"color=#FF3300><b><li><a href="getMRBinetKamatDetailedTestDetails.do?getMRBinetKamatDetailedTestDetails=getMRBinetKamatDetailedTestDetails"><font size="2"> Binet Kamat Test of Intelligence (B.K.T)</li></a></b></font>
                     </td>
                     <td width="40%"> <a href="mentalretarselect.do?selectMentalRetardation=selectMentalRetardation&iqforall=<%=binetkamalresult%>&testname=B.K.T"><font size="4"> <font size="3" color="red"><b><%=binetkamalresult%></b></font></a><br><br>  
                     </td>
                 </tr>
                 <tr>  
                     <td width="40%"><ul><font size="3"color=#FF3300><b><li><a href="MRMalinsdetailsselect.do?MRmalinstestdetailsselect=MRmalinstestdetailsselect"><font size="2"> Malins Intelligence Scale for Indian Children (M.I.S.I.C)</li></a></b></font>
                     </td>
                     <td width="40%"> <a href="mentalretarselect.do?selectMentalRetardation=selectMentalRetardation&iqforall=<%=malinsformresult%>&testname=M.I.S.I.C"><font size="4"> <font size="3" color="red"><b><%=malinsformresult%></b></font></a><br><br>  
                     </td>
                 </tr>
                 <tr> 
                     <td width="40%"><ul><font size="3"color=#FF3300><b><li><a href="MRSfbdetailsselect.do?MRseguintestdetailsselect=MRseguintestdetailsselect"><font size="2"> Seguin Form Board (S.F.B)</li></a></b></font>
                     </td>
                     <td width="40%"> <a href="mentalretarselect.do?selectMentalRetardation=selectMentalRetardation&iqforall=<%=seguinformresult%>&testname=S.F.B"><font size="4"> <font size="3" color="red"><b><%=seguinformresult%></b></font></a><br><br> 
                     </td>
                 </tr>
              
                 <tr>   
                     <td width="40%"><ul><font size="3"color=#FF3300><b><li><a href="getMRAlexanderTestDetails.do?getMRAlexanderTestDetails=getMRAlexanderTestDetails"><font size="2"> Alexander's Pass Along Test (P.A.T)</li></a></b></font>
                     </td>
                     <td width="40%"> <a href="mentalretarselect.do?selectMentalRetardation=selectMentalRetardation&iqforall=<%=pat%>&testname=P.A.T"><font size="4">  <font size="3" color="red"><b><%=pat%> </b></font></a><br><br>  
                     </td>
                 </tr>
                 <tr>              
                     <td width="40%"><ul><font size="3"color=#FF3300><b><li><a href="getBhatiaTestDetails.do?getBhatiaTestDetails=getBhatiaTestDetails"><font size="2"> Bhatias Battery of Intelligence Tests</li></a></b></font>
                     </td>
                     <td width="40%"> <a href="mentalretarselect.do?selectMentalRetardation=selectMentalRetardation&iqforall=<%=bhatia%>&testname=B.B.I.T"><font size="4"> <font size="3" color="red"><b><%=bhatia%></b></font></a><br><br> 
                     </td>
                 </tr> 
            <tr>
                <th colspan="8"><html:button property=""  value="Back" onclick="goBack()" styleClass="button" /></th>
                </tr>
                     </table>
      
   

             </form>
         </body>
     </html:html>
   
