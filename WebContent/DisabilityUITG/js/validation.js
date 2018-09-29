/*--------------COMMON JAVASCRIPT VALIDATIONS----------------------*/

/*
*------- This function doesnot allows Special characters including space in the text field -----
*/


function inputLimiter(e,allow) {
    var AllowableCharacters = '';
    if (allow == 'Letters'){
        AllowableCharacters=' ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz';
    }
    if (allow == 'Numbers'){
        AllowableCharacters='1234567890';
    }
    if (allow == 'landline'){
        AllowableCharacters='1234567890-';
    }
    if (allow == 'NameCharacters'){
        AllowableCharacters=' ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz-.\'';
    }
    if (allow == 'NameCharactersAndNumbers'){
        AllowableCharacters='1234567890 ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz-\'/';
    }
    if (allow == 'website'){
        AllowableCharacters=' ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz./:';
    }
    
    var k = document.all?parseInt(e.keyCode): parseInt(e.which);
    if (k!=13 && k!=8 && k!=0){
        if ((e.ctrlKey==false) && (e.altKey==false)) {
            return (AllowableCharacters.indexOf(String.fromCharCode(k))!=-1);
        } else {
            return true;
        }
    } else {
        return true;
    }
}

function removeSpecialChar(txtName) {
    if (txtName.value != '' && txtName.value.match(/^[\w ]+$/) == null) {
        txtName.value = txtName.value.replace(/[\W]/g, '');
    }
}

function allnumeric(inputtxt)
{

    var numbers =/^[0-9]+$/;

    if(inputtxt.value.match(numbers))
    {
        //alert('Your Registration number has accepted....');
        return true;
    }
    else
    {
        alert('Please input numeric characters only');
        inputtxt.focus();
        inputtxt.value="";
        return false;
    }

}

function allchars(inputtxt)

{

    var numbers = /^[0-9]+$/;

    if(inputtxt.value.match(numbers))
    {

        alert('Please input text characters only');

        inputtxt.focus();

        inputtxt.value="";

        return false;

    }else{
        return true;
    }
}

