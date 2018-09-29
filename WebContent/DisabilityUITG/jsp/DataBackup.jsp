<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<html>
<script language="JavaScript" src="./DisabilityUITG/js/date-picker"></script>


<body>
<center>
<table align="center" cellspacing="1" cellpadding="5" class="tbl_bg2" width="80%">
    <form action="databackup.do?parameter=takeBackup" method="post">
        
    <tr class="tbl_bg2_content_hdr">
        <th align="center"><font size="4">DATABASE BACKUP PAGE</font></th>
    </tr>
    
    <tr class="tbl_bg2_content">
        <td><b>Taking Database Backup is an important activity.</b>
        </td>
    </tr>
    <tr class="tbl_bg2_content">
        <td><b>Make sure the backup file is being downloaded in the specified folder.</b>
        </td>
    </tr>
    <tr class="tbl_bg2_content">
        <td><b>Give atleast one minute gap between each backup.</b>
        </td>
    </tr>
    <tr class="tbl_bg2_content">
        <td align="center">
            <input type="submit" style="font-weight:bold" value="Take Backup"/>
        </td>
    </tr>
    </form>
</table>
</center>
</body>
</html>