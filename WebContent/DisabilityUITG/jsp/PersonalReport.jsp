<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@page import="java.util.ArrayList"%>
<%@ page import="java.util.*"%>
<%@page session="true"%>

        <html:html>
            
        
            <head>
                <%
                    int prenext=((Integer)session.getAttribute("start")).intValue();
                    int idvar=((Integer)session.getAttribute("color")).intValue();
                    String url=(String)request.getAttribute("url");
                    String prenextt=String.valueOf(prenext+1);
                    String preprev=String.valueOf(prenext-1);
                %> 
                <script language="javascript" >
                    function changecolor(colorvar)
                    {
                    var colorvar1=colorvar;
                    document.getElementById(colorvar1).style.color="red";
                    }     
                </script>
            </head>
        
        
        
        
        
            <body  onload="changecolor('<%=prenext%>')">
                <div style="border:1px solid #000000; 
                background-color: #FFFFFF;width:1000px; height:100%; overflow-x:scroll; 
                overflow-y:scroll;">
                <html:form action="mentalillness.do" method="post">

                    <table  border="0" align="center" cellspacing="1" cellpadding="4" class="tbl_bg2" width="100%">
                        <tr class="tbl_bg2_content">
                            <td bgcolor="white"><center><font size="3" face="vardana"><font color="#660000">
                            <b>Disability Person Details</b></font></center></td>
                        </tr> 
                    </table>
       
                    <table  border="0" align="center" cellspacing="1" cellpadding="5" class="tbl_bg2" width="100%">
                        <tr class="tbl_bg2_content" valign="middle">
                            <td bgcolor="white"><font size="2"><font color="#660000"><b>S.No</b></font></td>  
                            <td bgcolor="white"><font size="2"><font color="#660000"><b>Name</b></font></td>
                            <td bgcolor="white"><font size="2"><font color="#660000"><b>Age</b></font></td>
                            <td bgcolor="white"> <font size="2"><font color="#660000"><b>Sex</b></font></td>
                            <td bgcolor="white"><font size="2"><font color="#660000"><b>Address</b></font></td>
                            <td bgcolor="white"><font size="2"><font color="#660000"><b>Type of disability</b></font></td>
                            <td bgcolor="white"> <font size="2"><font color="#660000"><b>Sub type of Disability</b></font></td>
                            <td bgcolor="white"><font size="2"><font color="#660000"><b>Parts Involved</b></font></td>
                    
                            <td bgcolor="white"><font size="2"><font color="#660000"><b>Percentage of Disability</b></font></td>
                            
                            <td bgcolor="white"><font size="2"><font color="#660000"><b>Caste </b></font></td>
                            <td bgcolor="white"><font size="2"><font color="#660000"><b>Occupation</b></font></td>                    
                    
                    
                        </tr><% int i=(prenext-1)*10+1; 
                                session.setAttribute("counter",new Integer(i));%>
                
                        <logic:iterate id="details" name="reportlist"  scope="request"> 
                            <tr class="tbl_bg2_content"> 
                                <td  bgcolor="white"><%=i++%></td>
                                <td bgcolor="white"><bean:write name="details" property="name"/>        </td>
                                <td bgcolor="white" ><bean:write name="details" property="age"/>   </td>
                                <td  bgcolor="white" ><bean:write name="details" property="gender"/></td> 
                                <td  bgcolor="white" ><bean:write name="details" property="houseno"/>,
                                <bean:write name="details" property="districtname"/>, 
                                <bean:write name="details" property="mandalname"/> ,
                                <bean:write name="details" property="villagename"/> ,
                                <bean:write name="details" property="habitationname"/></td> 
                                <td  bgcolor="white" class="cellheader"><bean:write name="details" property="typeofdisability"/>     </td> 
                                <td bgcolor="white" class="cellheader"><bean:write name="details" property="subtype"/>     </td> 
                                <td  bgcolor="white" class="cellheader"><bean:write name="details" property="subsubtype"/>     </td>       
                          
                                <td  bgcolor="white" class="cellheader"><bean:write name="details" property="totalpercent"/></td>
                              
                                <td  bgcolor="white" class="cellheader"><bean:write name="details" property="caste"/>        </td>
                                <td bgcolor="white" class="cellheader"><bean:write name="details" property="occupation"/>   </td>
                   
                            </tr>
               
                        </logic:iterate>  
                
                    </table>
                    <table align="center">
                        <%   // ArrayList arl = new ArrayList();
                           //arl = (ArrayList) request.getAttribute("reportlist");

                           // application.setAttribute("arraylist",arl); %>
                        <a href="personalreport.xls"  target="_blank">Total
                        <img src="DisabilityUITG/images/excel.jpg" height="25" width="25"/></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     
     
     
                        
                        <a href="personalreport1.xls"  target="_blank">Individual Page
                        <img src="DisabilityUITG/images/excel.jpg" height="25" width="25"/></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        
                        
                        
                        
                        <br>

                        <logic:notEqual  name="removeback" value="yes" scope="request">
                            <a  href="<%=url+"&back=true&nexxt=false"%>" style="color:black"><<=Previous</a>
                        </logic:notEqual>
                        <logic:notEqual  name="removeprevious" value="yes" scope="request">
                            <a  href="<%=url+"&nexxt=false&back=false&start="+preprev%>" style="color:green">Previous</a>
                        </logic:notEqual>
                        <%   
                                 ArrayList devi=(ArrayList)request.getAttribute("numbers");
                                 Iterator itr=devi.iterator();
                                 while(itr.hasNext())
                                 {
                                 String prasad=itr.next().toString();       
                            %>
                            
                            <a  href="<%=url+"&nexxt=false&back=false&start="+prasad%>" id="<%=idvar%>" onclick="changecolor('<%=idvar%>')" style="color:blue"><%out.println(prasad);%></a> 
                            <% ++idvar;
                                 } %>
                        <logic:equal  name="removenext" value="no" scope="request">
                            <a  href="<%=url+"&nexxt=false&back=false&start="+prenextt%>" style="color:green">Next</a> 
                        </logic:equal>
                        <logic:notEqual  name="removenexxt" value="yes" scope="request">
                            <a  href="<%=url+"&nexxt=true&back=false"%>" style="color:black">Next=>></a>
                        </logic:notEqual> 
  
                        
                        
                        
                    </table> 
                


                </html:form></div>
        
        
       
        
        
        
        
     
        
        
        
        
            </body>
        </html:html>