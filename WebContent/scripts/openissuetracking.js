function validateContactdetails() {
	var contactnum = $("#contid").val();
	var pattern = /^[0]?[789]\d{9}$/;

	if (pattern.test(contactnum) == false) {
		$("#contidErrMsg").addClass("errmsg");
		$("#contidErrMsg").html("Not Valid").show();
		$("#contid").focus();
		return false;
	} else {
		$("#contidErrMsg").removeClass("errmsg");
		$("#contidErrMsg").html("").show();
	}

	var altcontnum = $("#altcontid").val();
	if($("#contid").val()==$("#altcontid").val())
		{
		alert("Please select a differnt contact number");
	    $("#altcontid").val('');
		$('#altcontid').focus();
		  return false;
		}
	else
		{
		if (altcontnum.length > 0) {
			if (pattern.test(altcontnum) == false && altcontnum.length > 0) {
				$("#altcontidErrMsg").addClass("errmsg");
				$("#altcontidErrMsg").html("Not Valid").show();
				$("#altcontid").focus();
				return false;
			} else {
				$("#altcontidErrMsg").removeClass("errmsg");
				$("#altcontidErrMsg").html("").show();
			}
		}
		
		}
	

	var email = $("#emailid").val();

	var pattern = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;

	if (email.length > 0) {
		if (pattern.test(email) == false && email.length > 0) {
			$("#emailidErrMsg").addClass("errmsg");
			$("#emailidErrMsg").html("Not Valid").show();
			$("#emailid").focus();
			return false;
		} else {
			$("#emailidErrMsg").removeClass("errmsg");
			$("#emailidErrMsg").html("").show();
		}
	}

	return true;

}

function namechange(){
	
	if($('#fname').val()=="" || $('#fname').val()==null || $('#fname').length == 0)
	{
		alert("Please enter Name");
		$('#fname').focus();
		return false;
	}
	else if($('#decription').val()=="" || $('#decription').val()==null || $('#decription').length == 0)
	{
		alert("Please enter Remarks");
		$('#decription').focus();
		return false;
	}
	else if(proofdocument()){
		if(confirm("Are you sure you want to submit the request?"))
		{
		    /*//surname_many_words();
		    document.name_issue.target="_self";
			document.name_issue.action="<%=request.getContextPath()%>/nameChangeIssue.do";
			document.name_issue.submit();
			Screen Locking Started 
			$('#blocklayer').css({	"display": "block", opacity: 0.7,"z-index":"100000", "width":$(document).width(),"height":$(document).height()});
		    $('#processlayer').css({"display": "block","z-index":"110000"});*/
			//console.log('Name Change');
			return true;
			
		/*Screen Locking Ended */
		}
	}
	else{
		return false;
	}
	
}
function proofdocument(){
	if($('#proofidentity_1').val()=="" || $('#proofidentity_1').val()==null || $('#proofidentity_1').length ==0 ){
		alert("Please select the Proof Identity type");
		$('#proofidentity_1').focus();
		return false;
	}
	else if($('#proofid_1').val()=="" || $('#proofid_1').val()==null || $('#proofid_1').length ==0 ){
		alert("Please Enter Proof Id");
		$('#proofid_1').focus();
		return false;
	}
	else if($('#proofDoc_1').val()=="" || $('#proofDoc_1').val()==null || $('#proofDoc_1').length == 0)
	{
			alert("Please upload required document");
			$('#proofDoc_1').focus();
			return false;
	}
	if($('#proofidentity_1').val()==$('#proofidentity_2').val()){
		alert("Please select a differnt Proof Identity type");
		$('#proofid_2').val('');
		$("#proofDoc_2").val('');
		$('#proofidentity_2').focus();
		return false;
	}else{
		if($('#proofidentity_2').val()=="" || $('#proofidentity_2').val()==null || $('#proofidentity_2').length ==0 ){
			alert("Please select the Proof Identity type");
			$('#proofidentity_2').focus();
			return false;
		}
		else if($('#proofid_2').val()=="" || $('#proofid_2').val()==null || $('#proofid_2').length ==0 ){
			alert("Please Enter Proof Id");
			$('#proofid_2').focus();
			return false;
		}
		else if($('#proofDoc_2').val()=="" || $('#proofDoc_2').val()==null || $('#proofDoc_2').length == 0)
		{
				alert("Please upload required document");
				$('#proofDoc_2').focus();
				return false;
		}
		else return true;
	}
	
}

