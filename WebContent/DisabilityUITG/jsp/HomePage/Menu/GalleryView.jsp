<%-- 
    Document   : GalleryView
    Created on : 12 Nov, 2013, 2:55:28 PM
    Author     : 484898
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%
            String path = request.getContextPath();
            String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="<%=basePath%>DisabilityUITG/PhotoGalleryScripts/jquery.min.js"></script>
        <script type="text/javascript" src="<%=basePath%>DisabilityUITG/PhotoGalleryScripts/thumbslide.js"></script>
        <link rel="stylesheet" type="text/css" href="<%=basePath%>DisabilityUITG/PhotoGalleryScripts/ThumbSlideStyle.css" />
        <script type="text/javascript">

            //Initialization code:
            $(document).ready(function(){ // on document load

                $("#thumbsliderdiv").imageSlider({ //initialize slider
                    'thumbs':[<%=request.getAttribute("photosString")%>],
                    'auto_scroll':true,
                    'auto_scroll_speed':4500,
                    'stop_after': 0, //stop after x cycles? Set to 0 to disable.
                    'canvas_width':725,
                    'canvas_height':500 // <-- No comma after last option
                })
            });
        </script>

        <script type="text/javascript">
            function goBack()
            {
                document.forms[0].action ="./photos.do";
                document.forms[0].submit();
            }
        </script>


    </head>
    <body>
        <html:form action="/galleryView">
            <p>Photo Gallery</p>
            <table align="center" cellpadding="0" cellspacing="0" border="0" width="100%">
               
                <logic:present name="event">
                    <tr>
                        <td align="center">
                            <p><b>Event :</b> <%=request.getParameter("event")%></p>
                            <table align="center" cellpadding="0" cellspacing="0" border="0" class="grid">
                                <tr>
                                    <td>
                                        <div id="thumbsliderdiv" style="height: 500px"></div>
                                    </td>
                                </tr>
                            </table>

                        </td>
                    </tr>
                </logic:present>
                <logic:notPresent name="event">
                    <tr>
                        <td align="center">
                            <font color="red">Photos not Updated for this Event</font>
                        </td>
                    </tr>
                </logic:notPresent>
                <tr>
                    <td>
                        &nbsp;
                    </td>
                </tr>
                <tr>
                    <td align="right">
                        <a href="#" onclick="goBack();"> << Back</a>
                    </td>
                </tr>
            </table>
        </html:form>
    </body>
</html>
