var xmlHttp;
function callChangefunc(id)
{
  //alert("Inside this");
  // display loading image here... 
  
  
  try
  {    
	   // Firefox, Opera 8.0+, Safari    
     xmlHttp=new XMLHttpRequest();
  
  }catch (e)
  {    // Internet Explorer    
    try
    {      
     xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");  
    }
    catch (e)
    {
      try
      {        
          xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
      }
      catch (e)
      {
    	  try
          {        
              xmlHttp=new XDomainRequest();
          }
          catch (e)
          {
                alert("Your browser does not support AJAX!");
                return false;        
              
          }         
          
      } 
    } 
  }

  var params="/Nregs/FrontServlet?requestType=NewReportsRH&actionVal=AjaxDisplay&District="+document.getElementById('District').value+"&Mandal="+document.getElementById('Mandal').value+
  "&GP="+document.getElementById('Panchayat').value+"&vill="+document.getElementById('Village').value+"&Financial="+document.getElementById('Financial').value+"&Month="+document.getElementById('Month').value+"&id="+id+"&page=Newreportcenter_ajax_eng";
//alert(params);  
  
  xmlHttp.onreadystatechange=load;
  xmlHttp.open("GET",params,true);
  xmlHttp.send(null);
}

function load()
{
	if(xmlHttp.readyState==1)
	{
		document.getElementById('loading').style.display = ""; 
		
	}
	
    if(xmlHttp.readyState==4 && xmlHttp.status==200)
    {
      document.getElementById('YearHolder').innerHTML= xmlHttp.getResponseHeader('YearSelect');
      document.getElementById('MonthHolder').innerHTML= xmlHttp.getResponseHeader('MonthSelect');
      document.getElementById('DistrictHolder').innerHTML= xmlHttp.getResponseHeader('DistrictSelect');
      document.getElementById('MandalHolder').innerHTML= xmlHttp.getResponseHeader('MandalSelect');
      document.getElementById('GPHolder').innerHTML= xmlHttp.getResponseHeader('GPSelect');
      document.getElementById('VillageHolder').innerHTML= xmlHttp.getResponseHeader('VillSelect');
      LinksManagement();
      document.getElementById('loading').style.display = "none";

     
      
    }
}

var AllReports=['11','12','13','14','15','16',
                '21','22','23','241','242','25',
                '31','32','33',/*'34','35','36','36a','36b','36c','36d','36e','36f',*/'37',/*'38',*/'39a','39b','39c','39d','310','311',
                '312','313','314',
                '41','42','43','44','45','46','47','48',
                '5',
                '6',
                '71','72',
                '8','82',
                '91','92','93','94','95','96','97',
                '101','102','103','104','105','106','107','108','109','1010','1011','1012',
                '111','112a','112b','113','115','116','117','118','119a','119b','119c','1110',
                '121a','121b','122a','122b','123','124','126a','126b','128','129','1210a','1210b','1211','1212','1213a','1213b','1214a','1214b','1215',
                '131','132a','132b','132c','133a','133b','133c','133d','134',/*'137',*/'139','1317a','1317b','1318','1319','1320','1321','1322','1323',
                '140',
                '151','153','154','155',
                '161','162','163','164','165','166','167','168',
                '171',
                '181',
                '191',//'193',
                '201','202','203','205',/*'206a','206b'*/'206aa','207',
                //'205a','205b',//'204a','204b',
                '211a','211b','211c',/*'212a','212b'*/'212aa','212c','212d','212e',/*'212f'*/'212ff','212g','212h','212i','212j','213a','213b','213c','213d','214a','214b','220','221','222','223','224','225','226','227','228'
                //'214a','214b','214c'
              // '211a','211b','212a','212b','213a','213b','213c' ];
              ];
var StateReport=['11','12','13','14','15','16',
                 '21','22','23','241','242','25',
                 '31','32','33',/*'34','35','36','36a','36b','36c','36d','36e','36f',*/'37','312','313','314',
                 '5',
                 '6',
                 '71','72',
                 '8','82',
                 '91','92','93','94','95','96','97',
                 '101','102','103','104','105','106','107','108','109','1010','1012',
                 '111','116','117','118','119a','119b','119c','1110',
                 '121a','121b','122a','122b','123','124','126a','126b','128','129','1210a','1210b','1211','1212','1213a','1213b','1214a','1214b','1215',
                 '131','132a','132b','132c','133a','133b','133c','133d','134',/*'137',*/'139','1317a','1317b','1318','1319','1320','1321','1322',
                 '140',
                 '153','154',
                 '162','164','165','166','167','168',
                 '171',
                 '181',
                 '191',
                 '201','202','203',/*'206a','206b'*/'206aa','207',
                 //'205a','205b',//'204a','204b',
                 '211a','211b','211c',/*'212a','212b'*/'212aa','212c','212d','212e',/*'212f'*/'212ff','212g','212h','212i','212j','213a','213b','213c','213d','214a','214b','220','221','222','223','224','225','226','227'
                 //'214a','214b'
                // '211a','211b','212a','212b','213a','213b','213c' 
                ];
                
var DistrictReport=['11','12','13','14','15','16',
                    '21','22','23','241','242',
                    '31','32','33',/*'36','36a','36b','36c','36d','36e','36f',*/'37','312','313','314',
                    '311',
                    '71','82',
                    '96',
                    '112a','112b','116','118','119a','119b','119c','1011',
                    '121a','121b','122a','122b','123','124','126a','126b','128','129','1210a','1210b','1215',
                    '131','132a','132b','132c','133a','133b','133c','133d',/*'134','137',*/'139','1317a','1317b','1318','1319','1320','1321','1322',
                    '153','154',
                    '163','168',
                    '191',
                    '201','202','203',/*'206a','206b'*/'206aa','207',
                    //'205a','205b',//'204a','204b',
                    '211a','211b','211c',/*'212a','212b'*/'212aa','212c','212d','212e',/*'212f'*/'212ff','212g','212h','212i','212j','214a','214b','220','221','222','223','224','225','226','227'
                    //'214a','214b'
                   // '211a','211b','212a','212b'
                    ];
var MandalReport = ['11','12','14','15','16',
                    '21','22','124','23','241',
                    '31','32','33','37','39a','39b','39c','39d','310','312','313',
                    '71','82',
                    '112a','112b','116','118','119a','119b','119c','1011',
                    '121a','121b','122a','122b','123','126a','126b','128','129','1210a','1210b','1215',
                    '131','132a','132b','132c','133a','133b','133c','133d',/*'134','137',*/'139','1317a','1317b','1318','1319','1320','1321','1322',
                    '153','154',
                    '161','168',
                    '191',//'193',
                    '201','202','203','205',/*'206a','206b'*/'206aa','207',
                    //'205a','205b',//'204a','204b',
                    '211a','211b','211c',/*'212a','212b'*/'212aa','212d','212e',/*'212f'*/'212ff','212g','212h','212i','212j','214a','214b','220','221','222','223','224','225','226','227'
                   // '214a','214b'
                 // '211a','211b','212a','212b'
                    ];
var GPReport=['11','15',
              '42','43','48','82',
              '112a','112b','115','119a','119b','119c',
              '126a','126b','1210a','1210b',
              '131','132a','132b','132c','139','1317a','1320','1321','1322','1323',
              '151','155',
              '191',/*'206a','206b'*/'206aa','207',
              //'205a','205b',//'204a',
              '211a','211b','211c',/*'212a','212b'*/'212aa','212d','212e','212ff','214a','214b','220','221','224','225','226','227','228'
           // '211a','211b','212a','212b'
              ];
var VillReport=['41','44','45','46','47','82','207','211c'];

              
function LinksManagement()
{
	var DistrictID = document.forms[0].District.value;
	var MandalID = document.forms[0].Mandal.value;
	var PancID = document.forms[0].Panchayat.value;
	var vid=document.forms[0].Village.value;
	var year = document.forms[0].Financial.value;
	
	
	
	if(DistrictID=="-1" && MandalID=="-1")
  	{
		internalArray=StateReport;
		 document.getElementById('dmaDate3').style.display = "";
		 document.getElementById('img1').style.display = "";
  	}
	else if(DistrictID!="-1" && MandalID=="-1")
  	{
    	internalArray=DistrictReport;
    	document.getElementById('dmaDate3').style.display = "";
    	document.getElementById('img1').style.display = "";
  	}
  	else if(MandalID!="-1" && PancID=="-1")
  	{
    	internalArray=MandalReport;
    	
    	document.getElementById('dmaDate3').style.display = "none";
    	document.getElementById('img1').style.display = "none";
    	
  	}
    else if(PancID!="-1" && vid=="-1")
  	{
    	internalArray=GPReport;
    	 
  	}
    else if(vid!="-1")
  	{
    	internalArray=VillReport;
  	}
	//alert(internalArray);
	//alert(AllReports.length);
	//alert(internalArray.length);
	for(x=0; x<AllReports.length;x++)
  	{
    	DisableLink(AllReports[x]);
  	}
  	for(x=0;x<internalArray.length;x++)  	
  	{
  	  	//alert("before"+internalArray[x]);
    	EnableLink(internalArray[x]);
    	//alert("after"+internalArray[x]);
  	}
}

