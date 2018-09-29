
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri= "/WEB-INF/struts-bean.tld" prefix="bean"%>
<%
 String base64Photo = (String) request.getAttribute("base64Photo");
 String district_name = (String) request.getAttribute("district_name");
 %>
<html>
    <body>

        <form><br><br><br>
            <table width="100%" border="0" >
                <tr><td class="columnHeight20"></td></tr>
                <logic:notPresent name="dvaluess">
                    <tr><td align="center" class="labelBlue"><font  size="4">Please Make Sure that you made a Note of SADAREM ID<br>
                                (To Proceed Click on the Below Number. Before Upload Photo)</font></td></tr>

                    <tr><td align="center"><a href="getDisabilityTypesForPartAInsert.do"><font  size="10" color="red">
                                    <%out.println(session.getAttribute("personcode"));%></font></a></td>
                    </tr>
                    <tr><td class="columnHeight20"></td></tr>
                    <%
                    if(base64Photo!=null && !base64Photo.equalsIgnoreCase("-"))
                    {
                    %>              <!--  D:\SADAREMTG\SADAREMDOC\18310231611260001\Profile -->
                    <tr><td class="labelBlue" style="text-align: center">
                            <%-- <img align="right" src="./DisabilityUITG/uploadedphotos/<%=session.getAttribute("personcode")%>/Profilepic.JPG" width="100" height="100" onerror="this.src='<%=request.getContextPath()%>/images/noimage.png'"> --%>
                             <img  align="right" src="<%=request.getContextPath()%>/dispimg.do?action=showsadaremimg&sadaremid=<%=session.getAttribute("personcode")%>"  width="100" height="100" onerror="this.src='<%=request.getContextPath()%>/images/noimage.png'"/>
                    </td>
                    </tr>
                    <%
                     }
                    else if(base64Photo!=null && base64Photo.equalsIgnoreCase("-"))
                    {
                    %>
                    <tr><td class="labelBlue" align="center"><font size="3">To Upload Photo Please </font>
                            <a href="javascript:void(0);"  onClick=window.open("fileupload.do","Ratting","width=500,height=200,0,status=0");><u>Click here</u></a>
                        </td>
                    </tr>
                    <%}%>
                    <tr><td class="labelBlue" align="center"><font size="3">Acknowledgement </font>
                            <a href="javascript:void(0);"  onClick=window.open("insertPartAaction.do?insertPersonalDetails=PersonCodeAcknowledgement","Ratting","width=1000,height=600,0,status=0"); ><u>Click here</u></a>
                        </td></tr>
                    </logic:notPresent>
                    <logic:present name="dvaluess">
                    <tr><td class="labelBlue" align="center"><font size="3">Select Disabilities Exceeded for Selete Camp</font>

                        </td>
                    </tr>
                </logic:present>
            </table>
			<button type="button" id="" class="btn btn-success col-xs-4" onclick="window.print();"><b>Print</b></button>
        </form>
    </body>

</html> 
               