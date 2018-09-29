<%@ page language="java" import="com.tcs.sadarem.util.PasswordEncriptDecrypt" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>:: SADAREM :: Part-A Decision</title><title>:: SADAREM :: Part-A Form </title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/commonstyle.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/dataTables.bootstrap.css"/> 
 
<style type="text/css">
    
 /* Process Layer Started */
	#processlayer
	 {
		width: 300px;
		height: 50px;
		background: #ECF1EF;
		border: red dotted 5px;
		text-align: center;
		position: fixed;
		margin-right: -150px;
		margin-top: -75px;
		right: 50%;
		top: 50%;
		z-index: 99999;
		display: none;
	}
	
	#blocklayer 
	{
		position: absolute;
		left: 0;
		top: 0;
		background: #ECF1EF;
	}

/* 	 Process Layer Ended  */
  
</style> 
</head>
<body>
<div id="processlayer" >
	<font color="blue" size="2">Processing Please Wait...</font><br/>
	<img src="<%=request.getContextPath()%>/images/processing.gif" border="0"/>
</div>

<div id="blocklayer">
</div>  

<%
try
{ 

	ArrayList partAProofList = (ArrayList)request.getAttribute("partAProofList");
	
	String alert_msg = CommonUtility.checkNullObj(request.getAttribute("alert_msg"));
	String alert_css = CommonUtility.checkNullObj(request.getAttribute("alert_css"));
	
	if(alert_css.equals(""))
	{
		alert_css = "alert-info";
	}
 
	if(alert_msg!=null && alert_msg.trim().length()!=0 &&  !alert_msg.equals("") )
		{
		%> 
			<div class="alert <%=alert_css%>">
			  <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
			    <b><%=alert_msg %></b>
			</div>
		<%
		} 
%>

	<div class="row" id="PartA_Details_View_DIVID">
		<%@include file="/PartAForm/PartAFormFilledDataView.jsp" %>
	</div>
	
	<div class="row" id="PartA_Details_Edit_DIVID" style="display:none">
	
		<%@include file="/PartAForm/PartARequestFormEdit.jsp" %>
	</div>
	
	
	
	  <div class="modal fade" id="partASupportDocModal" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static" style="display: none;">
      <div class="modal-dialog" style=" margin-left: auto; margin-right: auto; width:70% !important">
        <div class="modal-content">
          <div class="modal-header" style="background-color:#dff0d8;">
            <button type="button" id="xSupportDocCloseBut" class="close" data-dismiss="modal" aria-label="Close" style="color:red;" title="Close"><span aria-hidden="true" class="glyphicon glyphicon-remove-circle"></span></button>
            <h4 class="modal-title">Part-A Details of Request Id: <b><%=CommonUtility.checkNullObj(session.getAttribute("ses_request_id")) %></b> Supporting Documents</h4>
          </div>
          <div class="modal-body" style="background-color:  #fcf8e3;">  
          
          				<%
          				if(partAProofList!=null && partAProofList.size()>0)
          				{ 
          					ArrayList tempList = new ArrayList();
          					String docPath = "",docType ="";
          					
          					for(int i=0;i<partAProofList.size();i++)
          					{
          						tempList = (ArrayList)partAProofList.get(i);
          						
          						//System.out.println("PasswordEncriptDecrypt.encrypt(CommonUtility.checkNullObj(tempList.get(2))) : "+(PasswordEncriptDecrypt.encrypt(CommonUtility.checkNullObj(tempList.get(2)))).replace("+", "%2B"));
          						
          						docPath =(PasswordEncriptDecrypt.encrypt(CommonUtility.checkNullObj(tempList.get(2)))).replace("+", "%2B");
          						docType =(PasswordEncriptDecrypt.encrypt(CommonUtility.checkNullObj(tempList.get(3)))).replace("+", "%2B");
          						
          						
          				%>
          				   <div class="progress"  style="margin-top: 10px;">
								  <div class="progress-bar progress-bar-danger" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width:100%; font-size: 18px;">
								   <b>Document Type : <%=tempList.get(0) %> , Document No : <%=tempList.get(1)%> </b>
								  </div>
							</div>
					   		 <div class="form-inline">
							    <div class="input-group" style="text-align: center;">
							    	<%
							    	if(CommonUtility.checkNullObj(tempList.get(3)).equalsIgnoreCase("pdf"))
							    	{ 
							    	%>
			            	  			<object data="<%=request.getContextPath()%>/dispimg.do?action=showpartaproofdetailsbyids&proof_path=<%=docPath%>&proof_type=<%=docType%>&randomid=<%=Math.random()%>" type="application/pdf" width="800" height="400" style="padding-left: 10%;"></object>
			            	  		<%
							    	}
							    	else
							    	{
							    	%>
			            	  			<img src="<%=request.getContextPath()%>/dispimg.do?action=showpartaproofdetailsbyids&proof_path=<%=docPath%>&proof_type=<%=docType%>&randomid=<%=Math.random()%>" onerror="this.src='<%=request.getContextPath()%>/images/NA.jpg'" border="0"  style="padding-left: 10%;max-width: 800px"/>
							    	<%
							    	}
			            	  		%>
			            	  	</div>
			            	 </div>
			            	 <%
          					}  
          				}
			           %>
          </div> 
        </div>
      </div>
    </div> 

<%
}
catch(Exception e)
{
	System.out.println("Exception in Page : "+e.getMessage());
	e.printStackTrace();
}
%>


	<form id="RequestFormId" name="RequestFormId" method="post">
		<input type="hidden" id="mode" name="mode" value="loadrequestdtlsforaction">
		<input type="hidden" id="reqFormId" name="reqFormId" value="<%=CommonUtility.checkNullObj(session.getAttribute("sesFormId"))%>">  
		<input type="hidden" id="request_id" name="request_id" value="<%=CommonUtility.checkNullObj(session.getAttribute("ses_request_id"))%>">
	</form>
 
 	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/bootstrap.min.js"></script> 
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/dataTables.bootstrap.js"></script>   
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery.datetimepicker.js"></script>
	     
	<script type="text/javascript"src="<%=request.getContextPath()%>/scripts/ProofValidation.js"></script>
	<script type="text/javascript"src="<%=request.getContextPath()%>/scripts/script_telugu_converter.js"></script> 
	<script type="JavaScript" src="<%=request.getContextPath()%>/DisabilityUITG/js/PartADetailsExistingPensionNumber.js"></script>  

