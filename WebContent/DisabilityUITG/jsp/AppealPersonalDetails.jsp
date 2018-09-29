<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@page import="java.util.ArrayList"%>
<%@ page import="java.util.*,java.text.*"%>
<%@page session="true"%>


<html:html>

    <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
    <script language="JavaScript">
        function validate_form(thisform)
        {

            //alert("enter validate");
            /*if(check.checked==false){
    //alert("if"+ check.value);
        alert("Please accept Declaration");

        return false;
    }else {

    //alert("else"+ check.value);

                return true;

    }*/

            //alert("values");
            var check=document.forms[0].terms;
            //alert(check+"value");
            var ration=document.forms[0].rationcard;
            var sid=document.forms[0].sid;
            //alert("value==========="+ration.value + sid.value);

            /*if(ration==null || ration.value==""){
      // alert("please enter rationcardnumber");
       }

        else if(sid==null ||  sid.value==""){
      // alert("please enter serialnumber");
        }

    else */
            if(check.checked==false){

                alert("Please select Appellate Authority checkbox");

                return false;
            }else {
                return true;

            }
            /*else{
         alert("please Enter SerialNumber");
        }}else{
    alert("please Enter SerialNumber");
        }*/
            return false;


        }

        function onlyNumbers(evt)
        {
            var e = event || evt; // for trans-browser compatibility
            var charCode = e.which || e.keyCode;

            if (charCode > 31 && (charCode < 48 || charCode > 57))
                return false;

            return true;

        }

        function ristrictZero() {
            if(document.forms[0].elements['sid'].value=="0") {
                document.forms[0].elements['sid'].value="";
            }

        }


    </script>



    <head>
        <style type="text/css">

            .innerTabl {


                BORDER-TOP       : gray 1px solid;
                BORDER-LEFT      : gray 1px solid;
                BORDER-RIGHT     : gray 1px solid;
                background-color : white ;
                padding          : 5px   ;

            }
            .innerTab {

                BORDER-BOTTOM    : gray 1px solid;
                BORDER-LEFT      : gray 1px solid;
                BORDER-RIGHT     : gray 1px solid;

                background-color : white ;
                padding          : 5px   ;

            }


        </style>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Appellet Authority  Page</title>

    </head>
    
    <body>
        <html:form action="Appeal.do" styleId="partAForm"  method="post" onsubmit="return validate_form(this)" >
            <%

                        String per = (String) request.getAttribute("per");
            %>
            <html:hidden  property="personcode" name="partAForm"/>
            <html:hidden property="status" name="partAForm"/>
            <input type="hidden" name="mode" value="mode"/>
            <html:hidden property="categoryid" name="partAForm"/>
            <html:hidden property="disability" name="partAForm"/>
            <html:hidden property="percentage" name="partAForm"/>
            <input type="hidden" name="pensioncode" value="<%=per%>"/>
            <table  align="center" cellspacing="1" cellpadding="0" class="inputform" width="80%">
                <tr><th colspan="4" > Existing  Person Details</th></tr>

                <tr >

                    <td >SADAREM ID</td>
                    <td> <html:text property="personcode"   name="partAForm" readonly="true"/></td>

                    <td >Name</td>
                    <td> <html:text property="name"   name="partAForm"  readonly="true"/></td>

                </tr>
                <tr>
                    <td >Age</td>
                    <td> <html:text property="age"   name="partAForm"  readonly="true"/></td>

                    <td >Gender</td>
                    <td> <html:text property="gender"   name="partAForm"  readonly="true"/></td>

                </tr>
                <tr >
                    <td >Relation Name</td>
                    <td> <html:text property="relationname"   name="partAForm" readonly="true" /></td>
                    <td >Disability</td>
                    <td> <html:text property="disabilitytype"   name="partAForm"  readonly="true"/></td>

                </tr>

                <tr >
                    <td >RationCardNumber</td>
                    <td> <html:text property="rationcard"   name="partAForm"  readonly="true"/></td>
                    <td >SerialNumber</td>
                    <td> <html:text property="sid"   name="partAForm"  maxlength="2" onkeypress="return onlyNumbers();" onkeyup="ristrictZero()"/></td>

                </tr>

                <tr > <td >Mandal</td>
                    <td> <html:text property="mandal"   name="partAForm" readonly="true" /></td>
                    <td >Village</td>
                    <td> <html:text property="village"   name="partAForm"  readonly="true"/></td>
                </tr>


                <tr>
                    <td colspan="4">
                        <input type="checkbox" name="terms" ><font size="2" >Selected for Second Time Re-Assessment.

                        </font>
                    </td>
                </tr>

                <tr><th colspan="4" >
                        <html:submit value="Submit" styleClass="button"/>&nbsp;&nbsp;

                    </th>
                </tr>

             



            </table>

        </html:form>



    </body>

</html:html>
