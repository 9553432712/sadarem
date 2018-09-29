package com.tcs.sadarem.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.codec.Base64;

public class CommonUtility 
{
	
	static ResourceBundle bundle 				= ResourceBundle.getBundle("CommonConstants");
	private static final String SMTP_HOST_NAME  = bundle.getString("SMTP_HOST_NAME");
	public static DecimalFormat twoplacesdf 	= new DecimalFormat("##.##");
	 
	public static int getStringDoubleToInt(String inStr)
	{
		int result =0;
		try
		{
			result = (int)Double.parseDouble(inStr);
		}
		catch(Exception e)
		{
			System.out.println("Excepiton in CommonUtility @ getStringDoubleToInt : "+e.getLocalizedMessage()); 
			e.printStackTrace();
		}
		return result;
	}
  
  public static String generateRandomStr(int length) 
	{    
		Random rng= new Random();
		String characters="ABCDEFGHIJK123456789LMNPQRSTUVWXYZ";
		
		char[] text = new char[length];     
		for (int i = 0; i < length; i++)     
		{         
			text[i] = characters.charAt(rng.nextInt(characters.length()));     
		}     
		return new String(text); 
	} 
  
  
  public static String generateRandomStrSession(int length,HttpSession session,String sesValName) 
	{    
		Random rng= new Random();
		String characters="AB2C3D4E5F6G3H5KMNP9Q2R5ST8UV7W7XY6Z"; 
		
		if(length==-1)
		{
			length=Integer.parseInt(bundle.getString("ENC_KEY_LENGTH").trim()); 
		}
		
		char[] text = new char[length];     
		for (int i = 0; i < length; i++)     
		{         
			text[i] = characters.charAt(rng.nextInt(characters.length()));     
		}
		session.setAttribute(sesValName,"");
		session.removeAttribute(sesValName);
		session.setAttribute(sesValName, new String(text));
		return new String(text); 
	} 
  
  
  public static String getAverageTime(String myTimes,int holidaysCount,int arraylength)
	{
		String myResult ="";
		try
		{
			if(myTimes.length()==0){
				myResult ="0.00";
			}
			
			else{
			
			String myTimesArray[] = myTimes.split(",");
			
			
			double sumOfminutes = 0.0;		
			String oneTime = "";
			int avgCountDays = arraylength-holidaysCount;
			
			for(int i=0;i<myTimesArray.length;i++)
			{
				oneTime = myTimesArray[i];
				
				//System.out.println("oneTime0 : "+oneTime);
				if(oneTime.indexOf(":")!=-1 &&  !oneTime.equals("-"))
				{
					oneTime = oneTime.substring(0,oneTime.indexOf(":", 3));
					//System.out.println("oneTime1 : "+oneTime);
					oneTime = oneTime.replaceAll(":", ".");
					//System.out.println("oneTime2 : "+Double.parseDouble(oneTime)*60);
					if(Double.parseDouble(oneTime)>0.59)
					{
						sumOfminutes=sumOfminutes+(Double.parseDouble(oneTime)*60);
					}
					else
					{
						sumOfminutes=sumOfminutes+(Double.parseDouble(oneTime)*100);
					}
				}
				
			}

			 //System.out.println("sumOfminutes : "+((sumOfminutes)));
			myResult = String.format("%.2f", Double.parseDouble(getDoubleinTime(sumOfminutes/avgCountDays)));
		}
		}
		catch(Exception e)
		{
			System.out.println("Exception in getAverageTime : "+e);
		}
		return myResult;
	}
  
	
	public static String getDoubleinTime(double myTime)
	{
		double myResult =0.0;
		String finalResult="";
		try
		{
						
			String myTimeStr = ""+ String.format("%.2f",myTime/60);
//System.out.println("myTimeStr : "+myTimeStr);
				
			String myTimeAry[] = myTimeStr.split("\\.");
			
			double myTimeNum = Integer.parseInt(myTimeAry[0]);
			double myTimeDec = Integer.parseInt(myTimeAry[1]);
			//System.out.println("myTimeDec : "+myTimeDec);
			double result = 0.0;
			
			if(myTimeDec>59)
			{
				myTimeNum = myTimeNum+1;
				//System.out.println("subbu : "+String.format("%.0f",(myTimeDec-60)));
				finalResult=String.format("%.0f",myTimeNum)+"."+(String.format("%.0f",(myTimeDec-60)));
				//System.out.println("finalResult : "+finalResult);
			}
			else
			{
				if(myTimeDec>9)
				{
					finalResult =""+ String.format("%.0f",myTimeNum)+"."+String.format("%.0f",(myTimeDec));
				}
				else
				{
					finalResult =""+ String.format("%.0f",myTimeNum)+".0"+String.format("%.0f",(myTimeDec));
				}
			}
				
				
		}
		catch(Exception e)
		{
			System.out.println("Exception in getDoubleinTime : "+e);
		}
		
		//System.out.println("myResult last: "+String.format("%.2f",Double.parseDouble(finalResult)));
		return String.format("%.2f",Double.parseDouble(finalResult));
	}
	
	
	public static String getDaybyDate(String mydate,String dateFormat)
	{
		String result="";
		
		try
		{
			SimpleDateFormat newDateFormat = new SimpleDateFormat(dateFormat);
			Date MyDate = newDateFormat.parse(mydate);
			newDateFormat.applyPattern("EE");
			result = newDateFormat.format(MyDate);
		}
		catch(Exception e)
		{
			System.out.println("Exception @ getDayByDate : "+e);
		}
		return result;
		
	}
	

	
	
	public static String getDateinFormat(String mydate,String fromFormat,String toFormat)
	{
		String result="";
		
		try
		{
			SimpleDateFormat newDateFormat = new SimpleDateFormat(fromFormat);
			Date MyDate = newDateFormat.parse(mydate);
			newDateFormat.applyPattern(toFormat);
			result = newDateFormat.format(MyDate);
		}
		catch(Exception e)
		{
			System.out.println("Exception @ getDayByDate : "+e);
		}
		return result;
		
	}
  