function DisableLink(element)
{
  	document.getElementById(element).disabled=true;
  	document.getElementById(element).onclick="";
}

function EnableLink(element)
{
	
  	document.getElementById(element).disabled=false;
  	document.getElementById(element).onclick=function anonymous() 
  	{ 
  		go(this.id)
   	};

}
function TestDate1(dateID)
{
	var dateVal=document.getElementById(dateID).value;
	var sel=document.getElementById("dmd1").value;
	
	if(dateVal.length>0)
	{
		var userDayStr=dateVal.split('/')[0];
		if(userDayStr.indexOf('0')==0)
		{
			userDayStr=userDayStr.substring(1);
		}
		var userMonthstr=dateVal.split('/')[1];
		if(userMonthstr.indexOf('0')==0)
		{
			userMonthstr=userMonthstr.substring(1);
		}
		var userDay=parseInt(userDayStr);
		var userMonth=parseInt(userMonthstr)-1;
		var userYear=parseInt(dateVal.split('/')[2]);
		var userDate=new Date();
		userDate.setFullYear(userYear,userMonth,userDay);

		if(sel=="allbatch2" || sel=="batch12" )
    	{  			
			var valDate1=new Date();
  			valDate1.setFullYear(parseInt('2014'),parseInt('3')-1,parseInt('10'));
  			if(userDate>valDate1){
  				alert("Date should not be Greater than Today's Date");
    			document.getElementById(dateID).value='10/03/2014';
  			}
    	}
    	else if("batch22")
    	{    		
    		var valDate2=new Date();
    		valDate2.setFullYear(parseInt('2014'),parseInt('3')-1,parseInt('13'));
    		if(userDate>valDate2){    			
    			alert("Date should not be Greater than Today's Date"); 
    		document.getElementById(dateID).value='13/03/2014';
    		}
    	} 		
  		
    }
	return true;
}
 function TestDate2(dateID)
{
	 var dateVal=document.getElementById(dateID).value;
var sel=document.getElementById("dmd2").value;

if(dateVal.length>0)
{
	var userDayStr=dateVal.split('/')[0];
	if(userDayStr.indexOf('0')==0)
	{
		userDayStr=userDayStr.substring(1);
	}
	var userMonthstr=dateVal.split('/')[1];
	if(userMonthstr.indexOf('0')==0)
	{
		userMonthstr=userMonthstr.substring(1);
	}
	var userDay=parseInt(userDayStr);
	var userMonth=parseInt(userMonthstr)-1;
	var userYear=parseInt(dateVal.split('/')[2]);
	var userDate=new Date();
	userDate.setFullYear(userYear,userMonth,userDay);
	
	if(sel=="allbatch2" || sel=="batch12" )
	{  			
		var valDate3=new Date();
			valDate3.setFullYear(parseInt('2014'),parseInt('3')-1,parseInt('10'));
		if(userDate>valDate3){
			alert("Date should not be Greater than Today's Date");
		document.getElementById(dateID).value='10/03/2014';
			}
	}
	else if("batch22")
	{    		
		var valDate4=new Date();
			valDate4.setFullYear(parseInt('2014'),parseInt('3')-1,parseInt('13'));
		if(userDate>valDate4){
		alert("Date should not be Greater than Today's Date");
		document.getElementById(dateID).value='13/03/2014';
		}
	} 		
		
}
return true;
}
function TestDate3(dateID)
{
	var dateVal=document.getElementById(dateID).value;
	var sel=document.getElementById("dmd4").value;
	
	if(dateVal.length>0)
	{
		var userDayStr=dateVal.split('/')[0];
		if(userDayStr.indexOf('0')==0)
		{
			userDayStr=userDayStr.substring(1);
		}
		var userMonthstr=dateVal.split('/')[1];
		if(userMonthstr.indexOf('0')==0)
		{
			userMonthstr=userMonthstr.substring(1);
		}
		var userDay=parseInt(userDayStr);
		var userMonth=parseInt(userMonthstr)-1;
		var userYear=parseInt(dateVal.split('/')[2]);
		var userDate=new Date();
		userDate.setFullYear(userYear,userMonth,userDay);

		if(sel=="allbatch1" || sel=="batch11" )
    	{  			
			var valDate5=new Date();
  			valDate5.setFullYear(parseInt('2014'),parseInt('3')-1,parseInt('10'));
  			if(userDate>valDate5){
  				alert("Date should not be Greater than Today's Date");
    			document.getElementById(dateID).value='10/03/2014';
  			}
    	}
    	else if("batch21")
    	{    		
      		
    		var valDate6=new Date();
    		valDate6.setFullYear(parseInt('2014'),parseInt('3')-1,parseInt('13'));
    		if(userDate>valDate6){    			
    			alert("Date should not be Greater than Today's Date"); 
    		document.getElementById(dateID).value='13/03/2014';
    		}
  		
    	} 
		
  		
    }
	return true;
}

function TestDate4(dateID)
{
	var dateVal=document.getElementById(dateID).value;
    var sel=document.getElementById("dmd5").value;

if(dateVal.length>0)
{
	var userDayStr=dateVal.split('/')[0];
	if(userDayStr.indexOf('0')==0)
	{
		userDayStr=userDayStr.substring(1);
	}
	var userMonthstr=dateVal.split('/')[1];
	if(userMonthstr.indexOf('0')==0)
	{
		userMonthstr=userMonthstr.substring(1);
	}
	var userDay=parseInt(userDayStr);
	var userMonth=parseInt(userMonthstr)-1;
	var userYear=parseInt(dateVal.split('/')[2]);
	var userDate=new Date();
	userDate.setFullYear(userYear,userMonth,userDay);
	
	if(sel=="allbatch2" || sel=="batch12" )
	{  			
		var valDate7=new Date();
		valDate7.setFullYear(parseInt('2014'),parseInt('3')-1,parseInt('10'));
	if(userDate>valDate7){
		alert("Date should not be Greater than Today's Date");
	document.getElementById(dateID).value='10/03/2014';
			}
	}
	else if("batch22")
	{    		
		var valDate8=new Date();
			valDate8.setFullYear(parseInt('2014'),parseInt('3')-1,parseInt('13'));
		if(userDate>valDate8){
		alert("Date should not be Greater than Today's Date");
		document.getElementById(dateID).value='13/03/2014';
		}
	} 		
		
}
return true;
}
function TestDate5(dateID)
{
	var dateVal=document.getElementById(dateID).value;
    var sel=document.getElementById("dmd6").value;

if(dateVal.length>0)
{
	var userDayStr=dateVal.split('/')[0];
	if(userDayStr.indexOf('0')==0)
	{
		userDayStr=userDayStr.substring(1);
	}
	var userMonthstr=dateVal.split('/')[1];
	if(userMonthstr.indexOf('0')==0)
	{
		userMonthstr=userMonthstr.substring(1);
	}
	var userDay=parseInt(userDayStr);
	var userMonth=parseInt(userMonthstr)-1;
	var userYear=parseInt(dateVal.split('/')[2]);
	var userDate=new Date();
	userDate.setFullYear(userYear,userMonth,userDay);
	
	if(sel=="allbatch2" || sel=="batch12" )
	{  			
		var valDate9=new Date();
		valDate9.setFullYear(parseInt('2014'),parseInt('3')-1,parseInt('10'));
	if(userDate>valDate9){
		alert("Date should not be Greater than Today's Date");
	document.getElementById(dateID).value='10/03/2014';
			}
	}
	else if("batch22")
	{    		
		var valDate10=new Date();
			valDate10.setFullYear(parseInt('2014'),parseInt('3')-1,parseInt('13'));
		if(userDate>valDate10){
		alert("Date should not be Greater than Today's Date");
		document.getElementById(dateID).value='13/03/2014';
		}
	} 		
		
}
return true;
}
function TestDate6(dateID)
{

	
return true;
}
function TestDate7(dateID)
{
	var dateVal=document.getElementById(dateID).value;
  document.getElementById(dateID).value=dateVal;

return true;
}

