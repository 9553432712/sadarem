 function trimFormfields(thisform)
            {
                thisform.rightear500.value=trim(thisform.rightear500.value," ");
                thisform.rightear1000.value=trim(thisform.rightear1000.value," ");
                thisform.rightear2000.value=trim(thisform.rightear2000.value," ");
                
                 thisform.leftear500.value=trim(thisform.leftear500.value," ");
                thisform.leftear1000.value=trim(thisform.leftear1000.value," ");
                thisform.leftear2000.value=trim(thisform.leftear2000.value," ");
                
            }
            function trim(str, chars) 
            {
            return ltrim(rtrim(str, chars), chars);
            }

            function ltrim(str, chars) 
            {
            chars = chars || "\\s";
            return str.replace(new RegExp("^[" + chars + "]+", "g"), "");
            }

            function rtrim(str, chars) 
            {
            chars = chars || "\\s";
            return str.replace(new RegExp("[" + chars + "]+$", "g"), "");
            }
            
            
  function validate_required(field,alerttxt)
            {
            with (field)
            {

            if (value==null||value=="")
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
            function validate_form(thisform)
            {
            trimFormfields(thisform);
            var flag = true;
            with (thisform)
            {
             //--Validation  For rightear500-->  
            if (validate_required(rightear500,"Right Ear 500 must be entered!")==false)
            {
            rightear500.focus();
            flag = false;
            return flag
            }
               //--Validation  For rightear1000-->  
            if (validate_required(rightear1000,"Right Ear 1000 must be entered!")==false)
            {
            rightear1000.focus();
            flag = false;
            return flag
            }

            //--Validation  For rightear2000-->  
            if (validate_required(rightear2000,"Right Ear 2000 must be entered!")==false)
            {
            rightear2000.focus();
            flag = false;
            return flag
            }

      //--Validation  For leftear500-->  
            if (validate_required(leftear500,"Left Ear 500 must be entered!")==false)
            {
            leftear500.focus();
            flag = false;
            return flag
            }
               //--Validation  For leftear1000-->  
            if (validate_required(leftear1000,"Left Ear 1000 must be entered!")==false)
            {
            leftear1000.focus();
            flag = false;
            return flag
            }

            //--Validation  For leftear2000-->  
            if (validate_required(leftear2000,"Left Ear 2000 must be entered!")==false)
            {
            leftear2000.focus();
            flag = false;
            return flag
            }
            }
            return flag;
}


 function validatecharecters(){  



            var iChars = "!@#$%^&*()+=[]\\\'`~;,./{}|\":<>?";

    var rightear250=document.forms[0].rightear250.value;
    var rightear500=document.forms[0].rightear500.value;
    var rightear1000=document.forms[0].rightear1000.value;
    var rightear2000=document.forms[0].rightear2000.value;
    var rightear4000=document.forms[0].rightear4000.value;
    var rightear8000=document.forms[0].rightear8000.value;

    var leftear250=document.forms[0].leftear250.value;
    var leftear500=document.forms[0].leftear500.value;
    var leftear1000=document.forms[0].leftear1000.value;
    var leftear2000=document.forms[0].leftear2000.value;
    var leftear4000=document.forms[0].leftear4000.value;
    var leftear8000=document.forms[0].leftear8000.value;

    var sre_pta=document.forms[0].speechaudiometryrightear_pta.value;
    var sle_pta=document.forms[0].speechaudiometryleftear_pta.value;






if(rightear250!="" || rightear500!="" || rightear1000!="" || rightear2000!="" || rightear4000!="" || rightear8000!="" || leftear250!="" || leftear500!="" || leftear1000!="" || leftear2000!="" || leftear4000!="" || leftear8000!="" || sre_pta!="" ||  sle_pta!="" )
{


 if(rightear250!=""){   
   
            for (var i = 0; i < rightear250.length; i++) {

  	    if (iChars.indexOf(rightear250.charAt(i)) != -1) {

  	    alert ("Please Enter Valid Air Counduction Right Ear 250 Number.");
            document.hearingactionform.rightear250.value="";
            document.hearingactionform.rightear250.focus();
  	    return false;
  	    }
            }
            if(isNaN(rightear250))
            {
            alert("Please Enter Valid Air Counduction Right Ear 250 Number.")
           document.hearingactionform.rightear250.value="";
            document.hearingactionform.rightear250.focus();
           return false;
            }  
           if(parseInt(rightear250)< -10 ||
            parseInt(rightear250)> 120)
            {
            alert ("Please enter a valid Air Counduction Right Ear 250 DB Level!");
            document.hearingactionform.rightear250.value="";
            document.hearingactionform.rightear250.focus();
            return false;
            }
              
          
            }         


 if(rightear500!=""){   
   
            for (var i = 0; i < rightear500.length; i++) {

  	    if (iChars.indexOf(rightear500.charAt(i)) != -1) {

  	    alert ("Please Enter Valid Air Counduction Right Ear 500 Number.");
            document.hearingactionform.rightear500.value="";
            document.hearingactionform.rightear500.focus();
  	    return false;
  	    }
            }
            if(isNaN(rightear500))
            {
            alert("Please Enter Valid Air Counduction Right Ear 500 Number.")
           document.hearingactionform.rightear500.value="";
            document.hearingactionform.rightear500.focus();
           return false;
            }  
            if(parseInt(rightear500)< -10 ||
            parseInt(rightear500)> 120)
            {
            alert ("Please enter a valid Air Counduction Right Ear 500 DB Level!");
            document.hearingactionform.rightear500.value="";
            document.hearingactionform.rightear500.focus();
            return false;
            }        
           
            }         


if(rightear1000!=""){   
   
            for (var i = 0; i < rightear1000.length; i++) {

  	    if (iChars.indexOf(rightear1000.charAt(i)) != -1) {

  	    alert ("Please Enter Valid Air Counduction Right Ear 1000 Number.");
            document.hearingactionform.rightear1000.value="";
            document.hearingactionform.rightear1000.focus();
  	    return false;
  	    }
            }
            if(isNaN(rightear1000))
            {
            alert("Please Enter Valid  Air Counduction Right Ear 1000 Number.")
           document.hearingactionform.rightear1000.value="";
            document.hearingactionform.rightear1000.focus();
           return false;
            }  
            if(parseInt(rightear1000)< -10 ||
            parseInt(rightear1000)> 120)
            {
            alert ("Please enter a valid Air Counduction Right Ear 1000 DB Level!");
            document.hearingactionform.rightear1000.value="";
            document.hearingactionform.rightear1000.focus();
            return false;
            }
            }         


if(rightear2000!=""){   
     
            for (var i = 0; i < rightear2000.length; i++) {

  	    if (iChars.indexOf(rightear2000.charAt(i)) != -1) {

  	    alert ("Please Enter Valid Air Counduction Right Ear 2000 Number.");
            document.hearingactionform.rightear2000.value="";
            document.hearingactionform.rightear2000.focus();
  	    return false;
  	    }
            }
            if(isNaN(rightear2000))
            {
            alert("Please Enter Valid Air Counduction Right Ear 2000 Number.")
           document.hearingactionform.rightear2000.value="";
            document.hearingactionform.rightear2000.focus();
           return false;
            } 
            if(parseInt(rightear2000)< -10 ||
            parseInt(rightear2000)> 120)
            {
            alert ("Please enter a valid Air Counduction Right Ear 2000 DB Level!");
            document.hearingactionform.rightear2000.value="";
            document.hearingactionform.rightear2000.focus();
             return false;
            }
            }         


if(rightear4000!=""){   
     
            for (var i = 0; i < rightear4000.length; i++) {

  	    if (iChars.indexOf(rightear4000.charAt(i)) != -1) {

  	    alert ("Please Enter Valid Air Counduction Right Ear 4000 Number.");
            document.hearingactionform.rightear4000.value="";
            document.hearingactionform.rightear4000.focus();
  	    return false;
  	    }
            }
            if(isNaN(rightear4000))
            {
            alert("Please Enter Valid Air Counduction Right Ear 4000 Number.")
           document.hearingactionform.rightear4000.value="";
            document.hearingactionform.rightear4000.focus();
           return false;
            }   
            if(parseInt(rightear4000)< -10 ||
            parseInt(rightear4000)> 120)
            {
            alert ("Please enter a valid Air Counduction Right Ear 4000 DB Level!");
            document.hearingactionform.rightear4000.value="";
            document.hearingactionform.rightear4000.focus();
             return false;
            }
            }         


if(rightear8000!=""){   
     
            for (var i = 0; i < rightear8000.length; i++) {

  	    if (iChars.indexOf(rightear8000.charAt(i)) != -1) {

  	    alert ("Please Enter Valid Air Counduction Right Ear 8000 Number.");
            document.hearingactionform.rightear8000.value="";
            document.hearingactionform.rightear8000.focus();
  	    return false;
  	    }
            }
            if(isNaN(rightear8000))
            {
            alert("Please Enter Valid Air Counduction Right Ear 8000 Number.")
           document.hearingactionform.rightear8000.value="";
            document.hearingactionform.rightear8000.focus();
           return false;
            }  
           if(parseInt(rightear8000)< -10 ||
            parseInt(rightear8000)> 120)
            {
            alert ("Please enter a valid Air Counduction Right Ear 8000 DB Level!");
            document.hearingactionform.rightear8000.value="";
            document.hearingactionform.rightear8000.focus();
            return false;
            }
            }   


if(leftear250!=""){   
     
            for (var i = 0; i < leftear250.length; i++) {

  	    if (iChars.indexOf(leftear250.charAt(i)) != -1) {

  	    alert ("Please Enter Valid Air Counduction Left Ear 250 Number.");
            document.hearingactionform.leftear250.value="";
            document.hearingactionform.leftear250.focus();
  	    return false;
  	    }
            }
            if(isNaN(leftear250))
            {
            alert("Please Enter Valid Air Counduction Left Ear 250 Number.")
           document.hearingactionform.leftear250.value="";
            document.hearingactionform.leftear250.focus();
           return false;
            }  
            if(parseInt(leftear250)< -10 ||
            parseInt(leftear250)> 120)
            {
            alert ("Please enter a valid Air Counduction Left Ear 250 DB Level!");
            document.hearingactionform.leftear250.value="";
            document.hearingactionform.leftear250.focus();
             return false;
            }
            }         


if(leftear500!=""){   
     
            for (var i = 0; i < leftear500.length; i++) {

  	    if (iChars.indexOf(leftear500.charAt(i)) != -1) {

  	    alert ("Please Enter Valid Air Counduction Left Ear 500 Number.");
            document.hearingactionform.leftear500.value="";
            document.hearingactionform.leftear500.focus();
  	    return false;
  	    }
            }
            if(isNaN(leftear500))
            {
            alert("Please Enter Valid Air Counduction Left Ear 500 Number.")
           document.hearingactionform.leftear500.value="";
            document.hearingactionform.leftear500.focus();
           return false;
            }  
            if(parseInt(leftear500)< -10 ||
            parseInt(leftear500)> 120)
            {
            alert ("Please enter a valid Air Counduction Left Ear 500 DB Level!");
            document.hearingactionform.leftear500.value="";
            document.hearingactionform.leftear500.focus();
            return false;
            }
            }         


if(leftear1000!=""){   
     
            for (var i = 0; i < leftear1000.length; i++) {

  	    if (iChars.indexOf(leftear1000.charAt(i)) != -1) {

  	    alert ("Please Enter Valid Air Counduction Left Ear 1000 Number.");
            document.hearingactionform.leftear1000.value="";
            document.hearingactionform.leftear1000.focus();
  	    return false;
  	    }
            }
            if(isNaN(leftear1000))
            {
            alert("Please Enter Valid  Air Counduction Left Ear 1000 Number.")
           document.hearingactionform.leftear1000.value="";
            document.hearingactionform.leftear1000.focus();
           return false;
            } 
           if(parseInt(leftear1000)< -10 ||
            parseInt(leftear1000)> 120)
            {
            alert ("Please enter a valid Air Counduction Left Ear 1000 DB Level!");
            document.hearingactionform.leftear1000.value="";
            document.hearingactionform.leftear1000.focus();
           return false;
            }
            }         


if(leftear2000!=""){   
     
            for (var i = 0; i < leftear2000.length; i++) {

  	    if (iChars.indexOf(leftear2000.charAt(i)) != -1) {

  	    alert ("Please Enter Valid Air Counduction Left Ear 2000 Number.");
            document.hearingactionform.leftear2000.value="";
            document.hearingactionform.leftear2000.focus();
  	    return false;
  	    }
            }
            if(isNaN(leftear2000))
            {
            alert("Please Enter Valid Air Counduction Left Ear 2000 Number.")
           document.hearingactionform.leftear2000.value="";
            document.hearingactionform.leftear2000.focus();
           return false;
            } 
           if(parseInt(leftear2000)< -10 ||
            parseInt(leftear2000)> 120)
            {
            alert ("Please enter a valid Air Counduction Left Ear 2000 DB Level!");
            document.hearingactionform.leftear2000.value="";
            document.hearingactionform.leftear2000.focus();
             return false;
            }
            }         


if(leftear4000!=""){   
     
            for (var i = 0; i < leftear4000.length; i++) {

  	    if (iChars.indexOf(leftear4000.charAt(i)) != -1) {

  	    alert ("Please Enter Valid Air Counduction Left Ear 4000 Number.");
            document.hearingactionform.leftear4000.value="";
            document.hearingactionform.leftear4000.focus();
  	    return false;
  	    }
            }
            if(isNaN(leftear4000))
            {
            alert("Please Enter Valid  Air Counduction Left Ear 4000 Number.")
           document.hearingactionform.leftear4000.value="";
            document.hearingactionform.leftear4000.focus();
           return false;
            }  
            if(parseInt(leftear4000)< -10 ||
            parseInt(leftear4000)> 120)
            {
            alert ("Please enter a valid Air Counduction Left Ear 4000 DB Level!");
            document.hearingactionform.leftear4000.value="";
            document.hearingactionform.leftear4000.focus();
             return false;
            }
            }         


if(leftear8000!=""){   
     
            for (var i = 0; i < leftear8000.length; i++) {

  	    if (iChars.indexOf(leftear8000.charAt(i)) != -1) {

  	    alert ("Please Enter Valid Air Counduction Left Ear 8000 Number.");
            document.hearingactionform.leftear8000.value="";
            document.hearingactionform.leftear8000.focus();
  	    return false;
  	    }
            }
            if(isNaN(leftear8000))
            {
            alert("Please Enter Valid Air Counduction Left Ear 8000 Number.")
           document.hearingactionform.leftear8000.value="";
            document.hearingactionform.leftear8000.focus();
           return false;
            }    
           if(parseInt(leftear8000)< -10 ||
            parseInt(leftear8000)> 120)
            {
            alert ("Please enter a valid Air Counduction Left Ear 8000 DB Level!");
            document.hearingactionform.leftear8000.value="";
            document.hearingactionform.leftear8000.focus();
            return false;
            }
            }         





     
if(sre_pta!=""){   
     
            for (var i = 0; i < sre_pta.length; i++) {

  	    if (iChars.indexOf(sre_pta.charAt(i)) != -1) {

  	    alert ("Please Enter Valid Speech Audiometry Rightear PTA.");
            document.hearingactionform.speechaudiometryrightear_pta.value="";
            document.hearingactionform.speechaudiometryrightear_pta.focus();
  	    return false;
  	    }
            }
            if(isNaN(sre_pta))
            {
            alert("Please Enter Valid Speech Audiometry Rightear PTA.")
           document.hearingactionform.speechaudiometryrightear_pta.value="";
            document.hearingactionform.speechaudiometryrightear_pta.focus();
           return false;
            }    
           if(parseInt(sre_pta)< -10 ||
            parseInt(sre_pta)> 120)
            {
            alert ("Please enter a valid Speech Audiometry Rightear PTA!");
            document.hearingactionform.speechaudiometryrightear_pta.value="";
            document.hearingactionform.speechaudiometryrightear_pta.focus();
            return false;
            }
            }         


     
if(sle_pta!=""){   
     
            for (var i = 0; i < sle_pta.length; i++) {

  	    if (iChars.indexOf(sle_pta.charAt(i)) != -1) {

  	    alert ("Please Enter Valid Speech Audiometry LeftEar PTA.");
            document.hearingactionform.speechaudiometryleftear_pta.value="";
            document.hearingactionform.speechaudiometryleftear_pta.focus();
  	    return false;
  	    }
            }
            if(isNaN(sle_pta))
            {
            alert("Please Enter Valid Speech Audiometry LeftEar PTA.")
           document.hearingactionform.speechaudiometryleftear_pta.value="";
            document.hearingactionform.speechaudiometryleftear_pta.focus();
           return false;
            }    
           if(parseInt(sle_pta)< -10 ||
            parseInt(sle_pta)> 120)
            {
            alert ("Please enter a valid Speech Audiometry LeftEar PTA!");
            document.hearingactionform.speechaudiometryleftear_pta.value="";
            document.hearingactionform.speechaudiometryleftear_pta.focus();
            return false;
            }
            }         

 
}
}




function resetbutton(form)
{

document.hearingactionform.rightear250.value="";
document.hearingactionform.rightear500.value="";
document.hearingactionform.rightear1000.value="";
document.hearingactionform.rightear2000.value="";
document.hearingactionform.rightear4000.value="";
document.hearingactionform.rightear8000.value="";
document.hearingactionform.righteardblevel.value="";

document.hearingactionform.leftear250.value="";
document.hearingactionform.leftear500.value="";
document.hearingactionform.leftear1000.value="";
document.hearingactionform.leftear2000.value="";
document.hearingactionform.leftear4000.value="";
document.hearingactionform.leftear8000.value="";
document.hearingactionform.lefteardblevel.value="";



document.hearingactionform.speechaudiometryrightear_pta.value="";

document.hearingactionform.speechaudiometryleftear_pta.value="";
    document.hearingactionform.speechtheraphy.checked="";
    document.hearingactionform.surgery.value="";
    document.hearingactionform.speechtherapy.checked="";
    document.hearingactionform.languagedevelopment.checked="";
    document.hearingactionform.hearingaidselect.value="";
    document.hearingactionform.hearingaidtype.value="";
    document.hearingactionform.cochlearimplantation.checked="";
    document.hearingactionform.implantablehearingaid.checked="";
    document.hearingactionform.puretoneaudiometric.checked="";
    document.hearingactionform.pediatricaudiometry.checked="";
    document.hearingactionform.selectealarmingdevices.value="";
    document.hearingactionform.speechtrainer.checked="";
    document.hearingactionform.anyadlequipment.checked="";
    document.hearingactionform.anyotherhearingimpairement.value="";
document.getElementById("row1").style.visibility='hidden';
document.getElementById("row1").style.display='none';
document.getElementById("row").style.visibility='hidden';
document.getElementById("row").style.display='none';

}