function relnamechange()
{
	if($('#relationname').val()=="" || $('#relationname').val()==null || $('#relationname').length == 0)
	{
		alert("Please enter Relation Name");
		$('#relationname').focus();
		return false;
	}
  else if ($('#relationType').val()==-1)
    {
              alert("Please Select Relation type");
              $('#relationType').focus();
              return false
    }
  else if($('#decription').val()=="" || $('#decription').val()==null || $('#decription').length == 0)
	{
		alert("Please enter Remarks");
		$('#decription').focus();
		return false;
	}else if(proofdocument()){
		if(confirm("Are you sure you want to submit the request?"))
		{
		    /*//surname_many_words();
		    document.name_issue.target="_self";
			document.name_issue.action="<%=request.getContextPath()%>/nameChangeIssue.do";
			document.name_issue.submit();
			Screen Locking Started 
			$('#blocklayer').css({	"display": "block", opacity: 0.7,"z-index":"100000", "width":$(document).width(),"height":$(document).height()});
		    $('#processlayer').css({"display": "block","z-index":"110000"});*/
			//console.log('Name Change');
			return true;
			
		/*Screen Locking Ended */
		}
		else{
			return false;
		}
 
  }
} 
function qualificationchange()
{
	
  if ($('#educationType').val()==-1)
    {
              alert("Please Select Qualification type");
              $('#educationType').focus();
              return false;
    }
  else if($('#decription').val()=="" || $('#decription').val()==null || $('#decription').length == 0)
	{
		alert("Please enter Remarks");
		$('#decription').focus();
		return false;
	}else if(proofdocument()){
		if(confirm("Are you sure you want to submit the request?"))
		{
		    /*//surname_many_words();
		    document.name_issue.target="_self";
			document.name_issue.action="<%=request.getContextPath()%>/nameChangeIssue.do";
			document.name_issue.submit();
			Screen Locking Started 
			$('#blocklayer').css({	"display": "block", opacity: 0.7,"z-index":"100000", "width":$(document).width(),"height":$(document).height()});
		    $('#processlayer').css({"display": "block","z-index":"110000"});*/
			//console.log('Name Change');
			return true;
			
		/*Screen Locking Ended */
		}
		else{
			return false;
		}
 
  }
}

