<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<html:html locale="true">
    <HEAD>

        <meta http-eqDisabilityUITGv="Content-Type" content="text/html; charset=UTF-8">
        <title>Disabilities</title>
    
        <script LANGUAGE="JavaScript" SRC="scripts/jsmenu/lw_layers.js"></script>
        <script LANGUAGE="JavaScript" SRC="scripts/jsmenu/menu.js"></script>
        <table width="100%" height="50">
            <tr>
                <td width="142" height="116"><div align="center"><img src="DisabilityUITG/images/CAAZCPKF.jpg" width="107" height="114"/></div></td>
                <td width="639"><h1 align="center" class="style1">Issue of Disability Certificate</h1></td>
                <td width="169"><div align="center"><img src="DisabilityUITG/images/6.jpg" width="137" height="86"/></div></td>
            </tr>
        </table>
        <table  border=0 cellspacing=0  width="100%" align=center>
            <tr>
            <td id="noprint">

            <tr>
            <td align="right">
                <b>
                </b>
                &nbsp;&nbsp;&nbsp;
            </td>	</tr>
        </table>
        <script language="JavaScript1.2" src="DisabilityUITG/scripts/coolmenus3.js"></script>
        <script language="javascript" src="DisabilityUITG/scriptscss/cal2.js"></script>
        <script language="javascript" src="DisabilityUITG/scriptscss/cal_conf2.js"></script>
       
        <TITLE> New Document </TITLE>
        <META property="Generator" CONTENT="EditPlus">
        <META property="Author" CONTENT="">
        <META property="Keywords" CONTENT="">
        <META property="Description" CONTENT="">
    </HEAD>

    <body>

    
     
        <TABLE align="center">
            
            <tr>
                <td height="327" colspan="2" bgcolor="#FFFFFF"><p align="center" class="style5"><img src="../images/exam1.jpg" width="538" height="300" /></p>    </td>
                <td width="232" bgcolor="#FFFFFF"> </tr>
            <tr class="tbl_bg2_content">
                <tr>please select the options: </tr>
                <tr><DisabilityUITG><li><a href="DisabilityUITG/jsp/VisualImpairment.jsp"><b>VisualImpairment</b></a></tr>
                <tr><DisabilityUITG><li><a href="DisabilityUITG/jsp/MentslIllness.jsp"><b>MentalIllness</b></a></tr>
                <tr><DisabilityUITG><li><a href="DisabilityUITG/jsp/CardioPulmonaryDeseases.jsp"><b>Evelution of Physical Imparment Due to Cardiopulmonary Deseases</b></a><td><% if(request.getAttribute("value")!=null) {  %> <%=request.getAttribute("value")%> <% } %> </td></tr>
                 
                <tr><DisabilityUITG><li><a href="DisabilityUITG/jsp/Dwarfism.jsp"><b>Evelution of PPI in cases of Short stature/Dwarfism</b></a></tr>
                 <tr>Update Links</tr>
                 <tr><DisabilityUITG><li><a href="./getcardiopolumonary.do"><b>update cardiopulmonary</a></tr>
                 <tr><DisabilityUITG><li><a href="./getmentalillness.do"><b>update MentalIllness</a></tr>
                 <tr><DisabilityUITG><li><a href="./getdwarfism.do?getDwarfismDetails=getDwarfismDetails"><b>update Dwarfism</a></tr>
                 <tr><DisabilityUITG><li><a href="./getvisualimpairment.do?getVisualImpairment=getVisualImpairment"><b>update Visual</a></tr>
                <td>
                <td>
           

                </td>
            </tr>
        </TABLE>
 

    </body>
    
</html:html>