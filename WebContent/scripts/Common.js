/*
1) Function for replacing extra space in text field and
text area
onBlur="this.value = SpaceReplace(this.value);"
*/

function SpaceReplace(str) 
{
var out = "", flag = 0;

	for (var i = 0; i < str.length; i++) 
	{
		if (str.charAt(i) != " ") 
			{
			out += str.charAt(i);
			flag = 0;
			}
			else 
			{
			if(flag == 0) 
				{
				out += "^";
				flag = 1;
				}
		}
	}
//var outer="";
		if(out!="^")
		{

			if(out.charAt(0)=="^")
				{
					out=out.replace(/\^/,'');
				}
			
			if(out.charAt(0)!="^")
				{
				out=out.replace(/\^/g,' ');
				}
				
			if(out.substr((out.length-1),out.length)==" ")
				{
				out=out.substr(0,out.length-1);
				}
		}
		else
		{
		out="";
		}
return out;
}


/* 
Disable CTRL + and other key combination
onKeyDown="return disableCtrlKeyCombination(event);" 
*/

function disableCtrlKeyCombination(e)
{
        //list all CTRL + key combinations you want to disable
        var forbiddenKeys = new Array('a', 'n', 'c', 'x', 'v', 'j','p');
        var key;
        var isCtrl;

        if(window.event)
        {
                key = window.event.keyCode;     //IE
                if(window.event.ctrlKey)
                        isCtrl = true;
                else
                        isCtrl = false;
        }
        else
        {
                key = e.which;     //firefox
                if(e.ctrlKey)
                        isCtrl = true;
                else
                        isCtrl = false;
        }

        //if ctrl is pressed check if other key is in forbidenKeys array
        if(isCtrl)
        {
                for(var i=0; i<forbiddenKeys.length; i++)
                {
                        //case-insensitive comparation
                        if(forbiddenKeys[i].toLowerCase() == String.fromCharCode(key).toLowerCase())
                        {
                                alert( "Key combination CTRL + " +String.fromCharCode(key)+" has been disabled.");
                                return false;
                        }
                }
        }
        return true;
}

/*
Email Validation
OnfocusOut="checkEmail(this)"
*/

function EmailCheck(myForm) 
{
	
	 var filter =/^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;

	   
	 if (myForm.value!="" && filter.test(myForm.value)) 
	    {
	        return true;
	    }
	    else
	    {
	    	if(myForm.value!="")
	    	{
	    		alert("Invalid E-mail address please re-enter.");
	    		myForm.value="";
	    	}
	        return false;
	    }
	
	/*if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(myForm.value))
	{
		return (true);
	}
	{
		alert("Invalid E-mail address please re-enter.");
		myForm.value="";
		return (false);
	}*/
}

/* Radio button validation
 * onClick="RadioButton(this)"
 */
function RadioButton(id)
{
	  var radios = document.getElementsByName("JourneyType");
	    //var found = 1;
	    for (var i = 0; i < radios.length; i++) 
		{       
	        if (radios[i].checked)
			{
	            //alert(radios[i].value);
	            found = 0;
	            return radios[i].value;
	        }
	    }

}
/*
Initcap function 
onFocusOut="InitCapFun(this)"
*/

function InitCapFun(id)
{
	var str=id.value;
var text=str;
var sptext=text.split(" ");
function initCap(str) { 
 /* First letter as uppercase, rest lower */ 
  str = str.substring(0,1).toUpperCase() + str.substring(1,str.length).toLowerCase(); 

 return str; 
} 
text="";
	for(var i=0;i<sptext.length;i++)
	{
		if(text!="")
		{
		text=text+" "+initCap(sptext[i]);
		}
		else
		{
		 text=initCap(sptext[i]);
		}
	}
	if((text.substring(text.length-1,text.length))=="")
	{
	 text.substring(0,text.length-1);
	}
	
id.value=text;
}

/* 
Only Numbers
onkeypress="return NumbersOnly(event);"
*/
 
    function NumbersOnly(e)       
    {   
        var key = window.event ? e.keyCode:e.which;   
		//alert(key);
        var keychar = String.fromCharCode(key);   
        reg = /\d/;   
	
		if(key==0 || key==8)
		{
			return true;
		}
		else
		{
			 return reg.test(keychar);   
		}
    }   
    
   
    
    

