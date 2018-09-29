
            function generateDob()
            {
            var x=document.getElementById("noOfYears").value;
            if(x=="")
            alert("Enter Years");
            else
            {
            var d=new Date();
            var year=d.getYear();
            var dob_year=year-x;
            document.getElementById("year").value=dob_year;
            document.getElementById("month").value=d.getMonth()+1;
            document.getElementById("day").value=d.getDate();
            }
             
            }
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
          
            document.forms[0].generate_dob.disabled = false;
            document.forms[0].calculate_age.disabled = true;
            }
           
                   
            function enableCalculateAge()
            {
            document.forms[0].calculate_age.disabled = false;
            document.forms[0].generate_dob.disabled = true;
            }
            
            function disabile()
            {
            if(document.forms[0].education.selectedIndex == 1)
            {
            document.forms[0].edustatus.disabled = true;
            document.forms[0].edustatus.value = 0;
            }
            else
            {
            document.forms[0].edustatus.disabled = false;
            }
            }  
     
             function goToNext()
             {
             
           
    <%String personstatus=(String)session.getAttribute("personstatus"); %>

var x="<%=personstatus%>"
   if(x=="Eligible")
           {
            document.partAForm.action="PartADisabilityDetailsUpdateForwdAction.do";
             document.forms[0].submit();
             }
           else{
            document.partAForm.action="PartARejectedDetailsUpdateForwdAction.do";
          document.forms[0].submit();
           }
            } 
                        
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
            document.forms[0].noOfDisableperson.value =0;
            document.forms[0].relation.value =0;
                             
            }
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
            with (thisform)
            {
           
           
            if (validate_required(formno,"Form No must be selected!")==false)
            {
            formno.focus();
            return false
            }
            if (validate_required(fromdate,"Date must be selected!")==false)
            {
            fromdate.focus();
            return false
            }
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
              
             if (validate_required(surname,"Sur Name must be Entered!")==false)
            {
            surname.focus();
            return false
            }	
            if (validate_required(firstname,"First Name must be Entered!")==false)
            {
            firstname.focus();
            return false
            }
            
            if (validate_required(mole1,"Identification Marks(Moles) must be Entered!")==false)
            {
            mole1.focus();
            return false
            }
            if (validate_required(mole2,"Identification Marks(Moles) must be Entered!")==false)
            {
            mole2.focus();
            return false
            }
            if (validate_required(noOfYears,"Age must be Entered!")==false)
            {
            noOfYears.focus();
            return false
            }
            
            if (validate_required(gender,"Gender Name must be selected!")==false)
            {
            gender.focus();
            return false
            }		
            if (validate_required(gsurname,"Father/Mother/Husband/Guardian's Name must be Entered!")==false)
            {
            gsurname.focus();
            return false
            }
            if (validate_required(grelation,"Relation must be selected!")==false)
            {
            grelation.focus();
            return false
            }
           
            if (validate_required(surnametelugu,"Candidate Telugu Nmae must be Enter!")==false)
            {
            surnameenglish.focus();
            return false
            }	
            if (validate_required(firstnametelugu,"Father/Mother/Husband/Guardian's Telugu Nmae must be Enter!")==false)
            {
            firstnameenglish.focus();
            return false
            }	
            if (validate_required(personstatus,"person status must be entered!")==false)
            {
            personstatus.focus();
            return false
            }                   
            }
            }
            
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
            
            function validatecandidateage(){  
            var noOfYears=document.partAForm.noOfYears.value;
        
            if(isNaN(noOfYears))
            {
            alert("Enter Numbers only in your age")
            document.partAForm.noOfYears.value="";
            document.partAForm.noOfYears.focus();
            }          
            if(parseInt(noOfYears)< 0 ||
            parseInt(noOfYears)> 120)
            {
            alert ("Please enter yours valid age!");
            document.partAForm.noOfYears.value="";
            document.partAForm.noOfYears.focus();
            }
            return true;
            }         
            
          
            
            function numbervalidation()
            {
            var phoneno=document.partAForm.phoneno.value;
            if(isNaN(phoneno))
            {
            alert("Enter Valid Phone Number")
            document.partAForm.phoneno.value="";
            document.partAForm.phoneno.focus();
       
            }
            return true;
            }
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