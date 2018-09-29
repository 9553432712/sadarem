


function enableAllProperties()
{
    for(var i=1;i<=2;i++){
        document.getElementById(i).disabled=false;
    }
    return true;
}
/*
<!-------Starts bapinaidu java script -------------------->

<!-------Starts script For Allow Alphabets Only -------------------->*/
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


/*<!-------Ends script For Allow Alphabets Only -------------------->




<!-------Starts script Allow Alphabets and Numerics -------------------->*/


function isAlphaNumeric(keyCode) {
    if (keyCode == 16)
        isShift = true;
    var res = (((keyCode >= 48 && keyCode <= 57) && isShift == false) ||
        (keyCode >= 65 && keyCode <= 90) || keyCode == 8 || (keyCode >= 96 && keyCode <= 105)
        || keyCode == 9 || keyCode == 37 || keyCode == 39 || keyCode == 46);
    return res;
}

/*<!-------Ends script Allow Alphabets and Numerics -------------------->*/
function isNumeric(keyCode) {
    if (keyCode == 16)
        isShift = true;
    var res = ((keyCode >= 48 && keyCode <= 57 || keyCode == 8 ||
        (keyCode >= 96 && keyCode <= 105)) && isShift == false ||
    keyCode == 9 || keyCode == 37 || keyCode == 39 || keyCode == 46);
    return res;
}

/*
<!-------Starts script Allow Alphabets and Numerics with  /-------------------->*/

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
    var flag=true;
  
    var flag=submitValildations();
    if(!flag)
        return flag;

    if(flag)
        enableAllProperties();

    return flag;

}


function submitValildations(){

    var flag=true;
    var flag=formNumber();
    if(!flag)
        return flag;
    flag=fromdateValidation();
    if(!flag)
        return flag;
    flag=personSurame();
    if(!flag)
        return flag;
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
    flag=validateTerritory();

    if(!flag)
        return flag;
//    flag=phoneNo();
//
//    if(!flag)
//        return flag;
    flag=typeOfDisability();

    if(!flag)
        return flag;

    if(!flag)
        return flag;
    flag=campValidation();

    if(!flag)
        return flag;
    flag=campDateValidation();


    if(!flag)
        return flag;

    flag=checkSelectingMultiple();
    if(!flag)
        return flag;

    flag=checkMultipleDisabilies();
    if(!flag)
        return flag;



    return flag;

}
<!-- Validation for Tyoe Of Disability  -->
function typeOfDisability(){
    var type_disability = document.forms[0].type_disability;
    if(type_disability !=null){
        if(type_disability.value == ""){
            alert("Type of Disability must be selected!");
            document.forms[0].type_disability.focus();
            return false;
        }
    }
    return true;
}
function checkMultipleDisabilies() {
    if(document.forms[0].type_disability.value==6){
        if(document.forms[0].noOfdisabilities.value==""){
            alert("Please Enter Multiple Disability Count");
            document.forms[0].noOfdisabilities.focus();
            return false;
        }
    }
    return true;
}
<!-- Validation for CampValidation  -->
function campValidation(){
    var partaCampIds = document.forms[0].partaCampId;
    if(document.forms[0].type_disability.value<=5){
        if(partaCampIds.value=="0"){
            alert("Camp must be selected!");
//            document.forms[0].partaCampId.focus();
            return false;
        }
    }
    return true;
}

<!-- Validation for CampValidation  -->
function campDateValidation(){
    var partaCampDate = document.forms[0].partaCampDate;
    if(document.forms[0].type_disability.value<=5){
        if(partaCampDate.value=="0"){
            alert("Camp Date Must be selected!");
//            document.forms[0].partaCampDate.focus();
            return false;
        }
    }
    return true;
}
function validateTerritory() {
    var flag = true;
    if(document.forms[0].mandal_id.value=="0") {
        alert("Please Select Mandal");
        document.forms[0].mandal_id.focus();
        flag = false;
    }else if(document.forms[0].panchayat_id.value=="0") {
        alert("Please Select Panchayat");
        document.forms[0].panchayat_id.focus();
        flag = false;
    }else if(document.forms[0].village_id.value=="0") {
        alert("Please Select Village");
        document.forms[0].village_id.focus();
        flag = false;
    }else if(document.forms[0].habitation_id.value=="0") {
        alert("Please Select Habitation");
        document.forms[0].habitation_id.focus();
        flag = false;
    }
    return flag;
}