/*
Only Characters
onkeypress="return CharactersOnly(event);"
*/

 function CharactersOnly(e)       
    {   
        var key = window.event ? e.keyCode:e.which;   
        var keychar = String.fromCharCode(key);   
        reg = /\d/;   
		if(reg.test(keychar)==false)
		{
        return true;
		}
		else
		{
		return false;
		}
	}

/*
Only Alphabates
onkeypress="return onKeyPressAlphbateOnly(event);"
*/

 function onKeyPressAlphbateOnly(e)       
 {   
     var key = window.event ? e.keyCode:e.which;   
     var keychar = String.fromCharCode(key);   
		//alert(key);
     reg = /\d/;   
		if(reg.test(keychar)==false)
		{
			if(((parseInt(key)>=65&&parseInt(key)<=90)||(parseInt(key)>=97&&parseInt(key)<=122))||parseInt(key)==32||parseInt(key)==8||parseInt(key)==0)
			{
			return true;
			}
			else
			{
			return false;
			}
		}
		else
		{
		return false;
		}
 }    
 

 /*
 Only AlphaNumeric no space
 onkeypress="return onKeyPressAlphaNumericOnly(event);"
 */

  function onKeyPressAlphaNumericOnly(e)       
  {   
      var key = window.event ? e.keyCode:e.which;   
      var keychar = String.fromCharCode(key);   
 		// alert(key);
      reg = /^[a-zA-Z0-9]*$/;   
 		if(reg.test(keychar))
 		{ 
 			return true;  
 		}
 		else
 		{
 			return false;
 		}
  } 

 
 
 function DateVerification(stdate,endate)
 {
		var inx="-",iny="-";
			if(stdate.indexOf('-')!="-1")
			{	
			inx="-";
			}
			if(endate.indexOf('-')!="-1")
			{
			iny="-";
			}
     var DateArray =stdate.split(inx);
     var CheckDateArray =endate.split(iny);
     var DateEntered1 =new Date(DateArray[2],parseInt(DateArray[1]-1), DateArray[0]); //Month is 0-11 in JavaScript
     var CheckDate  = new Date(CheckDateArray[2],parseInt(CheckDateArray[1]-1), CheckDateArray[0]); //Month is 0-11 in JavaScript
     var one_day=1000*60*60*24;
		//alert(CheckDate.getTime());
     var Difference = Math.ceil((CheckDate.getTime()-DateEntered1.getTime())/(one_day));
	//alert(Difference);
     if(parseInt(Difference)< 0)
     {
		//alert("Start Date Should not be less than End date. ");
     return false;
     }
		else
		{
		return true;
		}
    
    }
 
 
 function dateDiff(startDate,endDate)
 {
 	var d1 = dstrToUTC(startDate);
 	 var d2 = dstrToUTC(endDate);
 	  var oneday = 86400000;
 	 var noOfDays=((d2-d1)/oneday);
 	  return noOfDays;
 	
 }
 
 function dstrToUTC(ds) 
 {
	 var dsarr = ds.split("-");
	 var mm = parseInt(dsarr[1],10);
	 var dd = parseInt(dsarr[0],10);
	 var yy = parseInt(dsarr[2],10);
	 return Date.UTC(yy,mm-1,dd,0,0,0);
}
 
 function stripHTML(id)
 {
	
	 var idVal = id.value;
 	var reg =/<(.|\n)*?>/g; 
 	if (reg.test(idVal) == true)
 		{
 	    var ErrorText ='HTML TAGS are not allowed';
 	    alert(ErrorText);
 	   id.value ="";
 	    return false;
 	    }
 	else
 		{
 		return true;
 		}
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


 /*
  * Limiting the length 
  * onKeyPress="return fun_limitlength(this,'200')"
  * */
 
  
 function fun_limitlength(id,len) 
 {
      
 	 var k=id.value;
 	 if(parseInt(k.length)+1<=parseInt(len))
 	 {
 		return true;
 	 }
 	  else 
 	  {
 	  return false;
 	  }
     
 }
 
 /* String revers function*/
 
 function fun_reverse(s) 
 {
	  var o = '';
	  for (var i = s.length - 1; i >= 0; i--)
	    o += s[i];
	  return o;
 }