<script type="text/javascript"> 
 
	
//AJax call

	function postRequest(strURL,textID) 
	{
		var xmlHttp;
	    if (window.XMLHttpRequest)  // Mozilla, Safari, ...
		 {
	          xmlHttp = new XMLHttpRequest();
	    }
	    else if (window.ActiveXObject)  // IE
		{
	          xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
	    }
	    xmlHttp.open('POST', strURL, true);
	    xmlHttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	    xmlHttp.onreadystatechange = function()
	     {
		    
	    	if (xmlHttp.readyState == 1) 
	        {
	    		updatepage("<center><img src='<%=request.getContextPath()%>/images/loading.gif' width='15' height='15'></center>",textID);
	        }
		    else if(xmlHttp.readyState == 2)
		    {
		    	updatepage('Wait..',textID);
		    }
		    else if(xmlHttp.readyState == 3)
		    {
		    	updatepage('Please Wait..',textID);
		    }
		    else if (xmlHttp.readyState == 4) 
	        {
		    	var errorCode = xmlHttp.getResponseHeader('errorCode');
		    
		    	if(errorCode=="" || errorCode==null ) // Check null to for mozilla
		    	{
	        		updatepage(xmlHttp.responseText,textID);
	        		
		    	}
		    	else
		    	{
		    		$('#errorMsg').html(errorCode).fadeIn(100);
		    		location.replace(window.location);
		    	}
	    	}
		};
		xmlHttp.send(strURL);
	}
	
	function updatepage(msg,id)
	{		
		if(msg!="" && msg!="null")
		{
			document.getElementById(id).innerHTML=msg;
		}
	}


	function rationType()
	{
		try
		{
			//alert("RATION TYPE");
		    var rationCardNumber = document.getElementById("rationcard_number");
		    var rationcardType 	 = document.getElementById("rationcard_type"); 
		    
		    var cardnumber = null;
		    var cardnumberthree = null;
		    if(rationCardNumber != null)
		    {
		        var rationCardNumberValue =rationCardNumber.value;
		        if(rationCardNumberValue != "" && rationCardNumberValue.toString().length<=3)
		        {
		            cardnumber = rationCardNumberValue.toString().toUpperCase();
		            rationCardNumber.value=cardnumber;
		            rationcardType.selectedIndex = "";
		            rationcardType.disabled = true;
		        }
		        if(rationCardNumberValue != "" && (rationCardNumberValue.toString().length == 3 || rationCardNumberValue.toString().length >= 3))
		        {
		            cardnumberthree = rationCardNumberValue.toString().toUpperCase().substring(0,3);
		            rationcardType.disabled = false;
		            if(cardnumber != null || cardnumberthree != null)
		            {
		                if(cardnumber == "WAP" || cardnumberthree == "WAP")
		                {
		                    rationcardType.selectedIndex = 1;
		                }
		                else if(cardnumber == "PAP" || cardnumberthree == "PAP")
		                {
		                    rationcardType.selectedIndex = 2;
		                }
		                else if(cardnumber == "AAY" || cardnumberthree == "AAY")
		                {
		                    rationcardType.selectedIndex = 3;
		                }
		                else if(cardnumber == "AAP" || cardnumberthree == "AAP")
		                {
		                    rationcardType.selectedIndex = 4;
		                }
		                else if(cardnumber == "YAP" || cardnumberthree == "YAP")
		                {
		                    rationcardType.selectedIndex = 5;
		                }
		                else if(cardnumber == "TAP" || cardnumberthree == "TAP")
		                {
		                    rationcardType.selectedIndex = 6;
		                }
		                else if(cardnumber == "RAP" || cardnumberthree == "RAP")
		                {
		                    rationcardType.selectedIndex = 7;
		                }
		                else if(cardnumber == "WAD" || cardnumberthree == "WAD")
		                {
		                    rationcardType.selectedIndex = 8;
		                }
		            }
		        }else if(rationCardNumberValue == "")
		        {
		        	rationcardType.selectedIndex = "";
		        	rationcardType.disabled = true;
		        }
		    }
		}
		catch(e)
		{
			alert(e);
		}
	  
	} 

	function fun_reformatDateString(s)
	{
		  var b = s.split(/\D/);
		  return b.reverse().join('');
	}


	function submitValildations()
	{
		var rationStatus = 0;

		$(".ClassValidateContactNo").trigger("blur");
		$("#pin_code").trigger("blur" );
		$("#house_number").trigger("blur");
		$("#rationcard_number").trigger("blur");
		$("#rationcard_slno").trigger("blur");
		$("#pensioncard_no").trigger("blur"); 
		
		
		
		if($("#rationcard_number").val()!="")
			{
				rationStatus=rationStatus+1;
			}
			
			if($("#rationcard_type").val()!="" && $("#rationcard_type").val()!="-1" )
			{
				rationStatus=rationStatus+1;
			}
				
			if($("#rationcard_slno").val()!="")
			{
				rationStatus=rationStatus+1;
			}
			 
		 
			  
			var dobstr 	 = fun_reformatDateString($("#date_of_birth").val());
			var formdate = fun_reformatDateString($("#form_fill_date").val());

		 
		 if($("#form_fill_date").val()!="" && $("#date_of_birth").val()!="" && (parseInt(dobstr)>parseInt(formdate))==true)
		 {
			  alert("Date of Assessment should be after date of birth");
			  $("#form_fill_date").val("");
			  return false;
		 }
			
				
				
		
		if($('#reference_form_number').val()=="")
		{
		    alert("Please enter Form No.");
		    $('#reference_form_number').focus();
		    return false;
		}
		else if($("#form_fill_date").val()=="")
		{
			alert("Please select Date of Assessment");
			$("#form_fill_date").focus();
			return false;
		}
		else if($('#mandals').val()==-1)
		{
		    alert("Please select Mandal");
		    $('#mandals').focus();
		    return false;
		}	          
		else if($('#panchayats').val()==-1)
		{
		    alert("Please select Panchayat");
		    $('#panchayats').focus();
		    return false;
		}	          
		else if($('#villages').val()==-1)
		{
		    alert("Please select Village");
		    $('#villages').focus();
		    return false;
		}
		else if($('#habitation').val()==-1)
		{
		    alert("Please select Habitation");
		    $('#habitation').focus();
		    return false;
		}
		else if($("#house_number").val()=="")
		{
			alert("Please enter valid House No.");
			$("#house_number").focus();
			return false;
		}
		else if($("#pin_code").val()=="")
		{
			alert("Please enter valid Pin Code");
			$("#pin_code").focus();
			return false;
		}
		else if($("#phone_no").val()=="")
		{
			alert("Please enter valid Contact No.");
			$("#phone_no").focus();
			return false;
		}
		/* else if($("#surname").val()=="")
		{
			alert("Please enter Sur-Name");
			$("#surname").focus();
			return false;
		} */
		else if($("#first_name").val()=="")
		{
			alert("Please enter Person Name");
			$("#first_name").focus();
			return false;
		}
		else if($("#full_name_eng").val()=="")
		{
			alert("Please enter Name that should be shown on card");
			$("#full_name_eng").focus();
			return false;
		}
		else if($("#date_of_birth").val()=="")
		{
			alert("Please enter Date of Birth");
			$("#date_of_birth").focus();
			return false;
		}
		else if($("#gender").val()=="-1")
		{
			alert("Please select Gender");
			$("#gender").focus();
			return false;
		}		
		else if($("#grelation").val()=="-1")
		{
			alert("Please select Type of Relation");
			$("#grelation").focus();
			return false;
		}
		else if($("#relation_name").val()=="")
		{
			alert("Please enter Relation Name");
			$("#relation_name").focus();
			return false;
		}
		else if($("#relation_name_eng").val()=="")
		{
			alert("Please enter Father/Guardian Name");
			$("#relation_name_eng").focus();
			return false;
		}
		else if($("#education_id").val()=="-1")
		{
			alert("Please select Education");
			$("#education_id").focus();
			return false;
		}
		else if($("#employment_id").val()=="-1")
		{
			alert("Please select Employment");
			$("#employment_id").focus();
			return false;
		}
		else if($("#marital_status_id").val()=="-1")
		{
			alert("Please select Marital Status");
			$("#marital_status_id").focus();
			return false;
		}
		else if($("#religion_id").val()=="-1")
		{
			alert("Please select Religion");
			$("#religion_id").focus();
			return false;
		}
		else if($("#caste_id").val()=="-1")
		{
			alert("Please select Caste");
			$("#caste_id").focus();
			return false;
		}
		else if(rationStatus>0 && rationStatus<3)
		{ 
			alert("Please ration card details");
			return false;
		}
		else if($("#mole_one").val()=="" || ($("#mole_one").val()).length < 15)
		{
			alert("Please enter Identification Marks-1.(Minimum 15 characters)");
			$("#mole_one").focus();
			return false;
		} 
		/* else if($("#mole_two").val()=="" || ($("#mole_two").val()).length < 15)
		{
			alert("Please enter Identification Marks-2.(Minimum 15 characters)");
			$("#mole_two").focus();
			return false;
		}  */
		else if($("#mole_one").val()==$("#mole_two").val())
		{
			alert("Both mole should not be same.Please change the details");
			$("#mole_two").val("");
			$("#mole_two").focus();
			return false;
		} 
		else if($("#pension_type").val()!="-1" && $("#pensioncard_no").val()=="")
		{
			    alert("Please select Pension Number");
				$("#pensioncard_no").focus();
				return false;					
		}
		else if($("#parents_marriage").val()=="-1")
		{
			alert("Please select Consanguineous Marriage of Parents");
			$("#parents_marriage").focus();
			return false;
		} 
		else
		{
			return true;
		}
		
	}


