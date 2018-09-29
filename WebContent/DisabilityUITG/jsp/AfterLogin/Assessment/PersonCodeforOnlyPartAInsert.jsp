<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%
            String district = (String) request.getAttribute("district_name");
            String mandal = (String) request.getAttribute("mandal_name");
            String village = (String) request.getAttribute("village_name");
            String habitation = (String) request.getAttribute("habitation_name");
            String panchayat = (String) request.getParameter("panchayat_name");


            String district_id = (String) request.getAttribute("district_id");
            String mandal_id = (String) request.getAttribute("mandal_id");
            String village_id = (String) request.getAttribute("village_id");
            String habitation_id = (String) request.getAttribute("habitation_id");
            String assembly_id = (String) request.getAttribute("assembly_id");
            String panchayat_id = (String) request.getAttribute("panchayat_id");
            String restrictPartA = (String) request.getAttribute("restrictPartA");
            String res = (String) request.getAttribute("restrict");

            String base64Photo = (String) request.getAttribute("base64Photo");
            String district_name = (String) request.getAttribute("district_name");
            
            String name = session.getAttribute("Name").toString();
            String Surname = (String) request.getAttribute("Surname");
            String teluguname = session.getAttribute("teluguname").toString();
            System.out.print(Surname+name);


%>
<html>
    <head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="<%=request.getContextPath()%>/css/colorbox.css" type="text/css" rel="stylesheet"/> 
    
    <style type="text/css">    
	    label
		{
			margin-left:5px!important;
		}
    </style>
    
    
    	<script src="<%=request.getContextPath()%>/scripts/jquery.colorbox.js"></script>
			<script type="text/javascript">
			
			
			
			
			
			
					$(document).ready(function()
					{
						 
						//Action on the issue
						
						$(".iframe").colorbox(
						{
							
								iframe:true, width:"60%", height:"60%",
								 onClosed: function()
								 {
									 try
									 {
									 	var timestamp = (new Date()).getTime();
								        var newSrc = $("#imag1").attr("src").split("?");
								        
								     
								        newSrc = "<%=request.getContextPath()%>/dispimg.do?action=showsadaremimg&sadaremid=<%=session.getAttribute("personcode") %>" 
								        
								        $("#imag1").attr("src", newSrc);
								        $("#imag1").slideDown("fast");
									 }
									 catch(e)
									 {
										 alert(e);
									 }
								
								}
						});
						
						
						//History
						
					
						
						
						$(".prntbut").click(function(e){
							alert("Print Button");
						});
						$(".callbacks").colorbox({
							onOpen:function(){ alert('onOpen: colorbox is about to open'); },
							onLoad:function(){ alert('onLoad: colorbox has started to load the targeted content'); },
							onComplete:function(){ alert('onComplete: colorbox has displayed the loaded content'); },
							onCleanup:function(){ alert('onCleanup: colorbox has begun the close process'); },
							onClosed:function(){ alert('onClosed: colorbox has completely closed'); }
						});
						$('#cboxClose').click(function (){alert(1);});
		
						$('.non-retina').colorbox({rel:'group5', transition:'none'});
						$('.retina').colorbox({rel:'group5', transition:'none', retinaImage:true, retinaUrl:true});
						
						//Example of preserving a JavaScript event for inline calls.
						$("#click").click(function(){ 
							$('#click').css({"background-color":"#f00", "color":"#fff", "cursor":"inherit"}).text("Open this window again and this message will still be here.");
							return false;
						});
					}
					);
		
				</script>
    
    	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css"/>
        <script type="text/javascript" src="<%=request.getContextPath()%>/scripts/bootstrap.min.js"></script>
        <script language='javascript'>        
            function enableInput()
            {
                document.getElementById('onload').disabled=false;
            }
        </script>
    </head>

    <body onload="enableInput();">
      <div class="container">
      
      
      
      			
			        <html:form action="repeatPartA.do?repeatPartA=repeatPartAAddToRestrtictPartB" >
			            <input type="hidden" name="district_id" value="<%=district_id%>"/>
			            <input type="hidden" name="mandal_id" value="<%=mandal_id%>"/>
			            <input type="hidden" name="village_id" value="<%=village_id%>"/>
			            <input type="hidden" name="habitation_id" value="<%=habitation_id%>"/>
			            <input type="hidden" name="panchayat_id" value="<%=panchayat_id%>"/>
			            <input type="hidden" name="assembly_id" value="<%=assembly_id%>"/>
			
			            <input type="hidden" name="district_name" value="<%=district%>"/>
			            <input type="hidden" name="mandal_name" value="<%=mandal%>"/>
			            <input type="hidden" name="Panchayat_Name" value="<%=panchayat%>"/>
			
			            <input type="hidden" name="village_name" value="<%=village%>"/>
			            <input type="hidden" name="habitation_name" value="<%=habitation%>"/>
			
			
			            <logic:present name="restrictDataFlagTest">
			            		
			              		<div class="col-md-7 col-md-offset-3">
				            		<div class="panel panel-primary">
				            		 
			      						<div class="panel-heading" style="text-align: center;"><b> Acknowledgement </b></div>
									      <div class="panel-body" style="text-align: left;">
									      		<div class="row">
									      			<div class="col-md-3">
									      				<div class="form-group">								           		   	
										           		   	<div id="img1" name="img1">
								                    			<img  align="center"  id="imag1" name="imag1"  style="width: 140px; height: 140px;float:left;" src="<%=request.getContextPath()%>/dispimg.do?action=showsadaremimg&sadaremid=<%=session.getAttribute("personcode") %>"   onerror="this.src='<%=request.getContextPath()%>/images/noimage.png'"/>
								                    		</div>
										           		</div>
									      			
									      			</div>
									      			<div class="col-md-7">
									      				 <div class="form-group">
														  <label for="usr">SADAREM ID </label>
															<%out.println(session.getAttribute("personcode"));%>
														</div>
											        	<div class="form-group">
															  <label for="usr">Name </label>
																<%=name%> 
														</div>
														<div class="form-group">
															  <label for="usr">Surname : </label>
																<%=Surname%>
														</div>											
														<div class="form-group">
															  <label for="usr">Teluguname : </label>
																<%=teluguname%>
														</div> 
												        <div class="form-group">
															  <label for="usr">District : </label>
															 	<%=district%>
														</div>
												        <div class="form-group">
															  <label for="usr">Mandal : </label>
															 	<%=mandal%>
														</div>
														<div class="form-group">
															  <label for="usr">Village : </label>
															 	<%=village%>
														</div>
														<div class="form-group">
															  <label for="usr">Habitation : </label>
															 	<%=habitation%>
														</div>
										      			
										      			</div>
									      		</div>
									      			<div class="form-group">
													  <label for="usr" style="float: right;">
													  		<a class='iframe'  class="btn btn-info" style="overflow:hidden; font-family:sans-serif" href="<%=request.getContextPath()%>/fileupload.do">
								                          		<button type="button" class="btn btn-info" >Upload Profile Photo</button>
								                          	</a>
													  </label>
												 </div>
									        </div>         
										        
										  </div>
										  <div align="center">
							            	<button  class="btn icon-btn-primary btn-primary" class="btn btn-default" onclick="window.print()" type="button">
												<strong><font style="color:white;">Print</font></strong>
											</button>
							            </div>
		    					</div>
		    					
					           
			              </logic:present>
			                <%-- <table width="60%" border="0" cellpadding="3" cellspacing="">			
			                    <tr>
			                    	<td align="center" colspan="3">	                    		 
			                    		 	<%out.println(session.getAttribute("personcode"));%>			                    		 
			                    	</td>
			                    </tr>
			                    <tr >
			                    	<td align="center"  rowspan="8">
			                    		<font size="3">To Upload Photo Please</font>
			                    		<div id="img1" name="img1">
			                    			<img  align="center"  id="imag1" name="imag1" src="<%=request.getContextPath()%>/dispimg.do?action=showsadaremimg&sadaremid=<%=session.getAttribute("personcode") %>"  class="img-rounded"  width="204" height="204" onerror="this.src='<%=request.getContextPath()%>/images/noimage.png'"/>
			                    		</div>
			                          	<a class='iframe'  class="btn btn-info" style="overflow:hidden; font-family:sans-serif" href="<%=request.getContextPath()%>/fileupload.do">
			                          		<button type="button" class="btn btn-info" >Upload Profile Photo</button>
			                          	</a>
			                            <!-- <a href="javascript:void(0);"  onClick=window.open("fileupload.do","Ratting","width=500,height=200,0,status=0");><u>Click here</u></a> -->
			                        </td>
			                     </tr>
			                    <tr>
			                    	<td width="50%" >Name</td>
			                        <td width="50%" ><%=name%></td>
			                    </tr>
			                    <tr>
			                    	<td width="50%" >Surname</td>
			                        <td width="50%" ><%=Surname%></td>
			                    </tr>
			                    <tr>
			                    	<td width="50%" >Teluguname</td>
			                        <td width="50%" ><%=teluguname%></td>
			                    </tr>
			                    
			                    <tr>
			                    	<td width="50%" >District</td>
			                        <td width="50%" ><%=district%></td>
			                    </tr>
			                    <tr>
			                    	<td >Mandal</td>
			                        <td ><%=mandal%></td>
			                    </tr>			                   
			                    <tr>
			                    	<td >Village</td>
			                        <td ><%=village%></td>
			                    </tr>
			                    <tr>
			                    	<td >Habitation</td>
			                        <td ><%=habitation%></td>
			                    </tr>			                    		
			                </table> --%>
			                		
			          
			
			            
			
			            <logic:notPresent name="restrictDataFlagTest">			
			
			                <table width="60%" border="1" cellpadding="0" cellspacing="">
			                			
			                    <tr >
			                    	<td align="center"> 
			                    		<font  size="10" color="red" >
			                    			<%out.println(session.getAttribute("personcode"));%>
			                    		</font>
			                    	</td>
			                    </tr>			                    
			                    <tr>
				                    <td align="center" >
					                    <font size="3">
					                    To Upload Photo Please
					                    </font>
					                    <div id="img2" name="img2">
					                    	<img  align="center" src="<%=request.getContextPath()%>/dispimg.do?action=showsadaremimg&sadaremid=<%=session.getAttribute("personcode") %>"  class="img-rounded"  width="204" height="204"  onerror="this.src='<%=request.getContextPath()%>/images/noimage.png'"/>
							            </div>
						                    <a class='iframe' value="img2" style="overflow:hidden; font-family:sans-serif" href="<%=request.getContextPath()%>/fileupload.do">
						                    Click here
						                    </a>
				                           <!--  <a href="javascript:void(0);"  onClick=window.open("fileupload.do","Ratting","width=500,height=200,0,status=0");><u>Click here</u></a></td> -->
									</td>
			                    </tr>			
			
			                    <%if (base64Photo != null && !base64Photo.equalsIgnoreCase("-")) 
			                    {%>
			                    <tr>
			                    	<td class="labelBlue" style="text-align: center">
					                    <div id="img3" name="img3">
					                    	<img  align="center" src="<%=request.getContextPath()%>/dispimg.do?action=showsadaremimg&sadaremid=<%=session.getAttribute("personcode") %>"   width="200" height="200"  onerror="this.src='<%=request.getContextPath()%>/images/noimage.png'"/>
					                    </div>
			                            <img align="right" src="./DisabilityUITG/uploadedphotos/<%=district_name%>/<%=session.getAttribute("personcode")%>.JPG"  class="img-rounded"  width="204" height="204" onerror="this.src='<%=request.getContextPath()%>/images/noimage.png'">
			                        </td>
			                    </tr>
			                    <%} else if (base64Photo != null && base64Photo.equalsIgnoreCase("-")) 
			                    {%>
			                    <tr>
				                    <td class="labelBlue" align="center">
					                    <font size="3">
					                    	To Upload Photo Please 
					                    </font>
					                    <div id="img4" name="img4">
					                    	<img  align="center" src="<%=request.getContextPath()%>/dispimg.do?action=showsadaremimg&sadaremid=<%=session.getAttribute("personcode") %>"  class="img-rounded"  width="204" height="204" onerror="this.src='<%=request.getContextPath()%>/images/noimage.png'"/>
					                    </div>
					                    <a class='iframe' style="overflow:hidden; font-family:sans-serif" href="<%=request.getContextPath()%>/fileupload.do">
					                    	Click here
					                    </a>
				                            <!-- <a href="javascript:void(0);"  onClick=window.open("fileupload.do","Ratting","width=500,height=200,0,status=0");><u>Click here</u></a> -->
				                     </td>
			                    </tr>
			                    <%}%>
			                    <tr>
				                    <td class="labelBlue" align="center">
				                    		<font size="3">Acknowledgement </font>
				                            <a href="javascript:void(0);"  onClick=window.open("insertPartAaction.do?insertPersonalDetails=PersonCodeAcknowledgement","Ratting","width=1000,height=600,0,status=0"); ><u>Click here</u></a>
				                    </td>
			                    </tr>        
			                  
			                </table>
			
			
			
			                <%if ("true".equals(restrictPartA)) {%>
			                <input type="hidden" name="restrictPartA" value="true"/>
			                <%} else if ("pensionNumberRestrictPartA".equals(restrictPartA)) {%>
			                <input type="hidden" name="restrictPartA" value="pensionNumberRestrictPartA"/>
			                <%}%>
							
							
			                <%if (res != null && res.equalsIgnoreCase("yes")) {
			                        } else {%>
			                
			                    
			                    <tr>
			                    <td width="50%" >
			                    District
			                    </td>
			                        <td width="50%" >
			                        <%=district%>
			                        </td>
			                    </tr>
			                    <tr>
			                    <td >
			                    Mandal
			                    </td>
			                        <td >
			                        <%=mandal%>
			                        </td>
			                    </tr>
			                    <%--  <tr><td >panchayat</td>
			                          <td ><%=panchayat%></td>
			                      </tr>--%>
			                    <tr>
			                    <td>
			                    Village
			                    </td>
			                        <td >
			                        <%=village%>
			                        </td>
			                    </tr>
			                    <tr>
			                    <td > Habitation</td>
			                    <td ><%=habitation%></td>
			                    </tr>
			                    <tr> 
			                    <th colspan="2">
			                    <input type="submit" id="onload" disabled="disabled" value="Add One More" class="button" />
			                    </th>
			                    </tr>
			               <%}%>
							<div align="center">
				            	<button  class="btn icon-btn-primary btn-primary" class="btn btn-default" onclick="window.print()" type="button">
									<strong><font style="color:white;">Print</font></strong>
								</button>
				            </div>
			            </logic:notPresent>
			
			
			
			
			
			
			            <logic:notPresent name="restrictDataFlagTest">
			
			
			            </logic:notPresent>
			            <%--  ${restrictDataFlagTest}--%>
			
			          
			
			            <logic:present name="restrictDataFlagTest">
			                
			            </logic:present><br><br>			            
			            
			        </html:form>
          
     </div>   
   </body>

</html>