function addresschange()
{

       if ($('#mandals').val()==-1)
        {
                     alert("Please Select Mandal type");
                     $('#mandals').focus();
                     return false;
         }
         
	      else if ($('#panchayats').val()==-1)
         {
             alert("Please Select Panchayat type");
             $('#panchayats').focus();
             return false;
         }
         
	      else if ($('#villages').val()==-1)
         {
             alert("Please Select Village type");
             $('#villages').focus();
             return false;
         }
	      else if ($('#habitation').val()==-1)
         {
             alert("Please Select Habitation type");
             $('#habitation').focus();
             return false;
         }

	      else if($('#houseno').val()=="" || $('#houseno').val()==null || $('#houseno').val().length == 0)
			{
				alert("Please enter House No");
				$('#houseno').focus();
				return false;
			}
	      
	      else if($('#pincode').val()=="" || $('#pincode').val()==null ||  $('#pincode').val().length!=6)
			{   
	    	alert("Please enter valid PinCode");
				$('#pincode').focus();
				return false;	
			}
	      else if(proofdocument()){
	  		if(confirm("Are you sure you want to submit the request?"))
	  		{
	  		    /*//surname_many_words();
	  		    document.name_issue.target="_self";
	  			document.name_issue.action="<%=request.getContextPath()%>/nameChangeIssue.do";
	  			document.name_issue.submit();
	  			Screen Locking Started 
	  			$('#blocklayer').css({	"display": "block", opacity: 0.7,"z-index":"100000", "width":$(document).width(),"height":$(document).height()});
	  		    $('#processlayer').css({"display": "block","z-index":"110000"});*/
	  			//console.log('Name Change');
	  			return true;
	  			
	  		/*Screen Locking Ended */
	  		}
	  		else{
	  			return false;
	  		}
	   
	    }
	    	
}
function genderchange()
{
	 
	 if($('#decription').val()=="" || $('#decription').val()==null || $('#decription').length == 0)
		{
			alert("Please enter Remarks");
			$('#decription').focus();
			return false;
		}
	 else if(proofdocument()){
	  		if(confirm("Are you sure you want to submit the request?"))
	  		{
	  		    /*//surname_many_words();
	  		    document.name_issue.target="_self";
	  			document.name_issue.action="<%=request.getContextPath()%>/nameChangeIssue.do";
	  			document.name_issue.submit();
	  			Screen Locking Started 
	  			$('#blocklayer').css({	"display": "block", opacity: 0.7,"z-index":"100000", "width":$(document).width(),"height":$(document).height()});
	  		    $('#processlayer').css({"display": "block","z-index":"110000"});*/
	  			//console.log('Name Change');
	  			return true;
	  			
	  		/*Screen Locking Ended */
	  		}
	  		else{
	  			return false;
	  		}
	   
	    }else return false;
}

function agecorrection()
{
	if($('#dob').val()=="" || $('#dob').val()==null || $('#dob').length == 0)
	   {
	      alert("Please select date of birth");
	      $('#dob').focus();
	      return false;
	   }
      else if($('#decription').val()=="" || $('#decription').val()==null || $('#decription').length == 0)
	  {
		alert("Please enter Remarks");
		$('#decription').focus();
		return false;
	  }else if(proofdocument()){
	  		if(confirm("Are you sure you want to submit the request?"))
	  		{
	  		    /*//surname_many_words();
	  		    document.name_issue.target="_self";
	  			document.name_issue.action="<%=request.getContextPath()%>/nameChangeIssue.do";
	  			document.name_issue.submit();
	  			Screen Locking Started 
	  			$('#blocklayer').css({	"display": "block", opacity: 0.7,"z-index":"100000", "width":$(document).width(),"height":$(document).height()});
	  		    $('#processlayer').css({"display": "block","z-index":"110000"});*/
	  			//console.log('Name Change');
	  			return true;
	  			
	  		/*Screen Locking Ended */
	  		}
	  		else{
	  			return false;
	  		}
	   
	    }else return false;
      
}


function makereadonly()
{
	
	$('#decription').prop('readonly', true);
	$("#proofidentity_1").attr("disabled","disabled");
	$("#proofid_1").attr("readonly","readonly");
	$("#proofDoc_1").prop("disabled", true);
	$("#proofidentity_2").attr("disabled","disabled");
	$("#proofid_2").attr("readonly","readonly");
	$("#proofDoc_2").prop("disabled", true);
	
}
function removedisabled()
{
	$('#issueType').prop("disabled", false);
	$("#proofidentity_1").prop("disabled", false);
	$("#proofDoc_1").prop("disabled", false);
	$("#proofidentity_2").prop("disabled", false);
	$("#proofDoc_2").prop("disabled", false);
}

function ValidatesubmitBut()
{
    
	if($('#otpid').val()=="" || $('#otpid').val()==null || $('#otpid').length == 0)
	{
		alert("Please enter OTP");
		$('#otpid').focus();
		return false;
	}
	 
	else if($('#txtInput').val()=="" || $('#txtInput').val()==null || $('#txtInput').length == 0)
		{
			alert("Please enter CaptchaCode");
			$('#txtInput').focus();
			return false;
		}
	else {
		
		return true;
	}
	   
}
