package com.tcs.sadarem.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
 
public class CommonValidators
{
	
	private static Pattern pattern;
	private static Matcher matcher;
 	
	public static boolean emailValidator(String emailID)
	{
		boolean result = false;
		try
		{
			String EMAIL_PATTERN =  "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
			
			pattern = Pattern.compile(EMAIL_PATTERN);
			matcher = pattern.matcher(emailID);
		
			result =matcher.matches();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			result = false;
		}
		
		return result;
	}
	
	
			
 public static boolean NumberValidator(String number)
	{
		boolean result = false;
		try
		{
			String NUMBER_PATTERN =  "[\\-]?\\d+(\\.\\d+)?";
			
			pattern = Pattern.compile(NUMBER_PATTERN);
			matcher = pattern.matcher(number);
		
			result =matcher.matches();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			result = false;
		}
		 
		return result;
	}
	
	
	public static boolean mobileNumberValidator(String mobileNo)
	{
		boolean result = false;
		try
		{
			String EMAIL_PATTERN =  "([6-9])(\\d\\d\\d\\d\\d\\d\\d\\d\\d)";
			
			pattern = Pattern.compile(EMAIL_PATTERN);
			matcher = pattern.matcher(mobileNo);
		
			result =matcher.matches();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			result = false;
		}
		
		return result;
	}
	
	/**
	   * Validate date format with regular expression
	   * @param date date address for validation
	   * @return true valid date fromat, false invalid date format
	   */
	   public static boolean dateValidater(final String date)
	   {
		   boolean result = false;
		   try
		   {
		   String DATE_PATTERN =   "(0?[1-9]|[12][0-9]|3[01])[/|-](0?[1-9]|1[012])[/|-](\\d\\d\\d\\d)";
		   
		   pattern = Pattern.compile(DATE_PATTERN);		   
	       matcher = pattern.matcher(date);
	 
	     if(matcher.matches())
	     {
	 
		 matcher.reset();
	 
		 if(matcher.find())
		 {
	 
	         String day   = matcher.group(1);
		     String month = matcher.group(2);
		     int year 	  = Integer.parseInt(matcher.group(3));
	 
		     if (day.equals("31") &&  (month.equals("4") || month .equals("6") || month.equals("9") || month.equals("11") || month.equals("04") || month .equals("06") || month.equals("09"))) 
		     {
				return false; // only 1,3,5,7,8,10,12 has 31 days
		     } 
		     else if (month.equals("2") || month.equals("02"))
		     {
	                  //leap year
			  if(year % 4==0)
			  {
				  if(day.equals("30") || day.equals("31"))
				  {
					  result = false;
				  }
				  else
				  {
					  result = true;
				  }
			  }
			  else
			  {
			         if(day.equals("29")||day.equals("30")||day.equals("31"))
			         {
			        	 result = false;
			         }
			         else
			         {
			        	 result = true;
			         }
			  }
		      }
		     else
		      {				 
		    	 result = true;				 
		      }
		   }
		   else
		   {
			   result = false;
		   }		  
	     }
	     else
	     {
	    	 result = false;
	     }
		   }
		   catch(Exception e)
		   {
			   e.printStackTrace();
			   result = false;
		   }
		   
		   return result;
   }
	   /*
	    * Date & Time Format should be DD/MM/YYYY HH24:MI OR DD-MM-YYYY HH24:MI
	    */
	   
