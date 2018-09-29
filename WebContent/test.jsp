<%@ page language="java" import="com.tcs.sadarem.common.DAO.*,java.io.*,java.util.ArrayList,com.tcs.sadarem.util.DataAccess" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/cssmenu.css"/>
</head>
<body>
<%  
  try
{
  String manDir = "D:/SADAREMTG/SADAREMPHOTOS";
  

File[] dir = new File(manDir).listFiles();

ArrayList dirList = new ArrayList();
if(dir.length>0)
{
		  for (File file : dir) 
		  {
		      if (file.isDirectory())
		      {
		    	  dirList.add(file.getName());
		      }
		  }
}
response.getWriter().write("dirList : "+dirList+"<br/>");
  String queryStr ="";
	String dirPath ="";
	
	
  for(int looper=0 ; looper<dirList.size();looper++)
  {
	 dirPath = manDir+"/"+dirList.get(looper);
	try
	{
		
			  File[] files = new File(dirPath).listFiles();

				response.getWriter().write("<hr width='100%'><br/><font color='red'><b>Current Directory ("+files.length+") : </b></font><font color='blue'>"+dirList.get(looper)+"</font><hr width='100%'><br/>");
			  
			  if(files.length>0)
			  {
				  int myCount =0;
					  for (File file : files) 
					  {
					      if (file.isFile())
					      {
					    	  queryStr = "INSERT INTO sadarem_profile_photo_details VALUES('"+file.getName().substring(0,file.getName().indexOf("."))+"','"+file.getName()+"','"+dirList.get(looper)+"')";
					    	  response.getWriter().write(myCount+") "+dirList.get(looper) +"/"+file.getName()+" : "+ DataAccess.executeUpdate(queryStr)+"<br/>");
					      }
					      myCount++;
					  }
			  }
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
  }
}
catch(Exception e)
{
	e.printStackTrace();
}
%>  
</body>
</html>