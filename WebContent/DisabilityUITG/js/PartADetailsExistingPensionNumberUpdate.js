

<!-------Starts bapinaidu java script -------------------->

<!-------Starts script For Allow Alphabets Only -------------------->
var isShift=false;
function keyUP(keyCode)
{
    if(keyCode==16)
        isShift = false;
}
function isAlpha(keyCode,name)
{
    if (keyCode == 16)
        isShift = true;
    var str = name.value;
    if(str.substring(0,1)==" ")
    {
        name.value ="";
        name.focus();
        return ((keyCode >= 65 && keyCode <= 90) || keyCode == 8 || keyCode == 9 || keyCode == 37 || keyCode == 39 || keyCode == 46)
    }else{
        return ((keyCode >= 65 && keyCode <= 90) || keyCode == 8 || keyCode == 32 || keyCode == 9 || keyCode == 37 || keyCode == 39 || keyCode == 46)
    }
}


<!-------Ends script For Allow Alphabets Only -------------------->




<!-------Starts script Allow Alphabets and Numerics -------------------->


function isAlphaNumeric(keyCode) {
    if (keyCode == 16)
        isShift = true;
    var res = (((keyCode >= 48 && keyCode <= 57) && isShift == false) ||
        (keyCode >= 65 && keyCode <= 90) || keyCode == 8 || (keyCode >= 96 && keyCode <= 105)
        || keyCode == 9 || keyCode == 37 || keyCode == 39 || keyCode == 46);
    return res;
}

<!-------Ends script Allow Alphabets and Numerics -------------------->

<!-------Starts script Allow Alphabets and Numerics with  /-------------------->

function letternumber(e)
{
var key;
var keychar;

if (window.event)
   key = window.event.keyCode;
else if (e)
   key = e.which;
else
   return true;
keychar = String.fromCharCode(key);
keychar = keychar.toLowerCase();

// control keys
if ((key==null) || (key==0) || (key==8) ||
    (key==9) || (key==13) || (key==27) )
   return true;

// alphas and numbers
else if ((("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789/").indexOf(keychar) > -1))
   return true;
else
   return false;
}


function isNumeric(keyCode) {
    if (keyCode == 16)
        isShift = true;
    var res = ((keyCode >= 48 && keyCode <= 57 || keyCode == 8 ||
        (keyCode >= 96 && keyCode <= 105)) && isShift == false ||
        keyCode == 9 || keyCode == 37 || keyCode == 39 || keyCode == 46);
    return res;
}

<!--Script  For Calculation of DOB-->
function generateDob()
{

 //alert("in generateDob"+document.forms[0].noOfYears.value);
    var x= document.forms[0].noOfYears.value;
    var d=new Date();
    var year=d.getFullYear();
    var dob_year=year-x;
    try{

    document.getElementById("year").value=dob_year;
    document.getElementById("month").value=d.getMonth()+1;
    document.getElementById("day").value=d.getDate();
    }catch(ex){

  document.forms[0].year.value=dob_year;
  document.forms[0].month.value=d.getMonth()+1;
  document.forms[0].day.value=d.getDate();

    }

}

<!-- Validation for AadharCard No  -->
function aadharCardNo(){

    var aadharCardNo = document.forms[0].aadharCardNo;
    if(aadharCardNo !=null){
        if(aadharCardNo.value == ""){
            alert("Please Enter Aadhar Card No!");
            document.forms[0].aadharCardNo.focus();
            return false;
        }
        if(aadharCardNo.value != "" && (aadharCardNo.value.length<12 ||aadharCardNo.value.length>12)){
            alert("Given Aadhar Number is Not Valid Number. Aadhar Number should be 12 digits!");
            document.forms[0].elements['aadharCardNo'].value="";
            document.forms[0].aadharCardNo.focus();
            return false;
        }
    }
    return true;
}

