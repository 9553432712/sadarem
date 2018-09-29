<%--
    Document   : searchForRationCard
    Created on : Oct 12, 2012, 4:38:16 PM
    Author     : 525483
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<script type="text/javascript">
    function rationCrdNo(){
        if(document.forms[0].textBox.value==""){
            alert("Enter Number");
            document.forms[0].textBox.focus();
            return false;
        }else{
            document.forms[0].mode.value='getrationCrdNo';
            document.forms[0].submit();
        }
    }

</script>

<html>

    <head>
    	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css"/>
        <script type="text/javascript" src="<%=request.getContextPath()%>/scripts/bootstrap.min.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>
        <style type="text/css">
        	th 
        	{
			    padding: 5px;
			    color: white;
			    background-color: #337ab7;
			    height: 20px;
			    font-size: 12px;
			    font-weight: bold;
			}
			td 
        	{		    
			    height: 30px;
			    font-size: 12px;
			}
        
        </style>
    </head>
    <body>
        <html:form action="/searchForRationCard">
            <html:hidden property="mode"/>
            <logic:present name="msg">
                
                <h3><p id="succmsg"><b>${msg}</b></p></h3>
            </logic:present>
            
            
            				<div class="col-md-5 col-md-offset-3">
				            		<div class="panel panel-primary">				            		 
			      						<div class="panel-heading" style="text-align: center;"><b>RATIONCARD Search </b></div>
									    <div class="panel-body" style="text-align: left;">
									    	<div class="form-group">
									    		<div class="row">
									    			<div class="col-md-3">
									    				<label for="usr"> Ration Card No </label>
									    				<input type="hidden" name="rationCard" value="1">
									    											    				
									    			</div>
									    			<div class="col-md-2">
									    				<html:text property="textBox"  style="width:150px"/>
									    			</div>
									    		</div>														
											</div>
											<div class="form-group">
													  <label for="usr" style="float: right;">													  		
								                          		<html:button property="sub" value="submit" onclick="rationCrdNo();"/>								                          	
													  </label>
												 </div>
										</div>
									</div>
								</div>
            
            
            
                <%-- <table  align="center" cellspacing="1" cellpadding="0" class="inputform" border="0" width="50%" >
                <tr>
                    <th colspan="2">
                        SADAREM ID OR RATIONCARD search
                    </th>
                </tr>
                <tr>
                    <td>
                        <table  align="center" cellspacing="0" cellpadding="0" class="inputform" border="0" width="60%">
                            <tr>
                                <td align="center">Ration Card No<html:radio style="border:0px" style="width=25px"  property="rationCard" value="1"/>
                                </td>
                                <td align="center">
                                    SADAREM ID<html:radio style="border:0px" style="width=25px" property="rationCard" value="2"/>
                                </td>
                            </tr>
                            <tr>
                     <td  colspan="4" align="center">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<html:text property="textBox"  style="width:150px"/></td>
                            </tr>
                        </table>
                    </td>
                </tr>


                <tr>
                    <th colspan="2" align="center"><html:button property="sub" value="submit" onclick="rationCrdNo();"/></th>
                </tr>

            </table>
 --%>            <br>

            <logic:notEmpty name="getRationCardList">
                <table align="center" cellpadding="0" cellspacing="1" border="1" width="90%" class="tbl" >
                    <tr>
                        <th >
                            S No.
                        </th>
                        <%if (!request.getParameter("rationCard").toString().equals("2")) {%>
                        <th >
                            SADAREM ID
                        </th>
                        <%}%>
                        <%if (!request.getParameter("rationCard").toString().equals("1")) {%>
                        <th >
                            Rationcard Number
                        </th>
                        <%}%>
                        <th >
                            Name
                        </th>
                        <th >
                            Pensioncard Number
                        </th>
                        <th >
                            Pension Phase
                        </th>
                        <th >
                            Assessment Status
                        </th>
                        <th >
                            Person Status
                        </th>
                    </tr>
                    <%int i = 1;%>
                    <logic:iterate name="getRationCardList" id="row">
                        <tr bgcolor="white">
                            <td >
                                <%=i++%>
                            </td>
                            <%if (!request.getParameter("rationCard").toString().equals("2")) {%>
                            <td >
                                ${row.person_code}
                            </td>
                            <%}%>
                            <%if (!request.getParameter("rationCard").toString().equals("1")) {%>
                            <td >
                                ${row.RationCard_Number}
                            </td>
                            <%}%>
                            <td >
                                ${row.Name}
                            </td>
                            <td >
                                ${row.pensioncard_no}
                            </td>
                            <td >
                                ${row.pensionphase}
                            </td>
                            <td >
                                ${row.view_edit_mode}
                            </td>
                            <td >
                                ${row.ReasonforPersonStatus}
                            </td>
                        </tr>
                    </logic:iterate>
                </table>
            </logic:notEmpty>
        </html:form>
    </body>
</html>
