
<html>
<% try{
%>
<%@page import="com.tcs.sadarem.util.CommonUtility,java.util.ArrayList" %>
<% 	
		ArrayList userdata = new ArrayList();
		userdata	=	(ArrayList) request.getAttribute("userdata");
		String resultmsg=CommonUtility.checkNullObj((String)request.getAttribute("result"));
%>
    <head>
        <title>UNLockAccount</title>
        <link href="<%=request.getContextPath()%>/css/commonstyle.css" rel="stylesheet" type="text/css" />
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
        <style type="text/css">
        td{
        	font-size: 12px;
        	font-family: Verdana, Geneva, sans-serif;
        }
        th{
        	font-size: 12px;
        	font-family: Verdana, Geneva, sans-serif;
        }
        </style>
       
        <script LANGUAGE="JavaScript">
       
            function maximize() {
				
                window.moveTo(0, 0);
                window.resizeTo(screen.width, screen.height);
            }
            maximize();
            history.forward();
        </script>


        <script LANGUAGE="JavaScript">
        $(document).ready(function()
        		{
        	  	//$("#input[name='lockunlock']:radio:checked").data("chk",false);
        		    $("input:radio").click(function()
        		    {        		    	
        		    	if(confirm("You want to lock/Unlock the account"))
        	    		{
        	    			$("#mode").val('UnlockAccount');
        	    			/*Screen Locking Started */
        	    	      			$('#blocklayer').css({	"display": "block", opacity: 0.7,"z-index":"100000", "width":$(document).width(),"height":$(document).height()});
        	    	      		    $('#processlayer').css({"display": "block","z-index":"110000"});
        	          		/*Screen Locking Ended */
        	    				
        	    				document.unlockform.target="_self";
        	    				document.unlockform.action="<%=request.getContextPath()%>/unlockspmaccount.do?randomid="+Math.random();
        	    				document.unlockform.submit();
        	    		}
        	    		else
        	    		{ 
        	    			try{
        	    					  var name = $(this).attr('name');        	    					 
        	    					  $(this).removeAttr('checked');   					  
        	    					  
        	    				}catch(E){alert(E);}        	    				
        	    		
        		    }
        		});
        	});
        
        </script>
    </head>
    
    <script>
    //alert(document.getElementById('lable').value);
    $(document).ready(function()
    {
    	$(document).ready(function()
    	{
    	    $("form").submit(function()
    	    {
    	    	if(document.getElementById('userid').value==null || document.getElementById('userid').value.length==0)
				{
					alert("please Enter Employee ID");
					return false;
				}
    	    });
    	});
						
			$("#getuserdtls").click(function( event )
					{
					
								if(document.getElementById('userid').value==null || document.getElementById('userid').value.length==0)
								{
									alert("please Enter Employee ID");	
								}
								else
								{
									$("#mode").val('getUserdtlsmethod');			
									/*Screen Locking Started */
				    		      			$('#blocklayer').css({	"display": "block", opacity: 0.7,"z-index":"100000", "width":$(document).width(),"height":$(document).height()});
				    		      		    $('#processlayer').css({"display": "block","z-index":"110000"});
				    	      			/*Screen Locking Ended */
										
										document.unlockform.target="_self";
										document.unlockform.action="<%=request.getContextPath()%>/unlockspmaccount.do?randomid="+Math.random();
										document.unlockform.submit();										
								}
								
							
					});
			
    		});
    </script>
    

    <body >
   
        <form name="unlockform"  id="unlockform" method="post">
        <input type="hidden" id="mode" name="mode" value="getUserdtlsmethod">        
         
         <%if(resultmsg.length()!=0) 
             {%>
            <table  align="center" cellspacing="0" cellpadding="0"   style="width:50%"><tr><td align="center"> <b><%=resultmsg %></b></td></tr></table>
             <%} %>
             <br></br>
          <table  align="center" cellspacing="0" cellpadding="0"  border="0" style="width:40%">
          <tr  style="height:30px" align="center">
          	<td align="center" valign="middle">Enter UserID</td>
          	<td  align="center" valign="middle"><input type='text' id='userid' name='userid' autocomplete="off"></td>
          	<td  align="center" valign="middle"> <button type="button" id="getuserdtls" class="btn btn-success"><b>Get Data</b></button></td>
          </tr>
          </table>
          <br>
         
         <%if(userdata!=null && userdata.size()!=0)
        	{%>
        	
        	 <table  align="center" cellspacing="0" cellpadding="0"   style="width:80%">
          <tr  style="height:39px " bgcolor="#cceeff" align="center">
			<th height="30" class="hd_gd" align="center" valign="middle" >District Name</th>
			<th height="30" class="hd_gd" align="center" valign="middle" >Mandal Name</th>
			<th height="30" class="hd_gd" align="center" valign="middle" >Camp Name</th>
			<th height="30" class="hd_gd" align="center" valign="middle" >Person Name</th>
			<th height="30" class="hd_gd" align="center" valign="middle" >UserID</th>
			<th height="30" class="hd_gd" align="center" valign="middle" >Role</th>
			<th height="30" class="hd_gd" align="center" valign="middle" >Action</th>
		  </tr>
          <%
          	ArrayList temp1=new ArrayList();
			for(int i=0;i<userdata.size();i++)
			{
				temp1=(ArrayList) (userdata.get(i));
				
			
          %>
          <input type="hidden" id="empid<%=i%>" name="empid" value='<%=temp1.get(4) %>'>
           <tr>
           
           			<td class="secondrow" align="left" valign="middle" style="height:30px;border-bottom: #234466 solid 1px !important;" ><%=temp1.get(0)%></td>
                 	 <td class="secondrow" align="left" valign="middle" style="height:30px;border-bottom: #234466 solid 1px !important;"><%=temp1.get(1)%></td>
                 	  <td class="secondrow" align="left" valign="middle" style="height:30px;border-bottom: #234466 solid 1px !important;"><%=temp1.get(2)%></td>
                 	  <td class="secondrow" align="left" valign="middle" style="height:30px;border-bottom: #234466 solid 1px !important;"><%=temp1.get(3)%></td>
                 	  <td class="secondrow" align="left" valign="middle" style="height:30px;border-bottom: #234466 solid 1px !important;"><%=temp1.get(4)%></td>  
                 	  <td class="secondrow" align="left" valign="middle" style="height:30px;border-bottom: #234466 solid 1px !important;"><%=temp1.get(5)%></td>               
	                   <td class="secondrow" align="left" valign="middle" style="height:30px;border-bottom: #234466 solid 1px !important;border-right: #234466 solid 1px !important;">      
	                     		<%if(temp1.get(6).equals("1"))
	                     		{%>   
	                     			         	
	                     		<input type="radio" style="color:red" name='lockunlock' value='unlock' ><font color='green'>UnLock</font>
	                     		 
	                     		<%}else
	                     			{%>
	                     			
	                     		<input type="radio"  name='lockunlock' value='lock'><font color='red'>Lock</font>
	                     		
	                     		<%} %>
	                     </td>
           </tr>
           <%} %>
           </table>
           <%
           } %>
             
             <br>
             <p></p>
        </form>
        
    </body>
    <%}catch(Exception e)
    {
    	//e.printStackTrace();
    } 
    finally
    {}%>
    
</html>