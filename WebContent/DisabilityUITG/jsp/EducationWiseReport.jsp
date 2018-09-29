<%-- 
    Document   : EducationWiseReport
    Created on : Mar 20, 2011, 3:12:51 PM
    Author     : 509865
--%>



<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Iterator" %>
<%@page import="org.bf.disability.dto.FunctionalNeedReportDTO;" %>


<%          
            String district_id = (String) request.getParameter("districtId");
            String mandal_id = (String) request.getParameter("mandalId");
            String village_id = (String) request.getParameter("villageId");

            String dName = (String) request.getParameter("districtName");
            String mName = (String) request.getParameter("mandalName");
            String vName = (String) request.getParameter("villageName");
                       
            
            ArrayList educationwiseList = new ArrayList();
            educationwiseList = (ArrayList) request.getAttribute("educationwiseList");
            int illiterate = 0, belowTenth = 0, total = 0;
            int tenth = 0, inter = 0, diploma = 0;
            int graduate = 0, postGraduate = 0, notEntered = 0;
            Iterator iter = educationwiseList.iterator();
            while (iter.hasNext()) {
                FunctionalNeedReportDTO totalDTO = (FunctionalNeedReportDTO) iter.next();
                notEntered = notEntered + totalDTO.getNotEntered();
                illiterate = illiterate + totalDTO.getIlliterate();
                belowTenth = belowTenth + totalDTO.getBelowTenth();
                tenth = tenth + totalDTO.getTenth();
                inter = inter + totalDTO.getIntermediate();
                diploma = diploma + totalDTO.getDiplomaOrITI();
                graduate = graduate + totalDTO.getGraduate();
                postGraduate = postGraduate + totalDTO.getPostGraduate();
                total = total + totalDTO.getTotal();
            }
%>


