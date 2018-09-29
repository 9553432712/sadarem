package com.tcs.sadarem.util;


import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.bf.disability.Constants.CommonConstants;

import Aadhar.AadhaarUtility;

import com.ecentric.servicemodels.AadhaarProfile;
import com.lowagie.text.pdf.codec.Base64;
import com.tcs.sadare.issuetracksystem.DAO.CommonIssueApprovalDAO;
import com.tcs.sadare.issuetracksystem.DAO.CommonIssueApprovalDAOImpl;
import com.tcs.sadarem.common.DAO.CommonDAO;
import com.tcs.sadarem.common.DAO.CommonDAOImpl;

public class DisplayUploadedImages extends HttpServlet 
{
    
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
	  	          
            String Action = (String)request.getParameter("action");
            
            HttpSession session=request.getSession(true);
            
            if(Action!= null && !Action.equals("") && Action.equals("showsadaremimg"))
            {
            	  try
                  {  
            		  CommonDAO comObj = new CommonDAOImpl();
            		  
		             String sadaremid = CommonUtility.checkNullObj(request.getParameter("sadaremid")); 
		             //String distName = CommonUtility.checkNullObj(request.getParameter("distName")); 
		            
		             //String distName = comObj.getDistrictNameBySadaremID(sadaremid);
		             
		             String imgPath = CommonConstants.SADAREM_DOCUMENTS_PATH+sadaremid+"\\Profile\\Profilepic.JPG";
		             
		             
		            // System.out.println("imgPath : "+imgPath+"\n ");
		             
		             File empImage = new File(imgPath);
		             
		         //    System.out.println("empImage : "+empImage.exists());
		             
		             if(empImage.exists())
		             {
			             ByteArrayOutputStream baos = new ByteArrayOutputStream();		             
			             ImageIO.write(ImageIO.read(empImage), "jpeg", baos);
					   	   response.setContentType("image/jpeg");
			               OutputStream o = response.getOutputStream();
			               o.write(baos.toByteArray());
			               o.flush();
			               o.close();
		             }
	            }
	            catch (Exception e)
	            {
	              //e.printStackTrace();
	          //   System.out.println("Exception in DisplayUploadedImages : "+e.getLocalizedMessage());
	            }
            }
            else if(Action!= null && !Action.equals("") && Action.equals("downloadFile"))
            {
            	 CommonIssueApprovalDAO comObj1 = new CommonIssueApprovalDAOImpl();
            	 request.setCharacterEncoding("UTF-8");
     		    String reqId = CommonUtility.checkNullObj(request.getParameter("reqId"));
     		    String path="";
				try 
				{
					path = PasswordEncriptDecrypt.decrypt(reqId);
					 viewFile(path,reqId,".pdf", request, response);
				} 
				catch (Exception e)
				{
					e.printStackTrace();
				}
     	       
            
            }
            else if(Action!= null && !Action.equals("") && Action.equals("downloadCertificateIdCard"))
            {
            	 CommonIssueApprovalDAO comObj1 = new CommonIssueApprovalDAOImpl();
            	 request.setCharacterEncoding("UTF-8");
     		    String path = CommonUtility.checkNullObj(request.getParameter("path"));
     		    String filename="";
				try 
				{
					path = PasswordEncriptDecrypt.decrypt(path);
					viewCertiIDcard(path,filename, request, response);
				} 
				catch (Exception e)
				{
					e.printStackTrace();
				}
     	       
            
            }
            else if(Action!= null && !Action.equals("") && Action.equals("downloadPartAWithoutProofFiles"))
            {
            	
            	 request.setCharacterEncoding("UTF-8");
     		    String path = CommonUtility.checkNullObj(request.getParameter("reqId"));
     		    String filename="";
				try 
				{
					path = PasswordEncriptDecrypt.decrypt(path);
					viewPartaWithoutProofFile(path,filename, ".pdf", request, response);
				} 
				catch (Exception e)
				{
					e.printStackTrace();
				}
     	       
            
            }
            else if(Action!= null && !Action.equals("") && Action.equals("getAadharPhoto"))
            {
		            String aadharNo = CommonUtility.checkNullObj(request.getParameter("aadharNo"));
		            AadhaarProfile aadharProfile = null;
		           	String base64 = "",errmsg="";
		           	 try 
		           	 {
		           		  byte[] bytearray=null;
		           		 
		           			// aadharProfile = AadharDetails.getAadharDetails(aadharNo);
		           		  
		           		  aadharProfile = AadhaarUtility.getAadhaarProfileByUID(aadharNo);
                         
		       			 if (aadharProfile != null) 
		       			  {
		                        String invalidFlag = aadharProfile.getStatus();
		                        if (invalidFlag!=null && (invalidFlag.equalsIgnoreCase("100") || invalidFlag.equalsIgnoreCase("101")))
		                        {
		
		                            if (aadharProfile.getBase64file() != null && !aadharProfile.getBase64file().equalsIgnoreCase("101")) {
		                                base64 = aadharProfile.getBase64file();
		                            } else {
		                                base64 = "-";
		                            }
		                        } 
		                        else if (invalidFlag!=null && invalidFlag.substring(0, 3).equalsIgnoreCase("121")) 
		                        {
		                          errmsg  = "Given Aadhar Number is Cancelled/Rejected UID -  " + aadharNo + " due to reason '" + invalidFlag.substring(3, invalidFlag.length()) + "'";
		                         } 
		                        else
		                         {
		                       	 errmsg = "Given Aadhar Number is In-Valid. UID - " + aadharNo + ".";
		                       	 }
		                       if(base64!=null)
		                       {  
			                         bytearray = Base64.decode(base64);
			                         ByteArrayOutputStream baos = new ByteArrayOutputStream();		             
						             ImageIO.write(ImageIO.read(new ByteArrayInputStream(bytearray)), "jpg", baos);
								   	   response.setContentType("image/png");
						               OutputStream o = response.getOutputStream();
						               o.write(baos.toByteArray());
						               o.flush();
						               o.close();
		                       }
		                    }
		       		 }
		           	 catch (Exception e) 
		           	 {
		       		    e.printStackTrace();
		       		}
            }
            
