<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import ="com.tcs.sadarem.util.ComboUtil,com.tcs.sadarem.util.CommonUtility"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SADAREM :: Part-B Deletion</title>
<link href="<%=request.getContextPath()%>/css/commonstyle.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css"/>
    <link href="<%=request.getContextPath()%>/css/colorbox.css" type="text/css" rel="stylesheet"/> 
    <script type="text/javascript" src="<%=request.getContextPath()%>/scripts/bootstrap.min.js"></script> 
    <script type="text/javascript" src="<%=request.getContextPath()%>/scripts/Common.js"></script>


<% 
String result = CommonUtility.checkNullObject((String)request.getAttribute("finalresult"));
%>
<script>
$(document).ready(function()
		{ 
	 
	$('#submit').click(function() 
			{ 
	 if($("#sadaremid").val()=="" )
		{
			alert("Please provide SADAREM ID");
			$("#sadaremid").focus();
			
			 event.preventDefault();
			event.stopPropagation();
			return false;
		}

	 if(($("#sadaremid").val()!="" && ($("#sadaremid").val()).length<17))
		{
			alert("Please provide Valid SADAREM ID");
			$("#sadaremid").focus();
			
			 event.preventDefault();
			event.stopPropagation();
			return false;
		}
	 if($("#message").val()=="" )
		{
			alert("Please provide Remarks");
			$("#message").focus();
			
			 event.preventDefault();
			event.stopPropagation();
			return false;
		}
		else if($("#sadaremid").val().length!=0)
		{
			/*Screen Locking Started */
			$('#blocklayer').css({	"display": "block", opacity: 0.7,"z-index":"100000", "width":$(document).width(),"height":$(document).height()});
		    $('#processlayer').css({"display": "block","z-index":"110000"});
		/*Screen Locking Ended */
			
			document.searchformforid.target="_self";
			document.searchformforid.action="<%=request.getContextPath()%>/Partbdeletion.do?randomid="+Math.random();
			document.searchformforid.submit();
			
		}
			});
		});
</script>

  
<style type="text/css">
    #wrapper {
        width:450px;
        margin:0 auto;
        font-family:Verdana, sans-serif;
    }
    legend {
        color:#0481b1;
        font-size:16px;
        padding:0 10px;
        background:#fff;
        -moz-border-radius:4px;
        box-shadow: 0 1px 5px rgba(4, 129, 177, 0.5);
        padding:5px 10px;
        text-transform:uppercase;
        font-family:Helvetica, sans-serif;
        font-weight:bold;
    }
    fieldset {
        border-radius:4px;
        background: #fff; 
        background: -moz-linear-gradient(#fff, #f9fdff);
        background: -o-linear-gradient(#fff, #f9fdff);
        background: -webkit-gradient(linear, 0% 0%, 0% 100%, from(#fff), to(#f9fdff)); /
        background: -webkit-linear-gradient(#fff, #f9fdff);
        padding:20px;
        border-color:rgba(4, 129, 177, 0.4);
    }
    input,
    textarea {
        color: #373737;
        background: #fff;
        border: 1px solid #CCCCCC;
        color: #aaa;
        font-size: 14px;
        line-height: 1.2em;
        margin-bottom:15px;

        -moz-border-radius:4px;
        -webkit-border-radius:4px;
        border-radius:4px;
        box-shadow: 0 1px 2px rgba(0, 0, 0, 0.1) inset, 0 1px 0 rgba(255, 255, 255, 0.2);
    }
    input[type="text"],
    input[type="password"]{
        padding: 8px 6px;
        height: 22px;
        width:280px;
    }
    input[type="text"]:focus,
    input[type="password"]:focus {
        background:#f5fcfe;
        text-indent: 0;
        z-index: 1;
        color: #373737;
        -webkit-transition-duration: 400ms;
        -webkit-transition-property: width, background;
        -webkit-transition-timing-function: ease;
        -moz-transition-duration: 400ms;
        -moz-transition-property: width, background;
        -moz-transition-timing-function: ease;
        -o-transition-duration: 400ms;
        -o-transition-property: width, background;
        -o-transition-timing-function: ease;
        width: 380px;
        
        border-color:#ccc;
        box-shadow:0 0 5px rgba(4, 129, 177, 0.5);
        opacity:0.6;
    }
    input[type="submit"]{
        background: #222;
        border: none;
        text-shadow: 0 -1px 0 rgba(0,0,0,0.3);
        text-transform:uppercase;
        color: #eee;
        cursor: pointer;
        font-size: 15px;
        margin: 5px 0;
        padding: 5px 22px;
        -moz-border-radius: 4px;
        border-radius: 4px;
        -webkit-border-radius:4px;
        -webkit-box-shadow: 0px 1px 2px rgba(0,0,0,0.3);
        -moz-box-shadow: 0px 1px 2px rgba(0,0,0,0.3);
        box-shadow: 0px 1px 2px rgba(0,0,0,0.3);
    }
    textarea {
        padding:3px;
        width:96%;
        height:100px;
    }
    textarea:focus {
        background:#ebf8fd;
        text-indent: 0;
        z-index: 1;
        color: #373737;
        opacity:0.6;
        box-shadow:0 0 5px rgba(4, 129, 177, 0.5);
        border-color:#ccc;
    }
    .small {
        line-height:14px;
        font-size:12px;
        color:#999898;
        margin-bottom:3px;
    }
</style><style type="text/css"></style></head>

<body>
    <div id="wrapper">
        <form action="" method="post">
        <input type="hidden" name="mode" value="updaterecord" >
            <fieldset style="border:1px solid #999;border-radius:10px;box-shadow:0 0 10px #999;">
                <legend >Certificate Deletion</legend>
                <div>
                    <input type="text"  id="sadaremid" name="sadaremid" placeholder="SADAREM ID" onkeypress="return NumbersOnly(event);" onBlur="this.value = SpaceReplace(this.value);" maxlength=17>
                
            
                <div class="form-group">
        <label class=" control-label"><font color='#0481b1'><b>Is Aadhar should also delete?</b></font></label>
        <div class="col-sm-8">
            <label class="radio-inline"> <input type="radio" name="aadharflag" id="AadharYes" value="Y" checked> Yes </label>
            <label class="radio-inline"> <input type="radio" name="aadharflag" id="AadharNo" value="N"> No </label>
        </div>
    </div>
                
                <div>
                    <div class="small"></div>
                    <textarea name="message" id="message" placeholder="Reason" maxlength=300></textarea>	
                </div>    
                <input type="submit" id="submit" name="submit" value="Submit">
                <span><font color='green'><%=result %> </font></span>
            </fieldset>    
        </form>
    </div>


</body></html>