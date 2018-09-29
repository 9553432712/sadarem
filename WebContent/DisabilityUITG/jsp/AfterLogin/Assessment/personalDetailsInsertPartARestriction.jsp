<%--
    Document   : personalDetailsInsertPartARestriction
    Created on : Sep 7, 2014, 10:38:36 PM
    Author     : 310926
--%>


<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<% String result=null;
if(request.getAttribute("result")!=null){
   result=(String)request.getAttribute("result");}
%>
<html>
    <body>

        <form><br><br><br>
         
            <table width="100%" border="0" >
                <tr><td class="columnHeight20"></td></tr>
                <logic:notPresent name="dvaluess">
                    <tr><td align="center" class="labelBlue"><font  size="4">Please Make Sure that you made a Note of SADAREM ID
                             </font></td></tr>

                    <tr><td align="center"><font  size="10" color="red">
                                    <%out.println(session.getAttribute("personcode"));%></font></td>
                    </tr>
                    <tr><td class="columnHeight20"></td></tr>
                    <tr><td class="columnHeight20"></td></tr>

                    <tr><td class="labelBlue" align="center"><font size="3">Acknowledgement</font>
                            <a href="javascript:void(0);"  onClick=window.open("insertPartAaction.do?insertPersonalDetails=PersonCodeAcknowledgement&result=<%=result%>","Ratting","width=1000,height=600,0,status=0"); ><u>Click here</u></a>
                        </td></tr>
                    </logic:notPresent>
                    <logic:present name="dvaluess">
                    <tr><td class="labelBlue" align="center"><font size="3">Select Disabilities Exceeded on the selected Camp for the date</font>

                        </td>
                    </tr>

                   
                </logic:present>
            </table>

        </form>
    </body>

</html>

