<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<html>

    <body>


        <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
                <td bgcolor="#6b2525" >
                   <table width="100%" border="0" cellspacing="0" cellpadding="3" align="center" style="color:#fff;font-size:11px;font-weight:normal;">
		              <tr>
		                <td width="20%" align="center">
		                	Visitor Count :<font style="font-face:Times New Roman; font-style: none;" size="3"> <%=session.getAttribute("visitorCount")%><bean:write name="visitorCount"/></font>
		                </td>
		                 <td width="20%" align="center">
		                 	Copyright @2015. All rights Reserved.
		                 </td> 
		                <td width="20%" align="center"> Best viewed in IE - 1024x768 resolution</td>
		                <td width="20%" align="right">Designed, Developed and Maintained by </td>
		                <td width="20%" align="left"><a href="http://www.tcs.com/" target="_blank"><img src="<%=request.getContextPath()%>/DisabilityUITG/images/tata_img.png" /></a></td>
		              </tr>
       		 	  </table>
                	</td>
            	</tr>

        </table>
    </body>
</html>