function TestDate8(dateID)
{
	var dateVal=document.getElementById(dateID).value;
    var sel=document.getElementById("dmd8").value;

if(dateVal.length>0)
{
	var userDayStr=dateVal.split('/')[0];
	if(userDayStr.indexOf('0')==0)
	{
		userDayStr=userDayStr.substring(1);
	}
	var userMonthstr=dateVal.split('/')[1];
	if(userMonthstr.indexOf('0')==0)
	{
		userMonthstr=userMonthstr.substring(1);
	}
	var userDay=parseInt(userDayStr);
	var userMonth=parseInt(userMonthstr)-1;
	var userYear=parseInt(dateVal.split('/')[2]);
	var userDate=new Date();
	userDate.setFullYear(userYear,userMonth,userDay);
	
	if(sel=="allbatch2" || sel=="batch12" )
	{  			
		var valDate11=new Date();
		valDate11.setFullYear(parseInt('2014'),parseInt('3')-1,parseInt('10'));
	if(userDate>valDate11){
		alert("Date should not be Greater than Today's Date");
	document.getElementById(dateID).value='10/03/2014';
			}
	}
	else if("batch22")
	{    		
		var valDate12=new Date();
			valDate12.setFullYear(parseInt('2014'),parseInt('3')-1,parseInt('13'));
		if(userDate>valDate12){
		alert("Date should not be Greater than Today's Date");
		document.getElementById(dateID).value='13/03/2014';
		}
	} 		
		
}
return true;
}
function TestDate9(dateID)
{
	var dateVal=document.getElementById(dateID).value;
    var sel=document.getElementById("dmd9").value;

if(dateVal.length>0)
{
	var userDayStr=dateVal.split('/')[0];
	if(userDayStr.indexOf('0')==0)
	{
		userDayStr=userDayStr.substring(1);
	}
	var userMonthstr=dateVal.split('/')[1];
	if(userMonthstr.indexOf('0')==0)
	{
		userMonthstr=userMonthstr.substring(1);
	}
	var userDay=parseInt(userDayStr);
	var userMonth=parseInt(userMonthstr)-1;
	var userYear=parseInt(dateVal.split('/')[2]);
	var userDate=new Date();
	userDate.setFullYear(userYear,userMonth,userDay);
	
	if(sel=="allbatch2" || sel=="batch12" )
	{  			
		var valDate11=new Date();
		valDate11.setFullYear(parseInt('2014'),parseInt('3')-1,parseInt('10'));
	if(userDate>valDate11){
		alert("Date should not be Greater than Today's Date");
	document.getElementById(dateID).value='10/03/2014';
			}
	}
	else if("batch22")
	{    		
		var valDate12=new Date();
			valDate12.setFullYear(parseInt('2014'),parseInt('3')-1,parseInt('13'));
		if(userDate>valDate12){
		alert("Date should not be Greater than Today's Date");
		document.getElementById(dateID).value='13/03/2014';
		}
	} 		
		
}
return true;
}
function TestDate10(dateID)
{
	var dateVal=document.getElementById(dateID).value;
    var sel=document.getElementById("dmd10").value;

if(dateVal.length>0)
{
	var userDayStr=dateVal.split('/')[0];
	if(userDayStr.indexOf('0')==0)
	{
		userDayStr=userDayStr.substring(1);
	}
	var userMonthstr=dateVal.split('/')[1];
	if(userMonthstr.indexOf('0')==0)
	{
		userMonthstr=userMonthstr.substring(1);
	}
	var userDay=parseInt(userDayStr);
	var userMonth=parseInt(userMonthstr)-1;
	var userYear=parseInt(dateVal.split('/')[2]);
	var userDate=new Date();
	userDate.setFullYear(userYear,userMonth,userDay);
	
	if(sel=="allbatch2" || sel=="batch12" )
	{  			
		var valDate11=new Date();
		valDate11.setFullYear(parseInt('2014'),parseInt('3')-1,parseInt('10'));
	if(userDate>valDate11){
		alert("Date should not be Greater than Today's Date");
	document.getElementById(dateID).value='10/03/2014';
			}
	}
	else if("batch22")
	{    		
		var valDate12=new Date();
			valDate12.setFullYear(parseInt('2014'),parseInt('3')-1,parseInt('13'));
		if(userDate>valDate12){
		alert("Date should not be Greater than Today's Date");
		document.getElementById(dateID).value='13/03/2014';
		}
	} 		
		
}
return true;
}
function TestDate11(dateID)
{
	var dateVal=document.getElementById(dateID).value;
    var sel=document.getElementById("dmd11").value;

if(dateVal.length>0)
{
	var userDayStr=dateVal.split('/')[0];
	if(userDayStr.indexOf('0')==0)
	{
		userDayStr=userDayStr.substring(1);
	}
	var userMonthstr=dateVal.split('/')[1];
	if(userMonthstr.indexOf('0')==0)
	{
		userMonthstr=userMonthstr.substring(1);
	}
	var userDay=parseInt(userDayStr);
	var userMonth=parseInt(userMonthstr)-1;
	var userYear=parseInt(dateVal.split('/')[2]);
	var userDate=new Date();
	userDate.setFullYear(userYear,userMonth,userDay);
	
	if(sel=="allbatch2" || sel=="batch12" )
	{  			
		var valDate11=new Date();
		valDate11.setFullYear(parseInt('2014'),parseInt('3')-1,parseInt('10'));
	if(userDate>valDate11){
		alert("Date should not be Greater than Today's Date");
	document.getElementById(dateID).value='10/03/2014';
			}
	}
	else if("batch22")
	{    		
		var valDate12=new Date();
			valDate12.setFullYear(parseInt('2014'),parseInt('3')-1,parseInt('13'));
		if(userDate>valDate12){
		alert("Date should not be Greater than Today's Date");
		document.getElementById(dateID).value='13/03/2014';
		}
	} 		
		
}
return true;
}
function TestDate12(dateID)
{
	var dateVal=document.getElementById(dateID).value;
	var sel=document.getElementById("dmd12").value;
	
	if(dateVal.length>0)
	{
		var userDayStr=dateVal.split('/')[0];
		if(userDayStr.indexOf('0')==0)
		{
			userDayStr=userDayStr.substring(1);
		}
		var userMonthstr=dateVal.split('/')[1];
		if(userMonthstr.indexOf('0')==0)
		{
			userMonthstr=userMonthstr.substring(1);
		}
		var userDay=parseInt(userDayStr);
		var userMonth=parseInt(userMonthstr)-1;
		var userYear=parseInt(dateVal.split('/')[2]);
		var userDate=new Date();
		userDate.setFullYear(userYear,userMonth,userDay);

		if(sel=="allbatch2" || sel=="batch12" )
    	{  			
			var valDate1=new Date();
  			valDate1.setFullYear(parseInt('2014'),parseInt('3')-1,parseInt('10'));
  			if(userDate>valDate1){
  				alert("Date should not be Greater than Today's Date");
    			document.getElementById(dateID).value='10/03/2014';
  			}
    	}
    	else if("batch22")
    	{    		
    		var valDate2=new Date();
    		valDate2.setFullYear(parseInt('2014'),parseInt('3')-1,parseInt('13'));
    		if(userDate>valDate2){    			
    			alert("Date should not be Greater than Today's Date"); 
    		document.getElementById(dateID).value='13/03/2014';
    		}
    	} 		
  		
    }
	return true;
}
function window_open1(val,x,y)
{
    //parameter val contains the name of the textbox
    var newWindow;
    var s=document.getElementById('dmd1').value;
   
    if(s=="allbatch2" || s=="batch12")
    {
    var urlstring = '/Nregs/calender_Monday_enable.jsp?requestSent='+val;
    }
    else
    {
    var urlstring = '/Nregs/Calender_Thursday_enable.jsp?requestSent='+val;
    }  
    
    var urlstyle = 'height=200,width=280,toolbar=no,minimize=no,status=no,memubar=no,location=no,scrollbars=no,top='+x+',left='+y;
    newWindow = window.open(urlstring,'Calendar',urlstyle);
}
function window_open2(val,x,y)
{
    //parameter val contains the name of the textbox
   
    var newWindow;
    var s=document.getElementById('dmd2').value;
   
    if(s=="allbatch2" || s=="batch12")
    {
       
    var urlstring = '/Nregs/calender_Monday_enable.jsp?requestSent='+val;
    }
    else
    {
      
    var urlstring = '/Nregs/Calender_Thursday_enable.jsp?requestSent='+val;
    }  
    
    var urlstyle = 'height=200,width=280,toolbar=no,minimize=no,status=no,memubar=no,location=no,scrollbars=no,top='+x+',left='+y;
    newWindow = window.open(urlstring,'Calendar',urlstyle);
}
function window_open3(val,x,y)
{
    //parameter val contains the name of the textbox
    var newWindow;
    var s=document.getElementById('dmd4').value;
   
    if(s=="allbatch1" || s=="batch11")
    {
    var urlstring = '/Nregs/calender_Monday_enable.jsp?requestSent='+val;
    }
    else
    {
    var urlstring = '/Nregs/Calender_Thursday_enable.jsp?requestSent='+val;
    }  
    
    var urlstyle = 'height=200,width=280,toolbar=no,minimize=no,status=no,memubar=no,location=no,scrollbars=no,top='+x+',left='+y;
    newWindow = window.open(urlstring,'Calendar',urlstyle);
}
function window_open4(val,x,y)
{
    //parameter val contains the name of the textbox
   
    var newWindow;
    var s=document.getElementById('dmd5').value;
   
    if(s=="allbatch2" || s=="batch12")
    {
       
    var urlstring = '/Nregs/calender_Monday_enable.jsp?requestSent='+val;
    }
    else
    {
      
    var urlstring = '/Nregs/Calender_Thursday_enable.jsp?requestSent='+val;
    }  
    
    var urlstyle = 'height=200,width=280,toolbar=no,minimize=no,status=no,memubar=no,location=no,scrollbars=no,top='+x+',left='+y;
    newWindow = window.open(urlstring,'Calendar',urlstyle);
}

