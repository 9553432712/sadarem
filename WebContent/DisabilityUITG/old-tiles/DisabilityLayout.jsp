<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<html>

<head >
 <%
    HttpSession sessionForCheck=request.getSession(false);   
   
 String  reLoginURL=request.getContextPath()+"/login.do?mode=sessionvalidator";
    if(session.getAttribute("services")==null)
    {
        response.sendRedirect(reLoginURL);
        return;
    }   
%>

<%--
 <script>
            function updateOnlineStatus(msg) {

                var condition = navigator.onLine ? "ONLINE" : "OFFLINE";

                //status.setAttribute("class", condition);
                if(condition=="ONLINE"){
                    //  alert("online");
                }else if(condition=="OFFLINE"){
                    //  alert("offline");
                    window.location.replace("./DisabilityUITG/jsp/SessionExpireAlert.jsp");
                    //    alert("hi");


                }



            }
            function loaded() {
                // alert("hi");
                updateOnlineStatus("load");
                //  alert("hi11111111");
                document.body.addEventListener("offline", function () {
                    updateOnlineStatus("offline");
                }, false);
                document.body.addEventListener("online", function () {
                    updateOnlineStatus("online");
                }, false);
            }
        </script>--%>

</head>
 <body  class="body">

<table  align="center" height="100%" class="table_one">

<tr><td width="85%"><tiles:insert attribute="header" ignore="true"/></td></tr>

<%--<tr><td width="85%"><tiles:insert attribute="menu" ignore="true"/></td></tr>--%>

<tr valign="top"><td height="100%" width="85%"><tiles:insert attribute="body" ignore="true"/></td></tr>

<tr><td><tiles:insert attribute="footer" ignore="true" /></td></tr>

</table>

</body> 

</html>