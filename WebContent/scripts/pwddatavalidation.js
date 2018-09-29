function Validateform() 
{
	try
	{
	 
	var aadharExist			= $('input:radio[name=exist]:checked').val();
	var aadharno           = $('#aadharno').val();
	var alive              = $('input:radio[name=alive]:checked').val();
	var deadDate		   = $('#deadDate').val();
	var shg                = $('input:radio[name=shg]:checked').val();
	var vo				   = $('#vo').val();			
	var shglist			   = $('#shglist').val();
	var shgdate			   =$('#shgdate').val();
	var notshgreason       = $('#notshgreason').val();
	var aids			   = $('input:radio[name=aids]:checked').val();
	
	var applType			= $('#applType').val();
	var aidsorganisanation   = $('#aidsorganisanation').val();
	var aidsdate			= $('#aidsdate').val();
	var aidsreason			= $('#aidsreason').val();
	var surgical			=$('input:radio[name=surgical]:checked').val();

	var surType			           = $('#surType').val();
	var surorganisanation			= $('#surorganisanation').val();
	var surdate			           = $('#surdate').val();
	var surreason			       = $('#surreason').val();
	var pmjdy					=$('input:radio[name=pmjdy]:checked').val();
	
	var accno		           = $('#accno').val();
	var bank		           = $('#bank').val();
	var branch		           = $('#branch').val();
	var ifsc		           = $('#ifsc').val();
	var pmjnreason		       = $('#pmjnreason').val();
	
	var aasara					=$('input:radio[name=aasara]:checked').val();
	var aasarareason          = $('#aasarareason').val();
	
	var serveDoneBy		           = $('#serveDoneBy').val();
	var designation		           = $('#designation').val();
	var recivedDate		           = $('#recivedDate').val();
	var mapFlag                = $('#mapflag').val(); 
	
	
	
	
	  if(!radioValidation('exist'))
		{
			alert("Please select Aadhar Details")
			return false;
		}
	   
	
	   if(aadharExist=="Exist")
		{
		       if(aadharno==""||aadharno==null ||  fun_validateAadhaarID($("#aadharno").val())==false || !($("#aadharno").val().length == 12))
			   {
				    alert("Enter valid Aadhaar Card Number");
				    $('#aadharno').focus();
				    return false;
			   }
		}
	   
	   if(!radioValidation('alive'))
		{
			alert("Please select Alive or Dead Details")
			return false;
		}
	   
	    
	   if(alive=="Alive")
		{ 
		   if(!radioValidation('shg'))
		{
			alert("Please select SHG Details")
			return false;
		}
		       if(shg=="1" )
			   {
		    	  
				    if(mapFlag=="N")
				    { 
		    	       if(vo=="-1")
				    	{
		        	     alert("Please select VO");
					    $('#vo').focus();
					    return false;
				    	}
				      if(shglist=="-1")
				    	  {
				    	     alert("Please select SHG Name");
						    $('#shglist').focus();
						    return false;
				    	  }
				      
				      if(shgdate==""||shgdate==null)
			    	  {
			    	     alert("Please select Formation Date");
					    $('#shgdate').focus();
					    return false;
			    	  }
				    }
			   }
		       else 
	    	   {
		    	   if(notshgreason=="-1")
			    	  {
			    	     alert("Please select SHG Reason");
					    $('#notshgreason').focus();
					    return false;
			    	  }
	    	   }
		}	   
	    else if(alive=="Dead")
		{
		       if(deadDate==""||deadDate==null)
			   {
			    alert("Please select DeadDate");
			    $('#deadDate').focus();
			    return false;
			   }
		}
	   
	   if(!radioValidation('aids'))
		{
			alert("Please select aids and appliances Details")
			return false;
		}
		
	   if(aids=="Received")
	   {
		  
		   if(applType==""||applType==null)
			   {
			     alert("Please enter Appliance Type");
			    $('#applType').focus();
			    return false;
			   }
		   if(aidsorganisanation=="-1")
		    {
		     alert("Please select Organisation");
		     $('#aidsorganisanation').focus();
		    return false;
		   }
		   
		   if(aidsdate==""||aidsdate==null)
		    {
		     alert("Please select Formation Date");
		     $('#aidsdate').focus();
		    return false;
		   }
	   
	   }
	   else if(aids=="NotReceived")
	   {	  
		   if(aidsreason==""||aidsreason==null)
		   {
		     alert("Please enter Appliance Reason");
		    $('#aidsreason').focus();
		    return false;
		   }
	   }	   
	   if(!radioValidation('surgical'))
		{
			alert("Please select surgical correction")
			return false;
		}
	   
	   if(surgical=="Done")
		  {	   
	    	 if(surType==""||surType==null)
			   {
			     alert("Please enter Type of Surgery");
			    $('#surType').focus();
			    return false;
			   }
			   if(surorganisanation=="-1")
			    {
			     alert("Please select Surgery Organisation");
			     $('#surorganisanation').focus();
			    return false;
			   }
		   
			   if(surdate==""||surdate==null)
			    {
			     alert("Please select Date of Surgery");
			     $('#surdate').focus();
			    return false;
			   }
		  }
	   else if(surgical=="RequiredSurgery")
		   {
	    	 if(surreason==""||surreason==null)
			    {
			     alert("Please enter Surgery Reason");
			     $('#surreason').focus();
			    return false;
			   }
		   }

	   if(!radioValidation('pmjdy'))
		{
			alert("Please select pmjdy A/C Details")
			return false;
		}
	    
	   
	   if(pmjdy=="Covered")
		  {
	    	 if(accno==""||accno==null)
			   {
			     alert("Please enter Account Number");
			    $('#accno').focus();
			    return false;
			   }
			   if(bank==""||bank==null )
			    {
			     alert("Please enter Bank");
			     $('#bank').focus();
			    return false;
			   }
		   
			   if(branch==""||branch==null)
			    {
			     alert("Please enter  Branch");
			     $('#branch').focus();
			    return false;
			   }
			    
		  }
	   else if(pmjdy=="NotCovered")
	     {
	    	 if(pmjnreason==""||pmjnreason==null)
			    {
			     alert("Please enter PMJDY Reason");
			     $('#pmjnreason').focus();
			    return false;
			   }
	     }
	   
	   if(!radioValidation('aasara'))
		{
			alert("Please select aasara Details")
			return false;
		}
	    
	
	   if(aasara=="NotSanctioned")
    	 {
    	 if(aasarareason==""||aasarareason==null)
		    {
		     alert("Please enter AASARA Reason");
		     $('#aasarareason').focus();
		    return false;
		   }
    	 }

	   if(serveDoneBy==""||serveDoneBy==null)
		    {
		     alert("Please enter Validation Done By");
		     $('#serveDoneBy').focus();
		    return false;
		   }
	   else if(designation==""||designation==null)
		    {
		     alert("Please enter Designation");
		     $('#designation').focus();
		    return false;
		   }
	   else if(recivedDate==""||recivedDate==null)
		    {
		     alert("Please enter Validation Done Date");
		     $('#recivedDate').focus();
		    return false;
		   }
		   else
		   {
			   return true;  
		   }
	}
	
	catch(e)
	{
		//alert(e);
	}
}
function radioValidation(id)
{
	var i,obj,count=0;
	obj=document.getElementsByName(id);
	 
	for(i=0;i<obj.length;i++)
	{
		if(obj[i].checked)
		{
			count=1;
		}
	}
	 
	if(count == 1)
		 return true;
	else 
		return false;
	
}