function window_open5(val,x,y)
{
    //parameter val contains the name of the textbox
   
    var newWindow;
    var s=document.getElementById('dmd6').value;
   
    if(s=="allbatch2" || s=="batch12")
    {
       
    var urlstring = '/Nregs/calender_Monday_enable.jsp?requestSent='+val;
    }
    else
    {
      
    var urlstring = '/Nregs/Calender_Thursday_enable.jsp?requestSent='+val;
    }  
    
    var urlstyle = 'height=200,width=280,toolbar=no,minimize=no,status=no,memubar=no,location=no,scrollbars=no,top='+x+',left='+y;
    newWindow = window.open(urlstring,'Calendar',urlstyle);
}

function window_open6(val,x,y)
{
    //parameter val contains the name of the textbox
   
    var newWindow;
    var urlstring = '/Nregs/Calender_last5_Modays_enable.jsp?requestSent='+val;  
    var urlstyle = 'height=200,width=280,toolbar=no,minimize=no,status=no,memubar=no,location=no,scrollbars=no,top='+x+',left='+y;
    newWindow = window.open(urlstring,'Calendar',urlstyle);
}
function window_open7(val,x,y)
{
    //parameter val contains the name of the textbox
   
    var newWindow;
     //var urlstring = '/Nregs/Calender_4Weeks_enable.jsp?requestSent='+val;
     var urlstring = '/Nregs/Calender_last4_Modays_enable.jsp?requestSent='+val;
  
    
    var urlstyle = 'height=200,width=280,toolbar=no,minimize=no,status=no,memubar=no,location=no,scrollbars=no,top='+x+',left='+y;
    newWindow = window.open(urlstring,'Calendar',urlstyle);
}
function window_open8(val,x,y)
{
    //parameter val contains the name of the textbox
   
    var newWindow;
    var s=document.getElementById('dmd8').value;
   
    if(s=="allbatch2" || s=="batch12")
    {
       
    var urlstring = '/Nregs/calender_Monday_enable.jsp?requestSent='+val;
    }

    else
    {
      
    var urlstring = '/Nregs/Calender_Thursday_enable.jsp?requestSent='+val;
    }  
    
    var urlstyle = 'height=200,width=280,toolbar=no,minimize=no,status=no,memubar=no,location=no,scrollbars=no,top='+x+',left='+y;
    newWindow = window.open(urlstring,'Calendar',urlstyle);
}
function window_open9(val,x,y)
{
    //parameter val contains the name of the textbox
   
    var newWindow;
    var s=document.getElementById('dmd9').value;
   
    if(s=="allbatch2" || s=="batch12")
    {
       
    var urlstring = '/Nregs/calender_Monday_enable.jsp?requestSent='+val;
    }
    else
    {
      
    var urlstring = '/Nregs/Calender_Thursday_enable.jsp?requestSent='+val;
    }  
    
    var urlstyle = 'height=200,width=280,toolbar=no,minimize=no,status=no,memubar=no,location=no,scrollbars=no,top='+x+',left='+y;
    newWindow = window.open(urlstring,'Calendar',urlstyle);
}
function window_open10(val,x,y)
{
    //parameter val contains the name of the textbox
   
    var newWindow;
    var s=document.getElementById('dmd10').value;
   
    if(s=="allbatch2" || s=="batch12")
    {
       
    var urlstring = '/Nregs/calender_Monday_enable.jsp?requestSent='+val;
    }
    else
    {
      
    var urlstring = '/Nregs/Calender_Thursday_enable.jsp?requestSent='+val;
    }  
    
    var urlstyle = 'height=200,width=280,toolbar=no,minimize=no,status=no,memubar=no,location=no,scrollbars=no,top='+x+',left='+y;
    newWindow = window.open(urlstring,'Calendar',urlstyle);
}
function window_open11(val,x,y)
{
    //parameter val contains the name of the textbox
   
    var newWindow;
    var s=document.getElementById('dmd11').value;
   
    if(s=="allbatch2" || s=="batch12")
    {
       
    var urlstring = '/Nregs/calender_Monday_enable.jsp?requestSent='+val;
    }
    else
    {
      
    var urlstring = '/Nregs/Calender_Thursday_enable.jsp?requestSent='+val;
    }  
    
    var urlstyle = 'height=200,width=280,toolbar=no,minimize=no,status=no,memubar=no,location=no,scrollbars=no,top='+x+',left='+y;
    newWindow = window.open(urlstring,'Calendar',urlstyle);
}
function TestDate(dateID)
{
	
	var dateVal=document.getElementById(dateID).value;
	if(dateVal.length>0)
	{
		var userDayStr=dateVal.split('/')[0];
		if(userDayStr.indexOf('0')==0)
		{
			userDayStr=userDayStr.substring(1);
		}
		var userMonthstr=dateVal.split('/')[1];
		if(userMonthstr.indexOf('0')==0)
		{
			userMonthstr=userMonthstr.substring(1);
		}
		var userDay=parseInt(userDayStr);
		var userMonth=parseInt(userMonthstr)-1;
		var userYear=parseInt(dateVal.split('/')[2]);
		var userDate=new Date();
		userDate.setFullYear(userYear,userMonth,userDay);
  		var todayDate=new Date();
    if(userDate>todayDate)
    {
    	alert("Date should not be Greater than Today's Date");
    	document.getElementById(dateID).value='10/03/2014';
    	//document.getElementById(dateID).focus();
		return false;
		}
    }
	return true;
}

function window_open(val,x,y)
{
    //parameter val contains the name of the textbox
    var newWindow;
    var urlstring = '/Nregs/Calendar.jsp?requestSent='+val;
    var urlstyle = 'height=200,width=280,toolbar=no,minimize=no,status=no,memubar=no,location=no,scrollbars=no,top='+x+',left='+y;
    newWindow = window.open(urlstring,'Calendar',urlstyle);
}

