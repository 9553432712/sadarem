package com.tcs.sadarem.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

public class MenuUtil 
{
	
	
	public static boolean checkLinkAuthority(HttpSession session,String url)
	{
		boolean result = false;
		try
		{
			boolean urlCheckStatus = false;
			boolean urlStatus = false;
			
			ArrayList loginAllUrMenulList = (ArrayList)session.getAttribute("loginAllUrMenulList");
			ArrayList menuList 			  = (ArrayList)session.getAttribute("loginurllist");
			
			 			
			
			//  System.out.println("loginAllUrMenulList : "+loginAllUrMenulList);
			// System.out.println("menuList : \n "+menuList);
			
			if(loginAllUrMenulList!=null && loginAllUrMenulList.size()>0 && menuList!=null && menuList.size()>0)
			{
				for(int looper=0;looper<loginAllUrMenulList.size();looper++)
				{
					 
				    if(loginAllUrMenulList.get(looper).toString().contains("?"))
				    {
				    	int startingIndex = loginAllUrMenulList.get(looper).toString().indexOf("?");
				    	if(loginAllUrMenulList.get(looper).toString().toLowerCase().substring(0,startingIndex).equals(url.toLowerCase()))
						{
							urlCheckStatus = true;
							break;
						}
				    }
				    else if(url.toLowerCase().equals(loginAllUrMenulList.get(looper).toString().toLowerCase()))
					{
						urlCheckStatus = true;
						break;
					}				    					
				}
				
				
			//	System.out.println("urlCheckStatus : "+urlCheckStatus);
				
				 if(urlCheckStatus==true)
				 {
					 urlStatus = false;
					for(int looper=0;looper<menuList.size();looper++)
					{
						if(menuList.get(looper).toString().contains("?"))
					    {
					    	int startingIndex = menuList.get(looper).toString().indexOf("?");
					    	if(menuList.get(looper).toString().toLowerCase().substring(0,startingIndex).equals(url.toLowerCase()))
							{
					    		urlStatus = true;
								break;
							}
					    }
						else if(url.toLowerCase().equals(menuList.get(looper).toString().toLowerCase()))
						{
							urlStatus = true;
							break;
						}
							/*if(url.toLowerCase().contains(menuList.get(looper).toString().toLowerCase()))
							{
								//System.out.println(menuList.get(looper).toString().toLowerCase());
								urlStatus = true;
								break;
							}*/
					}
					

					//System.out.println("urlStatus : "+urlStatus);
					
					if(urlStatus == true)
					{
						result = true;
					}
					else
					{
						result = false;
					}
				 }
				 else
				 {
					 result = true;
				 }
			}
			else
			{
				 result = true;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		//System.out.println("result : "+result);
		return result;
	}
	
	
	
	 public static String createStrHeaderList(ArrayList headerList,String functionName,boolean urlCall)
	 {
		 String header="";
		 
		 ArrayList childList = new ArrayList();
		 ArrayList mngrList = new ArrayList();
		 ArrayList tempList = new ArrayList();

		 Map headerMap = new HashMap();
		 Map urlTitleMapper = new HashMap();
		 Map urlMapper = new HashMap();
		 
		 String linkID = "";
		 String headerID = "";
		 String title ="";
		 String linkUrl = "";
		 
		 
		 for(int i=0;i<headerList.size();i++)
		 {
		 	childList = new ArrayList();
		 	childList = (ArrayList)headerList.get(i);
		 	//System.out.println("childList : "+childList);
		 linkID 	= childList.get(0).toString();
		 headerID 	= childList.get(1).toString();
		 
		 if(childList.size()>=2)
		 {
			 title 	= childList.get(2).toString();
			 urlTitleMapper.put(linkID,title);
		 }
		 
		 if(childList.size()>=3)
		 {
			 linkUrl 	= childList.get(3).toString();
			 urlMapper.put(linkID,linkUrl);
		 }
		 else
		 {
			 urlCall=false;
		 }
		 
		
		 	if(headerID.equals(""))	
		 	{
		 		mngrList.add(linkID);
		 	}
		 	else
		 	{
		 		try
		 		{
		 			
		 			if(headerMap.containsKey(headerID))
		 			{
		 				tempList = new ArrayList();
		 				tempList = (ArrayList) headerMap.get(headerID);
		 				tempList.add(linkID);
		 			}
		 			else
		 			{
		 				tempList = new ArrayList();
		 				tempList.add(linkID);
		 			}
		 			
		 			//System.out.println("Catch tempList : "+tempList);
		 			headerMap.put(headerID,tempList);
		 			
		 		}
		 		catch(Exception e)
		 		{
		 			System.out.println("MenuUtil @ createStrHeaderList method : "+e.getLocalizedMessage());
		 		}
		 		
		 	}
		 }
		 	 
	
						try
						{
						ArrayList mydatalist = new ArrayList();
						
						for(int i=0;i<mngrList.size();i++)
						{
							if(headerMap.containsKey(mngrList.get(i).toString()))
							{
								mydatalist = (ArrayList)headerMap.get(mngrList.get(i).toString());
							//	System.out.println("mydatalist  ::"+mydatalist);
						
								header+="<li><a href=\"#\">"+urlTitleMapper.get(mngrList.get(i))+"</a>";
								header+=childHeaderList((String)mngrList.get(i),headerMap,urlTitleMapper,urlMapper,functionName,urlCall);
								header+="</li>";
										
							}
							else
							{	
								if(urlCall==false)
								{
									header+="<li ><a href=\"#\" onclick=\""+functionName+"('"+mngrList.get(i).toString().trim()+"')\">"+urlTitleMapper.get(mngrList.get(i)).toString().trim()+"</a></li>";
								}
								else
								{
									header+="<li ><a href=\"#\" onclick=\""+functionName+"('"+urlMapper.get(mngrList.get(i)).toString().trim()+"')\">"+urlTitleMapper.get(mngrList.get(i)).toString().trim()+"</a></li>";
								}
							}
						
							}
							if(!header.equals(""))
							{
								header="<div id=\"myslidemenu\" class=\"jqueryslidemenu\"><ul>"+header+"</ul></div>";
							}
		
						}
						catch(Exception e)
						{
							e.printStackTrace();
						}
		 
		 return header;
		 
	 }
	 
	 private  static String childHeaderList(String key,Map headerMap,Map ulrTitleMapper,Map urlMapper,String functionName,boolean urlCall)
	 {
	 	ArrayList mydatalist=new ArrayList();
	 	String ret="";
	 		ret="<ul>";
	 		mydatalist = (ArrayList)headerMap.get(key.toString());
	 	
	 		for(int j=0;j<mydatalist.size();j++)
	 		{
	 			
	 			if(headerMap.containsKey(mydatalist.get(j).toString()))
	 			{
		 			ret=ret+"<li><a href='#'>"+ulrTitleMapper.get(mydatalist.get(j))+"</a>";
		 			ret=ret+childHeaderList((String)mydatalist.get(j),headerMap,ulrTitleMapper,urlMapper,functionName,urlCall);
		 			ret=ret+"</li>";
	 			}
	 			else
	 			{
	 				if(urlCall==false)
					{
	 					ret=ret+"<li><a href='#' onclick=\""+functionName+"('"+mydatalist.get(j)+"')\">"+ulrTitleMapper.get(mydatalist.get(j))+"</a></li>";
					}
	 				else
	 				{
	 					ret=ret+"<li><a href='#' onclick=\""+functionName+"('"+urlMapper.get(mydatalist.get(j))+"')\">"+ulrTitleMapper.get(mydatalist.get(j))+"</a></li>";
	 				}
	 			}
	 			//System.out.println("ret out :: "+ret);
	 		}
	 		ret=ret+"</ul>";
	 	return ret;
	 }
}
