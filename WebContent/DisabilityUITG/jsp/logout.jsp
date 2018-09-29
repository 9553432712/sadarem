<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
    <head>
        <title>SADAREM</title>
    </head>

    <Script>
        window.history.forward(1);
    </Script>

    <script type="text/javascript">
        window.history.forward(1);
        function disp_confirm()
        {
            var name=confirm("Are You Sure you want to LogOff ?")
            if (name==true)
            {
                document.forms[0].action="logout.do";
                document.forms[0].submit();
            }
            else
            {

                document.forms[0].action="HomePageForwdAction.do";
                document.forms[0].submit();
            }
        }
    </script>


    <body onload="disp_confirm()">
        <form action="/logout.do">

        </form>
    </body>

</html>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p><br>
</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p><br>
</p>
<p>&nbsp;</p>
<p>&nbsp;</p>

<p>&nbsp;</p>
&nbsp;