            else if(Action!= null && !Action.equals("") && Action.equals("showphotoIssueimg"))
            {
            	String issueReqId = CommonUtility.checkNullObj(request.getParameter("issueid"));
            	String photoPath="";
            	
            	   photoPath = CommonConstants.ISSUETRACKING_ATTACHMENT1_PATH+"/"+CommonUtility.checkNullObj(request.getParameter("path"));
            	  //System.out.println("photoPath"+photoPath);
            	  File empImage = new File(photoPath);
            	  if(empImage.exists())
            	  {
            		  ByteArrayOutputStream baos = new ByteArrayOutputStream();
            		  ImageIO.write(ImageIO.read(empImage),"jpg",baos);
            		  response.setContentType("image/jpg");
            		  OutputStream o = response.getOutputStream();
            		  o.write(baos.toByteArray());
            		  o.flush();
            		  o.close();
            	  }
            	  
            	
            }
            else if(Action!= null && !Action.equals("") && Action.equals("showwithoutproofpartaimg"))
            {
            	  try
                  {  
            		  CommonDAO comObj = new CommonDAOImpl();
            		  
		             String adhaarId = CommonUtility.checkNullObj(request.getParameter("aadhaarid")); 		           
		             String requestID = CommonUtility.checkNullObj(request.getParameter("requestID")); 
		             
		             ArrayList paramList = new ArrayList();
		             ArrayList tempList = new ArrayList();

		             tempList = new ArrayList();
		             tempList.add("S");
		             tempList.add(requestID);
		             paramList.add(tempList);
		             
		             tempList = new ArrayList();
		             tempList.add("S");
		             tempList.add(requestID);
		             paramList.add(tempList);
		             
		             tempList = new ArrayList();
		             tempList.add("S");
		             tempList.add(adhaarId);
		             paramList.add(tempList);
		             
		             String filePath = DataAccess.getReturnResultByPstmt("SELECT profile_pic_path FROM sadarem_without_proof_request_master WITH(NOLOCK) WHERE request_id=? OR ( request_id=? AND proof_id=?) ", paramList);
		             
		             
		             String imgPath = CommonConstants.PARTA_ATTACHMENT_PATH+filePath;
		        //System.out.println("path-->"+imgPath);
		             //imgPath = "D:/SADAREMTG/PARTADOC/406734615107/406734615107.JPG";
		            // System.out.println("imgPath : "+imgPath+"\n ");
		             
		             File empImage = new File(imgPath);
		             
		           //System.out.println("empImage : "+empImage.exists());
		             
		             if(empImage.exists())
		             {
		            	 
			             response.setContentType("image/jpg");  	            
			               OutputStream o = response.getOutputStream();
			               o.write(CommonUtility.getByteArrayFromFile(empImage));
			               o.flush();
			               o.close();
		             }
	            }
	            catch (Exception e)
	            {
	              //e.printStackTrace();
	          //   System.out.println("Exception in DisplayUploadedImages : "+e.getLocalizedMessage());
	            }
            } 