<!--Script  For Calculation of Age-->
function calculateAge()
{
    //alert("in calculateAge");
    var x=document.getElementById("year").value;
    var d=new Date();
    var year=d.getFullYear();

    if(x <= year){
        var age=year-x;
        if(age <= 99){
        try{
        document.getElementById("noOfYears").value=age;
        }catch(ex){
            document.forms[0].noOfYears.value=age;
        }
        }else{
              alert("age exceeding 100");
             try{
        document.getElementById("noOfYears").value="";
          }catch(ex){
            document.forms[0].noOfYears.value="";
        }
        }
    }else{
        alert("year doesnot exceed current year");
        try{
        document.getElementById("noOfYears").value="";
          }catch(ex){
            document.forms[0].noOfYears.value="";
        }
    }

}

<!--Script  ForRequired Field Validations-->


function checkFormValidations(){
    var flag = true;
    flag = submitValildations();
    if(!flag)
        return flag;

    if(flag)
        enableAllProperties();

    return flag;

}


function submitValildations(){

    var flag=true;
     flag=formNumber();
    if(!flag)
        return flag;
    flag=fromdateValidation();
    if(!flag)
        return flag;
//    flag=personSurame();
//    if(!flag)
//        return flag;
    flag=personName();
    if(!flag)
        return flag;
    flag=personTeluguName();
    if(!flag)
        return flag;
    flag=agevalidation();
    if(!flag)
        return flag;
    flag=gender();
    if(!flag)
        return flag;
    flag=maritalStatus();
    if(!flag)
        return flag;
    flag = rationCardCheck();
    if(!flag)
        return flag;
     flag=EPICCardNumber();
    if(!flag)
        return flag;
    flag=moleone();
    if(!flag)
        return flag;
    flag=moletwo();
    if(!flag)
        return flag;
     flag=fatherName();
    if(!flag)
        return flag;
    flag=relationName();
    if(!flag)
        return flag;
    flag=fatherteluguname();
    if(!flag)
        return flag;
    flag=validateemailAddress();
    if(!flag)
        return flag;
    flag=personStatus();

    if(!flag)
        return flag;
    flag=aadharCardNo();
    if(!flag)
        return flag;


    return flag;

}


<!-- Validation for Form Number -->
function formNumber(){
    var formNumber =document.forms[0].formno;
    if(formNumber !=null){
        if(formNumber.value == ""){
            alert("Please enter Form Number!");
            document.forms[0].formno.focus();
            return false;
        }
    }
    return true;
}
<!-- Validation for Date -->

function fromdateValidation(){
    var fromdate =document.forms[0].fromdate;
    if(fromdate !=null){
        var datetobevalidated= fromdate.value;
        if(fromdate.value == ""){
            alert("Date must be selected!");
            document.forms[0].fromdate.focus();
            return false;
        }else if(fromdate.value != ""){
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
                return false;
            }


        }
    }
    return true;
}
<!-- Validation for Father Name  -->

function fatherName(){
    var firstname =document.forms[0].gsurname;
    if(firstname !=null){
        if(firstname.value == ""){
            alert("PWD FatherName must be Entered!");
            document.forms[0].gsurname.focus();
            return false;
        }
    }
    return true;
}
<!-- Validation for Person Surname Name  -->
function personSurame(){
    var firstname =document.forms[0].surname;
//    if(firstname !=null){
//        if(firstname.value == ""){
//            alert("PWD SurName must be Entered!");
//            document.forms[0].surname.focus();
//            return false;
//        }
//    }
    return true;
}

<!-- Validation for Person Name  -->

function personName(){
    var firstname =document.forms[0].firstname;
    if(firstname !=null){
        if(firstname.value == ""){
            alert("PWD Name must be Entered!");
           document.forms[0].firstname.focus();
            return false;
        }
    }
    return true;
}
<!-- Validation for Person Teluguname  -->
function personTeluguName(){
    var surnameenglish =document.forms[0].surnametelugu;
    if(surnameenglish !=null){
        if(surnameenglish.value == ""){
            alert("PWD Telugu Name must be Enter!");
            document.forms[0].surnameenglish.focus();
            return false;
        }
    }
    return true;
}
<!-- Validation for Age  -->
function agevalidation(){
    var noOfYears =document.forms[0].noOfYears;
    if(noOfYears !=null){
        if(noOfYears.value == ""){
            alert("Please enter Age");
            document.forms[0].noOfYears.focus();
            return false;
        }
    }
    return true;
}
<!-- Validation for Gender  -->
function gender(){
    var gender = document.forms[0].gender;
    if(gender !=null){
        if(gender.value == ""){
            alert("gender must be selected!");
            document.forms[0].gender.focus();
            return false;
        }
    }
    return true;
}