$(document).ready( function()
{
	
	
	$('#form_fill_date').datetimepicker(
			{
				dayOfWeekStart : 1,
				lang:'en',
				formatDate:'d/m/Y',
				format:'d/m/Y',
				theme:'',
				step:05,
				timepicker:false,
				minDate:'<%=CommonUtility.getDateAddOrSubDays(-30, "dd/MM/yyyy")%>',
				maxDate:'<%=CommonUtility.getDateTime("dd/MM/yyyy")%>', 
				scrollMonth : false,
	 			scrollInput : false
			});
	
	$('#date_of_birth').datetimepicker(
			{
				lang:'en',
				formatDate:'d/m/Y',
				format:'d/m/Y',
				theme:'',
				step:05,
				timepicker:false,
				minDate:'<%=CommonUtility.getDateAddOrSubDays(-32850, "dd/MM/yyyy")%>',
				maxDate:'<%=CommonUtility.getDateTime("dd/MM/yyyy")%>', 
				scrollMonth : false,
	 			scrollInput : false
			});		
 
		
	// Person Full Name to telugu
	
	$('body').on('focusin focusout change blur keyup','#full_name_eng',function()
    		{	 
				print_many_words('full_name_eng','disp_personname_telugu','personname_telugu');
    		});
	
	// Relation Name to telugu
	$('body').on('focusin focusout change blur keyup','#relation_name_eng',function()
    		{	 
				print_many_words('relation_name_eng','disp_relation_name','relationname_telugu');
    		});
	
	 
		$("#pension_type").change(function()
		{
			if($(this).val()=="-1")
				{
					$("#pensioncard_no").prop('readonly', true);
					$("#pensioncard_no").val("");
				} 
				else
				{
					$("#pensioncard_no").prop('readonly', false);
				}
		});
	
 

	 /*
	  * Contact No. Validation  
	  */
	  $(".ClassValidateContactNo").blur(function()
	    {	 
			var NumberRegex = /[6789]\d{9}$/;
			var myno = $(this).val();
		 //alert(myno);
				if((myno.length < 10) || NumberRegex.test(myno)==false || myno=="9999999999" || myno=="7777777777" || myno=="8888888888"|| myno=="6666666666")
				{  
					$(this).val("");
					//alert("Please enter valid contact no.");
					return false;
				}
				else
				{
					return true;
				}  
		});
	
	
	     /* 
		  * Pin Code Validation  
		  */ 
		  $('body').on('blur','#pin_code',function()
		    {	 
				var NumberRegex = /[1-9]\d{5}$/;
				var pincode = $(this).val();
			 //alert(myno);
					if((pincode.length < 6) || NumberRegex.test(pincode)==false)
					{  
						$(this).val("");
						//alert("Please enter valid contact no.");
						return false;
					}
					else
					{
						return true;
					}  
			});

	  	  
		  
		  /* 
			  * House Number Validation  
			  */ 
			  $('body').on('blur','#house_number',function()
			    {	 
				  var houserNumberRegex =  /\S\w+[\/|-]?\w+$/;
					var houseno = $(this).val();
				 //alert(myno);
						if(houserNumberRegex.test(houseno)==false)
						{  
							$(this).val("");
							//alert("Please enter valid contact no.");
							return false;
						}
						else
						{
							return true;
						}  
				});
	  	  
		 

			  /* 
				  * Ration Card No. Validation
				  */ 
				  $('body').on('blur','#rationcard_number',function()
				    {	 
						var RationCardNoRegex = /[A-Z]{3}\d{7}([A-Z]|[0-9])\d{4}/;
						var rationCardNo = $(this).val();
					 //alert(myno);
							if(RationCardNoRegex.test(rationCardNo)==false)
							{  
								$(this).val("");
								//alert("Please enter valid contact no.");
								return false;
							}
							else
							{
								return true;
							}  
					});
  	  
		  
		  /* 
			  * Ration Card Serial No. Validation
			  */ 
			  $('body').on('blur','#rationcard_slno',function()
			    {	 
					var RationCardSLNoRegex = /[1-9][0-9]/;
					var rationCardSLNo = $(this).val();
				 //alert(myno);
						if(RationCardSLNoRegex.test(rationCardSLNo)==false)
						{  
							$(this).val("");
							//alert("Please enter valid contact no.");
							return false;
						}
						else
						{
							return true;
						}  
				});

			  
			  /* 
				  * Pension No. Validation
				  */ 
				  $('body').on('blur','#pensioncard_no',function()
				    {	 
						var PensionNoRegex = /[1-9]\d{6}/;
						var pensionCardNo = $(this).val();
					 //alert(myno);
							if(PensionNoRegex.test(pensionCardNo)==false)
							{  
								$(this).val("");
								//alert("Please enter valid contact no.");
								return false;
							}
							else
							{
								return true;
							}  
					});
				  	  
				  	  
				  	  
	
			$('#mandals').change(function()
			{ 
				$("#panchayatTDID").html("<%=ComboUtil.createStrComboBoxAuto("panchayats",new ArrayList(),"","form-control","onchange='loadvillages()'",true,true,"") %>");
				$("#villageTDID").html("<%=ComboUtil.createStrComboBoxAuto("villages",new ArrayList(),"","form-control","onchange='loadhabitation()'",true,true,"")%>");
				$("#habitTDID").html("<%=ComboUtil.createStrComboBoxAuto("habitation",new ArrayList(),"","form-control","",true,true,"")%>");
		       
				postRequest("<%=request.getContextPath()%>/ajax.do?action=OpenIssueloadpanchayatopenrep&manId="+ $('#mandals').val()+"&districtId=<%=CommonUtility.checkNullObj(session.getAttribute("districtId"))%>&randomid="+Math.random(),"panchayatTDID");
		      
			});
	
			$('#gender').change(function()
			{			   {		
				  postRequest("<%=request.getContextPath()%>/ajax.do?action=loadRelationDetails&gender="+ $('#gender').val()+"&randomid="+Math.random(),"RelId");
				      
			}});
				
			
			
					// Back Button Action 
					$("#BackBtnID").click(function()
					{
						location.replace("<%=request.getContextPath()%>/withoutproofparta.do?mode=loadpendingrequestforpartadtls&randomid="+Math.random());
					});
				 
			
					// Edit Button Action
					
					$("#EditPartABtnID").click(function()
					{  
						$("#PartA_Details_View_DIVID").hide();
						$("#PartA_Details_Edit_DIVID").show();
					});
					
				
				// Cancel Button Action
					
				 $('#PartACancelBtnID').click(function()
				 {	  
					 /*Screen Locking Started */
						$('#blocklayer').css({	"display": "block", opacity: 0.7,"z-index":"100000", "width":$(document).width(),"height":$(document).height()});
					    $('#processlayer').css({"display": "block","z-index":"110000"});
					/*Screen Locking Ended */
							 
		   		 	document.RequestFormId.target="_self";
				 	document.RequestFormId.action="<%=request.getContextPath()%>/withoutproofparta.do?randomid="+Math.random();
				 	document.RequestFormId.submit(); 
				});
			
			
			
				// Update Button Action
				
				$('#PartAUpadateBtnID').click(function()
				{	 
					if(!submitValildations())
					{
					    return false;
					}
				    else if(confirm("Are you sure you want to update the request?"))
					{
				    	/*Screen Locking Started */
					 		$('#blocklayer').css({	"display": "block", opacity: 0.7,"z-index":"100000", "width":$(document).width(),"height":$(document).height()});
				     		$('#processlayer').css({"display": "block","z-index":"110000"}); 
						/*Screen Locking Ended */
					
						document.part_a_edit_form.target="_self";
						document.part_a_edit_form.action="<%=request.getContextPath()%>/withoutproofparta.do?mode=logupdaterequestdtls&randomid="+Math.random();
						document.part_a_edit_form.submit();
						 
					}  
				});
				
				// Approval Button Action
				$('#ApprovePartABtnID').click(function()
						{	 
							if(!submitValildations())
							{
								alert("Please click on Edit button to correct the details");
							    return false;
							}
							else if($("#status_remarks").val()=="" || ($("#status_remarks").val()).length<15)
							{
								alert("Please enter remarks atleast 15 characters");
								$("#status_remarks").focus();
								return false;
							}
							else if($("#term_status").is(':checked')==false)
							{
								alert("Please select the check box to accept the terms");
								$("#term_status").focus();
								return false;
							}
						    else if(confirm("Do you want to Approve the request?"))
							{ 
						    	/*Screen Locking Started */
							 	$('#blocklayer').css({	"display": "block", opacity: 0.7,"z-index":"100000", "width":$(document).width(),"height":$(document).height()});
						     	$('#processlayer').css({"display": "block","z-index":"110000"}); 
							/*Screen Locking Ended */
						
								document.parta_decision_form.target="_self";
						    	document.parta_decision_form.action="<%=request.getContextPath()%>/withoutproofparta.do?mode=logrequestapproval&randomid="+Math.random();
						    	document.parta_decision_form.submit(); 
							}
						    else
						    {
						    	alert("Request cancelled by user");
						    }
						});
				
				
				// Reject Button Action
				
				$('#RejectPartABtnID').click(function()
						{	 
					 
					
							if($("#status_remarks").val()=="" || ($("#status_remarks").val()).length<15)
							{
								alert("Please enter remarks atleast 15 characters");
								$("#status_remarks").focus();
								return false;
							}
							else if($("#term_status").is(':checked')==false)
							{
								alert("Please select the check box to accept the terms");
								$("#term_status").focus();
								return false;
							}
							else if(confirm("Do you want to Reject the request?"))
							{
								/*Screen Locking Started */
								 	$('#blocklayer').css({	"display": "block", opacity: 0.7,"z-index":"100000", "width":$(document).width(),"height":$(document).height()});
							     	$('#processlayer').css({"display": "block","z-index":"110000"}); 
								/*Screen Locking Ended */
							
								document.parta_decision_form.target="_self";
						    	document.parta_decision_form.action="<%=request.getContextPath()%>/withoutproofparta.do?mode=logrequestrejection&randomid="+Math.random();
						    	document.parta_decision_form.submit(); 
							}
						    else
						    {
						    	alert("Request cancelled by user");
						    }
						});
	
	
	});
		function loadvillages()
		{		     
			$("#villageTDID").html("<%=ComboUtil.createStrComboBoxAuto("villages",new ArrayList(),"","form-control","onchange='loadhabitation()'",true,true,"")%>");
			$("#habitTDID").html("<%=ComboUtil.createStrComboBoxAuto("habitation",new ArrayList(),"","form-control","",true,true,"")%>");
		    postRequest("<%=request.getContextPath()%>/ajax.do?action=loadVilagePanchayatChangerep&manId="+ $('#mandals').val()+"&panchId="+ $('#panchayats').val()+"&districtId= <%=CommonUtility.checkNullObj(session.getAttribute("districtId"))%>&randomid="+Math.random(),"villageTDID");
		}
		
		function loadhabitation()
		{ 
			$("#habitTDID").html("<%=ComboUtil.createStrComboBoxAuto("habitation",new ArrayList(),"","form-control","",true,true,"")%>");
		    postRequest("<%=request.getContextPath()%>/ajax.do?action=OpenIssueloadhabitationrep&manId="+ $('#mandals').val()+"&villageId="+ $('#villages').val()+"&districtId=<%=CommonUtility.checkNullObj(session.getAttribute("districtId"))%>&randomid="+Math.random(),"habitTDID");
		}
 
</script>


</body>
</html>