            else if(Action!= null && !Action.equals("") && Action.equals("showpartaproofdetailsbyids"))
            {
            	  try
                  {   
            		  
		             String proof_path = CommonUtility.checkNullObj(request.getParameter("proof_path")); 		           
		             String proof_type = PasswordEncriptDecrypt.decrypt(CommonUtility.checkNullObj(request.getParameter("proof_type"))); 
		              
		             
		             String filePath = CommonConstants.PARTA_ATTACHMENT_PATH+PasswordEncriptDecrypt.decrypt(proof_path);
		         

		             //System.out.println("proof_type : "+proof_type+"\nfilePath : "+filePath);
		             
		             File empImage = new File(filePath);
		             
		        //     System.out.println("empImage : "+empImage.exists());
		             
		             if(empImage.exists())
		             {
			             if(!proof_type.equalsIgnoreCase("pdf"))
			             {
			            	 response.setContentType("image/jpg");  	            
				               OutputStream o = response.getOutputStream();
				               o.write(CommonUtility.getByteArrayFromFile(empImage));
				               o.flush();
				               o.close();
			             }
			             else
			             {
			            	 partAProofPDFOpen(empImage, request, response);
			             }
			               
		             }
	            }
	            catch (Exception e)
	            {
	              e.printStackTrace();
	              System.out.println("Exception in showpartaproofdetailsbyids : "+e.getLocalizedMessage());
	            }
            }     
             
            
      //  response.sendRedirect("Aadhar service is down");
    } 

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException 
    {
        processRequest(request, response);
    } 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException 
    {
        processRequest(request, response);
    }
    public static void viewFile(String path,String fileName, String fileType, HttpServletRequest request, HttpServletResponse response) 
    {

    	 BufferedInputStream in = null;
	        ServletOutputStream out = null;
	        try {
	        	String downloadpath =  CommonConstants.ISSUETRACKING_ATTACHMENT1_PATH;
                File fileDetailsData = new File(downloadpath + path);
                
	            FileInputStream fin = new FileInputStream(fileDetailsData);
	            in = new BufferedInputStream(fin);
	            out = response.getOutputStream();
	            response.setContentType("application/force-download");
	            response.addHeader("Content-Disposition", "attachment; filename=\"" + fileDetailsData.getName() + "\"");
	            byte[] buffer = new byte[4 * 1024];

	            int data = 0;
	            while ((data = in.read(buffer)) != -1) 
	            {
	            	  out.write(buffer, 0, data);
	            }
	             out.flush();
 
	         }
	        catch (Exception e) 
	        {
	            e.printStackTrace();
	           
	        } finally {
	            if (out != null) {
	                try {
						out.close();
					} catch (IOException e) {
						 
						e.printStackTrace();
					}
	            }
	            if (in != null) {
	                try {
						in.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
	            }

       }
    }
    
    private void partAProofPDFOpen(File documentFile,HttpServletRequest request, HttpServletResponse response) 
    {

    	 BufferedInputStream in = null;
	        ServletOutputStream out = null;
	        try {
	         
                
	            FileInputStream fin = new FileInputStream(documentFile);
	            in = new BufferedInputStream(fin);
	            out = response.getOutputStream();
	            response.setContentType("application/pdf");
	            response.addHeader("Content-Disposition", "inline; filename=\"" + documentFile.getName() + "\""); 	            
	            byte[] buffer = new byte[4 * 1024];

	            int data = 0;
	            while ((data = in.read(buffer)) != -1) 
	            {
	            	  out.write(buffer, 0, data);
	            }
	             out.flush();
 
	         }
	        catch (Exception e) 
	        {
	            e.printStackTrace();
	           
	        } finally {
	            if (out != null) {
	                try {
						out.close();
					} catch (IOException e) {
						 
						e.printStackTrace();
					}
	            }
	            if (in != null) {
	                try {
						in.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
	            }

       }
    }
    
    
    public static void viewCertiIDcard(String path,String fileName, HttpServletRequest request, HttpServletResponse response) 
    {

    	 BufferedInputStream in = null;
	        ServletOutputStream out = null;
	        try {
	        
                File fileDetailsData = new File(path);
                
	            FileInputStream fin = new FileInputStream(fileDetailsData);
	            in = new BufferedInputStream(fin);
	            out = response.getOutputStream();
	            response.setContentType("application/force-download");
	            response.addHeader("Content-Disposition", "attachment; filename=\"" + fileDetailsData.getName() + "\"");
	            byte[] buffer = new byte[4 * 1024];

	            int data = 0;
	            while ((data = in.read(buffer)) != -1) 
	            {
	            	  out.write(buffer, 0, data);
	            }
	             out.flush();
 
	         }
	        catch (Exception e) 
	        {
	            e.printStackTrace();
	           
	        } finally {
	            if (out != null) {
	                try {
						out.close();
					} catch (IOException e) {
						 
						e.printStackTrace();
					}
	            }
	            if (in != null) {
	                try {
						in.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
	            }

       }
    }
    
    public static void viewPartaWithoutProofFile(String path,String fileName, String fileType, HttpServletRequest request, HttpServletResponse response) 
    {

    	 BufferedInputStream in = null;
	        ServletOutputStream out = null;
	        try {
	        	String downloadpath =  CommonConstants.PARTA_ATTACHMENT_PATH;
                File fileDetailsData = new File(downloadpath + path);
             //   System.out.println("==>"+downloadpath + path);
                
	            FileInputStream fin = new FileInputStream(fileDetailsData);
	            in = new BufferedInputStream(fin);
	            out = response.getOutputStream();
	            response.setContentType("application/force-download");
	            response.addHeader("Content-Disposition", "attachment; filename=\"" + fileDetailsData.getName() + "\"");
	            byte[] buffer = new byte[4 * 1024];

	            int data = 0;
	            while ((data = in.read(buffer)) != -1) 
	            {
	            	  out.write(buffer, 0, data);
	            }
	             out.flush();
 
	         }
	        catch (Exception e) 
	        {
	            e.printStackTrace();
	           
	        } finally {
	            if (out != null) {
	                try {
						out.close();
					} catch (IOException e) {
						 
						e.printStackTrace();
					}
	            }
	            if (in != null) {
	                try {
						in.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
	            }

       }
    }
    
    
}