<!-- Validation for marital  -->
function maritalStatus(){
    var marital =document.forms[0].marital;
    if(marital !=null){
        if(marital.value == ""){
            alert("marital must be selected!");
            document.forms[0].marital.focus();
            return false;
        }
    }
    return true;
}

<!-- Validation for ration Card -->
<!-- Added by Mohan on 19/09/2011 -->

function rationCardCheck() {

    var rcard = document.forms[0].card;
    var rationCardSlno = document.forms[0].rationCardSlno;
    if(rcard.value != "" && rationCardSlno.value == "") {
        alert("Please Enter RationCard Serial Number");
       document.forms[0].rationCardSlno.focus();
        return false;
    }

    return true;
}




<!-- End of Mohan Code -->


<!-- Validation for Moleone  -->
function moleone(){
    var mole1 =document.forms[0].mole1;
    if(mole1 !=null){
        if(mole1.value == ""){
            alert("Identification Marks(Moles) must be Entered!");
            document.forms[0].mole1.focus();
            return false;
        }
    }
    return true;
}


<!-- Validation for Moletwo  -->
function moletwo(){
    var mole2 =document.forms[0].mole2;
    if(mole2 !=null){
        if(mole2.value == ""){
            alert("Identification Marks(Moles) must be Entered!");
            document.forms[0].mole2.focus();
            return false;
        }
    }
    return true;
}
<!-- Validation for fatherTeluguname  -->

function fatherteluguname(){
    var firstnameenglish =document.forms[0].firstnametelugu;
    if(firstnameenglish !=null){
        if(firstnameenglish.value == ""){
            //alert("Father/Mother/Husband/Guardian's Name must be Entered!");
            document.forms[0].firstnameenglish.focus();
            //return false;
        }
    }
    return true;
}
<!-- Radio button validation -->
function personStatus(){
    var radio_choice = false;
    for (counter = 0; counter < partAForm.personstatus.length; counter++)
    {
        if (partAForm.personstatus[counter].checked)
            radio_choice = true;
    }
    if (!radio_choice)
    {
        alert("Please select a Person Status.")
        return false;
    }
    return true;
}


<!-- Validation for EPIC -->

