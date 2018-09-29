<%--
    Document   : NoPhotosData
    Created on : Jan 29, 2013, 11:12:56 AM
    Author     : 728056
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>


    </head>
    <script language="JavaScript" src="./DisabilityUITG/js/date-picker"></script>



    <script type="text/javascript">

        function Pager(tableName, itemsPerPage) {
            this.tableName = tableName;
            this.itemsPerPage = itemsPerPage;
            this.currentPage = 1;
            this.pages = 0;
            this.inited = false;

            this.showRecords = function(from, to) {
                var rows = document.getElementById(tableName).rows;
                // i starts from 1 to skip table header row
                for (var i = 1; i < rows.length; i++) {
                    if (i < from || i > to)
                        rows[i].style.display = 'none';
                    else
                        rows[i].style.display = '';
                }
            }

            this.showPage = function(pageNumber) {
                if (! this.inited) {
                    alert("not inited");
                    return;
                }

                var oldPageAnchor = document.getElementById('pg'+this.currentPage);
                oldPageAnchor.className = 'pg-normal';

                this.currentPage = pageNumber;
                var newPageAnchor = document.getElementById('pg'+this.currentPage);
                newPageAnchor.className = 'pg-selected';

                var from = (pageNumber - 1) * itemsPerPage + 1;
                var to = from + itemsPerPage - 1;
                this.showRecords(from, to);
            }

            this.prev = function() {
                if (this.currentPage > 1)
                    this.showPage(this.currentPage - 1);
            }

            this.next = function() {
                if (this.currentPage < this.pages) {
                    this.showPage(this.currentPage + 1);
                }
            }

            this.init = function() {
                var rows = document.getElementById(tableName).rows;
                var records = (rows.length - 1);
                this.pages = Math.ceil(records / itemsPerPage);
                this.inited = true;
            }

            this.showPageNav = function(pagerName, positionId) {
                if (! this.inited) {
                    alert("not inited");
                    return;
                }
                var element = document.getElementById(positionId);

                var pagerHtml = '<span onclick="' + pagerName + '.prev();" class="pg-normal">  Prev </span> | ';
                for (var page = 1; page <= this.pages; page++)
                    pagerHtml += '<span id="pg' + page + '" class="pg-normal" onclick="' + pagerName + '.showPage(' + page + ');">' + page + '</span> | ';
                pagerHtml += '<span onclick="'+pagerName+'.next();" class="pg-normal"> Next </span>';

                element.innerHTML = pagerHtml;
            }
        }


        function getReport(){
            var fromDate= document.forms[0].fromDate.value;
            var toDate= document.forms[0].toDate.value;
            var yye=fromDate.substr(6,4);
            var mme=fromDate.substr(3,2);
            var dde=fromDate.substr(0,2);
            var yyb=toDate.substr(6,4);
            var mmb=toDate.substr(3,2);
            var ddb=toDate.substr(0,2);
            var dob = new  Date();
            var etd = new  Date();
            var today=new Date();
            etd.setFullYear(yye,mme-1,dde);
            dob.setFullYear(yyb,mmb-1,ddb)
            var y1=today.getYear();
            var y2= dob.getYear();
            if (validate_required(fromDate,"From Date must be selected!")==false)
            {
                document.forms[0].fromDate.focus();
                return false
            }
            if (validate_required(toDate,"To Date must be selected!")==false)
            {
                document.forms[0].toDate.focus();
                return false
            }
            if (today < etd)
            {
                alert("From Date is before Today's Date");
                document.forms[0].fromDate.value="";
                return false;
            }
            if (today < dob)
            {
                alert("To Date does not exceed Today's Date");
                document.forms[0].toDate.value="";
                return false;
            }
            if (dob < etd)
            {
                alert("From date is greater than To Date");
                document.forms[0].fromDate.value="";
                return false;
            }
            if(document.forms[0].fromDate.value.length<1){
                alert('Please Select From Date');
                document.forms[0].fromDate.focus();
                return false;
            } else if(document.forms[0].toDate.value.length<1){
                alert('Please Select To Date');
                document.forms[0].toDate.focus();
                return false;
            }
            else{
                document.forms[0].mode.value="unspecified";
                document.forms[0].submit();
            }
           
        }

        function validate_required(field,alerttxt)
        {

            with (field)
            {

                if (field==null||field=="")
                {
                    alert(alerttxt);
                    return false
                }
                else
                {
                    return true
                }
            }
        }
    </script>
    <script language="JavaScript" src="./DisabilityUITG/js/date-picker"></script>
    <body class="body">
        <html:form action="noPhotosData">
            <html:hidden property="mode"/>

            <logic:present name="msg1">
                <p id="errmsg"><bean:write name="msg1"/></p>
            </logic:present>

            <table border="0" cellspacing="1" cellpadding="0" width="50%" align="center" class="inputform">
                <th colspan="2">
                    Photos Not Uploaded Details
                </th>
                <tr>
                    <td >  From Date<font color="red"><b>*</b></font>
                        <html:text property="fromDate" styleId="10" readonly="true" size="12"/>
                        <a  href="javascript:show_calendar('forms[0].fromDate');"
                            onmouseover="window.status='Date Picker';return true;"
                            onmouseout="window.status='';return true;">
                            <img src="./DisabilityUITG/images/calendar.png" border="0"></a> </td>
                    <td >    To Date<font color="red"><b>*</b></font>
                        <html:text property="toDate" styleId="8"  readonly="true" size="12"/>
                        <a  href="javascript:show_calendar('forms[0].toDate');"
                            onmouseover="window.status='Date Picker';return true;"
                            onmouseout="window.status='';return true;">
                            <img src="./DisabilityUITG/images/calendar.png" border="0"></a> </td>

                </tr>
                <tr>

                    <td colspan="2">
                        Camp<html:select  property="campId"  style="width:500px;">
                            <html:option  value="0">--ALL--</html:option>
                            <html:optionsCollection   property="campList" label="camp_name" value="camp_id"  />
                        </html:select>
                    </td>
                </tr>

                <tr>
                    <th colspan="2"><input type="button" value="Submit" onclick="getReport();"></th>
                </tr>
            </table>



            <br/>


            <%int i = 1;%>


            <logic:notEmpty name="msg">

                <div style="overflow:scroll; width:950px; height:335px" align="center">
                    <table align="center" border="0" cellpadding="0" cellspacing="1" width="90%" class="inputform" >

                        <tr>
                            <th >
                                SNo.
                            </th>
                            <th>PensionCard No</th>
                            <th>SADAREM ID </th>
                            <th> Name </th>
                            <th> Gender</th>
                            <th> Age</th>

                            <th>Relation Name</th>
                            <th>Rationcard Number</th>
                            <th>House Number</th>
                            <th>Mandal Name</th>
                            <th>Village Name</th>
                            <th>Habitation Name </th>

                        </tr>
                        <logic:iterate id="row" name="msg">
                            <tr>
                                <td >
                                    <%=i++%>.
                                </td>
                                <td>${row.Pensioncardno}</td>
                                <td >${row.PersonCode}</td>
                                <td>${row.firstname}</td>
                                <td>${row.Gender}</td>
                                <td>${row.age}</td>

                                <td>${row.relationname}</td>
                                <td >${row.rationcardnumber}</td>
                                <td >${row.houseNumber}</td>
                                <td>${row.MANDALNAME}</td>
                                <td>${row.VILLAGENAME}</td>
                                <td>${row.HabitationName}</td>
                            </tr>

                        </logic:iterate>
                    </table>
                </div>


                <div id="pageNavPosition" ></div>
            </logic:notEmpty>

        </html:form>

        <script type="text/javascript">
            var pager = new Pager('results',100);
            pager.init();
            pager.showPageNav('pager', 'pageNavPosition');
            pager.showPage(1);
        </script>



    </body>
</html>

