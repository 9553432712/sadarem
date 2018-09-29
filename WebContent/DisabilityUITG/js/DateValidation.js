

function datevalidation1(str)
{
    if(document.forms[0].elements[str]!= "")
    {
        var f=document.forms[0];
        var actual_date=f.elements[str].value;
        var split_date=actual_date.split("/");
        var currentTime =( f.elements["userProperties(systemDate)"].value).split("/");
        ;
        var day = currentTime[0];
        var month = currentTime[1];
        var year = currentTime[2];
        var startedyear=parseInt(currentTime[2])-1;
        if(parseInt(split_date[2])<parseInt(startedyear))
        {
            alert("Date Should  be greater than 31st March,"+startedyear);
            f.elements[str].value="";
            return false;
        }
        else if(parseInt(split_date[2])==parseInt(startedyear))
        {
            if(parseInt(split_date[1])<4){
                alert("Date Should  be greater than 31st March,"+startedyear);
                f.elements[str].value="";
                return false;
            }else{
                return true;
            }
        }
        else if(parseInt(split_date[2])>parseInt(year))
        {
            alert("Date Should be Less Than the Current Date");
            f.elements[str].value="";
            return false;
        }
        else if(parseInt(split_date[2])>=year && parseInt(split_date[1])>month)
        {
            alert("Date Should be Less Than the Current Date");
            f.elements[str].value="";
            return false;
        }
        else if(parseInt(split_date[2])>=year && parseInt(split_date[1])>=month && parseInt(split_date[0])>day)
        {
            alert("Date Should be Less Than the Current Date");
            f.elements[str].value="";
            return false;
        }
        else{
            return true;
        }
    }
	 
}
   
    
var dtCh= "/";
var minYear=1960;
var maxYear=2100;
//In general define minYear=1960 and maxYear=2100
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
    if(dtStr.substring(0,pos1).length==1){
        alert("Please enter a valid day")
        return false
    }
    if(dtStr.substring(pos1+1,pos2).length==1){
        alert("Please enter a valid month")
        return false
    }
    if (pos1==-1 || pos2==-1){
        alert("The date format should be : dd/mm/yyyy")
        return false
    }
    if (strMonth.length<1 || month<1 || month>12){
        alert("Please enter a valid month")
        return false
    }
    if (strDay.length<1 || day<1 || day>31 || (month==2 && day>daysInFebruary(year)) || day > daysInMonth[month]){
        alert("Please enter a valid day")
        return false
    }
    if (strYear.length != 4 || year==0 || year<minYear || year>maxYear){
        alert("Please enter a valid 4 digit year between "+minYear+" and "+maxYear)
        return false
    }
    if (dtStr.indexOf(dtCh,pos2+1)!=-1 || isInteger(stripCharsInBag(dtStr, dtCh))==false){
        alert("Please enter a valid date")
        return false
    }
    
    return true
}