function EPICCardNumber()
{
    var epiccard = document.forms[0].epiccard;
    var epiccardno =document.forms[0].epiccardno;
    if(epiccard != null && epiccardno != null){
        if ( (epiccard.checked==true) && (epiccardno.value==""))
        {
            alert("Please enter EPIC Number");
            epiccardno.focus();
            return false;
        }
    }
    return true;
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

<!-- Validation for Relation -->

function relationName()
{
     document.forms[0].grelation.disabled = false ;
    var grelation = document.forms[0].grelation;
    
    if(grelation != null){
        if(grelation.value == "" || grelation.value == "-1" ){
            alert("Relation must be selected!");
            document.forms[0].grelation.focus();
            return false;
        }
    }
    return true;
}

<!-- Validation for Email -->
function validateemailAddress(){
    var address = document.forms[0].email;
    if(address != null){
        if(address.value!=""){
            var reg =/^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
            if(reg.test(address.value) == false) {
                alert('Invalid Email Address');
                document.forms[0].email.value="";
                document.forms[0].email.focus();
                return false;
            }
        }
    }
    return true;
}



<!-- script for captal at first letter-->
function capitalizeMe(obj) {
    var val = obj.value;
    var newVal = '';
     if(val != ""){
    val = val.split(' ');
    for(var c=0; c < val.length; c++) {
        newVal += val[c].substring(0,1).toUpperCase() +
            val[c].substring(1,val[c].length) + ' ';
      }
     }
    obj.value = newVal;
}
<!-- current Date-->

function currentdate(){

    var currentTime = new Date()
    var month = currentTime.getMonth() + 1
    var day = currentTime.getDate()
    var year = currentTime.getFullYear()

    var todaydate=day+"/"+month+"/"+year;

   document.forms[0].fromdate.value=todaydate;

}

<!--Validation  For Pension Card Checkbox-->
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


<!-------End  bapinaidu java script -------------------->





function messagedisplay(id,name){

     if (id==1) id = "This Field Allow Numbers and Alphabets Only";
     if (id==2) id = "This Field Allow Alphabets Only";
     if (id==3) id = "This Field Allow Numbers Only";

        document.getElementById(name).onmouseover = function(){
            third2('<table cellpadding=3 border=0><td class=messagelablel>'+id+'</td></table>', '', 244);
        }
        document.getElementById(name).onmouseout = function(){
           first4();
        }
}

var sixteenth=-62;var seventeenth=21;var eighteenth=document.all;var nineteenth=document.getElementById && !document.all;var twentieth=false;
if (eighteenth||nineteenth)
var first2=document.all? document.all["fifteenth"] : document.getElementById? document.getElementById("fifteenth") : "";
function second2(){return (document.compatMode && document.compatMode!="BackCompat")? document.documentElement : document.body;}
function third2(fourth2, fifth2, sixth2){
if (nineteenth||eighteenth){
if (typeof sixth2!="undefined") first2.style.width=sixth2+"px";
if (typeof fifth2!="undefined" && fifth2!="") first2.style.backgroundColor=fifth2;first2.innerHTML=fourth2;twentieth=true;return false;}}
function fifteenth3(e){
if (twentieth){var sixteenth3=(nineteenth)?e.pageX : event.x+second2().scrollLeft;var seventeenth3=(nineteenth)?e.pageY : event.y+second2().scrollTop;var eighteenth3=eighteenth&&!window.opera? second2().clientWidth-event.clientX-sixteenth : window.innerWidth-e.clientX-sixteenth-20;var nineteenth3=eighteenth&&!window.opera? second2().clientHeight-event.clientY-seventeenth : window.innerHeight-e.clientY-seventeenth-20;var twentieth3=(sixteenth<0)? sixteenth*(-1) : -960;
if (eighteenth3<first2.offsetWidth)first2.style.left=eighteenth? second2().scrollLeft+event.clientX-first2.offsetWidth+"px" : window.pageXOffset+e.clientX-first2.offsetWidth+"px";
else if (sixteenth3<twentieth3)first2.style.left="5px";else first2.style.left=sixteenth3+sixteenth+"px";
if (nineteenth3<first2.offsetHeight)first2.style.top=eighteenth? second2().scrollTop+event.clientY-first2.offsetHeight-seventeenth+"px" : window.pageYOffset+e.clientY-first2.offsetHeight-seventeenth+"px";else first2.style.top=seventeenth3+seventeenth+"px";first2.style.visibility="visible";}}
function first4(){
if (nineteenth||eighteenth){twentieth=false;first2.style.visibility="hidden";first2.style.left="-1000px";first2.style.backgroundColor='';first2.style.width='';}}
document.onmousemove=fifteenth3;



         <!-------Starts script Allow Alphabets and Numerics For House Number-------------------->


function isAlphaNumericHouseNumber(keyCode,name) {

    if (keyCode == 16 )
        isShift = true;
    var str = name.value;
    if(str.substring(0,1)==" " || str.substring(0,1)==0)
    {
        name.value ="";
        name.focus();
        var res =(((keyCode >= 49 && keyCode <= 57) && isShift == false) ||
        (keyCode >= 65 && keyCode <= 90) || keyCode == 8 || (keyCode >= 96 && keyCode <= 105)
        || keyCode == 9 || keyCode == 37 || keyCode == 39 || keyCode == 46);
    }else{

    var res = (((keyCode >= 48 && keyCode <= 57) && isShift == false) ||
        (keyCode >= 65 && keyCode <= 90) || keyCode == 8 || (keyCode >= 96 && keyCode <= 105)
        || keyCode == 9 || keyCode == 37 || keyCode == 39 ||  keyCode == 32 || keyCode == 46
        || keyCode == 189 || keyCode == 191);
    }
    return res;
}

function space(evt,thisvalue)
    {
        var number = thisvalue.value;
            var charCode = (evt.which) ? evt.which : event.keyCode
            if(number.length < 1){
        if(evt.keyCode == 32) {

            return false;
        }
            }
        return true;
    }