/*<!-- Validation for ration Card -->
<!-- Added by Mohan on 19/09/2011 -->*/

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

<!-- Validation for Person Surname Name  -->
function personSurame(){
    var firstname =document.forms[0].surname;
    if(firstname !=null){
        if(firstname.value == ""){
            alert("PWD SurName must be Entered!");
            document.forms[0].surname.focus();
            return false;
        }
    }
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
        if(noOfYears.value == "" || noOfYears.value == "NA"){
            alert("Please enter Age");
            document.forms[0].noOfYears.focus();
            return false;
        }
    }
    return true;
}

<!-- Validation for Gender  -->
function gender(){
    var gender =document.forms[0].gender;
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

<!-- Validation for fatherTeluguname  -->
function fatherteluguname(){
    var firstnameenglish =document.forms[0].firstnametelugu;
    if(firstnameenglish !=null){
        if(firstnameenglish.value == ""){
            alert("Father/Mother/Husband/Guardian's Name must be Entered!");
            document.forms[0].firstnameenglish.focus();
            return false;
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
    var grelation = document.forms[0].grelation;
    if(grelation != null){
        if(grelation.value == ""){
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


/*<!-------End  bapinaidu java script -------------------->*/





function messagedisplay(id,name){

    if (id==1) id = "This Field Allow Numbers and Alphabets Only";
    if (id==2) id = "This Field Allow Alphabets Only";
    if (id==3) id = "This Field Allow Numbers Only";

    document.forms[0].name.onmouseover = function(){
        third2('<table cellpadding=3 border=0><td class=messagelablel>'+id+'</td></table>', '', 244);
    }
    document.forms[0].name.onmouseout = function(){
        first4();
    }
}

var sixteenth=-62;
var seventeenth=21;
var eighteenth=document.all;
var nineteenth=document.getElementById && !document.all;
var twentieth=false;
if (eighteenth||nineteenth)
    var first2=document.all? document.all["fifteenth"] : document.getElementById? document.getElementById("fifteenth") : "";
function second2(){
    return (document.compatMode && document.compatMode!="BackCompat")? document.documentElement : document.body;
}
function third2(fourth2, fifth2, sixth2){
    if (nineteenth||eighteenth){
        if (typeof sixth2!="undefined") first2.style.width=sixth2+"px";
        if (typeof fifth2!="undefined" && fifth2!="") first2.style.backgroundColor=fifth2;
        first2.innerHTML=fourth2;
        twentieth=true;
        return false;
    }
}
function fifteenth3(e){
    if (twentieth){
        var sixteenth3=(nineteenth)?e.pageX : event.x+second2().scrollLeft;
        var seventeenth3=(nineteenth)?e.pageY : event.y+second2().scrollTop;
        var eighteenth3=eighteenth&&!window.opera? second2().clientWidth-event.clientX-sixteenth : window.innerWidth-e.clientX-sixteenth-20;
        var nineteenth3=eighteenth&&!window.opera? second2().clientHeight-event.clientY-seventeenth : window.innerHeight-e.clientY-seventeenth-20;
        var twentieth3=(sixteenth<0)? sixteenth*(-1) : -960;
        if (eighteenth3<first2.offsetWidth)first2.style.left=eighteenth? second2().scrollLeft+event.clientX-first2.offsetWidth+"px" : window.pageXOffset+e.clientX-first2.offsetWidth+"px";
        else if (sixteenth3<twentieth3)first2.style.left="5px";else first2.style.left=sixteenth3+sixteenth+"px";
        if (nineteenth3<first2.offsetHeight)first2.style.top=eighteenth? second2().scrollTop+event.clientY-first2.offsetHeight-seventeenth+"px" : window.pageYOffset+e.clientY-first2.offsetHeight-seventeenth+"px";else first2.style.top=seventeenth3+seventeenth+"px";
        first2.style.visibility="visible";
    }
}
function first4(){
    if (nineteenth||eighteenth){
        twentieth=false;
        first2.style.visibility="hidden";
        first2.style.left="-1000px";
        first2.style.backgroundColor='';
        first2.style.width='';
    }
}
document.onmousemove=fifteenth3;



/*<!-------Starts script Allow Alphabets and Numerics For House Number-------------------->*/


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


var xmlHTTP = false;
var partaCampRequest;
var multipleCampRequest;
var multipleDisabilityRequest;
var multipleCampDateRequest;
var campDateRequest;

//Browser Support Code
function ajaxFunction(){

    try{
        // Opera 8.0+, Firefox, Safari
        partaCampRequest=new XMLHttpRequest();
        multipleCampRequest=new XMLHttpRequest();
        multipleDisabilityRequest=new XMLHttpRequest();
        multipleCampDateRequest=new XMLHttpRequest();
        campDateRequest=new XMLHttpRequest();
    }catch (e){
        // Internet Explorer Browsers
        try{

            partaCampRequest=new ActiveXObject("Msxml2.XMLHTTP");
            multipleCampRequest=new ActiveXObject("Msxml2.XMLHTTP");
            multipleDisabilityRequest=new ActiveXObject("Msxml2.XMLHTTP");
            multipleCampDateRequest=new ActiveXObject("Msxml2.XMLHTTP");
            campDateRequest=new ActiveXObject("Msxml2.XMLHTTP");
        }catch (e) {
            try{
                partaCampRequest = new ActiveXObject("Microsoft.XMLHTTP");
                multipleCampRequest = new ActiveXObject("Microsoft.XMLHTTP");
                multipleDisabilityRequest = new ActiveXObject("Microsoft.XMLHTTP");
                multipleCampDateRequest = new ActiveXObject("Microsoft.XMLHTTP");
                campDateRequest = new ActiveXObject("Microsoft.XMLHTTP");
            }
            catch (e){
                alert("Your browser broke!");
                return false;
            }
        }
    }
}
function GetXmlHttpObject()
{
    var objXmlHttp=null
    if(window.XMLHttpRequest)
    {
        objXmlHttp=new XMLHttpRequest();
    }
    else if(window.ActiveXObject)
    {
        objXmlHttp=new ActiveXObject("Msxml2.XMLHTTP");
    }
    return objXmlHttp;
}
var multipleCampId;
var multipleDisabilityId;
var multipleCampDateId;



function getMultipleDisabilitiesDetails(campid,rowId,flag,date) {
    ajaxFunction();
    multipleCamultipleDisabilityIdmpId = rowId;
    multipleDisabilityRequest.onreadystatechange=parseMultipleDisabilitiesDetails;
    var url="insertPartAaction.do?insertPersonalDetails=getMultipleDisabilitiesDetails&campid="+campid+"&rowID="+rowId+"&flag="+flag+"&date="+date;
    multipleDisabilityRequest.open("GET",url,true)
    multipleDisabilityRequest.send(null);
}

function parseMultipleDisabilitiesDetails() {
    if(!(multipleDisabilityRequest.readyState == 4 || multipleDisabilityRequest.readyState == "complete")) {
        var voDetails =multipleDisabilityRequest.responseText;
        if(voDetails!=null) {
            document.getElementById("MULTIPLEDISABILITIESDIV"+multipleCamultipleDisabilityIdmpId).innerHTML = voDetails;
        }
    }
}
function getMultipleCampDatesDetails(rowId,flag,campid) {
    ajaxFunction();
    multipleCampDateId = rowId;
    multipleCampDateRequest.onreadystatechange=parseMultipleCampDatesDetails;
    var url="insertPartAaction.do?insertPersonalDetails=getMultipleCampDatesDetails&rowID="+rowId+"&flag="+flag+"&campid="+campid;
    multipleCampDateRequest.open("GET",url,true)
    multipleCampDateRequest.send(null);
}

function parseMultipleCampDatesDetails() {
    if(!(multipleCampDateRequest.readyState == 4 || multipleCampDateRequest.readyState == "complete")) {
        var voDetails =multipleCampDateRequest.responseText;
        if(voDetails!=null) {
            document.getElementById("MULTIPLECAMPDATEDIV"+multipleCampDateId).innerHTML = voDetails;
        }
    }
}
function getNonpensionerCampDetails() {
    ajaxFunction();
    var disabilityId=document.forms[0].type_disability.value;
    if(disabilityId == ""){
        disabilityId =  document.forms[0].type_disability.value;
    }
    multipleDisabilityRequest.onreadystatechange=parseMultipleDisabilitiesDetailsnon;
    var url="insertAadharCardDetails.do?mode=campdropdownList&disabilityid="+disabilityId;
    multipleDisabilityRequest.open("GET",url,true)
    multipleDisabilityRequest.send(null);
}

function parseMultipleDisabilitiesDetailsnon() {
    if(!(multipleDisabilityRequest.readyState == 4 || multipleDisabilityRequest.readyState == "complete")) {
        var voDetails =multipleDisabilityRequest.responseText;
        if(voDetails!=null) {
            document.getElementById("MULTIPLEDISABILITIESDIVNON").innerHTML = voDetails;
        }
    }
}

function getNonpensionerCampdateDetails(partaCampId) {
    ajaxFunction();

    multipleDisabilityRequest.onreadystatechange=getNonpensionerCampdateDetailsnon;
    var url="insertAadharCardDetails.do?mode=campdatesdropdownList&disabilityid="+partaCampId;
    multipleDisabilityRequest.open("GET",url,true)
    multipleDisabilityRequest.send(null);
}

function getNonpensionerCampdateDetailsnon() {
    if(!(multipleDisabilityRequest.readyState == 4 || multipleDisabilityRequest.readyState == "complete")) {
        var voDetails =multipleDisabilityRequest.responseText;
        if(voDetails!=null) {
            document.getElementById("MULTIPLEDISABILITIESDIVNONCAMPDATE").innerHTML = voDetails;
        }
    }
}




function  createCampObjectslist()
{
    x=GetXmlHttpObject()
    x.onreadystatechange=getCampDetailsssingle;
    var disabilityId=document.forms[0].type_disability.value;
    if(disabilityId == ""){
        disabilityId =  document.forms[0].type_disability.value;
    }
    var url="insertAadharCardDetails.do?mode=getCampDetails&disabilityid="+disabilityId;
    
    x.open("GET",url,true)
    x.send();
}
function getCampDetailsssingle()
{ 
    var rs1,rs2;
    removeall("partaCampId");
    if(x.readyState==4 || x.readyState=="complete")
    {
        var m=x.responseXML.documentElement;
        var z=0;
        var counts=m.getElementsByTagName("name")[z].childNodes[0].nodeValue;
        m.getElementsByTagName("id")[z].childNodes[0].nodeValues;
        z=1;
        while(z<=counts)
        {
            rs1=m.getElementsByTagName("name")[z].childNodes[0].nodeValue;
            rs2=m.getElementsByTagName("id")[z].childNodes[0].nodeValue;
            addoptionsaadhar(rs1,rs2,"partaCampId");
            z++;
        }
    }
}


function  createCampDateObjectLists(partaCampId)
{
   
    x=GetXmlHttpObject()
   
    x.onreadystatechange=getCampDateDetailssLists;

    var url="insertAadharCardDetails.do?mode=getCampDetails&disabilityid="+partaCampId;
    x.open("GET",url,true)
    x.send();
}
function getCampDateDetailssLists()
{
    var rs1,rs2;
    removeall("partaCampDate");
    if(x.readyState==4 || x.readyState=="complete")
    {
        var m=x.responseXML.documentElement;
        var z=0;
        var counts=m.getElementsByTagName("name")[z].childNodes[0].nodeValue;
        m.getElementsByTagName("id")[z].childNodes[0].nodeValues;
        z=1;
        while(z<=counts)
        {
            rs1=m.getElementsByTagName("name")[z].childNodes[0].nodeValue;
            rs2=m.getElementsByTagName("id")[z].childNodes[0].nodeValue;
            addoptionsaadhar(rs1,rs2,"partaCampDate");
            z++;
        }
    }
}


function removeall(name)
{
    if(name=="partaCampId") {
        var x1=document.forms[0].partaCampId.options.length;
    }else if(name=="partaCampDate") {
        var x1=document.forms[0].partaCampDate.options.length;
    }
    for(x1;i>0;i--) {
        if(name=="partaCampId") {
            document.forms[0].partaCampId.options[i]=null;
            document.forms[0].partaCampDate.options[i]=null;
        }else if(name=="partaCampDate") {
            document.forms[0].partaCampDate.options[i]=null;
        }
    }
}


function  createCampObject()
{
    x=GetXmlHttpObject()
    x.onreadystatechange=getCampDetailss;
    var disabilityId=document.forms[0].type_disability.value;
    if(disabilityId == ""){
        disabilityId =  document.forms[0].type_disability.value;
    }
    var url="insertAadharCardDetails.do?mode=campdropdownList&disabilityid="+disabilityId;
    x.open("GET",url,true)
    x.send();
}

function  createNewCampObject()
{
    x=GetXmlHttpObject()
    x.onreadystatechange=getCampDetailss;
    var disabilityId=document.forms[0].type_disability.value;
    if(disabilityId == ""){
        disabilityId =  document.forms[0].type_disability.value;
    }
    var url="insertAadharCardDetails.do?mode=newcampdropdownList&disabilityid="+disabilityId;
    x.open("GET",url,true)
    x.send();
}





function getCampDetailss()
{
    var rs1,rs2;
    removeallsAAdhar("partaCampId");
    if(x.readyState==4 || x.readyState=="complete")
    {
        var m=x.responseXML.documentElement;
        var z=0;
        var counts=m.getElementsByTagName("name")[z].childNodes[0].nodeValue;
        m.getElementsByTagName("id")[z].childNodes[0].nodeValues;
        z=1;
        while(z<=counts)
        {
            rs1=m.getElementsByTagName("name")[z].childNodes[0].nodeValue;
            rs2=m.getElementsByTagName("id")[z].childNodes[0].nodeValue;
            addoptionsaadhar(rs1,rs2,"partaCampId");
            z++;
        }
    }
}
function  createCampDateObject()
{
    x=GetXmlHttpObject()
    x.onreadystatechange=getCampDateDetailss;
    var partaCampId=document.forms[0].partaCampId.value;
    if(partaCampId == ""){
        partaCampId =  document.forms[0].partaCampId.value;
    }
    var url="insertAadharCardDetails.do?mode=campdatesdropdownList&disabilityid="+partaCampId;
    x.open("GET",url,true)
    x.send();
}
function getCampDateDetailss()
{
    var rs1,rs2;
    removeallsAAdhar("partaCampDate");
   
    if(x.readyState==4 || x.readyState=="complete")
    {
        var m=x.responseXML.documentElement;
        var z=0;
        var counts=m.getElementsByTagName("name")[z].childNodes[0].nodeValue;
        m.getElementsByTagName("id")[z].childNodes[0].nodeValues;
        z=1;
        while(z<=counts)
        {
            rs1=m.getElementsByTagName("name")[z].childNodes[0].nodeValue;
            rs2=m.getElementsByTagName("id")[z].childNodes[0].nodeValue;
            addoptionsaadhar(rs1,rs2,"partaCampDate");
            z++;
        }
    }
}
function addoptionsaadhar(result1,result2,name)
{
    var opt=document.createElement("OPTION");
 
    opt.text=result1;
    opt.value=result2;
    try{
        document.getElementById(name).add(opt);
    }catch(ex){
        if(name=="partaCampId") {
            document.forms[0].partaCampId.appendChild(opt,null);
        }else  if(name=="partaCampDate") {
            document.forms[0].partaCampDate.appendChild(opt,null);
        }
    }
}
function removeallsAAdhar(name)
{
	var i=0;
    if(name=="partaCampId") {
        if(document.forms[0].partaCampId.value!=null){
        var x1=document.forms[0].partaCampId.options.length;
        }
    }else if(name=="partaCampDate") {
        if(document.forms[0].partaCampDate.value!=null){
            var x1=document.forms[0].partaCampDate.options.length;
        }
    }
    for(i=x1;i>0;i--) {
        if(name=="partaCampId") 
        {
           document.forms[0].partaCampId.options[i]=null;
            if(document.forms[0].partaCampDate!=null)
            {
             document.forms[0].partaCampDate.options[i]=null;
            }
        }
        else if(name=="partaCampDate") 
        {
            if(document.forms[0].partaCampDate!=null)
            {
              document.forms[0].partaCampDate.options[i]=null;
            }
        }
    }
}

<!-- Validation for phoneNo -->

function phoneNo()
{
    var phoneno = document.forms[0].phoneno;
    if(phoneno != null){
        if(phoneno.value == ""){
            alert("Phone No must be Entered");
            document.forms[0].phoneno.focus();
            return false;
        }
    }
    return true;
}
function addoption(result1,result2,name)
{
    var opt=document.createElement("option");
    opt.text=result1;
    opt.value=result2;
    try {
        document.getElementById(name).add(opt);
    }catch(ex)
    {
        if(name=="mandal_id") {
            document.forms[0].mandal_id.appendChild(opt,null);
        }else if(name=="village_id") {
            document.forms[0].village_id.appendChild(opt,null);
        }else if(name=="habitation_id") {
            document.forms[0].habitation_id.appendChild(opt,null);
        }else if(name=="panchayat_id") {
            document.forms[0].panchayat_id.appendChild(opt,null);
        }
    }
}
function removeall(name)
{
    if(name=="mandal_id") {
        var x1=document.forms[0].mandal_id.options.length;
    }else if(name=="village_id") {
        var x1=document.forms[0].village_id.options.length;
    }else if(name=="habitation_id") {
        var x1=document.forms[0].habitation_id.options.length;
    }else if(name=="panchayat_id") {
        var x1=document.forms[0].panchayat_id.options.length;
    }
    for(i=x1;i>0;i--) {
        if(name=="mandal_id") {
            document.forms[0].mandal_id.options[i]=null;
        }else if(name=="village_id") {
            document.forms[0].village_id.options[i]=null;
        } else if(name=="habitation_id") {
            document.forms[0].habitation_id.options[i]=null;
        }else if(name=="panchayat_id") {
            document.forms[0].panchayat_id.options[i]=null;
        }
    }
}



function getPanchayatList() {
    createPanchayatObject();
}
var x = null;
function  createPanchayatObject()
{  
    x = GetXmlHttpObject()
    x.onreadystatechange=getPanchayatDetails;    
    var url="getTerritory.do?parameter=getTerritoryList&districtid="+document.forms[0].district_id.value+"&mandalid="+document.forms[0].mandal_id.value+"&territory=4";
    x.open("GET",url,true)
    x.send();
}

function getPanchayatDetails()
{
    var rs1,rs2;
    removeall("panchayat_id");
    removeall("habitation_id");
    removeall("village_id");
    if(x.readyState==4 || x.readyState=="complete")
    {
        var m=x.responseXML.documentElement;
        var z=0;
        var counts=m.getElementsByTagName("name")[z].childNodes[0].nodeValue;
        m.getElementsByTagName("id")[z].childNodes[0].nodeValues;
        z=1;
        while(z<=counts)
        {
            rs1=m.getElementsByTagName("name")[z].childNodes[0].nodeValue;
            rs2=m.getElementsByTagName("id")[z].childNodes[0].nodeValue;
            addoption(rs1,rs2,"panchayat_id");
            z++;
        }
    }
}

function panchayatList(){
    var villageid = document.forms[0].panchayat_id.value;
    var slcBx = document.getElementById("3");
    document.getElementById("panchayatname").value = slcBx.options[slcBx.selectedIndex].text;
    if(villageid == ""){
        removeLists(6,6);
    }else{
        createHabitationObject();
    }
}

function  createVillageObject()
{
    //var distid;
    x=GetXmlHttpObject()
    x.onreadystatechange=getVillageDetails;

    var pan=document.forms[0].panchayat_id.value;
    var url="getTerritory.do?parameter=getTerritoryList&districtid="+document.forms[0].district_id.value+"&mandalid="+document.forms[0].mandal_id.value+"&panchayatid="+pan+"&territory=7";
    x.open("GET",url,true)
    x.send();
}

function getVillageDetails()
{
    var rs1,rs2;
    removeall("village_id");
    // removeall("panchayat_id");
    //    removeall("habitation_id");
    if(x.readyState==4 || x.readyState=="complete")
    {
        var m=x.responseXML.documentElement;
        var z=0;
        var counts=m.getElementsByTagName("name")[z].childNodes[0].nodeValue;
        m.getElementsByTagName("id")[z].childNodes[0].nodeValues;
        z=1;
        while(z<=counts)
        {
            rs1=m.getElementsByTagName("name")[z].childNodes[0].nodeValue;
            rs2=m.getElementsByTagName("id")[z].childNodes[0].nodeValue;
            addoption(rs1,rs2,"village_id");
            z++;
        }
    }
}

function villagesList(){
    var villageid = document.forms[0].village_id.value;
    var slcBx = document.getElementById("4");
    document.getElementById("villagename").value = slcBx.options[slcBx.selectedIndex].text;
    if(villageid == ""){
        removeLists(5,6);
    }else{
        createPanchayatObject();
    }
}






function  createHabitationObject()
{
    x=GetXmlHttpObject()
    x.onreadystatechange=getHabitationDetails;

    var villageid = document.forms[0].village_id.value;
    var panchayat = document.forms[0].panchayat_id.value;
    var url="getTerritory.do?parameter=getTerritoryList&districtid="+document.forms[0].district_id.value+"&mandalid="+document.forms[0].mandal_id.value+"&villageid="+villageid+"&panchayatid="+panchayat+"&territory=8";

    x.open("GET",url,true)
    x.send();
}

function getHabitationDetails()
{
    var rs1,rs2;
    removeall("habitation_id");
    if(x.readyState==4 || x.readyState=="complete")
    {
        var m=x.responseXML.documentElement;
        var z=0;
        var counts=m.getElementsByTagName("name")[z].childNodes[0].nodeValue;
        m.getElementsByTagName("id")[z].childNodes[0].nodeValues;
        z=1;
        while(z<=counts)
        {
            rs1=m.getElementsByTagName("name")[z].childNodes[0].nodeValue;
            rs2=m.getElementsByTagName("id")[z].childNodes[0].nodeValue;
            addoption(rs1,rs2,"habitation_id");
            z++;
        }
    }
}

function getCampDatesDetails(campid) {
    ajaxFunction();
    var disabilityId=document.forms[0].type_disability.value;
    campDateRequest.onreadystatechange=parseCampDatesDetails;
    var url="insertPartAaction.do?insertPersonalDetails=getCampDatesDetails&campid="+campid+"&disabilityId="+disabilityId;
    campDateRequest.open("GET",url,true)
    campDateRequest.send(null);
}
function parseCampDatesDetails() {
    if(!(campDateRequest.readyState == 4 || campDateRequest.readyState == "complete")) {
        var voDetails =campDateRequest.responseText;
        if(voDetails!=null) {
            document.getElementById("CAMPDATEDIV").innerHTML = voDetails;
        }
    }
}