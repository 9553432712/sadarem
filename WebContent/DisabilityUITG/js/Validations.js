/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


function isEmpty(field,alerttxt)
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



function dateValidation(fromdate,todate){
    
    var yye=fromdate.substr(6,4);
    var mme=fromdate.substr(3,2);
    var dde=fromdate.substr(0,2);
    var yyb=todate.substr(6,4);
    var mmb=todate.substr(3,2);
    var ddb=todate.substr(0,2);
    var dob = new  Date();
    var etd = new  Date();
    var today=new Date();
    etd.setFullYear(yye,mme-1,dde);
    dob.setFullYear(yyb,mmb-1,ddb)
    var y1=today.getYear();
    var y2= dob.getYear();
    if (today < etd)
    {
        alert("From date is after Todays Date");
        document.forms[0].fromdate.value="";
        return false;
    }
    if (today < dob)
    {
        alert("To date is after Todays Date");
        document.forms[0].todate.value="";
        return false;
    }
    if (dob < etd)
    {
        alert("From date is greater than To date");
        document.forms[0].fromdate.value="";
        return false;
    }
    return true;
}