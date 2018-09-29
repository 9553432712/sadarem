<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<html>
    <script>
    
    </script>
    <%;
        String contextPath=  request.getContextPath();
        String remoteAddr=  request.getServerName();
        String port=String.valueOf(request.getServerPort());
        String  reLoginURL="http://"+remoteAddr+":"+port+contextPath;
     

    %>
    <head>
      
        
    </head>
    <body background="./DisabilityUITG/images/background.jpg">
        <form action="<%=reLoginURL%>" >
        <table align="center" >
                
<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>


                <tr>
                    <td  ><font size="10" color="red" > <b>Session Has Expired </font></font>
                    </td>
                    
                </tr>
                <tr>
                    <td align="center" ><font size="2" color="blue" > <BLINK><b>Click Below Button To ReLogin </BLINK></font></font>
                    </td>
                    
                </tr>
                <tr>
                </tr><tr>
                </tr><tr>
                </tr><tr>
                </tr><tr>
                </tr><tr>
                </tr><tr>
                </tr><tr>
                </tr>
                <tr ><td align="center">
                   <input type="submit" value="ReLogin" >
                </td>
                </tr>
            </table>  
        
    
        </form>
    </body>
</html>
