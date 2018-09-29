function RegValidateform() 
{
	
	var aadharExist			       = $('input:radio[name=exist]:checked').val();
	var aadharno                   = $('#aadharno1').val();
	var serveDoneBy		           = $('#serveDoneBy1').val();
	var designation		           = $('#designation1').val();
	var recivedDate		           = $('#recivedDate1').val();
	
	
		  if(aadharExist=="Exist")
			{
			       if(aadharno==""||aadharno==null ||  fun_validateAadhaarID($("#aadharno1").val())==false || !($("#aadharno1").val().length == 12))
				   {
			    	
				    alert("Enter valid Aadhaar Card Number");
				    $('#aadharno1').focus();
				    return false;
				   }
			}
		  if(serveDoneBy==""||serveDoneBy==null)
		    {
		     alert("Please enter Validation Done By");
		     $('#serveDoneBy1').focus();
		    return false;
		   }
		    if(designation==""||designation==null)
		    {
		     alert("Please enter Designation");
		     $('#designation1').focus();
		    return false;
		   }
		 if(recivedDate==""||recivedDate==null)
		    {
		     alert("Please enter Validation Done Date");
		     $('#recivedDate1').focus();
		    return false;
		   }
		  else
		   {
			   return true;  
		   }    
}     