	public static String getSufixforDate(String mydate)
	{
		String result="";
		
		try
		{
			//System.out.println("mydate : "+mydate+" ==>st : "+mydate.indexOf("st")+" ==> nd : "+mydate.indexOf("nd")+" ==> rd : "+mydate.indexOf("rd")+" ==> th : "+mydate.indexOf("th"));
			if(mydate.indexOf("st")!=-1)
			{
				result=mydate.replace("st", "<sup>st</sup>");
			}
			else if(mydate.indexOf("nd")!=-1)
			{
				result=mydate.replace("nd", "<sup>nd</sup>");
			}
			else if(mydate.indexOf("rd")!=-1)
			{
				result=mydate.replace("rd", "<sup>rd</sup>");
			}
			else if(mydate.indexOf("th")!=-1)
			{
				result=mydate.replace("th", "<sup>th</sup>");
			}
			else
			{
				result = mydate;
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception @ getDayByDate : "+e);
		}
		return result;
		
	}
	
  public static String getClientIPAddress(HttpServletRequest request)
  {
	  String ClientIP=request.getHeader("x-forwarded-for");      
	    if (ClientIP == null)
	    {
	    	ClientIP = request.getHeader("X_FORWARDED_FOR");  
	          if (ClientIP == null)
	          {      
	        	  ClientIP = request.getRemoteAddr();
	          }
	    }		
	 
	 return ClientIP;
  }
  
  
  public static PdfPTable CreatePlainTable(ArrayList DataList) 
	{
	  PdfPTable table  = null;
	  try{  
	  int columnCount = ((ArrayList)DataList.get(0)).size();
	    PdfPCell cell;
	   
		// a table with three columns
	     float [] tableWidths1 =new float[columnCount];
		 float colwidth =0f;
		 ArrayList DataTypeList = new ArrayList();
		 DataTypeList = (ArrayList)DataList.get(0);
		 for(int colIndex=0;colIndex<columnCount;colIndex++)
		 {
			 //System.out.println("colIndex : "+colIndex);
				 colwidth = 15f;
			 tableWidths1[colIndex]=colwidth;
			 
		 }
		 
		if((DataList.get(DataList.size()-1).toString()) == "workdone")
		{ 
		     DataList.remove(DataList.size()-1);
			 tableWidths1[0]=3f;
			 tableWidths1[1]=10f;
			 tableWidths1[2]=30f;
			 tableWidths1[3]=5f;
			 
			  
			 
		} 
	    table = new PdfPTable(tableWidths1);
	    table.setLockedWidth(true);
	    table.setWidthPercentage(90f);
	    table.setTotalWidth(750f); 
	    // the cell object
	    
	    String cellData="";
	    ArrayList innerList = new ArrayList();	    
	    
	    for(int i=0;i<DataList.size();i++)
		   {
	    		innerList = (ArrayList)DataList.get(i);
	    		//System.out.println("innerList : "+innerList);
	    		for(int j=0;j<columnCount;j++)
			      {
	    				if(j<innerList.size())
	    				{
	    					cellData=" "+innerList.get(j);
	    				}
	    				else
	    				{
	    					cellData=" ";
	    				}
			           	//System.out.println("cellData : "+cellData);
			        	cell = new PdfPCell(new Phrase(cellData,FontFactory.getFont("Verdana, Geneva, sans-serif", 9,com.itextpdf.text.Font.NORMAL,BaseColor.BLACK)));
			        	String status = "";
			        	if(j==3)
			        	{
			        	  status = (String)innerList.get(j);
			        	} 
			        	if( (status.contains("GH")|| status.contains("SH")||status.contains("OH")))
			        	{
			        		cell.setBackgroundColor (new BaseColor(51,166,77));
			        	}
			        	else if(status.contains("WE"))
			        	{
			        		cell.setBackgroundColor (new BaseColor(204,104,153));
			        	}
			        	else if(j%2==0)
			        	{
			        		cell.setBackgroundColor (new BaseColor(237,237,246));
			        	}
			        	else
			        	{
			        		cell.setBackgroundColor (new BaseColor(239,239,239));
			        	}
			        	cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
			        	cell.setMinimumHeight(20);
						cell.setNoWrap(false);
		        		cell.setPadding(2);
					    table.addCell(cell);
			      }
	    		//table.addCell("SUBBU");
		   }
	  }
	  catch(Exception e)
	  {
		  e.printStackTrace();
	  }
	  
	    return table;
	}
	
  public static PdfPTable appendRowsToExtTablePdf(ArrayList DataList,PdfPTable table) 
	{
		
	  
	    String cellData="";
	    PdfPCell cell; 
	    ArrayList innerList = new ArrayList();	    
	    
	    for(int i=0;i<DataList.size();i++)
		   {
	    		innerList = (ArrayList)DataList.get(i);
	    		//System.out.println("innerList : "+innerList);
	    		for(int j=0;j<innerList.size();j++)
			      {
	    				if(j<innerList.size())
	    				{
	    					cellData=" "+innerList.get(j);
	    				}
	    				else
	    				{
	    					cellData=" ";
	    				}
			           	//System.out.println("cellData : "+cellData);
			        	cell = new PdfPCell(new Phrase(cellData,FontFactory.getFont("Verdana, Geneva, sans-serif", 9,com.itextpdf.text.Font.NORMAL,BaseColor.BLACK)));
			        	if(j%2==0)
			        	{
			        		cell.setBackgroundColor (new BaseColor(237,237,246));
			        	}
			        	else
			        	{
			        		cell.setBackgroundColor (new BaseColor(239,239,239));
			        	}
			        	if(j==0)
			        	{
			        		cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
			        		cell.setHorizontalAlignment(cell.ALIGN_CENTER);
			        	}
			        	else
			        	{
			        		cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
			        	}
			        	cell.setMinimumHeight(20);
						cell.setNoWrap(false);
		        		cell.setPadding(2);
					    table.addCell(cell);
			      }
	    		//table.addCell("SUBBU");
		   }
	    
	  
	    return table;
	}
	public static PdfPTable CreateHeardTable(ArrayList DataList) 
	{
		PdfPTable table = null;
	  try{	
		int columnCount = ((ArrayList)DataList.get(0)).size();
		 PdfPCell cell;
	
		// a table with three columns
		 float [] tableWidths1 =new float[columnCount];
		 float colwidth =0f;
		 ArrayList DataTypeList = new ArrayList();
		 DataTypeList = (ArrayList)DataList.get(0);
		 for(int colIndex=0;colIndex<columnCount;colIndex++)
		 {
			 //System.out.println("colIndex : "+colIndex);
			 colwidth = 15f;
			 tableWidths1[colIndex]=colwidth;
		 }
		 if(DataList.size()>1 && (DataList.get(DataList.size()-1).toString()) == "workdone" )                      //for workdone pdf
		 {
			 DataList.remove(1);
			 tableWidths1[0]=3f;
			 tableWidths1[1]=10f;
			 tableWidths1[2]=30f;
			 tableWidths1[3]=5f;
		 }
		  table = new PdfPTable(tableWidths1);
	    table.setLockedWidth(true);
	    table.setWidthPercentage(90f);
	    table.setTotalWidth(750f); 
	    // the cell object
	    
	    String cellData="";
	    ArrayList innerList = new ArrayList();	    
	    
	    for(int i=0;i<DataList.size();i++)
		   {
	    		innerList = (ArrayList)DataList.get(i);

	    		for(int j=0;j<columnCount;j++)
			      {
	    			
			        if(i==0)
			         {
			        	
			        	if(j<innerList.size())
	    				{
	    					cellData=" "+innerList.get(j);
	    				}
	    				else
	    				{
	    					cellData=" ";
	    				}
			        	cell = new PdfPCell(new Phrase(cellData ,FontFactory.getFont("Verdana, Geneva, sans-serif", 10,com.itextpdf.text.Font.NORMAL,BaseColor.WHITE) ));
			        	cell.setFixedHeight(20f);
						cell.setPadding(1);
						cell.setNoWrap(false);
						cell.setBackgroundColor (new BaseColor(0,137,171));
						cell.setHorizontalAlignment(cell.ALIGN_CENTER);
						cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
					    table.addCell(cell);
			         }
			        else
			        {
			        	if(j<innerList.size())
	    				{
	    					cellData=" "+innerList.get(j);
	    				}
	    				else
	    				{
	    					cellData=" ";
	    				}
			           //	System.out.println("cellData : "+cellData);
			        	cell = new PdfPCell(new Phrase(cellData,FontFactory.getFont("Verdana, Geneva, sans-serif", 9,com.itextpdf.text.Font.NORMAL,BaseColor.BLACK)));
			        	cell.setBackgroundColor (new BaseColor(237,237,246));
			        	cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
						cell.setNoWrap(false);
			        	cell.setMinimumHeight(20);
		        		cell.setPadding(2);
					    table.addCell(cell);
			       }
			      }
		   }
	  }
	  catch(Exception e)
	  {
		  System.out.println("in headertable method"+e);e.printStackTrace();
	  }
	  
	    return table;
	}
	  
	 
  public static String getDateTime(String my_format)
  {
	  String Result="";
      try
      {
    	  SimpleDateFormat formatter = new SimpleDateFormat(my_format);
    	  Result = formatter.format(new Date());
      }
      catch(Exception e)
      {
    	  System.out.println("Exception in CommonUtility @ getDateTime method : "+e);
      }
      return Result;
  }
  
  public static boolean getmyDateisBeforeCurrentDate(String myDate,String myDateFormat)
  {
	  boolean result=false;
      try
      {
    	  SimpleDateFormat formatter = new SimpleDateFormat(myDateFormat);
    	  Date today = new Date();
    	  
    	  result = (formatter.parse(myDate)).before(today);
    	//  System.out.println("result : "+result);
      }
      catch(Exception e)
      {
    	  System.out.println("Exception in CommonUtility @ getmyDateisAfterCurrentDate method : "+e);
      }
      return result;
  }
  
  
  
  public static String getFromToDateString(String fromDate,String toDate)
  {
	  String result ="";
	  try
	  {
		if(fromDate!=null && !fromDate.equals("null") && !fromDate.equals(""))
		{
			result+=""+fromDate;
		}
		
		if(toDate!=null && !toDate.equals("null") && !toDate.equals(""))
		{
			result+=" - "+toDate;
		}
		
	  }
	  catch(Exception e)
	  {
		  System.out.println("Exception in CommonUtility @ getFromToDateString method : "+e.getLocalizedMessage());
	  }
	  return result;
  }

  public static void exportDocument(HttpServletRequest request,HttpServletResponse response)
  {
	 try
	 {
      	SimpleDateFormat df = new SimpleDateFormat("(dd-MMM-yyyy_HH:mm:ss)");;
          String report=(String)request.getAttribute("report");
          response.addHeader("Content-Disposition", "attachment;filename="+report+""+df.format(new java.util.Date()).toString()+request.getAttribute("FileType"));
          response.setHeader("Pragma", "public");
          response.setHeader("Expires", "0");
          response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
          response.setContentType(request.getAttribute("ContentType").toString());
	      	try
	      	{
	          OutputStream lOutStream = response.getOutputStream();      
	          byte[] allBytesInBlob = (byte[])request.getAttribute("File"); 
          
              lOutStream.write(allBytesInBlob);
              lOutStream.flush();
              lOutStream.close();
          }
          catch (Exception ex)
          {
          	System.out.println("Exception exportDocument : "+ex.getLocalizedMessage());
          }
      }
      catch (Exception e)
      {
      	System.out.println("Exception in CommonUtility @ exportDocument method : "+e.getLocalizedMessage());
      }    
  }
  
  public static String getInStrCondforArrayList(ArrayList datalist)
  {
	  String result="";
	  try
	  {
		  if(datalist.size()>0)
		  {
			  ArrayList innerList = new ArrayList();
			  for(int i=0;i<datalist.size();i++)
			  {
				  innerList = (ArrayList)datalist.get(i);
				  if((result.trim()).length()==0)
				  {
					  result="'"+innerList.get(0).toString().trim().toUpperCase()+"'";
				  }
				  else
				  {
					  result+=",'"+innerList.get(0).toString().trim().toUpperCase()+"'";
				  }
			  }
		  }
	  }
	  catch(Exception e)
	  {
		  System.out.println("Exception in CommonUtility @ getInStringforArrayList method : "+e.getLocalizedMessage());
	  }
	  return result;
  }
  
  public static String getNewOtherStaffID(String distID,String mandID,String majorHeadID,String costCenterID)
    {
	  String staffNewId ="";
	  try
	  {
		SimpleDateFormat timeFormat = new SimpleDateFormat("ddMMyyHHmmss");
		staffNewId=distID.trim()+mandID.trim()+majorHeadID.trim()+costCenterID.trim()+((timeFormat.format(new Date())+System.nanoTime())).substring(0,14);
	  }
	  catch(Exception e)
	  {
		  System.out.println("Exception in CommonUtility @ getNewOtherStaffID method : "+e.getLocalizedMessage());
	  }
	  return staffNewId;
    }
  
  public static Double stringToDouble(String asCheckString)
  {
      if (asCheckString == null || asCheckString.equals(""))
      {
          return 0.0;
      }
      else
      {
    	  return Double.parseDouble(asCheckString);
      }
  }
  
  public static String checkNull(String asCheckString)
    {
        if (asCheckString == null)
        {
            return "";
        }

        return asCheckString;
    }
    public static String checkNullObj(Object aoCheckObject)
    {
        if (aoCheckObject == null)
        {
            return "";
        }

        return aoCheckObject.toString();
    }
     public static String checkNullObject(String asCheckString)
    {
        if (asCheckString == null)
        {
            return " ";
        }

        return asCheckString;
    }

  static String string;
  static String a[]={"",
                  "one",
                  "two",
                  "three",
                  "four",
                  "five",
                  "six",
                  "seven",
                  "eight",
                  "nine",
                  };

   static String b[]={
    "hundred",
    "thousand",
    "lakh",
    "crore"

    };

    static String c[]={"ten",
    "eleven",
    "twelve",
    "thirteen",
    "fourteen",
    "fifteen",
    "sixteen",
    "seventeen",
    "eighteen",
    "ninteen",
    };

   static String d[]={

    "twenty",
    "thirty",
    "fourty",
    "fifty",
    "sixty",
    "seventy",
    "eighty",
    "ninty"
    };

   public static String twoplaceDecimal(String decimalnum)
   {
 	 String Result = ""+decimalnum;
       try
       {
     	  Result = twoplacesdf.format(Double.parseDouble(decimalnum));
       }
       catch(Exception e)
       {
    	   System.out.println("Exception in CommonUtility @ twoplaceDecimal method : "+e.getLocalizedMessage());
       }
       return Result;
   }

public static String convertNumToWord(int number)
{
    int c=1;
    int rm ;
    string="";
    while ( number != 0 )
    {
      switch ( c )
      {
          case 1 :
              rm = number % 100 ;
              pass ( rm ) ;
              if( number > 100 && number % 100 != 0 )
              {
                display ( "and " ) ;
              }
              number /= 100 ;

          break ;

          case 2 :
              rm = number % 10 ;
              if ( rm != 0 )
              {
                display ( " " ) ;
                display ( b[0] ) ;
                display ( " " ) ;
                pass ( rm ) ;
              }
              number /= 10 ;
          break ;

          case 3 :
              rm = number % 100 ;
              if ( rm != 0 )
              {
                display ( " " ) ;
                display ( b[1] ) ;
                display ( " " ) ;
                pass ( rm ) ;
              }
              number /= 100 ;
          break ;

          case 4 :
              rm = number % 100 ;
              if ( rm != 0 )
              {
                display ( " " ) ;
                display ( b[2] ) ;
                display ( " " ) ;
                pass ( rm ) ;
              }
              number /= 100 ;
          break ;

          case 5 :
              rm = number % 100 ;
              if ( rm != 0 )
              {
                display ( " " ) ;
                display ( b[3] ) ;
                display ( " " ) ;
                pass ( rm ) ;
              }
              number /= 100 ;
          break ;

        }
      c++ ;
    }

  return string;
}

public static void pass(int number)
{
    int rm, q ;
    if ( number < 10 )
    {
      display ( a[number] ) ;
    }

    if ( number > 9 && number < 20 )
    {
      display ( c[number-10] ) ;
    }

    if ( number > 19 )
    {
        rm = number % 10 ;
        if ( rm == 0 )
        {
          q = number / 10 ;
          display ( d[q-2] ) ;
        }
        else
        {
          q = number / 10 ;
          display ( a[rm] ) ;
          display ( " " ) ;
          display ( d[q-2] ) ;
        }
    }
}

public static void display(String s)
{
    String t ;
    t= string ;
    string= s ;
    string+= t ;
}



public static float Round(float Rval, int Rpl) 
{
  float p = (float)Math.pow(10,Rpl);
  Rval = Rval * p;
  float tmp = Math.round(Rval);
  return (float)tmp/p;
 }

public static Boolean postEmail
(
	ArrayList<InternetAddress> ToMailsList,
	ArrayList<InternetAddress> CCMailsList,
	ArrayList<InternetAddress> BCCMailsList, 
	String subject, 
	String message , 
	String from,
	String FromName
) 
	{
		Boolean Msg=true;
		System.out.println("Recipients in sendEmail : "+ToMailsList);
		System.out.println("Recipients in CCMailsList : "+CCMailsList);
		try
		{
			
			java.util.Properties props = System.getProperties();
		
			props.put("mail.smtp.host",SMTP_HOST_NAME );
			props.put("mail.smtp.debug ","true");
			props.setProperty("mail.smtp.ssl.trust", SMTP_HOST_NAME);
			
		
			props.put("mail.transport.protocol", "smtp");
			props.put("mail.smtp.starttls.enable","true");
			Session session = Session.getInstance(props, null);
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(from,FromName));

			InternetAddress[] iaarrayTo=new InternetAddress[ToMailsList.size()];
			InternetAddress[] iaarrayCC=new InternetAddress[CCMailsList.size()];
			InternetAddress[] iaarrayBCC=new InternetAddress[BCCMailsList.size()];

			int count=0;
			for(InternetAddress ia:ToMailsList)
				{
				iaarrayTo[count]=ia;
				count++;
				}

				count=0;
			for(InternetAddress ia:CCMailsList)
				{
				iaarrayCC[count]=ia;
				count++;
				}

				count=0;
			for(InternetAddress ia:BCCMailsList)
				{
				iaarrayBCC[count]=ia;
				count++;
				}

			msg.setRecipients(Message.RecipientType.TO,iaarrayTo);
			msg.setRecipients(Message.RecipientType.CC,iaarrayCC);
			msg.setRecipients(Message.RecipientType.BCC,iaarrayBCC);

			msg.setSubject(subject);
			// msg.setText(message);
			msg.setContent(message, "text/html");
			session.setDebug(true);
			Transport tr = session.getTransport("smtp");
			tr.connect();
			msg.saveChanges(); // don't forget this
			tr.sendMessage(msg, msg.getAllRecipients());
			tr.close();
		}
		catch(Exception e)
		{
			System.out.println("Exception in eMailing : "+e.getMessage());
			Msg=false;
			//return false;
		}
		
	
		return Msg;
	
	}
	