function go(action)
{	var URL="";
	var DistrictID = document.forms[0].District.value;
	var MandalID = document.forms[0].Mandal.value;
	var PancID = document.forms[0].Panchayat.value;
	var vid=document.forms[0].Village.value;
	var year = document.forms[0].Financial.value;
	var month = document.forms[0].Month.value;
	//alert("Action Val"+action);
	if(action==11)
		URL="/Nregs/FrontServlet?requestType=NewReportsRH&actionVal=AllGlance";
	else if(action==12)
		URL="/Nregs/FrontServlet?requestType=NewReportsRH&actionVal=CRDR1" ;
	else if(action==13)
		URL="/Nregs/FrontServlet?requestType=NewReportsRH&actionVal=CRDR2" ;
	else if(action==14)
		URL="/Nregs/FrontServlet?requestType=NewReportsRH&actionVal=Abstract";
	else if(action==15)
		URL="/Nregs/FrontServlet?requestType=SpecialReportsRH&actionVal=MCCAllGlance";
	else if(action==16)
		URL="/Nregs/FrontServlet?requestType=SpecialReportsRH&actionVal=MCCAbstract";	
	else if(action==21)
		URL="/Nregs/FrontServlet?requestType=NewReportsRH&actionVal=NregaProgressReport&type=cum";
	else if(action==22)
		URL="/Nregs/FrontServlet?requestType=NewReportsRH&actionVal=EmpGenRep";
	else if(action==23)
		URL="/Nregs/FrontServlet?requestType=NewReportsRH&actionVal=PhyPerf&type=cum";
	else if(action==241)
		URL= "/Nregs/FrontServlet?requestType=NewReportsRH&actionVal=WagWrkFinPerf&type=wage";
	else if(action==242)
		URL="/Nregs/FrontServlet?requestType=NewReportsRH&actionVal=FinPerf&type=normal";
	else if(action==25)
	 	URL="/Nregs/FrontServlet?requestType=NewReportsRH&actionVal=DelayDtls";
	/*else if(action==25)
		URL="/Nregs/FrontServlet?requestType=GOIUnempDelay_engRH&actionVal=DelayDtls";  */
	else if(action==31)
		URL="/Nregs/FrontServlet?requestType=NewReportsRH&actionVal=revdelay&type=NO";
	else if(action==32)
		URL="/Nregs/FrontServlet?requestType=NewReportsRH&actionVal=revdelay&type=YES";
	else if(action==33)
		URL="/Nregs/FrontServlet?requestType=NewReportsRH&actionVal=SuspDtls";
	/*else if(action==34)
		URL="/Nregs/FrontServlet?requestType=Common_Ajax_engRH&actionVal=Display&page=Perfreports_eng";
	else if(action==35)
		URL="/Nregs/FrontServlet?requestType=NewReportsRH&actionVal=Excep";*/
	/*else if(action=='36a')
		URL="/Nregs/FrontServlet?requestType=NewReportsRH&actionVal=ZeroExp";
	else if(action=='36b')
		URL="/Nregs/FrontServlet?requestType=NewReportsRH&actionVal=ZeroShelf";
	else if(action=='36c')
		URL="/Nregs/FrontServlet?requestType=NewReportsRH&actionVal=DelayPay";
	else if(action=='36d')
		URL="/Nregs/FrontServlet?requestType=NewReportsRH&actionVal=FYWiseZeroExp";
	else if(action=='36e')
		URL="/Nregs/FrontServlet?requestType=NewReportsRH&actionVal=FYWiseZeroMuster";
	else if(action=='36f')
		URL="/Nregs/FrontServlet?requestType=NewReportsRH&actionVal=GPsPerformance"; */
	else if(action==37)
		URL="/Nregs/FrontServlet?requestType=NewReportsRH&actionVal=FTODelayAnalysis";
	/*else if(action==38)
		URL="/Nregs/FrontServlet?requestType=NewReportsRH&actionVal=APNAWrkngPrfrmnce";	*/
	else if(action=='39a')
		URL="/Nregs/FrontServlet?requestType=NewReportsRH&actionVal=GPPrfrmnceRpt&type=A";	
	else if(action=='39b')
		URL="/Nregs/FrontServlet?requestType=NewReportsRH&actionVal=GPPrfrmnceRpt&type=B";	
	else if(action=='39c')
		URL="/Nregs/FrontServlet?requestType=NewReportsRH&actionVal=GPPrfrmnceRpt&type=C";	
	else if(action=='39d')
		URL="/Nregs/FrontServlet?requestType=NewReportsRH&actionVal=GPPrfrmnceRpt&type=D";	
	else if(action==310)
		URL="/Nregs/FrontServlet?requestType=NewReportsRH&actionVal=TAPrfrmnceRpt";
	else if(action==311)
		URL="/Nregs/FrontServlet?requestType=NewReportsRH&actionVal=APOPrfrmnceRpt";
	else if(action==312)
		URL="/Nregs/FrontServlet?requestType=NewReportsRH&actionVal=SSSPrfrmnceRpt";
	else if(action==313)
		URL="/Nregs/FrontServlet?requestType=NewReportsRH&actionVal=GreenAssets";	
	else if(action==314)
		URL="/Nregs/FrontServlet?requestType=NewReportsRH&actionVal=Apdperforma";
	else if(action==41)
		URL="/Nregs/FrontServlet?requestType=Common_engRH&actionVal=workdet&page=DetailsofWork_eng";
	else if(action==42)
		URL="/Nregs/FrontServlet?requestType=Common_engRH&actionVal=musterinfo&page=MusterRolls_eng";
	else if(action==43)
		URL="/Nregs/FrontServlet?requestType=Common_engRH&actionVal=infodis&page=InfoDisplay_eng";
	else if(action==44)
		URL="/Nregs/FrontServlet?requestType=Common_engRH&actionVal=musterinfo&page=matsummary_eng";
	else if(action==45)
		URL="/Nregs/FrontServlet?requestType=Common_engRH&actionVal=musterinfo&page=MaterialsPurchased_eng";
	else if(action==46)
		URL="/Nregs/FrontServlet?requestType=Common_engRH&actionVal=workdet&page=MaterialDiff_eng";
	else if(action==47)
		URL="/Nregs/FrontServlet?requestType=Common_engRH&actionVal=workdet&page=MeasurementBook_eng";
	else if(action==48)
		URL="/Nregs/FrontServlet?requestType=Common_engRH&actionVal=scheme&page=schemes_eng";
	/*else if(action==49)
		URL="/Nregs/PdfPrintServlet?actionVal=wrkDtls";
	else if(action==410)
		URL="/Nregs/PdfPrintServlet?actionVal=lbrDtls";*/
	else if(action==5)
		URL="/Nregs/FrontServlet?requestType=Common_ITDA_Ajax_engRH&actionVal=Display&page=ITDAreportcenter_Ajax_eng";
	else if(action==6)
		URL="/Nregs/FrontServlet?requestType=Common_Ajax_engRH&actionVal=Display&page=Specialreportcenter_eng";
	/*else if(action==71)
		URL="/Nregs/FrontServlet?requestType=SMSReport_engRH&actionVal=PartiAdv";*/
	else if(action==71)
		URL="/Nregs/FrontServlet?requestType=ApnaReportsRH&actionVal=distWiseApna";
	else if(action==72)
		URL="/Nregs/FrontServlet?requestType=ApnaReportsRH&actionVal=ngoWiseApna";
	/*else if(action==73)
		URL="/Nregs/FrontServlet?requestType=ApnaReportsRH&actionVal=SSSdistWiseApna";*/
	/*else if(action==74)
		URL="/Nregs/FrontServlet?requestType=ApnaReportsRH&actionVal=SSSngoWiseApna";*/
	/*else if(action==75)
		URL="/Nregs/FrontServlet?requestType=ApnaReportsRH&actionVal=SSSngoWiseApnaFin";*/
	/*else if(action==76)
		URL="http://125.22.8.66/QC/apnaApplnAction.do?actionVal=MonthlyMeetingReport&pType=public";*/
	/*else if(action==77)
		URL="http://125.22.8.66/QC/apnaApplnAction.do?actionVal=MandLvlMeetingReport&pType=public&type=link";*/
	else if(action==8)
		URL="/Nregs/FrontServlet?requestType=Common_engRH&actionVal=Chenchu&page=chenchuReports_eng";
	else if(action==82)
		URL="/Nregs/FrontServlet?requestType=Common_engRH&actionVal=Chenchu&page=yanadi_challayanadi";
	else if(action==91)
		URL="/Nregs/FrontServlet?requestType=DCC_Common_AjaxRH&actionVal=Display&page=DCCPRRepCenter&dept=PR";
	else if(action==92)
		URL="/Nregs/FrontServlet?requestType=DCC_Common_AjaxRH&actionVal=Display&page=DCCForestRepCenter&dept=FOREST";
	else if(action==93)
		URL="/Nregs/FrontServlet?requestType=DCC_Common_AjaxRH&actionVal=Display&page=DCCSERPRepCenter&dept=SERP";
	else if(action==94)
		URL="/Nregs/FrontServlet?requestType=DCC_Common_AjaxRH&actionVal=Display&page=DCCITDARepCenter&dept=ITDA";
	else if(action==95)
		URL="/Nregs/FrontServlet?requestType=DCC_Common_AjaxRH&actionVal=Display&page=DCCHORTRepCenter&dept=HORTI";
	else if(action==96)
		URL="/Nregs/FrontServlet?requestType=DCC_Common_AjaxRH&actionVal=CementRpt";
	else if(action==97)
		URL="/Nregs/FrontServlet?requestType=DCC_Common_AjaxRH&actionVal=Display&page=HousingRep&dept=PR";
	else if(action==101)
		URL="/Nregs/FrontServlet?requestType=AjaxRH&actionVal=Display&Program=MCC";
	else if(action==102)
		URL="/Nregs/FrontServlet?requestType=AjaxRH&actionVal=Display&Program=DCC";
	else if(action==103)
		URL="/Nregs/FrontServlet?requestType=AjaxRH&actionVal=Display&Program=DAP";
	else if(action==104)
		URL="/Nregs/FrontServlet?requestType=ReconcilationRH&actionVal=public";
	else if(action==105)
		URL="/Nregs/FrontServlet?requestType=AjaxRH&actionVal=Display&Program=SAP";
	else if(action==106)
		URL="/Nregs/FrontServlet?requestType=AjaxRH&actionVal=Display&Program=CCC";		
	else if(action==107)
		URL="/Nregs/FrontServlet?requestType=AjaxRH&actionVal=Display&Program=SSAAT";	
	else if(action==108)
		URL="/Nregs/FrontServlet?requestType=AuditRptRH&actionVal=Display&type=download&page=AuditRpt";	
	else if(action==109)
		URL="/Nregs/FrontServlet?requestType=AjaxRH&actionVal=Display&Program=SRDS";
	else if(action==1010)
		URL="/Nregs/FrontServlet?requestType=AjaxRH&actionVal=Display&Program=RWS";
	else if(action==1011)
		URL="/Nregs/FrontServlet?requestType=CFMSReports_engRH&actionVal=EPFReport";
	else if(action==1012)
	URL="/Nregs/FrontServlet?requestType=SpecialReportsRH&actionVal=Airtel&type=nonfte";
	else if(action==111)
		URL="http://www.emuster.in"; 
	else if(action=='112a')
		URL="/Nregs/FrontServlet?requestType=SSSanghaRH&actionVal=FnlAllotment";
	else if(action=='112b')
		URL="/Nregs/FrontServlet?requestType=SSSanghaRH&actionVal=grpsAllotment";
	else if(action=='113')
		URL="/Nregs/FrontServlet?requestType=SSSanghaRH&actionVal=wrkEmpPlan";
	else if(action=='114')
		URL="/Nregs/FrontServlet?requestType=SSSanghaRH&actionVal=mustersEdtd";
	else if(action=='115')
		URL="/Nregs/FrontServlet?requestType=SSSanghaRH&actionVal=measurementsEdtd";
	else if(action=='116')
		URL="/Nregs/FrontServlet?requestType=SSSanghaRH&actionVal=ePayOrder";
	else if(action=='117')
		URL="http://125.22.8.66/eMuster/responseRedirect.jsp?link=Nregs_eMuster";
	else if(action=='118')
		URL="/Nregs/FrontServlet?requestType=SSSanghaRH&actionVal=ePayOrderStatus";
	else if(action=='119a')
		URL="/Nregs/FrontServlet?requestType=SSSanghaRH&actionVal=ShelfOWorksA";
	else if(action=='119b')
		URL="/Nregs/FrontServlet?requestType=SSSanghaRH&actionVal=ShelfOWorksB";
	else if(action=='119c')
		URL="/Nregs/FrontServlet?requestType=SSSanghaRH&actionVal=ShelfOWorksC";
	else if(action=='1110')
		URL="/Nregs/FrontServlet?requestType=SSSanghaRH&actionVal=PDloginRpt";
	else if(action=='121a')
		URL="/Nregs/FrontServlet?requestType=SSSanghaRH&actionVal=StatusReg";
	else if(action=='121b')
		URL="/Nregs/FrontServlet?requestType=SSSanghaRH&actionVal=StatRegVS";
	
	else if(action=='122a')
		URL="/Nregs/FrontServlet?requestType=SSSanghaRH&actionVal=Mate";
	else if(action=='122b')
		URL="/Nregs/FrontServlet?requestType=SSSanghaRH&actionVal=MateVS";
	/*else if(action==123)
		URL="/Nregs/FrontServlet?requestType=SSSanghaRH&actionVal=WorkAlloc";*/
	else if(action==123)
		URL="/Nregs/FrontServlet?requestType=SSSanghaRH&actionVal=MatesTraining";
	/*else if(action=='124a')
		URL="/Nregs/FrontServlet?requestType=SSSanghaRH&actionVal=WorksAllocatedSSS";*/
	else if(action==124)
		URL="/Nregs/FrontServlet?requestType=SSSanghaRH&actionVal=WorksAllocatedVSSS";
	else if(action==125)
		URL="/Nregs/FrontServlet?requestType=SSSanghaRH&actionVal=UncovLabour";
	else if(action=='126a')
		URL="/Nregs/FrontServlet?requestType=SSSanghaRH&actionVal=PWDHH";
	else if(action=='126b')
		URL="/Nregs/FrontServlet?requestType=SSSanghaRH&actionVal=PWDWS";
	/*else if(action==127)
		URL="/Nregs/FrontServlet?requestType=SSSanghaRH&actionVal=FnlAllotment";*/
	else if(action==128)
		URL="/Nregs/FrontServlet?requestType=SSSanghaRH&actionVal=TempRegWrkAllotment";
	else if(action==129)
		URL="/Nregs/FrontServlet?requestType=SSSanghaRH&actionVal=APNA";
	else if(action=='1210a')
		URL="/Nregs/FrontServlet?requestType=SSSanghaRH&actionVal=SSSGroups&type=SSS";
	else if(action=='1210b')
		URL="/Nregs/FrontServlet?requestType=SSSanghaRH&actionVal=SSSGroups&type=SSST";
	else if(action=='1211')
		URL="/Nregs/FrontServlet?requestType=Common_Ajax_engRH&actionVal=Display&page=SSSFederationreportcenter_eng";
		//URL="/Nregs/FrontServlet?requestType=SSSanghaRH&actionVal=SSSFederation";
	else if(action=='1212')
		URL="/Nregs/FrontServlet?requestType=SSSanghaRH&actionVal=SramaMithraCordinator";
	/*else if(action=='1213')
		URL="/Nregs/FrontServlet?requestType=SSSanghaRH&actionVal=SramaMithraCRP";
	else if(action=='1214')
		URL="/Nregs/FrontServlet?requestType=SSSanghaRH&actionVal=smtrainingprogress";*/
	else if(action=='1213a')
		URL="/Nregs/FrontServlet?requestType=SSSanghaRH&actionVal=SramaMithraCRP&type=A";
	else if(action=='1213b')
		URL="/Nregs/FrontServlet?requestType=SSSanghaRH&actionVal=SramaMithraCRP&type=B";
	else if(action=='1214a')
		URL="/Nregs/FrontServlet?requestType=SSSanghaRH&actionVal=smtrainingprogress&type=A";
	else if(action=='1214b')
		URL="/Nregs/FrontServlet?requestType=SSSanghaRH&actionVal=smtrainingprogress&type=B";
	else if(action=='1215')
		URL="/Nregs/FrontServlet?requestType=SSSanghaRH&actionVal=Trainingtrack";
	else if(action==131)
		URL="/Nregs/FrontServlet?requestType=LandDevelopmentRH&actionVal=LDPBlockDtls";
	/*else if(action==132)
		URL="/Nregs/FrontServlet?requestType=LandDevelopmentRH&actionVal=RoFRAbstract";*/
	else if(action=='132a')
		URL="/Nregs/FrontServlet?requestType=LandDevelopmentRH&actionVal=crowbars&type=crowbars";
	else if(action=='132b')

		URL="/Nregs/FrontServlet?requestType=LandDevelopmentRH&actionVal=crowbars&type=shades";
	else if(action=='132c')
		URL="/Nregs/FrontServlet?requestType=LandDevelopmentRH&actionVal=crowbars&type=firstaid";
		else if(action=='133a')
		URL="/Nregs/FrontServlet?requestType=LandDevelopmentRH&actionVal=LDSCST&status=ALL";
	else if(action=='133b')
		URL="/Nregs/FrontServlet?requestType=LandDevelopmentRH&actionVal=LDSCST&status=ST";
	else if(action=='133c')
		URL="/Nregs/FrontServlet?requestType=LandDevelopmentRH&actionVal=LDSCST&status=SC";
	else if(action=='133d')
		URL="/Nregs/FrontServlet?requestType=LandDevelopmentRH&actionVal=LDSCST&status=SM";
	else if(action==134)
		URL="/Nregs/FrontServlet?requestType=Common_Ajax_engRH&actionVal=Display&page=Planningreportcenter_eng";
	/*URL="/Nregs/FrontServlet?requestType=LandDevelopmentRH&actionVal=Planningshelf";
	else if(action==134)
		URL="/Nregs/FrontServlet?requestType=LandDevelopmentRH&actionVal=BalanceRevistdWrks";*/
	else if(action==135)
		URL="/Nregs/FrontServlet?requestType=LandDevelopmentRH&actionVal=ShelfWork&fin=fin12";
	/*else if(action==136)
		URL="/Nregs/FrontServlet?requestType=LandDevelopmentRH&actionVal=LDFarmer";*/
	else if(action==137)
		URL="/Nregs/FrontServlet?requestType=LandDevelopmentRH&actionVal=MandalPlan";
	/*else if(action==138)
		URL="/Nregs/FrontServlet?requestType=LandDevelopmentRH&actionVal=LDWorks";*/
	else if(action==139)
		URL="/Nregs/FrontServlet?requestType=LandDevelopmentRH&actionVal=PwiseWwiseDtls"; 
	/*else if(action==1310)
		URL="/Nregs/FrontServlet?requestType=LandDevelopmentRH&actionVal=PerfMonitWEP";*/  
	/*else if(action==1311)
		URL="/Nregs/FrontServlet?requestType=LandDevelopmentRH&actionVal=PlanWEP";*/
	/*else if(action==1312)
		URL="/Nregs/FrontServlet?requestType=LandDevelopmentRH&actionVal=AvgWEP";*/
	/*else if(action==1313)
		URL="/Nregs/FrontServlet?requestType=LandDevelopmentRH&actionVal=WEPnotDone";*/
	/*else if(action==1314)
		URL="/Nregs/FrontServlet?requestType=LandDevelopmentRH&actionVal=PersondaysLDP";*/
	/*else if(action==1315)
		URL="/Nregs/FrontServlet?requestType=LandDevelopmentRH&actionVal=formatQ";*/
	/*else if(action==1316)
		URL="/Nregs/FrontServlet?requestType=LandDevelopmentRH&actionVal=SiltAplicationProgress";*/
/*	else if(action==1317)
		URL="/Nregs/FrontServlet?requestType=LandDevelopmentRH&actionVal=Entitlement"; */
	else if(action=='1317a')
		URL="/Nregs/FrontServlet?requestType=LandDevelopmentRH&actionVal=PwiseWwiseDtlsNew"; 
	else if(action=='1317b')
		URL="/Nregs/FrontServlet?requestType=LandDevelopmentRH&actionVal=districtWiseRpt"; 
	else if(action=='1318')
		URL="/Nregs/FrontServlet?requestType=LandDevelopmentRH&actionVal=LabourBudget"; 
/*	else if(action==1318)
		URL="/Nregs/FrontServlet?requestType=SpecialReportsRH&actionVal=LbrBudgtRpt";*/
	else if(action==1319)
		URL="/Nregs/FrontServlet?requestType=LandDevelopmentRH&actionVal=shelfVSLbrBudgt";
	else if(action==1320)
		URL="/Nregs/FrontServlet?requestType=LandDevelopmentRH&actionVal=NBAProjectRpt"; 
	/*else if(action==1321)
		URL="/Nregs/FrontServlet?requestType=LandDevelopmentRH&actionVal=NBAProgressRpt"; */
	else if(action==1321)
		URL="/Nregs/FrontServlet?requestType=LandDevelopmentRH&actionVal=NBAcomponent"; 
	else if(action==1322)
		URL="/Nregs/FrontServlet?requestType=LandDevelopmentRH&actionVal=Workclosure";
	else if(action==1323)
		URL="/Nregs/PdfPrintServlet?actionVal=ssswrks";
	 
	
	
	else if(action==140)
		URL="/Nregs/FrontServlet?requestType=Common_Ajax_engRH&actionVal=Display&page=Smartreports_eng";
	else if(action==151)
		URL="/Nregs/PdfPrintServlet?actionVal=conSARpts";
	else if(action==153)
		URL="/Nregs/PdfPrintServlet?actionVal=VSA&type=VSA";
	else if(action==154)
		URL="/Nregs/PdfPrintServlet?actionVal=VSA&type=NGO";
	else if(action==155)
		URL="/Nregs/PdfPrintServlet?actionVal=SSSMemberDtls";
	else if(action==161)
		URL="/Nregs/FrontServlet?requestType=ExpendittureRH&actionVal=SAPDtlsRpt&type=MCC";
	else if(action==162)
		URL="/Nregs/FrontServlet?requestType=AjaxRH&actionVal=Display&Program=DCCExp";		
	else if(action==163)
		URL="/Nregs/FrontServlet?requestType=ExpendittureRH&actionVal=SAPDtlsRpt&type=DWMA";
	else if(action==164)
		URL="/Nregs/FrontServlet?requestType=ExpendittureRH&actionVal=SAPDtlsRpt&type=CRD";
	else if(action==165)
		URL="/Nregs/FrontServlet?requestType=ExpendittureRH&actionVal=SAPDtlsRpt&type=SSAAT";
	else if(action==166)
		URL="/Nregs/FrontServlet?requestType=ExpendittureRH&actionVal=SAPDtlsRpt&type=QC";
	else if(action==167)
		URL="/Nregs/FrontServlet?requestType=ExpendittureRH&actionVal=AdminExpRpt";
	else if(action==168)
		URL="/Nregs/FrontServlet?requestType=ExpendittureRH&actionVal=MGVNRpt";
	else if(action==171)
		URL="http://125.22.8.66/QC/reportAction.do?actionVal=PublicRep";
	else if(action==181)
		URL="/Nregs/FrontServlet?requestType=ConstituentRH&actionVal=display&page=Constituent_Rpt_Center";			
	else if(action==191)
		{
		if(PancID=="-1")
		URL="/Nregs/FrontServlet?requestType=SpecialReportsRH&actionVal=WorkStatus&type=R19.1";
		else
		URL="/Nregs/FrontServlet?requestType=AssetregRH&actionVal=Assetreg";
		}
	else if(action==192)
		URL="/Nregs/FrontServlet?requestType=Common_Ajax_engRH&actionVal=Display&page=PanchayatRegRptCenter_eng";
	else if(action==193)
		URL="/Nregs/PdfPrintServlet?actionVal=MandReg";
	else if(action==201)
		URL="/Nregs/FrontServlet?requestType=PWDReportsRH&actionVal=PersonDisabilityGlance";
	else if(action==202)
		URL="/Nregs/FrontServlet?requestType=PWDReportsRH&actionVal=ProgressRpt";
	else if(action==203)
		URL="/Nregs/FrontServlet?requestType=PWDReportsRH&actionVal=Abstract";
	else if(action=='204a')
		URL="/Nregs/FrontServlet?requestType=PWDReportsRH&actionVal=DemandPDRpt";
	else if(action=='204b')
		URL="/Nregs/FrontServlet?requestType=PWDReportsRH&actionVal=DemandWrkAllotmntPDRpt";
	else if(action=='205a')
	{
		URL="/Nregs/FrontServlet?requestType=DemandRH&actionVal=demanddtlsa&type="+document.forms[0].stmtDate1.value;
	}
	else if(action=='205b')
     	URL="/Nregs/FrontServlet?requestType=DemandRH&actionVal=demanddtlsb&type="+document.forms[0].dmaDate2.value;
    else if(action=='205')
		URL="/Nregs/FrontServlet?requestType=PWDReportsRH&actionVal=PerformanceRpt";
	else if(action=='206a')
		URL="/Nregs/FrontServlet?requestType=DemandRH&actionVal=PwdDmndWeeklyRpt&type="+document.forms[0].stmtDate2.value+"&btype="+document.getElementById('dmd4').value;
	else if(action=='206b')
     	URL="/Nregs/FrontServlet?requestType=DemandRH&actionVal=PwdDmndAloctnWeeklyRpt&type="+document.forms[0].dmaDate4.value+"&btype="+document.getElementById('dmd5').value;
	else if(action=='206aa')
		URL="/Nregs/FrontServlet?requestType=DemandRH&actionVal=VSSSDmndWeeklyRpt&type="+document.forms[0].stmtDate5.value+"&btype="+document.getElementById('dmd12').value;
	
	else if(action=='207')
     	URL="/Nregs/FrontServlet?requestType=DemandRH&actionVal=VSSSApna";

	
	else if(action=='211a')
		URL="/Nregs/FrontServlet?requestType=DemandRH&actionVal=DemandRpt";
	else if(action=='211b')
		URL="/Nregs/FrontServlet?requestType=DemandRH&actionVal=DemandWrkAllotmntRpt";
	else if(action=='211c')
		URL="/Nregs/FrontServlet?requestType=DemandRH&actionVal=Demandcapture";
	/*else if(action=='212b')
		URL="/Nregs/FrontServlet?requestType=DemandRH&actionVal=DemandWrkAllotmntRptWeekly";*/
	
	/*as per new format
	else if(action=='211a')
		URL="/Nregs/FrontServlet?requestType=DemandRH&actionVal=DemandRpt";
	else if(action=='211b')
		URL="/Nregs/FrontServlet?requestType=DemandRH&actionVal=WorkDmdMonthly";
	else if(action=='212a')
		URL="/Nregs/FrontServlet?requestType=DemandRH&actionVal=WorkDmdWeekly";
	else if(action=='212b')
		URL="/Nregs/FrontServlet?requestType=DemandRH&actionVal=WorkAllocWeekly";
		*/ 
	else if(action=='212a')
		URL="/Nregs/FrontServlet?requestType=DemandRH&actionVal=DmndWeeklyRpt&type="+document.forms[0].stmtDate.value+"&btype="+document.getElementById('dmd1').value;
	else if(action=='212b')
		URL="/Nregs/FrontServlet?requestType=DemandRH&actionVal=DmndAloctnWeeklyRpt&type="+document.forms[0].dmaDate.value+"&btype="+document.getElementById('dmd2').value;
	else if(action=='212aa')
		URL="/Nregs/FrontServlet?requestType=DemandRH&actionVal=DmndWeeklyRptNew&type="+document.forms[0].stmtDate.value+"&btype="+document.getElementById('dmd1').value;
	else if(action=='212c')
        URL="/Nregs/FrontServlet?requestType=DemandRH&actionVal=labourworkd&type="+document.forms[0].dmaDate3.value;
	else if(action=='212d')
		URL="/Nregs/FrontServlet?requestType=DemandRH&actionVal=eMusterIDStatusRpt&type="+document.forms[0].dmaDate5.value+"&btype="+document.getElementById('dmd6').value;
	else if(action=='212e')
		URL="/Nregs/FrontServlet?requestType=DemandRH&actionVal=EmusterReportProjectwise&type="+document.forms[0].dmaDate6.value;
	/*else if(action=='212f')
		URL="/Nregs/FrontServlet?requestType=DemandRH&actionVal=eMusterGeneration&type="+document.forms[0].dmaDate7.value;*/
	else if(action=='212ff')
		URL="/Nregs/FrontServlet?requestType=DemandRH&actionVal=eMusterGenerationNew&type="+document.forms[0].dmaDate7.value;
	else if(action=='212g')
		URL="/Nregs/FrontServlet?requestType=DemandRH&actionVal=FAdmdCptrperformnceRpt&type="+document.forms[0].dmaDate8.value+"&btype="+document.getElementById('dmd8').value;
	
	else if(action=='212h')
		URL="/Nregs/FrontServlet?requestType=DemandRH&actionVal=GPWise&type="+document.forms[0].dmaDate9.value+"&btype="+document.getElementById('dmd9').value;
	
	else if(action=='212i')
		URL="/Nregs/FrontServlet?requestType=DemandRH&actionVal=SSSWise&type="+document.forms[0].dmaDate10.value+"&btype="+document.getElementById('dmd10').value;
	
	else if(action=='212j')
		URL="/Nregs/FrontServlet?requestType=DemandRH&actionVal=jobcardWise&type="+document.forms[0].dmaDate11.value+"&btype="+document.getElementById('dmd11').value;
	
	
	
	
	
	
	else if(action=='213a')
		URL="/Nregs/FrontServlet?requestType=DemandRH&actionVal=DmndAloctnFileProcesRpt&type=WD";
	else if(action=='213b')
		URL="/Nregs/FrontServlet?requestType=DemandRH&actionVal=DmndAloctnFileProcesRpt&type=WA";
	else if(action=='213c')
		URL="/Nregs/FrontServlet?requestType=DemandRH&actionVal=DmndAloctnFileProcesRpt&type=RS";
	else if(action=='213d')
		URL="/Nregs/FrontServlet?requestType=DemandRH&actionVal=DmndAloctnFileProcesRpt&type=WZ";
	else if(action=='214a')
	{
		
		URL="/Nregs/FrontServlet?requestType=DemandRH&actionVal=UnEmpRpt&type=monthly";
		
	}

	else if(action=='214b')
	{
			URL="/Nregs/FrontServlet?requestType=DemandRH&actionVal=UnemplymntStatusRpt";
		
	}
else if(action==220)
		URL="/Nregs/FrontServlet?requestType=NewReportsRH&actionVal=iptglance&year="+document.getElementById('year').value ;
	
	else if(action==221)
		URL="/Nregs/FrontServlet?requestType=HorticultureRH&actionVal=ipt&year="+document.getElementById('year').value ;
	else if(action==222)
		URL="/Nregs/FrontServlet?requestType=HorticultureRH&actionVal=payement&year="+document.getElementById('year').value ;
	else if(action==223)
		URL="/Nregs/FrontServlet?requestType=HorticultureRH&actionVal=plantmaterialdaily&year="+document.getElementById('year').value ;
	else if(action==224)
		URL="/Nregs/FrontServlet?requestType=HorticultureRH&actionVal=wardpayments&year="+document.getElementById('year').value ;
	else if(action==225)
		URL="/Nregs/FrontServlet?requestType=HorticultureRH&actionVal=waterpaymnts&year="+document.getElementById('year').value ;
	else if(action==226)
		URL="/Nregs/FrontServlet?requestType=HorticultureRH&actionVal=wateringpayments&year="+document.getElementById('year').value ;
	else if(action==227)
		URL="/Nregs/FrontServlet?requestType=HorticultureRH&actionVal=specieswisedtls&year="+document.getElementById('year').value;
	else if(action==228)
		URL="/Nregs/PdfPrintServlet?actionVal=auditsurvival";
	
	/*else if(action=='214a')
	{
		URL="/Nregs/FrontServlet?requestType=DemandRH&actionVal=DmndWeeklyRpt&type="+document.forms[0].stmtDate.value;
	}
	else if(action=='214b')
		URL="/Nregs/FrontServlet?requestType=DemandRH&actionVal=DmndAloctnWeeklyRpt";
	else if(action=='214c')
		URL="/Nregs/FrontServlet?requestType=DemandRH&actionVal=UnEmpRpt";
	else if(action=='214')
		URL="/Nregs/FrontServlet?requestType=DemandRH&actionVal=DefaultedFAs";*/
	
	/*if(DistrictID=="-1")
	{
		if(action==22)
			URL="/Nregs/FrontServlet?requestType=report_engRH&actionVal=commonselect&report=statereport&pagename=Employment";
		else if(action==23)
			URL="/Nregs/FrontServlet?requestType=report_engRH&actionVal=commonselect&report=statereport&financialType=CumulativePhysical&pagename=Physical";
		else if(action==241)
			URL= "//Nregs/FrontServlet?requestType=report_engRH&actionVal=commonselect&report=statereport&pagename=Financial";
		else if(action==242)
			URL="/Nregs/FrontServlet?requestType=report_engRH&actionVal=commonselect&report=statereport&pagename=NewFinancial&financialType=Muster";
	}
	else if(MandalID=="-1")
	{
		if(action==22)
			URL="/Nregs/FrontServlet?requestType=report_engRH&actionVal=commonselect&report=districtreport&pagename=Employment";
		else if(action==23)
			URL="/Nregs/FrontServlet?requestType=report_engRH&actionVal=commonselect&report=districtreport&financialType=CumulativePhysical&pagename=Physical";
		else if(action==241)
			URL= "/Nregs/FrontServlet?requestType=report_engRH&actionVal=commonselect&report=districtreport&pagename=Financial";
		else if(action==242)
			URL="/Nregs/FrontServlet?requestType=report_engRH&actionVal=commonselect&report=districtreport&pagename=NewFinancial&financialType=Muster";
	}
	else if(PancID=="-1")
	{
		if(action==22)
			URL="/Nregs/FrontServlet?requestType=report_engRH&actionVal=commonselect&report=mandalreport&pagename=Employment";
		else if(action==23)
			URL="/Nregs/FrontServlet?requestType=report_engRH&actionVal=commonselect&report=mandalreport&financialType=CumulativePhysical&pagename=Physical";
		else if(action==241)
			URL= "/Nregs/FrontServlet?requestType=report_engRH&actionVal=commonselect&report=mandalreport&pagename=Financial";
		
	}*/
	
  	//alert("URL is :"+URL);


  	window.open(URL,"","directories=yes,location=yes,menubar=yes,status=yes,titlebar=yes,scrollbars=yes,resizable=yes");
}

