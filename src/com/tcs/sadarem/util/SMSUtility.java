package com.tcs.sadarem.util;


import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;

public class SMSUtility 
{
	static ResourceBundle bundle = ResourceBundle.getBundle("CommonConstants");
	static final private Logger log = Logger.getLogger(SMSUtility.class);
	
	   static String SMSUserID 	 = bundle.getString("SMSUserID");  
	   static String SMSPwd		 = bundle.getString("SMSPwd");  
	   static String SMSsenderid = bundle.getString("SMSsenderid");
	   
	   
	   static URL url = null;
	   static HttpURLConnection connection = null;  

	  // Method for sending single SMS.  
	   
	   public static ArrayList sendmysms
		(
				String mobileno, String message
		)
		{
			String Result ="";
			ArrayList DataList = new ArrayList();
			String MobileList[] = mobileno.split(",");
		 //System.out.println("Ceil : "+Math.ceil(Double.parseDouble(""+MobileList.length)/5.0)); 
			
			
			int count = (int)Math.ceil(Double.parseDouble(""+MobileList.length)/5.0);
			 int position =0;
			 int inner_pos = 0;
			 String ContactNos = "";
					
			 int totalRec =0;
			 String SMSIDs = "";
			 int failedCount = 0;
			 int flagNCount =0;
			 int flagECount =0;
			 
			 ArrayList resultList = new ArrayList();
								 for(int i=0;i<count;i++)
								 {
									 ContactNos="";
									 inner_pos=0;
									 while(inner_pos<5 && position<MobileList.length)
									 {
										 if(ContactNos.equals(""))
										 {
											 ContactNos=MobileList[position];
										 }
										 else
										 {
										 ContactNos+=","+MobileList[position];
										 }
										 
										 inner_pos=inner_pos+1;
										 position=position+1;
									 }
									 DataList = (ArrayList)PushMySMS(ContactNos, message);
									 
									 if(DataList.size()>0)
										 
									 {
										 resultList.add(DataList);
									 }

									// System.out.println("SMS Status : "+DataList);
									 log.info("SMSUtility SMS Status("+i+") : "+DataList);	
						    	   		
								 }
						
								 for(int i=0;i<resultList.size();i++)
								 {
									 DataList =(ArrayList)resultList.get(i);
									 
									 if(DataList.size()==2)
									 {
										 if(DataList.get(0).toString().equalsIgnoreCase("N")) // Not sent count
										 {
											 flagNCount=flagNCount+1;
										 }
										 
										 if(DataList.get(0).toString().equalsIgnoreCase("E"))    // Exception count
										 {
											 flagECount=flagNCount+1;
										 }
										 
										 SMSIDs += DataList.get(1).toString();
										 
										/* totalRec 	 = totalRec+Integer.parseInt(DataList.get(1).toString());
										 failedCount = failedCount+Integer.parseInt(DataList.get(2).toString());*/
									 }
									 
								 }
								 
								 DataList = new ArrayList();
								  
								 if(flagNCount>0 || flagECount>0)
								 {
									 DataList.add("N");
								 }
								 else
								 {
									 DataList.add("Y");
								 }
								// DataList.add("(T:"+totalRec+",F: "+failedCount+",E:"+flagECount+")");
								 DataList.add(SMSIDs);

								 log.info("SMSUtility Final Status : "+DataList);			
					 
		 return DataList;
		}
			    
