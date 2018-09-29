package com.tcs.sadarem.util;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;



public class RequestUtility
{
	
	public static HashMap getReqParamList(HttpServletRequest request,HttpSession session,boolean isParamNameEncrypt,boolean isParamValueEncrypt,String sessionAtrName)
	{
		HashMap resultList = new HashMap();
		
		try
		{
	    	   String formType = CommonUtility.checkNull(request.getContentType());
	    	 
	    	  //System.out.println("FormType : "+formType);
	    	   if (formType.toLowerCase().contains("form-data")==false) 
	    	   {
	    		   resultList = (HashMap)getNormalPostMethodReqList(request,session,isParamNameEncrypt,isParamValueEncrypt,sessionAtrName);
	    	   }
	    	   else
	    	   {
	    		   resultList = (HashMap)getDiskFileItemFactory(request,session,isParamNameEncrypt,sessionAtrName);
	    	   }
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return resultList;
	}

	private static HashMap getNormalPostMethodReqList(HttpServletRequest req,HttpSession session,boolean isParamNameEncrypt,boolean isParamValueEncrypt,String sessionAtrName)
	{
		HashMap resultList = new HashMap();
		try
		{
		Enumeration<String> parameterNames = req.getParameterNames();

		String paramName ="";
		String paramValue = "";
		
		//System.out.println(sessionAtrName +" : "+session.getAttribute(sessionAtrName));
				while (parameterNames.hasMoreElements()) 
				{
					paramName ="";
					paramName = parameterNames.nextElement();
					paramValue = getParameterValues(paramName,req);


					//System.out.println("Befor ::::::::::: paramName : "+paramName+" ==== paramvalue : "+paramValue);
					
					if(isParamNameEncrypt==true || isParamValueEncrypt==true)
					{
		
						String mySecurityCode = CommonUtility.checkNullObj(session.getAttribute(sessionAtrName));
						if(isParamNameEncrypt==true)
						{
							paramName  = EncriptDecrypt.decipher(mySecurityCode,(""+paramName).replaceAll("@^","="));
						}
						
						if(isParamValueEncrypt==true)
						{
							paramValue = EncriptDecrypt.decipher(mySecurityCode,(""+paramValue).replaceAll("@^","="));
						}
					}

					//System.out.println("After ::::::::::: paramName : "+paramName+" ==== paramvalue : "+paramValue);
					
					resultList.put(paramName,paramValue);
				}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return resultList;
	}

	private static String getParameterValues(String paramName,HttpServletRequest request)
	{
		String result ="";
		String[] paramValues = request.getParameterValues(paramName);
		for (int i = 0; i < paramValues.length; i++) 
		{
			String paramValue = paramValues[i];
			result+=paramValue;
			
		}
		
		return result;
	}
	
	private static HashMap getDiskFileItemFactory(HttpServletRequest request,HttpSession session,boolean isParamNameEncrypt,String sessionAtrName)
	{
		HashMap myFileItems =new HashMap();
		
		try
		{

	    	   DiskFileItemFactory factory = new DiskFileItemFactory();
	    	   ServletFileUpload sfu  = new ServletFileUpload(factory);
	    	   
			
			  List items = sfu.parseRequest(request);
	    	   	    	   
	    	   FileItem  myFileItem;
	    	   String keyStr = "";
	    	   String mySecurityCode ="";
	    	   
    		   if(isParamNameEncrypt==true)
    		   { 
    			    mySecurityCode = CommonUtility.checkNullObj(session.getAttribute(sessionAtrName));
    		   }
				
	    	   
	    	   for(int loop=0;loop<items.size();loop++)
	    	   {
	    		   myFileItem = (FileItem) items.get(loop);
	    		   
	    		   keyStr = myFileItem.getFieldName().toString();
	    		   
	    		   if(isParamNameEncrypt==true)
	    		   { 
	    			   keyStr = EncriptDecrypt.decipher(mySecurityCode, keyStr.replaceAll("@^","="));
	    		   }
	    		   
	    		   myFileItems.put(keyStr, myFileItem);
	    		   
	    	   }
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return myFileItems;
	}
	
	
}
