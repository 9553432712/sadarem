<%@ page import="java.util.*" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>SADAREM</title>
</head>

<script LANGUAGE="JavaScript" SRC="./DisabilityUITG/js/jsmenu/lw_layers.js"></script>
<script LANGUAGE="JavaScript" SRC="./DisabilityUITG/js/jsmenu/menu.js"></script>
<!-- style={margin:0px} -->
<script>
    document.onkeydown = showDown;

    function showDown(evt) {
        evt = (evt)? evt : ((event)? event : null);
        if (evt) {
           
            if (event.keyCode == 116) {
                // When F5 is pressed
                cancelKey(evt);
            }
            else if (event.keyCode == 122) {
                // When F11 is pressed
                cancelKey(evt);
            }else if (event.ctrlKey && (event.keyCode == 78 || event.keyCode == 82)) {
                // When ctrl is pressed with R or N
                cancelKey(evt);
            }
            else if (event.altKey && event.keyCode==37 ) {
                // stop Alt left cursor
                return false;
            }
        }
    }

    function cancelKey(evt) {
        if (evt.preventDefault) {
            evt.preventDefault();
            return false;
        }
        else {
            evt.keyCode = 0;
            evt.returnValue = false;
        }
    }



</script>


<table  border=0 cellspacing=0  width=85% align="center">

    <tr>
        <td id="noprint">
            <%
                        Vector services = (Vector) session.getAttribute("services");
                        if (services != null) {
                            out.println("<script LANGUAGE=\"JavaScript\">");
                            for (int i = 0; i < services.size(); i++) {
                                String servicedesc[] = (String[]) services.elementAt(i);
                                String serviceid = servicedesc[0];
                                String parentid = servicedesc[1];
                                String targeturl = servicedesc[2];
                                String menuitemname = servicedesc[3];

                                out.println("AddMenuItem (" + serviceid + ", " + parentid + ", \"" + targeturl + "\", \"" + menuitemname + "\", \"\");");
                            }
                        } else {
                            out.println("could not get services");
                        }
                        out.println("</script>");
            %>
            <script LANGUAGE="JavaScript">
                DrawMenu();
            </script>
        </td>
    </tr>
</table>
<input type="hidden" name="logintime" value=<%=(String) session.getAttribute("logintime")%> >



