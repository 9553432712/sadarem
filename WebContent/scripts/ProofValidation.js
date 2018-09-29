

function validate(id,proofnoid)
{
	 
	 var e = document.getElementById(id);
	 var idType = e.options[e.selectedIndex].value;
	 var proofno = document.getElementById(proofnoid).value;
	 if(idType=='PR001')
	 {
		 if(!fun_validateAadhaarID(proofno))
			 {
			  alert("Please provide valid Aadhar Number");
			  $('#'+proofnoid).val('');
			  return false;
			  }
	 }
	 else if(idType=='PR002') 
	 {
	    if(!fun_validatePassport(proofno))
	    	{
	    	  alert("Please provide valid Passport Number");
			  $('#'+proofnoid).val('');
			 
			  return false;
	    	}
	 }
	 else if(idType=='PR003') 
	 {
	    if(!fun_validatePANCard(proofno))
	    	{
	    	  alert("Please provide valid PAN Number");
			  $('#'+proofnoid).val('');
			  return false;
	    	}
	 }
	 else if(!onKeyPressAlphbaNumericOnly(proofno) )
	 {
	   alert("Please provide valid proof number");
	   $('#'+proofnoid).val('');
		  return false;
	 }
	 return true;
}



function fun_validateAadhaarID(txtUID)
{
	 var pattern = /^\d{12}$/;
	
	if(pattern.test(txtUID)==false)
	 {
		return false;
	 }
	 else if (txtUID != "") 
	 {
        var d = [
            [0, 1, 2, 3, 4, 5, 6, 7, 8, 9],
            [1, 2, 3, 4, 0, 6, 7, 8, 9, 5],
            [2, 3, 4, 0, 1, 7, 8, 9, 5, 6],
            [3, 4, 0, 1, 2, 8, 9, 5, 6, 7],
            [4, 0, 1, 2, 3, 9, 5, 6, 7, 8],
            [5, 9, 8, 7, 6, 0, 4, 3, 2, 1],
            [6, 5, 9, 8, 7, 1, 0, 4, 3, 2],
            [7, 6, 5, 9, 8, 2, 1, 0, 4, 3],
            [8, 7, 6, 5, 9, 3, 2, 1, 0, 4],
            [9, 8, 7, 6, 5, 4, 3, 2, 1, 0]
        ];
        var p = [
            [0, 1, 2, 3, 4, 5, 6, 7, 8, 9],
            [1, 5, 7, 6, 2, 8, 3, 0, 9, 4],
            [5, 8, 0, 3, 7, 9, 6, 1, 4, 2],
            [8, 9, 1, 6, 0, 4, 3, 5, 2, 7],
            [9, 4, 5, 3, 1, 2, 6, 8, 7, 0],
            [4, 2, 8, 6, 5, 7, 3, 9, 0, 1],
            [2, 7, 9, 3, 8, 0, 6, 4, 1, 5],
            [7, 0, 4, 6, 9, 1, 3, 2, 5, 8]
        ];

        var j = [0, 4, 3, 2, 1, 5, 6, 7, 8, 9];

        var c = 0;
        txtUID.replace(/\D+/g, "").split("").reverse().join("").replace(/[\d]/g, function (u, i, o) {
            c = d[c][p[i & 7][parseInt(u, 10)]];
        });
       
        var t = (c === 0); 
        return (t); 
        
    }
	else
		{
			return false;
		}
	
}

	function fun_validatePANCard(idval)
	{
		var pattern = /^([a-zA-Z]{5})(\d{4})([a-zA-Z]{1})$/;
	    if(pattern.test(idval)==false)
	    	{
	    	  return false;
	    	}
	   return true;
	
	}

	function fun_validatePassport(idval)
	{
		var pattern = /^([a-zA-Z]{1})(\d{7})$/;
	    if(pattern.test(idval)==false)
	    	{
	    	  return false;
	    	}
	   return true;
	
	}

	function onKeyPressAlphbaNumericOnly(val)       
	 {   
	     
			//alert(key);
	     reg = /^[0-9a-zA-Z]+$/;   
			 
				
				if(reg.test(val)==true)
				{
				return true;
				}
				else
				{
				return false;
				}
			 
	 }   
	function validateContactdetailsms(contid,contidErrMsg)
	{
		//alert(contidErrMsg+""+contid);
			var contactnum = $("#contid").val();
			var pattern = /^[0]?[789]\d{9}$/;
			if(contactnum.length==0)
	 		{
				$("#contidErrMsg").removeClass("errmsg");
				$("#contidErrMsg").html("").show();
	 			return true;	
	 		}
			if (pattern.test(contactnum) == false)
			{
				$("#contidErrMsg").addClass("errmsg");
				$("#contidErrMsg").html("Not Valid").show();
				$("#contid").val("");
				$("#contid").focus();
				alert("Please Enter Valid Contact No.");
				return false;
			}
			else
			{
				$("#contidErrMsg").removeClass("errmsg");
				$("#contidErrMsg").html("").show();
				return true;
			}
	}
    