		public static boolean dateTimeValidator(String dateTime)
		{
			boolean result = false;
			
			String dataregfor = "(0?[1-9]|[12][0-9]|3[01])[/|-](0[1-9]|1[0-2])[/|-](\\d\\d\\d\\d)\\s([01]?[0-9]|2[0-3]):[0-5][0-9]";
			pattern = Pattern.compile(dataregfor);		   
		       matcher = pattern.matcher(dateTime);
			
			try
			{
				 if(matcher.matches())
			     {
			 
				 matcher.reset();
			 
				 if(matcher.find())
				 {
			 
			         String day   = matcher.group(1);
				     String month = matcher.group(2);
				     int year 	  = Integer.parseInt(matcher.group(3));
			 
				     if (day.equals("31") &&  (month.equals("4") || month .equals("6") || month.equals("9") || month.equals("11") || month.equals("04") || month .equals("06") || month.equals("09"))) 
				     {
						result=false; // only 1,3,5,7,8,10,12 has 31 days
				     } 
				     else if (month.equals("2") || month.equals("02"))
				     {
			                  //leap year
					  if(year % 4==0)
					  {
						  if(day.equals("30") || day.equals("31"))
						  {
							  result=false;
						  }
						  else
						  {
							  result=true;
						  }
					  }
					  else
					  {
					         if(day.equals("29")||day.equals("30")||day.equals("31"))
					         {
							  result=false;
					         }
					         else
					         {
							  result=true;
					         }
					  }
				      }
				     else
				      {				 
				    	 result=true;				 
				      }
				   }
				   else
				   {
			    	      result=false;
				   }		  
			     }
			     else
			     {
				  result=false;
			     }	
			}
			catch(Exception e)
			{
				e.printStackTrace();
				result=false;
			}
			
			return result;
		}
		
		
 public static boolean htmlStrValidation(String value) 
		    {
			 	boolean status = false;
		        if (value != null) 
		        {
		        	
		        	int statusCount = 0;
		        	
		            // Avoid null characters
		            value = value.replaceAll("", "");

		            // Avoid anything between script tags
		            pattern = Pattern.compile("(.*?)<script>(.*?)</script>(.*?)", Pattern.CASE_INSENSITIVE);
		            if((pattern.matcher(value)).find()) statusCount++;
		            

		            // Avoid href tags
		            pattern = Pattern.compile("(.*?)<a href[\r\n]*=[\r\n]*\\\'(.*?)>(.*?)</a>(.*?)", Pattern.CASE_INSENSITIVE);
		            if((pattern.matcher(value)).find()) statusCount++;
	            
				     // Avoid input tags
				     pattern = Pattern.compile("(.*?)<input (.*?)>(.*?)", Pattern.CASE_INSENSITIVE);
				     if((pattern.matcher(value)).find()) statusCount++;
				     
					// Avoid textarea tags
					pattern = Pattern.compile("(.*?)<text(.*?)>(.*?)</text(.*?)>(.*?)", Pattern.CASE_INSENSITIVE);
					if((pattern.matcher(value)).find()) statusCount++;
		            
		            // Avoid anything between script tags
		            pattern = Pattern.compile("(.*?)<a href (.*?)</a>(.*?)", Pattern.CASE_INSENSITIVE);
		            if((pattern.matcher(value)).find()) statusCount++;

		            // Avoid anything in a src='...' type of expression
		            pattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
		            if((pattern.matcher(value)).find()) statusCount++;

		            // Avoid avoids string between '><' tags
		            pattern = Pattern.compile("'>(.*?)<'", Pattern.CASE_INSENSITIVE);
		            if((pattern.matcher(value)).find()) statusCount++;


		            // Remove any lonesome <img ...> tag
		            pattern = Pattern.compile("(.*?)<img(.*?)>(.*?)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
		            if((pattern.matcher(value)).find()) statusCount++;
		            

		            // Remove any lonesome </script> tag
		            pattern = Pattern.compile("(.*?)</script>(.*?)", Pattern.CASE_INSENSITIVE);
		            if((pattern.matcher(value)).find()) statusCount++;

		            // Remove any lonesome <script ...> tag
		            pattern = Pattern.compile("(.*?)<script(.*?)>(.*?)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
		            if((pattern.matcher(value)).find()) statusCount++;

		            // Avoid eval(...) expressions
		            pattern = Pattern.compile("eval\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
		            if((pattern.matcher(value)).find()) statusCount++;

		            // Avoid expression(...) expressions
		            pattern = Pattern.compile("expression\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
		            if((pattern.matcher(value)).find()) statusCount++;

		            // Avoid javascript:... expressions
		            pattern = Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE);
		            if((pattern.matcher(value)).find()) statusCount++;

		            // Avoid vbscript:... expressions
		            pattern = Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE);
		            if((pattern.matcher(value)).find()) statusCount++;

		            // Avoid onload= expressions
		            pattern = Pattern.compile("(.*?)onload(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
		            if((pattern.matcher(value)).find()) statusCount++;
		            
		            // Avoid onchange= expressions
		            pattern = Pattern.compile("(.*?)onchange(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
		            if((pattern.matcher(value)).find()) statusCount++;

		            // Avoid onsubmit= expressions
		            pattern = Pattern.compile("(.*?)onsubmit(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
		            if((pattern.matcher(value)).find()) statusCount++;
		            
		            
		            // Avoid onreset= expressions
		            pattern = Pattern.compile("(.*?)onreset(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
		            if((pattern.matcher(value)).find()) statusCount++;
		            
		            
		            // Avoid onselect= expressions
		            pattern = Pattern.compile("(.*?)onselect[(](.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
		            if((pattern.matcher(value)).find()) statusCount++;
		            
		            
		            // Avoid onblur= expressions
		            pattern = Pattern.compile("(.*?)onblur[(](.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
		            if((pattern.matcher(value)).find()) statusCount++;

		            
		            // Avoid onfocus= expressions
		            pattern = Pattern.compile("(.*?)onfocus[(](.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
		            if((pattern.matcher(value)).find()) statusCount++;

		            
		            // Avoid onkeydown= expressions
		            pattern = Pattern.compile("onkeydown[(](.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
		            if((pattern.matcher(value)).find()) statusCount++;


		            // Avoid onkeypress= expressions
		            pattern = Pattern.compile("(.*?)onkeypress[(](.*?)=(.*?)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
		            if((pattern.matcher(value)).find()) statusCount++;

		            // Avoid onkeyup= expressions
		            pattern = Pattern.compile("(.*?)onkeyup[(](.*?)=(.*?)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
		            if((pattern.matcher(value)).find()) statusCount++;

		            // Avoid onclick= expressions
		            pattern = Pattern.compile("(.*?)onclick[(](.*?)=(.*?)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
		            if((pattern.matcher(value)).find()) statusCount++;

		            // Avoid ondblclick= expressions
		            pattern = Pattern.compile("(.*?)ondblclick[(](.*?)=(.*?)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
		            if((pattern.matcher(value)).find()) statusCount++;

		            // Avoid onmousedown= expressions
		            pattern = Pattern.compile("(.*?)onmousedown(.*?)=(.*?)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
		            if((pattern.matcher(value)).find()) statusCount++;

		            // Avoid onmousemove= expressions
		            pattern = Pattern.compile("onmousemove(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
		            if((pattern.matcher(value)).find()) statusCount++;

		            // Avoid onmouseout= expressions
		            pattern = Pattern.compile("(.*?)onmouseout(.*?)=(.*?)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
		            if((pattern.matcher(value)).find()) statusCount++;

		            // Avoid onmouseover= expressions
		            pattern = Pattern.compile("(.*?)onmouseover(.*?)=(.*?)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
		            if((pattern.matcher(value)).find()) statusCount++;

		            // Avoid onmouseup= expressions
		            pattern = Pattern.compile("(.*?)onmouseup(.*?)=(.*?)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
		            if((pattern.matcher(value)).find()) statusCount++;            

		            
		            // Avoid onerror= expressions
		            pattern = Pattern.compile("(.*?)onerror(.*?)=(.*?)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
		            if((pattern.matcher(value)).find()) statusCount++;
		            
		            // Avoid prompt= expressions
		            pattern = Pattern.compile("(.*?)prompt((.*?)).*", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
		            if((pattern.matcher(value)).find()) statusCount++;
		            
		            // Avoid alert expressions
		            pattern = Pattern.compile("(.*?)alert[(](.*?)[)].*", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
		            if((pattern.matcher(value)).find()) statusCount++;
		            
		            // Avoid avoids string between '(' tags
		            pattern = Pattern.compile("(.*?)\"(.*?)", Pattern.CASE_INSENSITIVE);
		            if((pattern.matcher(value)).find()) statusCount++;

		            // Avoid avoids string between ')' tags
		            pattern = Pattern.compile("(.*?)'(.*?)", Pattern.CASE_INSENSITIVE);
		            if((pattern.matcher(value)).find()) statusCount++;

		            // Avoid avoids string between '>tags
		            pattern = Pattern.compile("(.*?)'>(.*?)", Pattern.CASE_INSENSITIVE);
		            if((pattern.matcher(value)).find()) statusCount++;

		            // Avoid avoids string between '<"' tags
		            pattern = Pattern.compile("(.*?)<'(.*?)", Pattern.CASE_INSENSITIVE);
		            if((pattern.matcher(value)).find()) statusCount++;
		            
		            // Avoid avoids string between "> tags
		            pattern = Pattern.compile("(.*?)\">(.*?)", Pattern.CASE_INSENSITIVE);
		            if((pattern.matcher(value)).find()) statusCount++;
		            
		            // Avoid avoids string between <" tags
		            pattern = Pattern.compile("(.*?)<\"(.*?)", Pattern.CASE_INSENSITIVE);
		            if((pattern.matcher(value)).find()) statusCount++;
		            
		            // Avoid avoids string between '> tags
		            pattern = Pattern.compile("(.*?)'>(.*?)", Pattern.CASE_INSENSITIVE);
		            if((pattern.matcher(value)).find()) statusCount++;
		            
		            // Avoid avoids string between > tags
		            pattern = Pattern.compile("(.*?)>(.*?)", Pattern.CASE_INSENSITIVE); 
		            if((pattern.matcher(value)).find()) statusCount++;
		            
		            // Avoid avoids string between < tags
		            pattern = Pattern.compile("(.*?)<(.*?)", Pattern.CASE_INSENSITIVE);
		            if((pattern.matcher(value)).find()) statusCount++;
		            
		           		            
		            // Avoid avoids string between ' tags
		            pattern = Pattern.compile("'", Pattern.CASE_INSENSITIVE);
		            if((pattern.matcher(value)).find()) statusCount++;
		            
		           
		           if(statusCount==0)
		           {
		        	   status = true;
		           }
		           else
		           {
		        	   status = false;
		           }
		        }
		        else
		        {
		        	status = true;
		        }
		        
		        return status;
		    }
		  
	
		 public static String stripXSS(String value) 
		    {
		        if (value != null) 
		        {
		            // NOTE: It's highly recommended to use the ESAPI library and uncomment the following line to
		            // avoid encoded attacks.
		            // value = ESAPI.encoder().canonicalize(value);

		            // Avoid null characters
		            value = value.replaceAll("", "");

		            // Avoid anything between script tags
		            Pattern scriptPattern = Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE);
		            value = scriptPattern.matcher(value).replaceAll("");
		            

		            // Avoid href tags
		            scriptPattern = Pattern.compile("<a href[\r\n]*=[\r\n]*\\\'(.*?)>(.*?)</a>", Pattern.CASE_INSENSITIVE);
		            value = scriptPattern.matcher(value).replaceAll("");
	            
				     // Avoid input tags
				     scriptPattern = Pattern.compile("<input (.*?)>", Pattern.CASE_INSENSITIVE);
				     value = scriptPattern.matcher(value).replaceAll("");
				     
					// Avoid textarea tags
					scriptPattern = Pattern.compile("<text(.*?)>(.*?)</text(.*?)>", Pattern.CASE_INSENSITIVE);
					value = scriptPattern.matcher(value).replaceAll("");
		            
		            // Avoid anything between script tags
		            scriptPattern = Pattern.compile("<a href (.*?)</a>", Pattern.CASE_INSENSITIVE);
		            value = scriptPattern.matcher(value).replaceAll("");

		            // Avoid anything in a src='...' type of expression
		            scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
		            value = scriptPattern.matcher(value).replaceAll("");

		            // Avoid avoids string between '><' tags
		            scriptPattern = Pattern.compile("'>(.*?)<'", Pattern.CASE_INSENSITIVE);
		            value = scriptPattern.matcher(value).replaceAll("");

		           
		            

		            scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
		            value = scriptPattern.matcher(value).replaceAll("");
		            
		            // Remove any lonesome <img ...> tag
		            scriptPattern = Pattern.compile("<img(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
		            value = scriptPattern.matcher(value).replaceAll("");
		            

		            // Remove any lonesome </script> tag
		            scriptPattern = Pattern.compile("</script>", Pattern.CASE_INSENSITIVE);
		            value = scriptPattern.matcher(value).replaceAll("");

		            // Remove any lonesome <script ...> tag
		            scriptPattern = Pattern.compile("<script(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
		            value = scriptPattern.matcher(value).replaceAll("");

		            // Avoid eval(...) expressions
		            scriptPattern = Pattern.compile("eval\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
		            value = scriptPattern.matcher(value).replaceAll("");

		            // Avoid expression(...) expressions
		            scriptPattern = Pattern.compile("expression\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
		            value = scriptPattern.matcher(value).replaceAll("");

		            // Avoid javascript:... expressions
		            scriptPattern = Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE);
		            value = scriptPattern.matcher(value).replaceAll("");

		            // Avoid vbscript:... expressions
		            scriptPattern = Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE);
		            value = scriptPattern.matcher(value).replaceAll("");

		            // Avoid onload= expressions
		            scriptPattern = Pattern.compile("onload(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
		            value = scriptPattern.matcher(value).replaceAll("");
		            
		            // Avoid onchange= expressions
		            scriptPattern = Pattern.compile("onchange(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
		            value = scriptPattern.matcher(value).replaceAll("");

		            // Avoid onsubmit= expressions
		            scriptPattern = Pattern.compile("onsubmit(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
		            value = scriptPattern.matcher(value).replaceAll("");
		            
		            
		            // Avoid onreset= expressions
		            scriptPattern = Pattern.compile("onreset(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
		            value = scriptPattern.matcher(value).replaceAll("");
		            
		            
		            // Avoid onselect= expressions
		            scriptPattern = Pattern.compile("onselect(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
		            value = scriptPattern.matcher(value).replaceAll("");
		            
		            
		            // Avoid onblur= expressions
		            scriptPattern = Pattern.compile("onblur(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
		            value = scriptPattern.matcher(value).replaceAll("");

		            
		            // Avoid onfocus= expressions
		            scriptPattern = Pattern.compile("onfocus(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
		            value = scriptPattern.matcher(value).replaceAll("");

		            
		            // Avoid onkeydown= expressions
		            scriptPattern = Pattern.compile("onkeydown(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
		            value = scriptPattern.matcher(value).replaceAll("");


		            // Avoid onkeypress= expressions
		            scriptPattern = Pattern.compile("onkeypress(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
		            value = scriptPattern.matcher(value).replaceAll("");

		            // Avoid onkeyup= expressions
		            scriptPattern = Pattern.compile("onkeyup(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
		            value = scriptPattern.matcher(value).replaceAll("");

		            // Avoid onclick= expressions
		            scriptPattern = Pattern.compile("onclick(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
		            value = scriptPattern.matcher(value).replaceAll("");

		            // Avoid ondblclick= expressions
		            scriptPattern = Pattern.compile("ondblclick(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
		            value = scriptPattern.matcher(value).replaceAll("");

		            // Avoid onmousedown= expressions
		            scriptPattern = Pattern.compile("onmousedown(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
		            value = scriptPattern.matcher(value).replaceAll("");

		            // Avoid onmousemove= expressions
		            scriptPattern = Pattern.compile("onmousemove(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
		            value = scriptPattern.matcher(value).replaceAll("");

		            // Avoid onmouseout= expressions
		            scriptPattern = Pattern.compile("onmouseout(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
		            value = scriptPattern.matcher(value).replaceAll("");

		            // Avoid onmouseover= expressions
		            scriptPattern = Pattern.compile("onmouseover(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
		            value = scriptPattern.matcher(value).replaceAll("");

		            // Avoid onmouseup= expressions
		            scriptPattern = Pattern.compile("onmouseup(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
		            value = scriptPattern.matcher(value).replaceAll("");            

		            
		            // Avoid onerror= expressions
		            scriptPattern = Pattern.compile("onerror(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
		            value = scriptPattern.matcher(value).replaceAll("");
		            
		            // Avoid prompt= expressions
		            scriptPattern = Pattern.compile("prompt((.*?)).*", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
		            value = scriptPattern.matcher(value).replaceAll("");
		            
		            // Avoid alert= expressions
		            scriptPattern = Pattern.compile("alert((.*?)).*", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
		            value = scriptPattern.matcher(value).replaceAll("");
		            
		            // Avoid avoids string between '(' tags
		            scriptPattern = Pattern.compile("\"", Pattern.CASE_INSENSITIVE);
		            value = scriptPattern.matcher(value).replaceAll("");

		            // Avoid avoids string between ')' tags
		            scriptPattern = Pattern.compile("'", Pattern.CASE_INSENSITIVE);
		            value = scriptPattern.matcher(value).replaceAll("");

		            // Avoid avoids string between '>tags
		            scriptPattern = Pattern.compile("'>", Pattern.CASE_INSENSITIVE);
		            value = scriptPattern.matcher(value).replaceAll("");

		            // Avoid avoids string between '<"' tags
		            scriptPattern = Pattern.compile("<'", Pattern.CASE_INSENSITIVE);
		            value = scriptPattern.matcher(value).replaceAll("");
		            
		            // Avoid avoids string between "> tags
		            scriptPattern = Pattern.compile("\">", Pattern.CASE_INSENSITIVE);
		            value = scriptPattern.matcher(value).replaceAll("");
		            
		            // Avoid avoids string between <" tags
		            scriptPattern = Pattern.compile("<\"", Pattern.CASE_INSENSITIVE);
		            value = scriptPattern.matcher(value).replaceAll("");
		            
		            // Avoid avoids string between '> tags
		            scriptPattern = Pattern.compile("'>", Pattern.CASE_INSENSITIVE);
		            value = scriptPattern.matcher(value).replaceAll("");
		            
		            // Avoid avoids string between <' tags
		            scriptPattern = Pattern.compile("<'", Pattern.CASE_INSENSITIVE);
		            value = scriptPattern.matcher(value).replaceAll("");

		            // Avoid avoids string between > tags
		            scriptPattern = Pattern.compile(">", Pattern.CASE_INSENSITIVE); 
		            value = scriptPattern.matcher(value).replaceAll("");
		            
		            // Avoid avoids string between < tags
		            scriptPattern = Pattern.compile("<", Pattern.CASE_INSENSITIVE);
		            value = scriptPattern.matcher(value).replaceAll("");
		            
		            // Avoid avoids string between ' tags
		            scriptPattern = Pattern.compile("'", Pattern.CASE_INSENSITIVE);
		            value = scriptPattern.matcher(value).replaceAll("");
		            
		            //System.out.println("Value : "+value);
		        }
		        return value;
		    }
		 
		 
		 public static boolean validateBetweenTwoDateTimes(String startDateTime,String startDateTimeFormat,String endDateTime,String endDateTimeFormat,int daysDiff,int hoursDiff)
		 {
			 boolean result = false;
			 
				//HH converts hour in 24 hours format (0-23), day calculation
				SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

				Date d1 = null;
				Date d2 = null;

				try 
				{
					format = new SimpleDateFormat(startDateTimeFormat);
					d1 = format.parse(startDateTime);
					

					format = new SimpleDateFormat(endDateTimeFormat);
					d2 = format.parse(endDateTime);

					//in milliseconds
					long diff = d2.getTime() - d1.getTime();

					long diffSeconds = diff / 1000 % 60;
					long diffMinutes = diff / (60 * 1000) % 60;
					long diffHours = diff / (60 * 60 * 1000) % 24;
					long diffDays = diff / (24 * 60 * 60 * 1000);

					/*System.out.print(diffDays + " days, ");
					System.out.print(diffHours + " hours, ");
					System.out.print(diffMinutes + " minutes, ");
					System.out.print(diffSeconds + " seconds.");*/
					
					if((diffDays>=0 && diffDays>=daysDiff) || (diffDays>=0 && diffDays>=daysDiff && diffHours>=hoursDiff))
					{
						result = true;
					}
					else
					{
						result = false;
					}

				} catch (Exception e) 
				{
					result = false;
					e.printStackTrace();
				}
				
				return result;
		 }
		 
		 
		 public static boolean AadhaarNumberValidator(String number)
			{
				boolean result = false;
				try
				{
					String NUMBER_PATTERN =  "^\\d+(\\.\\d+)?";
					
					pattern = Pattern.compile(NUMBER_PATTERN);
					matcher = pattern.matcher(number);
				
					if(matcher.matches()==true && number.length()==12)
					{
						result = AadhaarValidationUtil.validateVerhoeff(number);
					}
					else
					{
						result = false;
					}
				}
				catch(Exception e)
				{
					e.printStackTrace();
					result = false;
				}
				
				return result;
			}
			
		 
	/*public static void main(String arg[] )
	{
			String emailId = "dpmdiskrmn@gmail.com";
			String dateStr = "12/12/2015";
			
			System.out.println("Email Validator : "+emailValidator(emailId));
			
			//System.out.println("Date Validator : "+dateValidater(dateStr));
			
			//System.out.println(dateTimeValidator("29-02-0000 00:59"));
		
		//System.out.println(mobileNumberValidator("788528228511"));
		//System.out.println(htmlStrValidation("hi subbu <script>alert('hi')</script>")); 
		//System.out.println(validateBetweenTwoDateTimes("06-08-2015 10:00","dd-MM-yyyy HH:mm","07-08-2015 10:00","dd-MM-yyyy HH:mm",0,1));
		
		
	}*/
}