function DisableCopyPaste(e,id) {
    // Message to display
    var message = "Cntrl key/ Right Click Option disabled";
    var kCode = event.keyCode || e.charCode;
    kCode = event.keyCode || e.charCode; //FF and Safari use e.charCode, while IE use e.keyCode
    if (kCode == 17 || kCode == 2)
    {
        alert("Copy & Paste Disabled");
        document.forms[0].elements[id].value="";
        return false;
    }else {
        return true;
    }
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

function checkEmail(id) {

    var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
  
    if(reg.test(id) == false) {
       
        alert("Invalid Email Address");
        document.forms[0].id.value=""; 
        document.forms[0].id.focus();
        return false;
    }else {
        return true;
    }

}


function splChar(){
    var iChars = "!@#$%^&*()+=-[]\\\';,./{}|\":<>? ";
    // alert(document.forms[0].pword.value);
    for (var i = 0; i < document.forms[0].pword.value.length; i++) {
        if (iChars.indexOf(document.forms[0].pword.value.charAt(i)) != -1) {
            alert ("The box has special characters. \nThese are not allowed.\n");
            document.forms[0].pword.value="";
            return false;
        }
    }
}




/*
*------- This function allows only numbers -----
*/
function onlyNumbers(evt) {
    var charCode = (evt.which) ? evt.which : event.keyCode
    if (charCode > 31 && (charCode < 48 || charCode > 57))
        return false;
    return true;
}

/*
*------- This function allows only Alphbets to be entered and converts them to capital
* letters in the text field entered-----
*/
function isAlphaKey(i) {
    // alert(i);
    if((i.value).length>0) {
        i.value = i.value.replace(/[^\d\d.\w\s]+/g, '');
        i.value = i.value.replace(/[\d\d.\d]+/g, '');
    }
    var str=i.value;
    i.value=str.toUpperCase();
		
}
	
	
/**
 * DHTML date validation script for dd/mm/yyyy format. 
 *CALL ISDATE(dtstr) FUNCTION WHILE SUBMISSION
 *
 *Declaring valid date character, minimum year and maximum year
 */
var dtCh= "/";


function isInteger(s){
    var i;
    for (i = 0; i < s.length; i++){   
        // Check that current character is number.
        var c = s.charAt(i);
        if (((c < "0") || (c > "9"))) return false;
    }
    // All characters are numbers.
    return true;
}



function stripCharsInBag(s, bag){
    var i;
    var returnString = "";
    // Search through string's characters one by one.
    // If character is not in bag, append to returnString.
    for (i = 0; i < s.length; i++){   
        var c = s.charAt(i);
        if (bag.indexOf(c) == -1) returnString += c;
    }
    return returnString;
}

function daysInFebruary (year){
    // February has 29 days in any year evenly divisible by four,
    // EXCEPT for centurial years which are not also divisible by 400.
    return (((year % 4 == 0) && ( (!(year % 100 == 0)) || (year % 400 == 0))) ? 29 : 28 );
}
function DaysArray(n) {
    for (var i = 1; i <= n; i++) {
        this[i] = 31
        if (i==4 || i==6 || i==9 || i==11) {
            this[i] = 30
        }
        if (i==2) {
            this[i] = 29
        }
    }
    return this
}

function isDate(dtStr){
    var daysInMonth = DaysArray(12)
    var pos1=dtStr.indexOf(dtCh)
    var pos2=dtStr.indexOf(dtCh,pos1+1)
    var strDay=dtStr.substring(0,pos1)
    var strMonth=dtStr.substring(pos1+1,pos2)
    var strYear=dtStr.substring(pos2+1)
    // alert(dtStr+pos1+pos2+strDay+strMonth+strYear);
    strYr=strYear
    if (strDay.charAt(0)=="0" && strDay.length>1) strDay=strDay.substring(1)
    if (strMonth.charAt(0)=="0" && strMonth.length>1) strMonth=strMonth.substring(1)
    for (var i = 1; i <= 3; i++) {
        if (strYr.charAt(0)=="0" && strYr.length>1) strYr=strYr.substring(1)
    }
    month=parseInt(strMonth)
    day=parseInt(strDay)
    year=parseInt(strYr)
    /*if (pos1==-1 || pos2==-1){
		alert("The date format should be : dd/mm/yyyy")
		return false
	}*/
	
    if (strMonth.length<1 || month<1 || month>12){
        alert("Please enter a valid month")
        return false
    }
    if (strYear.length != 4 || year==0){
        alert("Please enter a valid 4 digit year");
        return false
    }
	
	
    if (strDay.length<1 || day<1 || day>31 || (month==2 && day>daysInFebruary(year)) || day > daysInMonth[month]){
        alert("Please enter a valid day")
        return false
    }
	
    /*if (dtStr.indexOf(dtCh,pos2+1)!=-1 || isInteger(stripCharsInBag(dtStr, dtCh))==false){
		alert("Please enter a valid date")
		return false
	}*/
    return true
}

function chkDate(dateString, d, m, currentYear) {	
	
    var f = document.forms[0].elements[d].value+"/"+document.forms[0].elements[m].value+"/"+dateString.value;

    if (isDate(f)){
        getAge(f, (document.forms[0].elements[currentYear].value));
        return true;
    } else {
        return false;
    }
}


function valButton(btn) {
    var cnt = -1;
    for (var i=btn.length-1; i > -1; i--) {
        if (btn[i].checked) {
            cnt = i;
            i = -1;
        }
    }
    if (cnt > -1) return btn[cnt].value;
    else return null;
}


function elementsResetting() {
    document.forms[0].reset();
	
}


function validateMobileNo(val) {
    if (val.value != "") {
        if (val.value.length < 10) {
            alert("Mobile / Telephone No 10 digits are required");
            eval(val).focus();
            return false;
        }
    }
    return true;
}

function chkFileTypes(val, fileType) {
    val = val.slice(val.lastIndexOf(".")).toLowerCase();
    if (val != fileType) {
        alert("Invalid File format!!!");
        return false;
    } else {
        return true;
    }
}
			

/* Login scripts start*/

function encryptPassword() {
		
    if (document.forms[0].userName.value == "" || document.forms[0].password.value == "" ) {
	
        alert("All values are required");
        return false;
	
    } else {
        with( document.forms[0] ) {
	 	
            elements["password"].value = hex_md5( elements["password"].value );
            //submit();
            return true;
            }
    }
}


	
function toMinuteAndSecond( x ) {
    return Math.floor(x/60) + ":" + x%60;
}
	
function setTimer( remain, actions ) {
    (function countdown() {
        display("countdown", toMinuteAndSecond(remain));
        actions[remain] && actions[remain]();
        (remain -= 1) >= 0 && setTimeout(arguments.callee, 1000);
    })();
}
 

function displayPdf(url) {
    window.open(url);

}

function showUrl(url) {   
    window.location.replace(url);
}

function uploadPDF(str,leaf)
{
    var ext = str.value;
    ext = ext.substring(ext.lastIndexOf(".")+1,ext.length);
   
    if("pdf"!=ext && "PDF"!=ext && ext.length!=3)
    {
        alert('You selected a " .'+ext+' "  file; Please Select a " .pdf " file');
        document.getElementById(leaf).innerHTML = document.getElementById(leaf).innerHTML;
        return false;
    }else{
        return true;
    }
}

function uploadJPG(str,leaf)
{
    var ext = str.value;
    ext = ext.substring(ext.lastIndexOf(".")+1,ext.length);
   
    if("jpg"!=ext && ext.length!=3)
    {
        alert('You selected a " .'+ext+' "  file; Please Select a " .jpg " file');
        document.getElementById(leaf).innerHTML = document.getElementById(leaf).innerHTML;
        return false;
    }else{
        return true;
    }
}

function checkBoxGroup(groupName) {
    var checkedCount=0;
    for(var i = 0;i<groupName.length;i++)  {
        if(groupName[i].checked==true)  {
            checkedCount +=1;
        }
    }
    if(checkedCount <=0 )  {
        return false;
    }
    return true;
}


function checkAll(field,id)
{

    if(document.forms[0].elements[id].checked==true) {
        for (i = 0; i < field.length; i++) {
            field[i].checked = true ;
        }
    }else {
        for (i = 0; i < field.length; i++) {
            field[i].checked = false ;
        }
    }

}


