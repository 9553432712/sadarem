<%@page contentType="text/html"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%
            String personcode = (String) session.getAttribute("personcode");
            String name = (String) session.getAttribute("Name");
%>
<head><br>
<script language="javascript" >
    function goBack()
    {
        document.forms[0].action="showCalculationsAction.do";
        document.forms[0].submit();
    }
</script>
<LINK REL="stylesheet" HREF="./DisabilityUITG/css/printeformatecss.css">
</head>
<html:html>

    <body>

        <form>

            <logic:notEmpty  name="bKTBean" scope="request">
                <table  align="center" cellspacing="1" cellpadding="8" class="innerTable" width="85%">
                    <tr><td colspan="3"  align="right" class="label"><b>ID No.&nbsp;<font color="blue"><%=personcode%></font></b></td></tr>
                    <tr><td colspan="3"  align="right" class="label"><b>Name.&nbsp;<font color="blue"><%=name%></font></b></td></tr>

                    <tr align="center">
                        <td  class="heading">BKT Detailed Test Calculations
                        </td>
                    </tr>
                </table>
                <table  align="center" cellspacing="1" cellpadding="8"  class="innerTable1" width="85%">
                    <tr>
                        <td colspan="14">&nbsp;</td>
                    </tr>
                    <tr >
                        <td  rowspan="3"  align="center" class="labelBlue"><b>Item No
                        </td>
                        <td width="90%"colspan="14" align="center" class="labelBlue"><b>AGE LEVELS
                        </td>
                    <tr>
                    <tr >
                        <td align="center" ><b>3
                        </td>
                        <td align="center"><b>4
                        </td>
                        <td align="center" ><b>5
                        </td>
                        <td align="center" ><b>6
                        </td>
                        <td align="center"><b>7
                        </td>
                        <td align="center"><b>8
                        </td>
                        <td align="center"><b>9
                        </td>
                        <td align="center"><b>10
                        </td>
                        <td align="center"><b>12
                        </td>
                        <td align="center"><b>14
                        </td>
                        <td align="center"><b>16
                        </td>
                        <td align="center"><b>19
                        </td >
                        <td align="center"><b>22
                        </td>
                    </tr>
                    <tr>
                        <td align="center" ><b>1
                        </td>
                        <td align="center">
                            <logic:notEqual name="bKTBean" property="ageLevel3_Item1" value="0">
                                <bean:write name="bKTBean" property="ageLevel3_Item1"/>
                            </logic:notEqual>
                        </td>
                        <td align="center">
                            <logic:notEqual name="bKTBean" property="ageLevel4_Item1" value="0">
                                <bean:write name="bKTBean" property="ageLevel4_Item1"/>
                            </logic:notEqual>
                        </td>
                        <td align="center">
                            <logic:notEqual name="bKTBean" property="ageLevel5_Item1" value="0">
                                <bean:write name="bKTBean" property="ageLevel5_Item1"/>
                            </logic:notEqual>
                        </td>
                        <td align="center">
                            <logic:notEqual name="bKTBean" property="ageLevel6_Item1" value="0">
                                <bean:write name="bKTBean" property="ageLevel6_Item1"/>
                            </logic:notEqual>
                        </td>
                        <td align="center">
                            <logic:notEqual name="bKTBean" property="ageLevel7_Item1" value="0">
                                <bean:write name="bKTBean" property="ageLevel7_Item1"/>
                            </logic:notEqual>
                        </td>
                        <td align="center">
                            <logic:notEqual name="bKTBean" property="ageLevel8_Item1" value="0">
                                <bean:write name="bKTBean" property="ageLevel8_Item1"/>
                            </logic:notEqual>
                        </td>
                        <td align="center">
                            <logic:notEqual name="bKTBean" property="ageLevel9_Item1" value="0">
                                <bean:write name="bKTBean" property="ageLevel9_Item1"/>
                            </logic:notEqual>
                        </td>
                        <td align="center">
                            <logic:notEqual name="bKTBean" property="ageLevel10_Item1" value="0">
                                <bean:write name="bKTBean" property="ageLevel10_Item1"/>
                            </logic:notEqual>
                        </td>
                        <td align="center">
                            <logic:notEqual name="bKTBean" property="ageLevel12_Item1" value="0">
                                <bean:write name="bKTBean" property="ageLevel12_Item1"/>
                            </logic:notEqual>
                        </td>
                        <td align="center">
                            <logic:notEqual name="bKTBean" property="ageLevel14_Item1" value="0">
                                <bean:write name="bKTBean" property="ageLevel14_Item1"/>
                            </logic:notEqual>
                        </td>
                        <td align="center">
                            <logic:notEqual name="bKTBean" property="ageLevel16_Item1" value="0">
                                <bean:write name="bKTBean" property="ageLevel16_Item1"/>
                            </logic:notEqual>
                        </td>
                        <td align="center">
                            <logic:notEqual name="bKTBean" property="ageLevel19_Item1" value="0">
                                <bean:write name="bKTBean" property="ageLevel19_Item1"/>
                            </logic:notEqual>
                        </td>
                        <td align="center">
                            <logic:notEqual name="bKTBean" property="ageLevel22_Item1" value="0">
                                <bean:write name="bKTBean" property="ageLevel22_Item1"/>
                            </logic:notEqual>
                        </td>
                    </tr>
                    <tr>
                        <td align="center" ><b>2</b></td>
                        <td align="center">
                            <logic:notEqual name="bKTBean" property="ageLevel3_Item2" value="0">
                                <bean:write name="bKTBean" property="ageLevel3_Item2"/>
                            </logic:notEqual>

                        </td>
                        <td align="center">
                            <logic:notEqual name="bKTBean" property="ageLevel4_Item2" value="0">
                                <bean:write name="bKTBean" property="ageLevel4_Item2"/>
                            </logic:notEqual>

                        </td>
                        <td align="center">
                            <logic:notEqual name="bKTBean" property="ageLevel5_Item2" value="0">
                                <bean:write name="bKTBean" property="ageLevel5_Item2"/>
                            </logic:notEqual>

                        </td>
                        <td align="center">
                            <logic:notEqual name="bKTBean" property="ageLevel6_Item2" value="0">
                                <bean:write name="bKTBean" property="ageLevel6_Item2"/>
                            </logic:notEqual>

                        </td>
                        <td align="center">
                            <logic:notEqual name="bKTBean" property="ageLevel7_Item2" value="0">
                                <bean:write name="bKTBean" property="ageLevel7_Item2"/>
                            </logic:notEqual>

                        </td>
                        <td align="center">
                            <logic:notEqual name="bKTBean" property="ageLevel8_Item2" value="0">
                                <bean:write name="bKTBean" property="ageLevel8_Item2"/>
                            </logic:notEqual>

                        </td>
                        <td align="center">
                            <logic:notEqual name="bKTBean" property="ageLevel9_Item2" value="0">
                                <bean:write name="bKTBean" property="ageLevel9_Item2"/>
                            </logic:notEqual>

                        </td>
                        <td align="center">
                            <logic:notEqual name="bKTBean" property="ageLevel10_Item2" value="0">
                                <bean:write name="bKTBean" property="ageLevel10_Item2"/>
                            </logic:notEqual>

                        </td>
                        <td align="center">
                            <logic:notEqual name="bKTBean" property="ageLevel12_Item2" value="0">
                                <bean:write name="bKTBean" property="ageLevel12_Item2"/>
                            </logic:notEqual>

                        </td>
                        <td align="center">
                            <logic:notEqual name="bKTBean" property="ageLevel14_Item2" value="0">
                                <bean:write name="bKTBean" property="ageLevel14_Item2"/>
                            </logic:notEqual>

                        </td>
                        <td align="center">
                            <logic:notEqual name="bKTBean" property="ageLevel16_Item2" value="0">
                                <bean:write name="bKTBean" property="ageLevel16_Item2"/>
                            </logic:notEqual>

                        </td>
                        <td align="center">
                            <logic:notEqual name="bKTBean" property="ageLevel19_Item2" value="0">
                                <bean:write name="bKTBean" property="ageLevel19_Item2"/>
                            </logic:notEqual>

                        </td>
                        <td align="center">
                            <logic:notEqual name="bKTBean" property="ageLevel22_Item2" value="0">
                                <bean:write name="bKTBean" property="ageLevel22_Item2"/>
                            </logic:notEqual>

                        </td>
                    </tr>
                    <tr>
                        <td align="center" ><b>3
                        </td>
                        <td align="center">
                            <logic:notEqual name="bKTBean" property="ageLevel3_Item3" value="0">
                                <bean:write name="bKTBean" property="ageLevel3_Item3"/>
                            </logic:notEqual>

                        </td>
                        <td align="center">
                            <logic:notEqual name="bKTBean" property="ageLevel4_Item3" value="0">
                                <bean:write name="bKTBean" property="ageLevel4_Item3"/>
                            </logic:notEqual>

                        </td>
                        <td align="center">
                            <logic:notEqual name="bKTBean" property="ageLevel5_Item3" value="0">
                                <bean:write name="bKTBean" property="ageLevel5_Item3"/>
                            </logic:notEqual>

                        </td>
                        <td align="center">
                            <logic:notEqual name="bKTBean" property="ageLevel6_Item3" value="0">
                                <bean:write name="bKTBean" property="ageLevel6_Item3"/>
                            </logic:notEqual>

                        </td>
                        <td align="center">
                            <logic:notEqual name="bKTBean" property="ageLevel7_Item3" value="0">
                                <bean:write name="bKTBean" property="ageLevel7_Item3"/>
                            </logic:notEqual>

                        </td>
                        <td align="center">
                            <logic:notEqual name="bKTBean" property="ageLevel8_Item3" value="0">
                                <bean:write name="bKTBean" property="ageLevel8_Item3"/>
                            </logic:notEqual>

                        </td>
                        <td align="center">
                            <logic:notEqual name="bKTBean" property="ageLevel9_Item3" value="0">
                                <bean:write name="bKTBean" property="ageLevel9_Item3"/>
                            </logic:notEqual>

                        </td>
                        <td align="center">
                            <logic:notEqual name="bKTBean" property="ageLevel10_Item3" value="0">
                                <bean:write name="bKTBean" property="ageLevel10_Item3"/>
                            </logic:notEqual>

                        </td>
                        <td align="center">
                            <logic:notEqual name="bKTBean" property="ageLevel12_Item3" value="0">
                                <bean:write name="bKTBean" property="ageLevel12_Item3"/>
                            </logic:notEqual>

                        </td>
                        <td align="center">
                            <logic:notEqual name="bKTBean" property="ageLevel14_Item3" value="0">
                                <bean:write name="bKTBean" property="ageLevel14_Item3"/>
                            </logic:notEqual>

                        </td>
                        <td align="center">
                            <logic:notEqual name="bKTBean" property="ageLevel16_Item3" value="0">
                                <bean:write name="bKTBean" property="ageLevel16_Item3"/>
                            </logic:notEqual>

                        </td>
                        <td align="center">
                            <logic:notEqual name="bKTBean" property="ageLevel19_Item3" value="0">
                                <bean:write name="bKTBean" property="ageLevel19_Item3"/>
                            </logic:notEqual>

                        </td>
                        <td align="center">
                            <logic:notEqual name="bKTBean" property="ageLevel22_Item3" value="0">
                                <bean:write name="bKTBean" property="ageLevel22_Item3"/>
                            </logic:notEqual>

                        </td>
                    </tr>
                    <tr>
                        <td align="center" ><b>4
                        </td>
                        <td align="center">
                            <logic:notEqual name="bKTBean" property="ageLevel3_Item4" value="0">
                                <bean:write name="bKTBean" property="ageLevel3_Item4"/>
                            </logic:notEqual>

                        </td>
                        <td align="center">
                            <logic:notEqual name="bKTBean" property="ageLevel4_Item4" value="0">
                                <bean:write name="bKTBean" property="ageLevel4_Item4"/>
                            </logic:notEqual>

                        </td>
                        <td align="center">
                            <logic:notEqual name="bKTBean" property="ageLevel5_Item4" value="0">
                                <bean:write name="bKTBean" property="ageLevel5_Item4"/>
                            </logic:notEqual>

                        </td>
                        <td align="center">
                            <logic:notEqual name="bKTBean" property="ageLevel6_Item4" value="0">
                                <bean:write name="bKTBean" property="ageLevel6_Item4"/>
                            </logic:notEqual>

                        </td>
                        <td align="center">
                            <logic:notEqual name="bKTBean" property="ageLevel7_Item4" value="0">
                                <bean:write name="bKTBean" property="ageLevel7_Item4"/>
                            </logic:notEqual>

                        </td>
                        <td align="center">
                            <logic:notEqual name="bKTBean" property="ageLevel8_Item4" value="0">
                                <bean:write name="bKTBean" property="ageLevel8_Item4"/>
                            </logic:notEqual>

                        </td>
                        <td align="center">
                            <logic:notEqual name="bKTBean" property="ageLevel9_Item4" value="0">
                                <bean:write name="bKTBean" property="ageLevel9_Item4"/>
                            </logic:notEqual>

                        </td>
                        <td align="center">
                            <logic:notEqual name="bKTBean" property="ageLevel10_Item4" value="0">
                                <bean:write name="bKTBean" property="ageLevel10_Item4"/>
                            </logic:notEqual>

                        </td>
                        <td align="center">
                            <logic:notEqual name="bKTBean" property="ageLevel12_Item4" value="0">
                                <bean:write name="bKTBean" property="ageLevel12_Item4"/>
                            </logic:notEqual>

                        </td>
                        <td align="center">
                            <logic:notEqual name="bKTBean" property="ageLevel14_Item4" value="0">
                                <bean:write name="bKTBean" property="ageLevel14_Item4"/>
                            </logic:notEqual>

                        </td>
                        <td align="center">
                            <logic:notEqual name="bKTBean" property="ageLevel16_Item4" value="0">
                                <bean:write name="bKTBean" property="ageLevel16_Item4"/>
                            </logic:notEqual>

                        </td>
                        <td align="center">
                            <logic:notEqual name="bKTBean" property="ageLevel19_Item4" value="0">
                                <bean:write name="bKTBean" property="ageLevel19_Item4"/>
                            </logic:notEqual>

                        </td>
                        <td align="center">
                            <logic:notEqual name="bKTBean" property="ageLevel22_Item4" value="0">
                                <bean:write name="bKTBean" property="ageLevel22_Item4"/>
                            </logic:notEqual>

                        </td>
                    </tr>
                    <tr>
                        <td align="center"><b>5
                        </td>
                        <td align="center">
                            <logic:notEqual name="bKTBean" property="ageLevel3_Item5" value="0">
                                <bean:write name="bKTBean" property="ageLevel3_Item5"/>
                            </logic:notEqual>

                        </td>
                        <td align="center">
                            <logic:notEqual name="bKTBean" property="ageLevel4_Item5" value="0">
                                <bean:write name="bKTBean" property="ageLevel4_Item5"/>
                            </logic:notEqual>

                        </td>
                        <td align="center">
                            <logic:notEqual name="bKTBean" property="ageLevel5_Item5" value="0">
                                <bean:write name="bKTBean" property="ageLevel5_Item5"/>
                            </logic:notEqual>

                        </td>
                        <td align="center">
                            <logic:notEqual name="bKTBean" property="ageLevel6_Item5" value="0">
                                <bean:write name="bKTBean" property="ageLevel6_Item5"/>
                            </logic:notEqual>

                        </td>
                        <td align="center">
                            <logic:notEqual name="bKTBean" property="ageLevel7_Item5" value="0">
                                <bean:write name="bKTBean" property="ageLevel7_Item5"/>
                            </logic:notEqual>

                        </td>
                        <td align="center">
                            <logic:notEqual name="bKTBean" property="ageLevel8_Item5" value="0">
                                <bean:write name="bKTBean" property="ageLevel8_Item5"/>
                            </logic:notEqual>

                        </td>
                        <td align="center">
                            <logic:notEqual name="bKTBean" property="ageLevel9_Item5" value="0">
                                <bean:write name="bKTBean" property="ageLevel9_Item5"/>
                            </logic:notEqual>

                        </td>
                        <td align="center">
                            <logic:notEqual name="bKTBean" property="ageLevel10_Item5" value="0">
                                <bean:write name="bKTBean" property="ageLevel10_Item5"/>
                            </logic:notEqual>

                        </td>
                        <td align="center">
                            <logic:notEqual name="bKTBean" property="ageLevel12_Item5" value="0">
                                <bean:write name="bKTBean" property="ageLevel12_Item5"/>
                            </logic:notEqual>

                        </td>
                        <td align="center">
                            <logic:notEqual name="bKTBean" property="ageLevel14_Item5" value="0">
                                <bean:write name="bKTBean" property="ageLevel14_Item5"/>
                            </logic:notEqual>

                        </td>
                        <td align="center">
                            <logic:notEqual name="bKTBean" property="ageLevel16_Item5" value="0">
                                <bean:write name="bKTBean" property="ageLevel16_Item5"/>
                            </logic:notEqual>

                        </td>
                        <td align="center">
                            <logic:notEqual name="bKTBean" property="ageLevel19_Item5" value="0">
                                <bean:write name="bKTBean" property="ageLevel19_Item5"/>
                            </logic:notEqual>

                        </td>
                        <td align="center">
                            <logic:notEqual name="bKTBean" property="ageLevel22_Item5" value="0">
                                <bean:write name="bKTBean" property="ageLevel22_Item5"/>
                            </logic:notEqual>

                        </td>
                    </tr>
                    <tr>
                        <td align="center" ><b>6
                        </td>
                        <td align="center">
                            <logic:notEqual name="bKTBean" property="ageLevel3_Item6" value="0">
                                <bean:write name="bKTBean" property="ageLevel3_Item6"/>
                            </logic:notEqual>

                        </td>
                        <td align="center">
                            <logic:notEqual name="bKTBean" property="ageLevel4_Item6" value="0">
                                <bean:write name="bKTBean" property="ageLevel4_Item6"/>
                            </logic:notEqual>

                        </td>
                        <td align="center">
                            <logic:notEqual name="bKTBean" property="ageLevel5_Item6" value="0">
                                <bean:write name="bKTBean" property="ageLevel5_Item6"/>
                            </logic:notEqual>

                        </td>
                        <td align="center">
                            <logic:notEqual name="bKTBean" property="ageLevel6_Item6" value="0">
                                <bean:write name="bKTBean" property="ageLevel6_Item6"/>
                            </logic:notEqual>

                        </td>
                        <td align="center">
                            <logic:notEqual name="bKTBean" property="ageLevel7_Item6" value="0">
                                <bean:write name="bKTBean" property="ageLevel7_Item6"/>
                            </logic:notEqual>

                        </td>
                        <td align="center">
                            <logic:notEqual name="bKTBean" property="ageLevel8_Item6" value="0">
                                <bean:write name="bKTBean" property="ageLevel8_Item6"/>
                            </logic:notEqual>

                        </td>
                        <td align="center">
                            <logic:notEqual name="bKTBean" property="ageLevel9_Item6" value="0">
                                <bean:write name="bKTBean" property="ageLevel9_Item6"/>
                            </logic:notEqual>

                        </td>
                        <td align="center">
                            <logic:notEqual name="bKTBean" property="ageLevel10_Item6" value="0">
                                <bean:write name="bKTBean" property="ageLevel10_Item6"/>
                            </logic:notEqual>

                        </td>
                        <td align="center">
                            <logic:notEqual name="bKTBean" property="ageLevel12_Item6" value="0">
                                <bean:write name="bKTBean" property="ageLevel12_Item6"/>
                            </logic:notEqual>


                        </td>
                        <td align="center">
                            <logic:notEqual name="bKTBean" property="ageLevel14_Item6" value="0">
                                <bean:write name="bKTBean" property="ageLevel14_Item6"/>
                            </logic:notEqual>

                        </td>
                        <td align="center">
                            <logic:notEqual name="bKTBean" property="ageLevel16_Item6" value="0">
                                <bean:write name="bKTBean" property="ageLevel16_Item6"/>
                            </logic:notEqual>

                        </td>
                        <td align="center">
                            <logic:notEqual name="bKTBean" property="ageLevel19_Item6" value="0">
                                <bean:write name="bKTBean" property="ageLevel19_Item6"/>
                            </logic:notEqual>

                        </td>
                        <td align="center">
                            <logic:notEqual name="bKTBean" property="ageLevel22_Item6" value="0">
                                <bean:write name="bKTBean" property="ageLevel22_Item6"/>
                            </logic:notEqual>

                        </td>
                    </tr>
                    <tr>
                        <td align="center"><font size="2"><b>Alt. No. 1
                                    </td>
                                    <td align="center">
                                        <logic:notEqual name="bKTBean" property="ageLevel3_ItemAlt1" value="0">
                                            <bean:write name="bKTBean" property="ageLevel3_ItemAlt1"/>
                                        </logic:notEqual>

                                    </td>
                                    <td align="center">
                                        <logic:notEqual name="bKTBean" property="ageLevel4_ItemAlt1" value="0">
                                            <bean:write name="bKTBean" property="ageLevel4_ItemAlt1"/>
                                        </logic:notEqual>

                                    </td>
                                    <td align="center">
                                        <logic:notEqual name="bKTBean" property="ageLevel5_ItemAlt1" value="0">
                                            <bean:write name="bKTBean" property="ageLevel5_ItemAlt1"/>
                                        </logic:notEqual>

                                    </td>
                                    <td align="center">
                                        <logic:notEqual name="bKTBean" property="ageLevel6_ItemAlt1" value="0">
                                            <bean:write name="bKTBean" property="ageLevel6_ItemAlt1"/>
                                        </logic:notEqual>

                                    </td>
                                    <td align="center">
                                        <logic:notEqual name="bKTBean" property="ageLevel7_ItemAlt1" value="0">
                                            <bean:write name="bKTBean" property="ageLevel7_ItemAlt1"/>
                                        </logic:notEqual>

                                    </td>
                                    <td align="center">
                                        <logic:notEqual name="bKTBean" property="ageLevel8_ItemAlt1" value="0">
                                            <bean:write name="bKTBean" property="ageLevel8_ItemAlt1"/>
                                        </logic:notEqual>

                                    </td>
                                    <td align="center">
                                        <logic:notEqual name="bKTBean" property="ageLevel9_ItemAlt1" value="0">
                                            <bean:write name="bKTBean" property="ageLevel9_ItemAlt1"/>
                                        </logic:notEqual>

                                    </td>
                                    <td align="center">
                                        <logic:notEqual name="bKTBean" property="ageLevel10_ItemAlt1" value="0">
                                            <bean:write name="bKTBean" property="ageLevel10_ItemAlt1"/>
                                        </logic:notEqual>

                                    </td>
                                    <td align="center">
                                        <logic:notEqual name="bKTBean" property="ageLevel12_ItemAlt1" value="0">
                                            <bean:write name="bKTBean" property="ageLevel12_ItemAlt1"/>
                                        </logic:notEqual>

                                    </td>
                                    <td align="center">
                                        <logic:notEqual name="bKTBean" property="ageLevel14_ItemAlt1" value="0">
                                            <bean:write name="bKTBean" property="ageLevel14_ItemAlt1"/>
                                        </logic:notEqual>

                                    </td>
                                    <td align="center">
                                        <logic:notEqual name="bKTBean" property="ageLevel16_ItemAlt1" value="0">
                                            <bean:write name="bKTBean" property="ageLevel16_ItemAlt1"/>
                                        </logic:notEqual>

                                    </td>
                                    <td align="center">
                                        <logic:notEqual name="bKTBean" property="ageLevel19_ItemAlt1" value="0">
                                            <bean:write name="bKTBean" property="ageLevel19_ItemAlt1"/>
                                        </logic:notEqual>

                                    </td>
                                    <td align="center">
                                        <logic:notEqual name="bKTBean" property="ageLevel22_ItemAlt1" value="0">
                                            <bean:write name="bKTBean" property="ageLevel22_ItemAlt1"/>
                                        </logic:notEqual>

                                    </td>
                                    </tr>
                                    <tr>
                                        <td align="center" class="label"><font size="2"><b>Alt. No. 2
                                                    </td>
                                                    <td align="center">
                                                        <logic:notEqual name="bKTBean" property="ageLevel3_ItemAlt2" value="0">
                                                            <bean:write name="bKTBean" property="ageLevel3_ItemAlt2"/>
                                                        </logic:notEqual>

                                                    </td>
                                                    <td align="center">
                                                        <logic:notEqual name="bKTBean" property="ageLevel4_ItemAlt2" value="0">
                                                            <bean:write name="bKTBean" property="ageLevel4_ItemAlt2"/>
                                                        </logic:notEqual>

                                                    </td>
                                                    <td align="center">
                                                        <logic:notEqual name="bKTBean" property="ageLevel5_ItemAlt2" value="0">
                                                            <bean:write name="bKTBean" property="ageLevel5_ItemAlt2"/>
                                                        </logic:notEqual>

                                                    </td>
                                                    <td align="center">
                                                        <logic:notEqual name="bKTBean" property="ageLevel6_ItemAlt2" value="0">
                                                            <bean:write name="bKTBean" property="ageLevel6_ItemAlt2"/>
                                                        </logic:notEqual>

                                                    </td>
                                                    <td align="center">
                                                        <logic:notEqual name="bKTBean" property="ageLevel7_ItemAlt2" value="0">
                                                            <bean:write name="bKTBean" property="ageLevel7_ItemAlt2"/>
                                                        </logic:notEqual>

                                                    </td>
                                                    <td align="center">
                                                        <logic:notEqual name="bKTBean" property="ageLevel8_ItemAlt2" value="0">
                                                            <bean:write name="bKTBean" property="ageLevel8_ItemAlt2"/>
                                                        </logic:notEqual>

                                                    </td>
                                                    <td align="center">
                                                        <logic:notEqual name="bKTBean" property="ageLevel9_ItemAlt2" value="0">
                                                            <bean:write name="bKTBean" property="ageLevel9_ItemAlt2"/>
                                                        </logic:notEqual>

                                                    </td>
                                                    <td align="center">
                                                        <logic:notEqual name="bKTBean" property="ageLevel10_ItemAlt2" value="0">
                                                            <bean:write name="bKTBean" property="ageLevel10_ItemAlt2"/>
                                                        </logic:notEqual>

                                                    </td>
                                                    <td align="center">
                                                        <logic:notEqual name="bKTBean" property="ageLevel12_ItemAlt2" value="0">
                                                            <bean:write name="bKTBean" property="ageLevel12_ItemAlt2"/>
                                                        </logic:notEqual>


                                                    </td>
                                                    <td align="center">
                                                        <logic:notEqual name="bKTBean" property="ageLevel14_ItemAlt2" value="0">
                                                            <bean:write name="bKTBean" property="ageLevel14_ItemAlt2"/>
                                                        </logic:notEqual>


                                                    </td>
                                                    <td align="center">
                                                        <logic:notEqual name="bKTBean" property="ageLevel16_ItemAlt2" value="0">
                                                            <bean:write name="bKTBean" property="ageLevel16_ItemAlt2"/>
                                                        </logic:notEqual>

                                                    </td>
                                                    <td align="center">
                                                        <logic:notEqual name="bKTBean" property="ageLevel19_ItemAlt2" value="0">
                                                            <bean:write name="bKTBean" property="ageLevel19_ItemAlt2"/>
                                                        </logic:notEqual>

                                                    </td>
                                                    <td align="center">
                                                        <logic:notEqual name="bKTBean" property="ageLevel22_ItemAlt2" value="0">
                                                            <bean:write name="bKTBean" property="ageLevel22_ItemAlt2"/>
                                                        </logic:notEqual>

                                                    </td>
                                                    </tr>
                                                    <tr>
                                                        <td align="center" class="label"><font size="2"><b>Alt. No. 3
                                                                    </td>
                                                                    <td align="center">
                                                                        <logic:notEqual name="bKTBean" property="ageLevel3_ItemAlt3" value="0">
                                                                            <bean:write name="bKTBean" property="ageLevel3_ItemAlt3"/>
                                                                        </logic:notEqual>

                                                                    </td>
                                                                    <td align="center">
                                                                        <logic:notEqual name="bKTBean" property="ageLevel4_ItemAlt3" value="0">
                                                                            <bean:write name="bKTBean" property="ageLevel4_ItemAlt3"/>
                                                                        </logic:notEqual>

                                                                    </td>
                                                                    <td align="center">
                                                                        <logic:notEqual name="bKTBean" property="ageLevel5_ItemAlt3" value="0">
                                                                            <bean:write name="bKTBean" property="ageLevel5_ItemAlt3"/>
                                                                        </logic:notEqual>

                                                                    </td>
                                                                    <td align="center">
                                                                        <logic:notEqual name="bKTBean" property="ageLevel6_ItemAlt3" value="0">
                                                                            <bean:write name="bKTBean" property="ageLevel6_ItemAlt3"/>
                                                                        </logic:notEqual>

                                                                    </td>
                                                                    <td align="center">
                                                                        <logic:notEqual name="bKTBean" property="ageLevel7_ItemAlt3" value="0">
                                                                            <bean:write name="bKTBean" property="ageLevel7_ItemAlt3"/>
                                                                        </logic:notEqual>

                                                                    </td>
                                                                    <td align="center">
                                                                        <logic:notEqual name="bKTBean" property="ageLevel8_ItemAlt3" value="0">
                                                                            <bean:write name="bKTBean" property="ageLevel8_ItemAlt3"/>
                                                                        </logic:notEqual>

                                                                    </td>
                                                                    <td align="center">
                                                                        <logic:notEqual name="bKTBean" property="ageLevel9_ItemAlt3" value="0">
                                                                            <bean:write name="bKTBean" property="ageLevel9_ItemAlt3"/>
                                                                        </logic:notEqual>

                                                                    </td>
                                                                    <td align="center">
                                                                        <logic:notEqual name="bKTBean" property="ageLevel10_ItemAlt3" value="0">
                                                                            <bean:write name="bKTBean" property="ageLevel10_ItemAlt3"/>
                                                                        </logic:notEqual>

                                                                    </td>
                                                                    <td align="center">
                                                                        <logic:notEqual name="bKTBean" property="ageLevel12_ItemAlt3" value="0">
                                                                            <bean:write name="bKTBean" property="ageLevel12_ItemAlt3"/>
                                                                        </logic:notEqual>

                                                                    </td>
                                                                    <td align="center">
                                                                        <logic:notEqual name="bKTBean" property="ageLevel14_ItemAlt3" value="0">
                                                                            <bean:write name="bKTBean" property="ageLevel14_ItemAlt3"/>
                                                                        </logic:notEqual>

                                                                    </td>
                                                                    <td align="center">
                                                                        <logic:notEqual name="bKTBean" property="ageLevel16_ItemAlt3" value="0">
                                                                            <bean:write name="bKTBean" property="ageLevel16_ItemAlt3"/>
                                                                        </logic:notEqual>

                                                                    </td>
                                                                    <td align="center">
                                                                        <logic:notEqual name="bKTBean" property="ageLevel19_ItemAlt3" value="0">
                                                                            <bean:write name="bKTBean" property="ageLevel19_ItemAlt3"/>
                                                                        </logic:notEqual>

                                                                    </td>
                                                                    <td align="center">
                                                                        <logic:notEqual name="bKTBean" property="ageLevel22_ItemAlt3" value="0">
                                                                            <bean:write name="bKTBean" property="ageLevel22_ItemAlt3"/>
                                                                        </logic:notEqual>

                                                                    </td>
                                                                    </tr>
                                                                    </table>
                                                                    <table  align="center" cellspacing="1" cellpadding="8"  class="innerTable1" width="85%">
                                                                        <tr>
                                                                            <td colspan="14">&nbsp;</td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td colspan="7" align="center" class="label"><b>*&nbsp;Basal Age </b></td>
                                                                            <td>:</td>
                                                                            <td colspan="6" class="label"><bean:write name="bKTBean" property="basalage"/></td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td colspan="7" align="center" class="label"><b>**&nbsp;Terminal Age </b></td>
                                                                            <td>:</td>
                                                                            <td colspan="6" class="label"><bean:write name="bKTBean" property="terminalage"/></td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td colspan="7" align="center" class="label"><b>Total Mental Age from selected check boxes </b></td>
                                                                            <td>:</td>
                                                                            <td colspan="6" class="label"><bean:write name="bKTBean" property="mentalagefromchecks"/></td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td colspan="7" align="center" class="label"><b>Total Mental Age</b></td>
                                                                            <td>:</td>
                                                                            <td colspan="6" class="label">Total Mental Age from selected check boxes+24</td>

                                                                        </tr>
                                                                        <tr>
                                                                            <td colspan="7">&nbsp;</td>
                                                                            <td>:</td>
                                                                            <td colspan="6"><bean:write name="bKTBean" property="mentalagefromchecks"/>+24=<bean:write name="bKTBean" property="totalmentalageinmonths"/></td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td colspan="7" align="center" class="label"><b>Mental Age in years</b></td>
                                                                            <td>:</td>
                                                                            <td colspan="6" class="label">(<bean:write name="bKTBean" property="totalmentalageinmonths"/>/12)=<bean:write name="bKTBean" property="mentalageinyears"/></td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td colspan="7" align="center" class="label"><b>Mental Age in months</b></td>
                                                                            <td>:</td>
                                                                            <td colspan="6" class="label"><bean:write name="bKTBean" property="mentalageinmonths"/></td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td colspan="7" align="center" class="label"><b>Mental Age</b></td>
                                                                            <td>:</td>
                                                                            <td colspan="6" class="label"><b><bean:write name="bKTBean" property="mentalagebuffer"/></b></td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td colspan="7" align="center" class="label"><b>Date Of Birth</b></td>
                                                                            <td>:</td>
                                                                            <td colspan="6" class="label"><bean:write name="bKTBean" property="dateofbirth"/></td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td colspan="7" align="center" class="label"><b>Todays Date</b></td>
                                                                            <td>:</td>
                                                                            <td colspan="6" class="label"><bean:write name="bKTBean" property="todaysdate"/></td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td colspan="7" align="center" class="label"><b>Chronological Age</b></td>
                                                                            <td>:</td>
                                                                            <td colspan="6" class="label">Today`s Date-Date Of Birth</td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td colspan="7" >&nbsp;</td>
                                                                            <td>:</td>
                                                                            <td colspan="6" class="label"><b><bean:write name="bKTBean" property="chronologicalage"/></b></td>
                                                                        </tr>
                                                                        <logic:equal name="bKTBean" property="chronologicalageflag" value="true">
                                                                            <tr>
                                                                                <td colspan="2">&nbsp;</td>
                                                                                <td colspan="12" align="center" class="label">Since Chronological Age is greater than 16 <b>Chronological Age=16</b></td>
                                                                            </tr>
                                                                        </logic:equal>
                                                                        <tr>
                                                                            <td colspan="7" align="center" class="label"><b>IQ</b></td>
                                                                            <td>:</td>
                                                                            <td colspan="6" class="label">(Mental Age/Chronological Age)*100</td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td colspan="7" >&nbsp;</td>
                                                                            <td>:</td>
                                                                            <td colspan="6" class="label"><b><bean:write name="bKTBean" property="iqbuffer"/></b></td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td colspan="14">&nbsp;</td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td colspan="2">&nbsp;</td>
                                                                            <td colspan="12" class="label">*&nbsp;Basal Age is the Age Level in which all the checkboxes selected would end</td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td colspan="2">&nbsp;</td>
                                                                            <td colspan="12" class="label">**&nbsp;Terminal Age is the Age Level in which no checkbox is  selected would start</td>
                                                                        </tr>

                                                                    </table>
                                                                </logic:notEmpty>

                                                                <BR><BR><BR>
                                                                <table align="center">
                                                                    <tr>
                                                                        <html:button property="" value="Back" onclick="goBack()" styleClass="button" />  </tr>
                                                                    <tr></tr><tr></tr>
                                                                    <tr><a href="showCalc.do?typeofDisabilityFlag=13&flagPrint=print" target="_blank">
                                                                        Print<img src="DisabilityUITG/images/print.gif"  height="25" width="35"/></a></tr>

                                                                </table>
                                                                </form>

                                                                </body>
                                                            </html:html>