function doMenu(item) 
{
 	obj=document.getElementById(item);
 	col=document.getElementById("x" + item);
 	if (obj.style.display=="none") {
  		obj.style.display="block";
  		col.innerHTML="-";
 	}
 	else {
  		obj.style.display="none";
  		col.innerHTML="+";
 	}
}

function doMenubody(item) 
{
 obj1=document.getElementById('main1');
 obj2=document.getElementById('main2');
 obj3=document.getElementById('main3');
 obj4=document.getElementById('main4');
 obj5=document.getElementById('main5');
 obj6=document.getElementById('main6');
 obj7=document.getElementById('main7');
 obj8=document.getElementById('main8');
 obj9=document.getElementById('main9');
 obj10=document.getElementById('main10');
 obj11=document.getElementById('main11');
 obj12=document.getElementById('main12');
 obj13=document.getElementById('main13');
 obj14=document.getElementById('main14');
 obj15=document.getElementById('main15');
 obj16=document.getElementById('main16');
 obj17=document.getElementById('main17');
 obj18=document.getElementById('main18');
 obj19=document.getElementById('main19');
 obj20=document.getElementById('main20');
 obj21=document.getElementById('main21');
 obj22=document.getElementById('main22');

 obj1.style.display=item;
 obj2.style.display=item;
 obj3.style.display=item;
 obj4.style.display=item;
 obj5.style.display=item;
 obj6.style.display=item;
 obj7.style.display=item;
 obj8.style.display=item;
 obj9.style.display=item;
 obj10.style.display=item;
 obj11.style.display=item;
 obj12.style.display=item;
 obj13.style.display=item;
 obj14.style.display=item;
 obj15.style.display=item;
 obj16.style.display=item;
 obj17.style.display=item;
 obj18.style.display=item;
 obj19.style.display=item;
 obj20.style.display=item;
 obj21.style.display=item;
 obj22.style.display=item;
}