		private static ArrayList PushMySMS(String mobileno, String message)
		{
			ArrayList ResultList = new ArrayList();
			try
			{
				  URL url = new URL("http://msdgweb.mgov.gov.in/esms/sendsmsrequest");  
		          connection = (HttpURLConnection) url.openConnection();  
	              connection.setDoInput(true);  
	              connection.setDoOutput(true);
		          connection.setRequestMethod("POST");
		          connection.setFollowRedirects(true);
		          ArrayList Datalist = new ArrayList();
		          String Result ="";

	        		  try
	        		  {
	        			  HashMap smsErrorCode = new HashMap();
							smsErrorCode.put("401","Credentials Error, may be invalid username or password");
							smsErrorCode.put("402","X messages submitted successfully");
							smsErrorCode.put("403","Credits not available");
							smsErrorCode.put("404","Internal Database Error");
							smsErrorCode.put("405","Internal Networking Error");
							smsErrorCode.put("406","Invalid or Duplicate numbers");
							smsErrorCode.put("407","Network Error on SMSC");
							smsErrorCode.put("408","Network Error on SMSC");
							smsErrorCode.put("409","SMSC response timed out, message will besubmitted");
							smsErrorCode.put("410","Internal Limit Exceeded, Contact support");
							smsErrorCode.put("411", "Sender ID not approved.");
							smsErrorCode.put("412","Sender ID not approved.");
							smsErrorCode.put("413","Suspect Spam, we do not accept these messages.");
							smsErrorCode.put("414","Rejected by various reasons by the operator such as DND, SPAM etc");
		  
							 Result = sendBulkSMS(SMSUserID, SMSPwd, SMSsenderid,mobileno, message);
							 
							  StringTokenizer Response = new StringTokenizer(Result, ",");
					  		 
							  while (Response.hasMoreElements())
						   		{
								  Datalist.add(Response.nextElement().toString().trim());
								}
							  
							  log.info("PushMySMS Datalist : "+Datalist);
							  
							  if(Datalist.size()>0)
							  {
								 // ResultList.add("Status Code : "+Datalist.get(0).toString()+" : "+"Total Numbers ["+Datalist.get(1).toString()+"] & Failed ["+Datalist.get(2).toString()+"]");
							  
								 if(Datalist.get(0).toString().trim().equals("402"))
							        {
								         ResultList.add("Y");
							        }
							        else
							        {
							      	  ResultList.add("N");
							        }
								 
								 	ResultList.add(Datalist.get(1).toString()); // Total 
							
								 }
								 else
								 {
									 log.info("PushMySMS " +Datalist.get(0)+" Datalist Ex : "+Datalist+"\n Key Value : "+smsErrorCode.get(Datalist.get(0).toString()));
								      //ResultList.add(""+smsErrorCode.get(Datalist.get(0).toString()));
								      ResultList.add("E");
								      ResultList.add(Datalist.get(1).toString()); // Total 
								  }
		        		
	        		  }
        			catch(Exception e)
        			{
        				log.info("PushMySMS Exception in SMS : "+e.getLocalizedMessage());
        				 ResultList.add("E");
					      ResultList.add("0"); // Total
						  ResultList.add("0");  // Failed Record
        			} 
	          
	        /*
	          .put("401","Credentials Error, may be invalid username or password")
	          .put("402","X messages submitted successfully")
	          .put("403","Credits not available")
	          .put("404","Internal Database Error")
	          .put("405","Internal Networking Error")
	          .put("406","Invalid or Duplicate numbers")
	          .put("407,"Network Error on SMSC")
	          .put(408","Network Error on SMSC")
	          .put("409","SMSC response timed out, message will besubmitted")
	          .put("410","Internal Limit Exceeded, Contact support")
	          .put("411", "Sender ID not approved.")
	          .put(412","Sender ID not approved.")
	          .put("413","Suspect Spam, we do not accept these messages.")
	          .put("414","Rejected by various reasons by the operator such as DND, SPAM etc")
	         */
		}
		catch(Exception e)
		{
			log.info("Exception in SMS : "+e.getLocalizedMessage());
			ResultList = new ArrayList();
	         ResultList.add(""+e.getMessage());
	         ResultList.add("N");
	         
		}
		//log.info("ResultList : "+ResultList);
		return ResultList;
	}
		

	private static String getStringFromInputStream(InputStream is) {
		 
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
 
		String line;
		try {
 
			br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
 
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
 
		return sb.toString();
 
}
		 


				// method for sending bulk SMS Modified
		public static String sendBulkSMS
		(
				String username,String password, String senderId,
				String mobileNos, String message
		)
		{
			String Result = "";
		
			try 
				{
					
				url = new URL("http://msdgweb.mgov.gov.in/esms/sendsmsrequest");
						
						connection = (HttpURLConnection) url.openConnection();
						connection.setDoInput(true);
						connection.setDoOutput(true);
						connection.setRequestMethod("POST");
						connection.setFollowRedirects(true);
						
						String smsservicetype = "bulkmsg"; // For bulk msg
						String query = "username=" + URLEncoder.encode(username)
							+ "&password=" + URLEncoder.encode(password)
							+ "&smsservicetype=" + URLEncoder.encode(smsservicetype)
							+ "&content=" + URLEncoder.encode(message) 
							+ "&bulkmobno=" + URLEncoder.encode(mobileNos, "UTF-8") 
							+ "&senderid=" + URLEncoder.encode(SMSsenderid);
		
						connection.setRequestProperty("Content-length", String
							.valueOf(query.length()));
						connection.setRequestProperty("Content-Type",
							"application/x-www-form-urlencoded");
						connection.setRequestProperty("User-Agent",
							"Mozilla/4.0 (compatible; MSIE 5.0; Windows 98; DigExt)");
		
						// open up the output stream of the connection
						DataOutputStream output = new DataOutputStream(connection
							.getOutputStream());
		
						// write out the data
						int queryLength = query.length();
						output.writeBytes(query);
						// output.close();
		
						//log.info("Resp Code:" + connection.getResponseCode());
						//log.info("Resp Message:" + connection.getResponseMessage());
		
						// get ready to read the response from the cgi script
						DataInputStream input = new DataInputStream(connection.getInputStream());
		
						// read in each character until end-of-stream is detected
						   for (int c = input.read(); c != -1; c = input.read())  
				            	 Result=Result+""+(char)c;
						input.close();
						}
						catch (Exception e) 
						{
							Result="000,00";
							//log.info("Something bad just happened.");
						//	log.info(e);
							//e.printStackTrace();
						}
				log.info("Result : "+Result);
			
				return Result;
			} 

}