<html:html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>
        <link rel="stylesheet" href="./DisabilityUITG/css/Sadarem-Style.css" type="text/css">
        <link rel="stylesheet" href="./DisabilityUITG/css/jqueryslidemenu.css" type="text/css">
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.2.6/jquery.min.js"></script>

        <style type="text/css">


            .arrowlistmenu{
                width: 185px; /*width of accordion menu*/
            }

            .arrowlistmenu .menuheader{ /*CSS class for menu headers in general (expanding or not!)*/
                                        font: bold 11px Verdana;
                                        color: #828282;


                                        padding-bottom:7px; /*header text is indented 10px*/
                                        cursor: hand;
                                        border-bottom:1px solid #828282;
                                        padding-left:5px;
            }

            .arrowlistmenu .openheader{ /*CSS class to apply to expandable header when it's expanded*/
                                        border-bottom:1px solid #000000;
                                        background-image:url(./DisabilityUITG/images/titlebar-active.png);
                                        padding-bottom:4px;
                                        color:#000000;
            }

            .arrowlistmenu ul{ /*CSS for UL of each sub menu*/
                               list-style-type: none;
                               margin: 0;
                               padding: 0;
                               margin-bottom: 8px; /*bottom spacing between each UL and rest of content*/
            }

            .arrowlistmenu ul li{
                padding-bottom: 2px; /*bottom spacing between menu items*/
            }

            .arrowlistmenu ul li a{
                color: #A70303;
                /*custom bullet list image*/
                display: block;
                padding:0px 0px 0px 5px;
                margin:0px 0px 0px 25px;
                text-decoration: none;
                line-height:20px;
                vertical-align:middle;
                font-weight: normal;
                border-bottom: 1px solid #dadada;
                list-style-image:url(./DisabilityUITG/images/arrowbullet.png);
                font-size: 11px;
            }

            .arrowlistmenu ul li a:visited{
                color: #ssdd33;
            }

            .arrowlistmenu ul li a:hover{ /*hover state CSS*/
                                          color: #ff9900;
                                          background-color:#FFFFFF; height:100%;
            }


        </style>


        <script language="javascript" src="./DisabilityUITG/css/jqueryslidemenu.js"></script>
        <script language="javascript" src="./DisabilityUITG/css/jquery.min.js"></script>

        
    </head>

    <body>

    
    <script language="JavaScript" src="./DisabilityUITG/js/date-picker"></script>

    <html:form action="functionalNeed.do?getFunctionalNeedReport=getFunctionalNeedReport" method="post" onsubmit="return selectedNames(),validate_form(this)"   >

        <table width="99%" border="0" cellspacing="0" cellpadding="10" align="center">
            <tr>
                <td height="445" align="center" bgcolor="#e4f5fd" valign="top">        

                  
                    <logic:present name="educationwiseList" scope="request">
                        <table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="90%">
                            <tr><td class="formhdbg" align="center" colspan="11">Educationwise Report</td></tr>
                            <tr>
                                <td align="center" class="formhdbg">S.No</td>
                                <% if (district_id.equals("0")) {%>
                                <td class="formhdbg" align="center">District</td>
                                <% } else if (mandal_id.equals("0")) {%>
                                <td class="formhdbg" align="center">Mandal</td>
                                <% } else if (village_id.equals("0")) {%>
                                <td class="formhdbg" align="center">Village</td>
                                <% } else if (!district_id.equals("0")) {%>
                                <td class="formhdbg" align="center">Habitation</td>
                                <% }%>
                                <td align="center" class="formhdbg">Not Entered</td>
                                <td align="center" class="formhdbg">Illirerate</td>
                                <td align="center" class="formhdbg">Below 10th</td>
                                <td align="center" class="formhdbg">10th Class</td>
                                <td align="center" class="formhdbg">Inter</td>
                                <td align="center" class="formhdbg">Diploma</td>
                                <td align="center" class="formhdbg">Graduate</td>
                                <td align="center" class="formhdbg">Post Graduate</td>
                                <td align="center" class="formhdbg">Total</td>
                            </tr>

                            <% int i = 0;%>
                            <logic:iterate id="modify" name="educationwiseList" scope="request">
                                <%if (i % 2 == 1) {%>
                                <tr>
                                    <td  align="center"  class="formbg" ><%=++i%></td>
                                    <logic:notEmpty name="modify" property="districtName">
                                        <td  class="formbg"><bean:write name="modify" property="districtName"/></td>
                                    </logic:notEmpty>
                                    <logic:notEmpty name="modify" property="mandalName">
                                        <td  class="formbg"><bean:write name="modify" property="mandalName"/></td>
                                    </logic:notEmpty>
                                    <logic:notEmpty name="modify" property="villageName">
                                        <td  class="formbg"><bean:write name="modify" property="villageName"/></td>
                                    </logic:notEmpty>
                                    <logic:notEmpty name="modify" property="habitationName">
                                        <td  class="formbg"><bean:write name="modify" property="habitationName"/></td>
                                    </logic:notEmpty>
                                        <td class="formbg"><bean:write name="modify" property="notEntered"/></td>
                                        <td class="formbg"><bean:write name="modify" property="illiterate"/></td>
                                        <td class="formbg"><bean:write name="modify" property="belowTenth"/></td>
                                        <td class="formbg"><bean:write name="modify" property="tenth"/></td>
                                        <td class="formbg"><bean:write name="modify" property="intermediate"/></td>
                                        <td class="formbg"><bean:write name="modify" property="diplomaOrITI"/></td>
                                        <td class="formbg"><bean:write name="modify" property="graduate"/></td>
                                        <td class="formbg"><bean:write name="modify" property="postGraduate"/></td>
                                        <td class="formbg"><bean:write name="modify" property="total"/></td>
                                </tr>
                                <%} else {%>
                                <tr>
                                    <td  align="center"  class="formaltbg" ><%=++i%></td>
                                    <logic:notEmpty name="modify" property="districtName">
                                        <td  class="formaltbg"><bean:write name="modify" property="districtName"/></td>
                                    </logic:notEmpty>
                                    <logic:notEmpty name="modify" property="mandalName">
                                        <td  class="formaltbg"><bean:write name="modify" property="mandalName"/></td>
                                    </logic:notEmpty>
                                    <logic:notEmpty name="modify" property="villageName">
                                        <td  class="formaltbg"><bean:write name="modify" property="villageName"/></td>
                                    </logic:notEmpty>
                                    <logic:notEmpty name="modify" property="habitationName">
                                        <td  class="formaltbg"><bean:write name="modify" property="habitationName"/></td>
                                    </logic:notEmpty>
                                        <td class="formaltbg"><bean:write name="modify" property="notEntered"/></td>
                                        <td class="formaltbg"><bean:write name="modify" property="illiterate"/></td>
                                        <td class="formaltbg"><bean:write name="modify" property="belowTenth"/></td>
                                        <td class="formaltbg"><bean:write name="modify" property="tenth"/></td>
                                        <td class="formaltbg"><bean:write name="modify" property="intermediate"/></td>
                                        <td class="formaltbg"><bean:write name="modify" property="diplomaOrITI"/></td>
                                        <td class="formaltbg"><bean:write name="modify" property="graduate"/></td>
                                        <td class="formaltbg"><bean:write name="modify" property="postGraduate"/></td>
                                        <td class="formaltbg"><bean:write name="modify" property="total"/></td>
                                </tr>
                                <%}%>
                            </logic:iterate>
                            <tr>
                                <td class="formhdbg" colspan="2" align="center">Total</td>
                                <td class="formhdbg"><%=notEntered%></td>
                                <td class="formhdbg"><%=illiterate %></td>
                                <td class="formhdbg"><%=belowTenth %></td>
                                <td class="formhdbg"><%=tenth %></td>
                                <td class="formhdbg"><%=inter %></td>
                                <td class="formhdbg"><%=diploma %></td>
                                <td class="formhdbg"><%=graduate %></td>
                                <td class="formhdbg"><%=postGraduate %></td>
                                <td class="formhdbg"><%= total%></td>
                            </tr>
                        </table><br>
                        <table align="center">                            
                            <a href="getEducationWiseReportExcel.xls?getEducationWiseReport=getEducationWiseReport&villageId=<%=village_id%>&mandalId=<%=mandal_id%>&districtId=<%=district_id%>&villageName=<%=vName%>&mandalName=<%=mName%>&districtName=<%=dName%>" target="_blank">
                                Export To Excel <img src="DisabilityUITG/images/excel.jpg"
                                                     height="25" width="25"/></a> &nbsp; &nbsp; &nbsp;

                            <a href="getEducationWiseReportPrint.xls?getEducationWiseReport=getEducationWiseReport&villageId=<%=village_id%>&mandalId=<%=mandal_id%>&districtId=<%=district_id%>&villageName=<%=vName%>&mandalName=<%=mName%>&districtName=<%=dName%>" target="_blank">
                                Print<img src="DisabilityUITG/images/print.gif"  height="25" width="25"/></a>

                        </table>
                    </logic:present>
                </td>
            </tr>
        </table>
    </html:form>
</body>
</html:html>