function openDoc(docName)
{
  window.open('LoadDocument?docName=GovtDocs\\'+docName);
}
function batch3()
{
	var id=document.getElementById('dmd4').value;
if(id=="batch21")
	document.getElementById('stmtDate2').value='13/03/2014';
else
	document.getElementById('stmtDate2').value='10/03/2014';
}
function batch4()
{
	var id=document.getElementById('dmd5').value;
if(id=="batch22")
	document.getElementById('dmaDate4').value='13/03/2014';
else
	document.getElementById('dmaDate4').value='10/03/2014';
}
function batch2()
{
	var id=document.getElementById('dmd1').value;
if(id=="batch22")
	document.getElementById('stmtDate').value='13/03/2014';
else
	document.getElementById('stmtDate').value='10/03/2014';
}
function batch1()
{
	var id=document.getElementById('dmd2').value;
if(id=="batch22")
	document.getElementById('dmaDate').value='13/03/2014';
else
	document.getElementById('dmaDate').value='10/03/2014';
}
function batch5()
{
	var id=document.getElementById('dmd6').value;
if(id=="batch22")
	document.getElementById('dmaDate5').value='13/03/2014';
else
	document.getElementById('dmaDate5').value='10/03/2014';
}
function batch6()
{
	var id=document.getElementById('dmd8').value;
if(id=="batch22")
	document.getElementById('dmaDate8').value='13/03/2014';
else
	document.getElementById('dmaDate8').value='10/03/2014';
}
function batch7()
{
	var id=document.getElementById('dmd9').value;
if(id=="batch22")
	document.getElementById('dmaDate9').value='13/03/2014';
else
	document.getElementById('dmaDate9').value='10/03/2014';
}
function batch8()
{
	var id=document.getElementById('dmd10').value;
if(id=="batch22")
	document.getElementById('dmaDate10').value='13/03/2014';
else
	document.getElementById('dmaDate10').value='10/03/2014';
}
function batch9()
{
	var id=document.getElementById('dmd11').value;
if(id=="batch22")
	document.getElementById('dmaDate11').value='13/03/2014';
else
	document.getElementById('dmaDate11').value='10/03/2014';
}
function batch10()
{
	var id=document.getElementById('dmd12').value;
if(id=="batch22")
	document.getElementById('stmtDate5').value='13/03/2014';
else
	document.getElementById('stmtDate5').value='10/03/2014';
}
