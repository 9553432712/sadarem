  /*!--Validation For Space Bar-->*/
            function trimFormfields(thisform)
            {

                
                thisform.formno.value=trim(thisform.formno.value," ");
               
                thisform.fromdate.value=trim(thisform.fromdate.value," ");
                
                
                thisform.surname.value=trim(thisform.surname.value," ");
                
                thisform.firstname.value=trim(thisform.firstname.value," ");
                thisform.surnameenglish.value=trim(thisform.surnameenglish.value," ");
                thisform.noOfYears.value=trim(thisform.noOfYears.value," ");
                thisform.gender.value=trim(thisform.gender.value," ");
                thisform.marital.value=trim(thisform.marital.value," ");
                thisform.mole1.value=trim(thisform.mole1.value," ");
                thisform.mole2.value=trim(thisform.mole2.value," ");
                thisform.gsurname.value=trim(thisform.gsurname.value," ");
                thisform.grelation.value=trim(thisform.grelation.value," ");
                thisform.firstnameenglish.value=trim(thisform.firstnameenglish.value," ");
                thisform.houseno.value=trim(thisform.houseno.value," ");

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
            
            
            
              
           /*  <!--Script  For Calculation of Age-->*/
            function calculateAge()
            {
            var x=document.getElementById("year").value;
            var d=new Date();
            var year=d.getYear();
            var age=year-x;
            document.getElementById("noOfYears").value=age;
            }
            function disableCalculateDob()
            {
          
            
            document.forms[0].calculate_age.disabled = true;
            }
            function enableCalculateAge()
            {
            document.forms[0].calculate_age.disabled = false;
           
            }
    
            
            
            /* <!--Validation  For Education Illeterate-->*/
            function disabile()
            {
            if(document.forms[0].education.selectedIndex == 0 || document.forms[0].education.selectedIndex == 1)
            {
            document.forms[0].edustatus.disabled = true;
            document.forms[0].edustatus.value = 0;
            }
            else
            {
            document.forms[0].edustatus.disabled = false;
            }
            }           
           /* <!--Validation  For Relation Checkbox-->*/
            function validaterelationcheckbox()
            {
            if(document.partAForm.yesanyDisableperson.checked)
            {
            document.getElementById("diasabledinfamily").style.visibility = "Visible";
            document.getElementById("diasabledinfamily").style.display = "Inline";
            }
            else
            {
            document.getElementById("diasabledinfamily").style.visibility = "hidden";
            document.getElementById("diasabledinfamily").style.display = "none";
            document.forms[0].noOfDisableperson.value ="";
            document.forms[0].relation.value =0;
            }
            }
      /*       <!--Validation  For NregCard Checkbox-->*/
            function validatenregcheckbox()
            {
            if(document.partAForm.nregcard.checked)
            {
            document.getElementById("nreg").style.visibility = "Visible";
            document.getElementById("nreg").style.display = "Inline";
            }
            else
            {
            document.getElementById("nreg").style.visibility = "hidden";
            document.getElementById("nreg").style.display = "none";
            document.forms[0].nregcardno.value ="";
            }
            }
            
    /*       <!--Validation  For Arogyasri Checkbox--> */ 
            function validatearogyacheckbox()
            {
            if(document.partAForm.arogyasricard.checked)
            {
            document.getElementById("arogya").style.visibility = "Visible";
            document.getElementById("arogya").style.display = "Inline";
            }
            else
            {
            document.getElementById("arogya").style.visibility = "hidden";
            document.getElementById("arogya").style.display = "none";
            document.forms[0].arogyasricardno.value ="";
            }
            }
          /*   <!--Validation  For Epic Card Checkbox--> */ 
            function validateepiccheckbox()
            {
            if(document.partAForm.epiccard.checked)
            {
            document.getElementById("epic").style.visibility = "Visible";
            document.getElementById("epic").style.display = "Inline";
            }
            else
            {
            document.getElementById("epic").style.visibility = "hidden";
            document.getElementById("epic").style.display = "none";
            document.forms[0].epiccardno.value ="";
            }
            }
      /*       <!--Validation  For Pension Card Checkbox-->  */
            function validatepensioncheckbox()
            {
            if(document.partAForm.pensioncard.checked)
            {
            document.getElementById("pension").style.visibility = "Visible";
            document.getElementById("pension").style.display = "Inline";
            }
            else
            {
            document.getElementById("pension").style.visibility = "hidden";
            document.getElementById("pension").style.display = "none";
            document.forms[0].pensioncardno.value ="";
            }
            }

            
            
            
            function validate_required(field,alerttxt)
            {
            with (field)
            {
            if (field.value==null || field.value=="")
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
        
            with (thisform)
            {
             
              

          /*  <!--Validation  For Form No-->  */
            if (validate_required(formno,"Form No must be entered!")==false)
            {
            formno.focus();
            return false
            }

            
            
        /*       <!-- Form Date validation -->*/
            var datetobevalidated= document.getElementById("fromdate").value;	
        var age1 =0;
        var yy=datetobevalidated.substr(6,4);
        var mm=datetobevalidated.substr(3,2);
        var dd=datetobevalidated.substr(0,2);
        var dob = new  Date();
        var today=new Date();
        dob.setFullYear(yy,mm-1,dd);
        var y1=today.getYear();
        var y2= dob.getYear();
            if (today< dob )
        {
        alert("Please Check the Form Date")
        fromdate.focus();
         return (false);
            }
          /*  <!--Validation  For Date-->*/
            if (validate_required(fromdate,"Date must be selected!")==false)
            {
            fromdate.focus();
            return false
            }
           
            
           /*   <!--Validation  For Sur Name-->*/

            if (validate_required(surname,"Sur Name must be Entered!")==false)
            {
            surname.focus();
            return false
            }
      /*      <!--Validation  For First Name-->*/
            if (validate_required(firstname,"First Name must be Entered!")==false)
            {
            firstname.focus();
            return false
            }
           /* <!--Validation  For Candidate Telugu -->*/
            if (validate_required(surnametelugu,"PWD Telugu Name must be Enter!")==false)
            {
            surnameenglish.focus();
            return false
            }
           /*  <!--Validation  For Age -->  */             
            if (validate_required(noOfYears,"Age must be Entered!")==false)
            {
             noOfYears.focus();
            return false
            }
            
     /*        <!--Validation  For Gender -->*/
            if (validate_required(gender,"Gender Name must be selected!")==false)
            {
            gender.focus();
            return false
            }
          /*   <!--Validation  For Maritalr -->*/
            if (validate_required(marital,"marital must be selected!")==false)
            {
            marital.focus();
            return false
            }


	/*   <!--Validation  For Identification One -->*/
           
           if (validate_required(mole1,"Identification Marks(Moles) must be Entered!")==false)
            {
            mole1.focus();
            return false
            }

          /* <!--Validation  For Identification Two -->*/
          if (validate_required(mole2,"Identification Marks(Moles) must be Entered!")==false)
            {
            mole2.focus();
            return false
            }
          /* <!--Validation  For Father/Husband/Guardian's -->
*/            if (validate_required(gsurname,"Father/Mother/Husband/Guardian's Name must be Entered!")==false)
            {
            gsurname.focus();
            return false
            }
            /* <!--Validation  For Relation-->*/
            if (validate_required(grelation,"Relation must be selected!")==false)
            {
            grelation.focus();
            return false
            }	
         

           /* <!--Validation  For Father's Name in Telugu-->
*/            if (validate_required(firstnametelugu,"Father/Mother/Husband/Guardian's Name in Telugu must be Enter!")==false)
            {
            firstnameenglish.focus();
            return false
            }


         if (validate_required(houseno,"House No must be Entered!")==false)
            {
            houseno.focus();
            return false
            }	
           

          
          /* <!-- Radio button validation -->*/
            var radio_choice = false;
            for (counter = 0; counter < partAForm.personstatus.length; counter++)
               {
                   if (partAForm.personstatus[counter].checked)
            radio_choice = true; 
            }
            if (!radio_choice)
            {
            alert("Please select a Person Status.")
            return (false);
            }
            return (true);
                                  
            }
            }
          /* <!--Validation  For Numbers only in Candidate age-->*/

            function validatecandidateage(){  
            var iChars = "!@#$%^&*()+=-[]\\\'`~;,./{}|\":<>?";
            var noOfYears=document.partAForm.noOfYears.value;
            for (var i = 0; i < noOfYears.length; i++) {
  	    if (iChars.indexOf(noOfYears.charAt(i)) != -1) {
  	    alert ("Please Enter Valid Age.");
            document.partAForm.noOfYears.value="";
            document.partAForm.noOfYears.focus();
  	    return false;
  	    }
            }
            if(isNaN(noOfYears))
            {
            alert("Enter Numbers only in your age")
            document.partAForm.noOfYears.value="";
            document.partAForm.noOfYears.focus();
            }          
            if(parseInt(noOfYears)<1 ||
            parseInt(noOfYears)> 120)
            {
            alert ("Please enter yours valid age!");
            document.partAForm.noOfYears.value="";
            document.partAForm.noOfYears.focus();
            }
            return true;
            }         
            
            
          /*<!--Validation  For Numbers only in Phone Numbers-->*/
            function numbervalidation()
            {
            var iChars = "!@#$%^&*()+=[]\\\'`~;,./{}|\":<>?";
            var phoneno=document.partAForm.phoneno.value;
            for (var i = 0; i < phoneno.length; i++) {
  	    if (iChars.indexOf(phoneno.charAt(i)) != -1) {
  	    alert ("Please Enter Valid phone No Number.");
            document.partAForm.phoneno.value="";
            document.partAForm.phoneno.focus();
  	    return false;
  	    }
            }
           
            if(isNaN(phoneno))
            {
            alert("Enter Valid Phone Number")
            document.partAForm.phoneno.value="";
            document.partAForm.phoneno.focus();
            }
            return true;
            }
            
           /* <!--Validation  For Numbers only in  Number of Disabilied Persons-->*/
            function numberpersonvalidation()
            {
            var noOfDisableperson=document.partAForm.noOfDisableperson.value;
            if(isNaN(noOfDisableperson))
            {
            alert("Enter numeric data at No. of Disabiled persons")
            document.partAForm.noOfDisableperson.value="";
            document.partAForm.noOfDisableperson.focus();
            }
            return true;
            }
            /* <!--Validation  For Ration Card Type--> */
            function selecttype()
            {
            var card=document.partAForm.card.value;
            var type=document.partAForm.rtype.selectedIndex;
           // var pension_type=document.partAForm.pension_type.selectedIndex;
            if(card!="")
            {
            if(type==0)
            {
            alert ("Please enter Ration Type!");
            document.partAForm.rtype.focus();  
            return false;
            }
            }

            if(type!="")
            {
            if(card==0)
            {
            alert ("Please enter Ration card Number!");
            document.partAForm.card.focus();
            return false;
            }
            }

           // if(pension_type!="")

                //{


               // }
            if ( (document.getElementById("epiccard").checked==true) && (document.getElementById("epiccardno").value==""))
               {
          //alert("Please enter EPIC Number textbox");
          document.partAForm.epiccardno.focus(); return false;
              }

            if ( (document.getElementById("pensioncard").checked==true) && (document.getElementById("pension_type").value ==""))
               {
          alert("Please Select pension_type");
          document.partAForm.pension_type.focus(); return false;
              }

              if((document.getElementById("pensioncard").checked==true) && (document.getElementById("pensioncardno").value==""))

            {
             alert("Please Enter pension Number textbox");
             document.partAForm.pensioncardno.focus(); return false;
            }


            if ( (document.getElementById("pensioncard").isChecked==false) && (document.getElementById("pension_type").selectedIndex=0))
               //alert("Please Select type");
                   {
          //alert("Please Select pensioncazrd");
          document.partAForm.pensioncard.focus();
          //document.getElementById("pension_type").value="0";

          return false;
              }


          

            {
            return true;
            }
                 //if(document.getElementById("pensioncard").checked==false)alert("kiran");{
            //document.getElementById("pension_type").selectedIndex.value="";
          // }
                }

                function pension()
            {           //alert("we are in pension");
               if(document.partAForm.pensioncard.checked==false)
              // alert("after check");
                   {
                     //document.getElementById("pension_type").selectedIndex=0;
                     document.partAForm.pension_type.selectedIndex=0;
                      //document.partAForm.pensioncard.focus();
                   }
            }
                

/*<!-- Validation for EPIC -->*/

function EPIC()
{
    if ( (document.getElementById("epiccard").checked==true) && (document.getElementById("epiccardno").value=="") )
               {
           alert("Please enter EPIC Number textbox");
           document.partAForm.epiccardno.focus();
           return false;
               }
}



/* <!--Validation  For Email-->*/
function validateemail(){
var address = document.forms[0].email.value;
       if(address!=""){
        var reg =/^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
          
          if(reg.test(address) == false) {
          alert('Invalid Email Address');
            document.forms[0].email.value="";
            document.forms[0].email.focus();
          return false;
           }
          }
}



 



 /*   <!--Validation  For Education Status-->*/ 
            function validateeducation()
            {
          
           var education=document.partAForm.education.selectedIndex;
            var edustatus=document.partAForm.edustatus.selectedIndex;
            if(education!="" && education!=1)
            {
            if(edustatus==0)
            {
            alert ("Select Education Status ");
          
            document.partAForm.edustatus.focus();  
            return false;
            }
            }
            }

/* <!--Validation  For Identification Moles--> */

function validatemole1()
{
 var iChars = "!@#$%^&*()+=-[]\\\'`~;,/{}|\":<>?1234567890";
            var mole1=document.partAForm.mole1.value;
            for (var i = 0; i < mole1.length; i++) {
              
  	    if (iChars.indexOf(mole1.charAt(i)) != -1) {
  	    alert ("Please Enter Valid Identifacation Mole.");
            document.partAForm.mole1.value="";
            document.partAForm.mole1.focus();
  	    return false;
  	    }
            } 
}

function validatemole2()
{
 var iChars = "!@#$%^&*()+=-[]\\\'`~;,/{}|\":<>?1234567890";   
            var mole2=document.partAForm.mole2.value;
            for (var i = 0; i < mole2.length; i++) {
              
  	    if (iChars.indexOf(mole2.charAt(i)) != -1) {
  	    alert ("Please Enter Valid Identifacation Mole.");
            document.partAForm.mole2.value="";
            document.partAForm.mole2.focus();
  	    return false;
  	    }
            } 
}

/*<!-- current Date--> */

function currentdate(){

var currentTime = new Date()
var month = currentTime.getMonth() + 1
var day = currentTime.getDate()
var year = currentTime.getFullYear()

var todaydate=day+"/"+month+"/"+year;

document.getElementById("fromdate").value=todaydate;

}

 function validatesurname(){
 var iChars = "!@#$%^&*()+=-[]\\\'`~;,/{}|\":<>?";   
            var surname=document.partAForm.surname.value;
            for (var i = 0; i < surname.length; i++) {
  	    if (iChars.indexOf(surname.charAt(i)) != -1) {
  	    alert ("Please Enter Valid Surname.");
            document.partAForm.surname.value="";
            document.partAForm.surname.focus();
  	    return false;
  	    }
            } 
}

 function validatefirstname(){
 var iChars = "!@#$%^&*()+=-[]\\\'`~;,/{}|\":<>?";   
            var firstname=document.partAForm.firstname.value;
            for (var i = 0; i < firstname.length; i++) {
  	    if (iChars.indexOf(firstname.charAt(i)) != -1) {
  	    alert ("Please Enter Valid Firstname.");
            document.partAForm.firstname.value="";
            document.partAForm.firstname.focus();
  	    return false;
  	    }
            } 
}
function validatelastname(){
 var iChars = "!@#$%^&*()+=-[]\\\'`~;,/{}|\":<>?";   
            var lastname=document.partAForm.lastname.value;
            for (var i = 0; i < lastname.length; i++) {
  	    if (iChars.indexOf(lastname.charAt(i)) != -1) {
  	    alert ("Please Enter Valid Last name.");
            document.partAForm.lastname.value="";
            document.partAForm.lastname.focus();
  	    return false;
  	    }
            } 
}
function validatefathername(){
 var iChars = "!@#$%^&*()+=-[]\\\'`~;,/{}|\":<>?";   
            var gsurname=document.partAForm.gsurname.value;
            for (var i = 0; i < gsurname.length; i++) {
  	    if (iChars.indexOf(gsurname.charAt(i)) != -1) {
  	    alert ("Please Enter Valid Father name.");
            document.partAForm.gsurname.value="";
            document.partAForm.gsurname.focus();
  	    return false;
  	    }
            } 
}
 function validatepin(){  
            var iChars = "!@#$%^&*()+=-[]\\\'`~;,./{}|\":<>?e";
            var pin=document.partAForm.pin.value;
            for (var i = 0; i < pin.length; i++) {
  	    if (iChars.indexOf(pin.charAt(i)) != -1) {
  	    alert ("Please Enter Valid Pin Number.");
            document.partAForm.pin.value="";
            document.partAForm.pin.focus();
  	    return false;
  	    }
            }
            if(isNaN(pin))
            {
            alert("Please Enter Valid Pin Number.")
            document.partAForm.pin.value="";
            document.partAForm.pin.focus();
           return false;
            }          
            return true;
            }
            //for Each word Capital
function capitalizeMe(obj) {
        val = obj.value;
        newVal = '';
        val = val.split(' ');
        for(var c=0; c < val.length; c++) {
        newVal += val[c].substring(0,1).toUpperCase() +
        val[c].substring(1,val[c].length) + ' ';
        }
        obj.value = newVal;
}