	public static Boolean sendEmail(ArrayList<InternetAddress> Recipients, String subject, String message , String from) throws MessagingException
	{
		Boolean Msg=true;
		System.out.println("Recipients in sendEmail : "+Recipients);
		try
		{
			System.setProperty("mail.smtp.auth", "true");
			java.util.Properties props = System.getProperties();
			props.put("mail.smtp.auth ","true");
			props.put("mail.smtp.host","smtp.gmail.com");
			props.put("mail.smtp.debug ","true");
			props.setProperty("mail.smtp.user", "serpgov@gmail.com");
			props.setProperty ("mail.smtp.password", "11421666");
			props.setProperty("mail.smtp.port","587");
			props.put("mail.transport.protocol", "smtp");
			props.put("mail.smtp.starttls.enable","true");
			Session session = Session.getInstance(props, new Authenticator() {
			    @Override
			    protected PasswordAuthentication getPasswordAuthentication() {
			            return new PasswordAuthentication("serpgov@gmail.com", "11421666");
			    }
			});
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(from));
			InternetAddress[] iaarray=new InternetAddress[Recipients.size()];
			int count=0;
			for(InternetAddress ia:Recipients){
			iaarray[count]=ia;
			count++;
			}
			msg.setRecipients(Message.RecipientType.TO,iaarray);
			msg.setSubject(subject);
			// msg.setText(message);
			msg.setContent(message, "text/html");
			session.setDebug(true);
			Transport tr = session.getTransport("smtp");
			tr.connect();
			msg.saveChanges(); // don't forget this
			tr.sendMessage(msg, msg.getAllRecipients());
			tr.close();
		}
		catch(Exception e)
		{
			System.out.println("Exception in eMailing : "+e.getMessage());
			Msg=false;
		}
		
	
		return Msg;
	
	}
	
	
	public static Boolean sendAPonlineEmail(ArrayList<InternetAddress> Recipients, String subject, String message , String from) throws MessagingException
	{
		Boolean Msg=true;
		System.out.println("Recipients in sendEmail : "+Recipients);
		try
		{
			boolean debug = false;

		     //Set the host smtp address
		     Properties props = new Properties();
		     props.put("mail.smtp.host", "110.234.140.190");
		    // props.put("mail.smtp.auth", "true");

		    //Authenticator auth = new SMTPAuthenticator();
		    Session session = Session.getDefaultInstance(props, null);
		    session.setDebug(debug);

		    // create a message
		    Message msg = new MimeMessage(session);

		    // set the from and to address
		    InternetAddress addressFrom = new InternetAddress(from);
		    			
			msg.setFrom(new InternetAddress(from));
			InternetAddress[] iaarray=new InternetAddress[Recipients.size()];
			int count=0;
			for(InternetAddress ia:Recipients){
			iaarray[count]=ia;
			count++;
			}
			msg.setRecipients(Message.RecipientType.TO,iaarray);
			msg.setSubject(subject);
			// msg.setText(message);
			msg.setContent(message, "text/html");
			session.setDebug(true);
			Transport tr = session.getTransport("smtp");
			tr.connect();
			msg.saveChanges(); // don't forget this
			tr.sendMessage(msg, msg.getAllRecipients());
			tr.close();
		}
		catch(Exception e)
		{
			System.out.println("Exception in eMailing : "+e.getMessage());
			Msg=false;
		}
		
	
		return Msg;
	
	}


	public static String IndianCurrencyFormat(String Amount)
	{
		String a=Amount.trim();
		//System.out.println("a val "+a);
		
		a=a.replaceAll(",", "");
		//System.out.println("a after->"+a);
		boolean neg=false;
			if((a.substring(0,1).equals("-")))
					{
					a=a.substring(1);
					neg=true;
					}
		String amount[]=a.split("\\.");
		String num=""+amount[0];
		String desimal="00";
		if(amount.length==2)
		{
		desimal=""+amount[1];
		}
		int leng=num.length();
		if(leng>3)
		{
			String b="";
			for(int i=num.length()-3;i>=-2;)
	    	{
	    		if(i==num.length()-3)
	    		{
	    			//System.out.println(num.substring(i));
	    			b=""+num.substring(i);
	    				
	    		}
	    		else if(i>-1)
	    		{
	    			//System.out.println("Only Two 1->"+i+"--"+num.substring(i,i+2));
	    			b=num.substring(i,i+2)+","+b;
	    		}
	    		else if(i!=0&&i!=-2)
	    		{
	    			//System.out.println("Only Two 1->"+i+"--"+num.substring(0,1));
	    			b=num.substring(0,1)+","+b;
	    		}
	    		
	    			i=i-2;	    		
	    	}
			num=b;
			
		}
		String Result="";
		if(amount.length==2)
		{
			Result=num+"."+desimal.substring(0,2);
		//System.out.println(num+"."+desimal);
		}
		else
		{
			Result=num;
			//System.out.println(num);
		}
		if(neg==true)
		{
			Result="-"+Result;
		}
	return Result;
	}
	
	public static String ForgotPwdeMailFormat(String UserId, String Pwd,String WebAdd) 
	{
		String Msg=null;
	
		Msg="<html><body><table background='http://www.serp.ap.gov.in/admin/images/BG.JPG' width='610px;' align='center'>"+
			"<tr><td><img src='http://www.serp.ap.gov.in/admin/images/Header.JPG'></td>"+
			"</tr><tr height='100px;'><td><table width='97%' align='cetner' border='0' cellpadding='0' cellspacing='0'>"+
			"<tr><td style='font-size:15px;'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;We have processed your request for account information. Your login information is : <br><br>"+
			"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Username&nbsp;&nbsp;: </b>"+UserId+"<br>"+
			"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Password&nbsp;&nbsp;&nbsp;: </b>"+Pwd+"<br><br><br>"+
			"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Click <a href='"+WebAdd+"' target='_blank'>here</a> to log in now<br><br>"+
			"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;If you experienc problems clicking above link, you can click<br>"+
			"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href='"+WebAdd+"' target='_blank'>"+WebAdd+"</a> to log in.<br><br><br>"+
			"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Thanks & Regards,<br>"+
			"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;SERP TCS Team<br><br>"+
			"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color='red'>Note : </font>Don't reply to this mail. Its a system generated mail."+
			"</td></tr></table></td></tr><tr>"+
			"<td><img src='http://www.serp.ap.gov.in/admin/images/FPWDFooter.JPG'></td>"+
			"</tr></table></body></html>";
	return Msg;
	}
	
	
	public static String ForgotPwdSMSMsg(String UserId, String Pwd,String WebAdd) 
	{
		String Msg=null;
	
		Msg="One time passsword for "+UserId+" user id is "+Pwd +". Please login & change password at "+WebAdd;	
		
	return Msg;
	}
	public static String CreateUsereMailFormat(String UserId, String Pwd) 
	{
		String Msg=null;
	
		Msg="<html><body><table background='http://www.serp.ap.gov.in/admin/images/BG.JPG' width='610px;' align='center'> "+
		"<tr><td><img src='http://www.serp.ap.gov.in/admin/images/Header.JPG'></td> "+
		"</tr><tr height='100px;'><td><table width='97%' align='cetner' border='0' cellpadding='0' cellspacing='0'> "+
		"<tr><td style='font-size:15px;'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;An Account has been created for you. Your login information is : <br><br> "+
		"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Username&nbsp;&nbsp;: </b>"+UserId+"<br> "+
		"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Password&nbsp;&nbsp;&nbsp;: </b>"+Pwd+"<br><br><br> "+
		"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Click <a href='http://www.serp.ap.gov.in/admin' target='_blank'>here</a> to log in now<br><br> "+
		"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;If you experienc problems clicking above link, you can click<br> "+
		"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href='http://www.serp.ap.gov.in/admin' target='_blank'>http://www.serp.ap.gov.in/admin</a> to log in.<br><br><br> "+
		"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Thanks & Regards,<br> "+
		"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;SERP TCS Team<br><br> "+
		"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color='red'>Note : </font>Don't reply to this mail. Its a system generated mail. "+
		"</td></tr></table></td></tr><tr> "+
		"<td><img src='http://www.serp.ap.gov.in/admin/images/FPWDFooter.JPG'></td> "+
		"</tr></table></body></html> ";
		return Msg;
	}
	
	public static ArrayList timeComboValues()
	{
		int i;
		int n=48;
		int c=0;
		String data="";
		//ArrayList<String> al;
		/*System.out.println("Time is  00:00");
		System.out.println("Time is  00:30");*/
		ArrayList ResultList = new ArrayList();
		ArrayList innerList = new ArrayList();
		
		innerList = new ArrayList();
		innerList.add("00:00");
		innerList.add("00:00");
		ResultList.add(innerList);
		
		innerList = new ArrayList();
		innerList.add("00:30");
		innerList.add("00:30");
		ResultList.add(innerList);
		
		
		for ( i=2; i<n; i++)
		{
			//System.out.println(i);
			if(i%2==0)
			{
				c=i/2;
				if(c<10)
				{
					data = "0"+c+":00";
				}
				else
				{
					data = c+":00";					
				}
				innerList = new ArrayList();
				innerList.add(data);
				innerList.add(data);
				
				ResultList.add(innerList);
			}
			else
			{
				c=i/2;
				if(c<10)
				{
					data = "0"+c+":30";
				}
				else
				{
					data = c+":30";					
				}
				innerList = new ArrayList();
				innerList.add(data);
				innerList.add(data);
				ResultList.add(innerList);
			}
		}
		return ResultList;
	}
	public static boolean  isNullObj(Object aoCheckObject)
    {
        if (aoCheckObject == null)
        {
            return true;
        }

        return false;
    }
	
	
	public static String htmlConvertion(String strInput)
	{
		String strOutput = "";
		try
		{
			strInput = checkNullObj(strInput);
			
			  /*HashMap strToHTML = new HashMap();
			  strToHTML.put("–","&ndash;");
			  strToHTML.put("—","&mdash;");
			  strToHTML.put("¡","&iexcl;");
			  strToHTML.put("¿","&iquest;");
			  strToHTML.put("\"","&quot;");
			  strToHTML.put("“","&ldquo;");
			  strToHTML.put("”","&rdquo;");
			  strToHTML.put("\"","&#39;");
			  strToHTML.put("‘","&lsquo;");
			  strToHTML.put("’","&rsquo;");
			  strToHTML.put("«","&laquo;");
			  strToHTML.put("»","&raquo;");
			  strToHTML.put(" ","&nbsp;;");
			  
			  strToHTML.put("&","&amp;");
			  strToHTML.put("¢","&cent;");
			  strToHTML.put("©","&copy;");
			  strToHTML.put("÷","&divide;");
			  strToHTML.put(">","&gt;");
			  strToHTML.put("<","&lt;");
			  strToHTML.put("µ","&micro;");
			  strToHTML.put("·","&middot;");
			  strToHTML.put("¶","&para;");
			  strToHTML.put("±","&plusmn;");
			  strToHTML.put("€","&euro;");
			  strToHTML.put("£","&pound;");
			  strToHTML.put("®","&reg;");
			  strToHTML.put("§","&sect;");
			  strToHTML.put("™","&trade;");
			  strToHTML.put("¥","&yen;");*/
			  char ch = 0;
			  for(int i = 0 ; i<strInput.length();i++)
			  {
					
					 ch = strInput.charAt(i);
					int j = (int) ch;
			//		System.out.println("iiii::"+j);
					strOutput = strOutput+"&#"+j+";";
				}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return strOutput;
	}
	
	public static ArrayList getArrayListfromHashMap(HashMap dataList)
	{
		ArrayList resultList = new ArrayList();
		try
		{
			Collection colValue = dataList.values();
			Iterator itr = colValue.iterator();
		 
			 while(itr.hasNext())
			 {
				 resultList.add((ArrayList)itr.next());
			 }
		}
		catch(Exception e)
		{
			System.out.println("Exception in CommonUtiliyt @ getArrayListfromHashMap : "+e.getLocalizedMessage());
		}
		System.out.println("Result List : "+resultList);
		return resultList;
	}
	
	public static HashMap getHashMapfromArrayList(ArrayList dataList,int keyPosition,int valuePosition)
	{
		HashMap resultList = new HashMap();
		try
		{
			ArrayList tempList = new ArrayList();
			for(int looper=0;looper<dataList.size();looper++)
			{
				tempList = (ArrayList)dataList.get(looper);
				System.out.println("HashMap Temp : "+tempList);
				if(valuePosition==-1)
				{
					resultList.put(tempList.get(keyPosition),tempList);
				}
				else
				{
					resultList.put(tempList.get(keyPosition),tempList.get(valuePosition));
				}
			}
		 
		}
		catch(Exception e)
		{
			System.out.println("Exception in CommonUtiliyt @ getHashMapfromArrayList : "+e.getLocalizedMessage());
		} 
		return resultList;
	}

	public static ArrayList getHashMapElementsFromArraylistElements(ArrayList listwithHeader)
	{
		ArrayList resultList = new ArrayList();
		try
		{
			if(listwithHeader!=null && listwithHeader.size()>1)
			{
				ArrayList headerList = (ArrayList)listwithHeader.get(0);
					
			   HashMap hashList 	= new HashMap();
			   ArrayList dataList 	= new ArrayList(); 
				
					for(int i=1;i<listwithHeader.size();i++)
					{ 
						hashList 	= new HashMap();
						dataList = (ArrayList)listwithHeader.get(i);
						
						for(int looper=0;looper<dataList.size();looper++)
						{
							hashList.put(headerList.get(looper).toString().trim(), dataList.get(looper).toString().trim());
						}
						resultList.add(hashList);
					}
			}
			
		}
		catch(Exception e)
		{
			System.out.println("Exception in CommonUtiliyt @ getHashMapElementsFromArraylistElements : "+e.getLocalizedMessage());
		}
		
		return resultList;
	}
	
	public static ArrayList getKeysValuefromHashMap(HashMap dataList)
	{
		ArrayList resultList = new ArrayList();
		try
		{
			String keyStr   = "";
			String valueStr = "";
			
			ArrayList keyArrayList = new ArrayList();
			ArrayList valueArrayList = new ArrayList();
			
			Iterator entries = dataList.entrySet().iterator();
			System.out.println("dataList.keySet().toArray() : "+dataList.keySet().toString());
			while (entries.hasNext()) 
			{
			    Map.Entry entry = (Map.Entry) entries.next();
			     keyStr =  entry.getKey().toString();
			     valueStr = entry.getValue().toString();
			     
			     keyArrayList.add(keyStr);
			     valueArrayList.add(valueStr);
			   
			}
			resultList.add(keyArrayList);
			resultList.add(valueArrayList);
		}
		catch(Exception e)
		{
			System.out.println("Exception in CommonUtiliyt @ getKeysValuefromHashMap : "+e.getLocalizedMessage());
		}
		System.out.println("Result List : "+resultList);
		return resultList;
	}
	
	public static String CapchaImage(String text)
	{
		String returnResult ="";
		
		try
		{
			//create String object to be converted to image
		       String sampleText = text;
		 
		        //Image file name
		       String fileName = "Image";
		        
		        //create a File Object
		        File newFile = new File("./" + fileName + ".jpeg");
		         
		        //create the font you wish to use
		        Font font = new Font("Tahoma", Font.TYPE1_FONT, 15);
		         
		           
		        //create the FontRenderContext object which helps us to measure the text
		        FontRenderContext frc = new FontRenderContext(null, true, true);
		         
		        //get the height and width of the text
		        Rectangle2D bounds = font.getStringBounds(sampleText, frc);
		        int w = (int) bounds.getWidth()+10;
		        int h = (int) bounds.getHeight()+10;
		        
		        //create a BufferedImage object
		       BufferedImage image = new BufferedImage(w, h,   BufferedImage.TYPE_INT_RGB);
		        
		        //calling createGraphics() to get the Graphics2D
		        Graphics2D g = image.createGraphics();
		        
		        //set color and other parameters
		        g.setColor(Color.WHITE);
		        g.fillRect(0, 0, w, h);
		        g.setColor(Color.BLACK);
		        g.setFont(font);
		        g.setBackground(Color.gray);
		             
		       g.drawString(sampleText, (float) bounds.getX()+5, (float) -bounds.getY()+5);
		       
		      //releasing resources
		      g.dispose();
		       
		     
		       
		   	ByteArrayOutputStream baos = new ByteArrayOutputStream();
		    ImageIO.write(image, "png", baos);
		    byte[] res=baos.toByteArray();
		    returnResult = Base64.encodeBytes(baos.toByteArray());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return returnResult;
	}
	
	
	public static String generateRandomStr(int length,HttpSession session,String sesValName) 
	{    
		Random rng= new Random();
		String characters="123456789ABCDEFGHIJKLMNPQRSTUVWXYZ987654321";
		
		char[] text = new char[length];     
		for (int i = 0; i < length; i++)     
		{         
			text[i] = characters.charAt(rng.nextInt(characters.length()));     
		}     
		session.setAttribute(sesValName, new String(text));
		return new String(text); 
	} 
	
	public static String removeSeconds(String time){
	   try
	   {	
			if(time.contains(":"))
			{
				time   =  time.substring(0,time.lastIndexOf(':'));
			}
	   }
	   catch(Exception e)
	   {
		   e.printStackTrace();
	   }
	  return time;	
		
	}
   public static String getEndDate(String startDate,int noOfdays){
	   String endDate = "";
	   SimpleDateFormat newDateFormat = new SimpleDateFormat("dd-MM-yyyy");
		try {
			Date start = newDateFormat.parse(startDate);
			Calendar c = Calendar.getInstance(); 
			c.setTime(start); 
			c.add(Calendar.DATE, 7);                                                                 
			start = c.getTime();
			endDate = start.toString();
		} catch (ParseException e) {
			 
			e.printStackTrace();
		}
	   
	   return endDate;
   }
	
   
   
	public static HashMap getDiskFileItemFactory( ServletFileUpload sfu,HttpServletRequest request)
	{
		HashMap myFileItems =new HashMap();
		
		try
		{
			  List items = sfu.parseRequest(request);
	    	   	    	   
	    	   FileItem  myFileItem;
	    	   
	         
	    	   for(int loop=0;loop<items.size();loop++)
	    	   {
	    		   myFileItem = (FileItem) items.get(loop);
	    		    myFileItems.put(myFileItem.getFieldName(), myFileItem);
	    		   
	    	   }
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return myFileItems;
	}
	
	
	public static java.sql.Date getSqlDate(String userdate,String userDateFormat)
	{
		java.sql.Date modifiedDate = null;
		  DateFormat dtFormat = new SimpleDateFormat(userDateFormat);
		try
		{
			modifiedDate = new java.sql.Date((dtFormat.parse(userdate)).getTime());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return modifiedDate;
	}
	
	public static java.sql.Timestamp getSqlTimeStamp(String userdate,String userDateFormat)
	{
		java.sql.Timestamp modifiedDate = null;
		  DateFormat dtFormat = new SimpleDateFormat(userDateFormat);
		try
		{
			modifiedDate = new java.sql.Timestamp((dtFormat.parse(userdate)).getTime());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return modifiedDate;
	}
	
	 public static String getReportingDescByCode(int repCode)
	 {
		   String result = "";
			try 
			{
				switch (repCode)
				{
					case 1 : result="Chief Executive Officer";
							break;
						
					case 2 : result="Employees reporting to CEO";
							 break;
							 
					case 3 : result="Employees reporting to Directors";
							 break;
				
					case 4 : result="Employees in Deputation";
					 		 break;
					 		 
					default : result="Not taged Employees";
			 		 		  break;
				}
			}
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		   
		   return result;
	   }
	 
		public static String getDateAddOrSubDays(int daysdiff,String returnDateformat)
		{
			String result="";
			
			try
			{
				SimpleDateFormat newDateFormat = new SimpleDateFormat(returnDateformat);
				Date MyDate = new Date();
				Calendar c = Calendar.getInstance();
				c.setTime(MyDate);
				c.add(Calendar.DATE, daysdiff);
				MyDate.setTime(c.getTime().getTime());
				
				result = newDateFormat.format(MyDate);
			}
			catch(Exception e)
			{
				System.out.println("Exception @ getDateAddOrSubDays : "+e);
			}
			return result;
			
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

	            // Avoid anything between script tags
	            scriptPattern = Pattern.compile("<object(.*?)>(.*?)</object>", Pattern.CASE_INSENSITIVE);
	            value = scriptPattern.matcher(value).replaceAll("");
	            
	            // Avoid anything between '' codes
	            scriptPattern = Pattern.compile("'(.*?)'", Pattern.CASE_INSENSITIVE);
	            value = scriptPattern.matcher(value).replaceAll("");
	            
	            // Avoid anything between "" codes
	            scriptPattern = Pattern.compile("\"(.*?)\"", Pattern.CASE_INSENSITIVE);
	            value = scriptPattern.matcher(value).replaceAll("");
	            
	            // Avoid anything between '' codes
	            scriptPattern = Pattern.compile("<(.*?)>", Pattern.CASE_INSENSITIVE);
	            value = scriptPattern.matcher(value).replaceAll("");
	            

	            // Avoid href tags
	            scriptPattern = Pattern.compile("<a href[\r\n]*=[\r\n]*\\\'(.*?)>(.*?)</a>", Pattern.CASE_INSENSITIVE);
	            value = scriptPattern.matcher(value).replaceAll("");
            
			     // Avoid input tags
			     scriptPattern = Pattern.compile("<input (.*?)>", Pattern.CASE_INSENSITIVE);
			     value = scriptPattern.matcher(value).replaceAll("");
			     
				// Avoid input tags
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
	            
	            // Avoid avoids string " 
	            scriptPattern = Pattern.compile("\"", Pattern.CASE_INSENSITIVE);
	            value = scriptPattern.matcher(value).replaceAll("");

	            // Avoid avoids '
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
	            
	            // Avoid avoids string between ' tags
	            scriptPattern = Pattern.compile("", Pattern.CASE_INSENSITIVE);
	            value = scriptPattern.matcher(value).replaceAll("");
	            
	            //System.out.println("Value : "+value);
	        }
	        return value;
	    }
		  
		  public static boolean isValidDate(String input)
			{
				if(input.matches("([0-9]{2})(/|-)([0-9]{2})(/|-)([0-9]{4})"))  //([0-9]{2}):([0-9]{2}):([0-9]{2})
				{
					return true;
				}
				else
				{
					return false;
				}
			}
		  
		  public static ArrayList getTimeHourList()
		  {
			  ArrayList timeList  = new ArrayList();
			  ArrayList innerList = new ArrayList();
			  
			  for(int i=0;i<24;i++)
			  {
				  innerList = new ArrayList();
				  
				  if(i<10)
				  {
					  innerList.add("0"+i);
					  innerList.add("0"+i);
				  }
				  else
				  {
					  innerList.add(""+i);
					  innerList.add(""+i);
				  }
				  timeList.add(innerList);
			  }
			  
			  return timeList;
		  }
		  
		  public static ArrayList getTimeMinuteList()
		  {
			  ArrayList timeList  = new ArrayList();
			  ArrayList innerList = new ArrayList();
			  
			  innerList.add("00");
			  innerList.add("00");
			  timeList.add(innerList);
			  
			  innerList = new ArrayList();
			  innerList.add("15");
			  innerList.add("15");
			  timeList.add(innerList);
			  
			  innerList = new ArrayList();
			  innerList.add("30");
			  innerList.add("30");
			  timeList.add(innerList);
			  
			  innerList = new ArrayList();
			  innerList.add("45");
			  innerList.add("45");
			  timeList.add(innerList);
			  
			  
			  
			  return timeList;
		  }
		  
		  
		  public static BufferedImage rotateImageToanAngle(BufferedImage img, double angle)
		  {
			  BufferedImage dimg = null;
			  try 
			  {
				  double sin = Math.abs(Math.sin(Math.toRadians(angle))),
				           cos = Math.abs(Math.cos(Math.toRadians(angle)));

				    int w = img.getWidth(), h = img.getHeight();

				    int neww = (int) Math.floor(w*cos + h*sin),
				        newh = (int) Math.floor(h*cos + w*sin);

				     dimg = new BufferedImage(neww, newh, img.getType());
				    Graphics2D g = dimg.createGraphics();

				    g.translate((neww-w)/2, (newh-h)/2);
				    g.rotate(Math.toRadians(angle), w/2, h/2);
				    g.drawRenderedImage(img, null);
				    g.dispose();

			  }
			  catch(Exception e)
			  {
				  e.printStackTrace();
			  }
			  
			  return dimg;
		  }
		  
		  public static String getAMSDefaultMonth(String selMonth,String selYear)
		  {
			  if(selYear.equals("") || selYear.equals("-1"))
				{
					selYear = CommonUtility.getDateTime("yyyy");
				}
			  
			 		  
			  String currentDay = CommonUtility.getDateTime("dd");
				
				if((selMonth.equals("") || selMonth.equals("-1")) && Integer.parseInt(currentDay)>=25)
				{
				 if(selMonth.equals("") || selMonth.equals("-1"))
					{
						selMonth = CommonUtility.getDateTime("MM");
					}
					if((Integer.parseInt(selMonth)+1)==13)
					{
						selMonth="1";
						selYear = ""+(Integer.parseInt(selYear)+1);
					}
					else
					{
						selMonth=""+(Integer.parseInt(selMonth)+1);
					}
					
					if(Integer.parseInt(selMonth)<10)
					{
						selMonth="0"+selMonth;
					}
				}
				else
				{
					if(selMonth.equals(""))
					{
						selMonth = CommonUtility.getDateTime("MM");
					}
				}
				
				return selMonth;
				
		  }
		  
		  public static String getAMSDefaultYear(String selMonth,String selYear)
		  {
			 
			  if(selYear.equals("") || selYear.equals("-1"))
				{
					selYear = CommonUtility.getDateTime("yyyy");
				}
			  
			  if(selMonth.equals("") || selMonth.equals("-1"))
				{
					selMonth = CommonUtility.getDateTime("MM");
				}
			  
			  String currentDay = CommonUtility.getDateTime("dd");
				
				if((selMonth.equals("") || selMonth.equals("-1")) && Integer.parseInt(currentDay)>=25)
				{
				
					if((Integer.parseInt(selMonth)+1)==13)
					{
						selMonth="1";
						selYear = ""+(Integer.parseInt(selYear)+1);
					}
					else
					{
						selMonth=""+(Integer.parseInt(selMonth)+1);
					}
					
					if(Integer.parseInt(selMonth)<10)
					{
						selMonth="0"+selMonth;
					}
				}
				else
				{
					if(selMonth.equals(""))
					{
						selMonth = CommonUtility.getDateTime("MM");
					}
				}
				
				
				return selYear;
				
		  }
		  public static HashMap getHashMapfromArrayListWithcolumnName(ArrayList dataList)
			{
				HashMap resultList = new HashMap();
				try
				{
					if(dataList.size()==2)
					{
						ArrayList headers = new ArrayList();
						ArrayList values = new ArrayList();
						headers = (ArrayList)dataList.get(0);
						values = (ArrayList)dataList.get(1);
					
						for(int looper=0;looper<headers.size();looper++)
						{				
								resultList.put(headers.get(looper),values.get(looper));				
						}	
					}
					else
					{
						System.out.println("Not possible");
					}
				}
				catch(Exception e)
				{
					System.out.println("Exception in CommonUtiliyt @ getHashMapfromArrayList : "+e.getLocalizedMessage());
				} 
				return resultList;
			} 
		  public static boolean urlaccesscontrolcheck(HttpSession session,HttpServletRequest request)
		  
		  {
			  boolean status=false;
			  try{
			  String roleId = CommonUtility.checkNullObj(session.getAttribute("roleId"));
			  String url = request.getRequestURL().toString();
			  System.out.println("--current url"+url);
			  ArrayList loginurllist = (ArrayList)session.getAttribute("loginurllist");
	
			  
			  if(loginurllist!=null && loginurllist.size()>0)
				{
					for(int looper=0;looper<loginurllist.size();looper++)
					{
							if(url.toLowerCase().contains(loginurllist.get(looper).toString().toLowerCase()))
							{
								status = true;
								break;
							}
					}
				}
			  else
			  {
				  status=false;
			  }
			  
			  }catch(Exception e)
			  {
				  e.printStackTrace();
			  }
			  
			  
			return status;
			  
		  }
		  
		  
		  public static boolean getIEBrowserStatus(HttpServletRequest request) 
		  {
			  boolean status = true;
			  
			  String ua = request.getHeader( "User-Agent" );
			 
			    
		/*	    int msie = ua.indexOf("MSIE");
                if (msie > 0) {
                    // IE 10 or older => return version number
                    return parseInt(ua.substring(msie + 5, ua.indexOf('.', msie)), 10);
                }

                int trident = ua.indexOf("Trident/");
                if (trident > 0) 
                {
                    // IE 11 => return version number
                    int rv = ua.indexOf("rv:");
                    return parseInt(ua.substring(rv + 3, ua.indexOf('.', rv)), 10);
                }

                int edge = ua.indexOf("Edge/");
                if (edge > 0) {
                   // Edge (IE 12+) => return version number
                   return parseInt(ua.substring(edge + 5, ua.indexOf('.', edge)), 10);
                }*/

                if(ua.indexOf("MSIE")>0 || ua.indexOf("Trident/")>0 || ua.indexOf("Edge/")>0)
                {
                	status = true;
                }
                else
                {
                	status = false;
                }
			    
			    
			    return status;
			}	  
		  public static String generateHtmlTable(ArrayList bodylist,ArrayList Headers,int rows, int cols,String  tableName)
		  {
			  String result = "";
			  String style = " style=\" background-repeat:no-repeat; width:80%;margin:1;\" cellpadding=\"0\" cellspacing=\"0\" border=\"1\"";
			  String id = "id=" + tableName;
			  result  = result + "<table  "+id+style+"> ";
			  ArrayList tempList = new ArrayList();
			  
			 
				  result  = result + "<tr> ";
				  for(int j=0;j<cols;j++)
				  {
					  result  = result+ "<th style=\"text-align: center;\"><b>"+Headers.get(j);
					  result  = result+ "</b></th>";
				  }
				  result  = result + "</tr> ";
			  
			  
			  
			  for(int i=0;i<rows;i++)
			  {
				  tempList = (ArrayList)bodylist.get(i);
				  result  = result + "<tr> ";
				  for(int j=0;j<cols;j++)
				  {
					  result  = result+ "<td>"+tempList.get(j);
					  result  = result+ "</td>";
				  }
				  result  = result + "</tr> ";
			  }
			  result  = result + "</table> ";
			  return result;
		  }
		  
		  
		  
			public static String getStringFromFileItem(FileItem inputValue)
			{
				String result = ""; 
				try
				{
					if(inputValue!=null)
					{
						result = checkNullObj(inputValue.getString());
					}
					else
					{
						result="";
					}
				}
				catch(Exception e)
				{
					//e.printStackTrace();
				}
				
				return result;
			}
			
			

			public static boolean copyFileAndDelete(File oldFile,File newFile,boolean delOldFile,boolean ignoreFileExist)
			{
				boolean result = false; 

			    	try
			    	{ 
			    		if(oldFile.exists()==true && oldFile.isFile()==true && (newFile.exists()==false || ignoreFileExist==true) )
			    		{
							InputStream inStream = new FileInputStream(oldFile);
							OutputStream outStream = new FileOutputStream(newFile); 
							
				    	    byte[] buffer = new byte[1024];
	
				    	    int length;
				    	    //copy the file content in bytes
				    	    while ((length = inStream.read(buffer)) > 0)
				    	    { 
				    	    	outStream.write(buffer, 0, length); 
				    	    }
	
				    	    inStream.close();
				    	    outStream.close(); 
				    	    
				    	    //delete the original file
				    	    if(delOldFile==true)
				    	    {
				    	    	oldFile.delete();
				    	    }
				    	    
				    	    result = true;
			    		}
			    		else if(oldFile.exists()==false)
			    		{
			    			result = true;
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
			 
			
			public static String CapchaColorImage(String text)
			{
				String returnResult ="";
				
				try
				{
					
					//System.out.println("color txt : "+text);
					//create String object to be converted to image
				       String sampleText = text.replace("", "  ");
				 
				        //Image file name
				       String fileName = "Image";
				        
				        //create a File Object
				        File newFile = new File("./" + fileName + ".jpeg");
				         
				        //create the font you wish to use
				        Font font = new Font("Plantagenet Cherokee", Font.BOLD, 17);
				         
				           
				        //create the FontRenderContext object which helps us to measure the text
				        FontRenderContext frc = new FontRenderContext(null, true, true);
				         
				        //get the height and width of the text
				        Rectangle2D bounds = font.getStringBounds(sampleText, frc);
				        int w = (int) bounds.getWidth()+5;
				        int h = (int) bounds.getHeight()+1;
				        
				        //create a BufferedImage object
				       BufferedImage image = new BufferedImage(w, h,   BufferedImage.TYPE_INT_RGB);
				        
				        //calling createGraphics() to get the Graphics2D
				        Graphics2D g = image.createGraphics();
				        
				        //set color and other parameters
				        g.setColor(new Color(238, 238, 238)); 
				        g.fillRect(0, 0, w, h);
				        
				     // set gradient font of text to be converted to image
				        GradientPaint gradientPaint = new GradientPaint(10, 15, Color.RED , 20, 30, Color.BLUE, true);
				        g.setPaint(gradientPaint);
				        g.setFont(font);
				        
				       g.drawString(sampleText, (float) bounds.getX()+2, (float) -bounds.getY()+0);
				       
				      //releasing resources
				      g.dispose();
				       
				     
				       
				   	ByteArrayOutputStream baos = new ByteArrayOutputStream();
				    ImageIO.write(image, "png", baos);
				    byte[] res=baos.toByteArray();
				    returnResult = Base64.encodeBytes(baos.toByteArray());
				}
				catch(Exception e)
				{
					e.printStackTrace();
				} 
				
				return returnResult;
			}
			
	public static byte[] getByteArrayFromFile(File file)
	{
		 FileInputStream fileInputStream = null;
	        byte[] bytesArray = null;

	        try 
	        {
 
	            bytesArray = new byte[(int) file.length()];

	            //read file into bytes[]
	            fileInputStream = new FileInputStream(file);
	            fileInputStream.read(bytesArray);

	        } catch (IOException e) 
	        {
	            e.printStackTrace();
	        }
	        finally
	        {
	            if (fileInputStream != null)
	            {
	                try 
	                {
	                    fileInputStream.close();
	                } 
	                catch (IOException e)
	                {
	                    e.printStackTrace();
	                }
	            } 
	        }
	    return bytesArray;
	} 
			
	public static String emptyCheck(String literal)
	 {
	        String replaceString = null;

	        if (literal.equals("") || literal.length() == 0)
	        {
	            replaceString = "-";
	        }
	        else 
	        {
	            replaceString = literal;
	        }

	        return replaceString;
	    } 
}