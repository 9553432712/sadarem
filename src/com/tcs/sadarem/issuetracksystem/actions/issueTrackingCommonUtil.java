package com.tcs.sadarem.issuetracksystem.actions;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import org.apache.commons.fileupload.FileItem;
import org.apache.log4j.Logger;

import Aadhar.AadhaarUtility;

import com.ecentric.servicemodels.AadhaarProfile;
import com.lowagie.text.pdf.codec.Base64;

public class issueTrackingCommonUtil
{
	static final private Logger log = Logger.getLogger(issueTrackingCommonUtil.class);

	 public static boolean uploadDocument(FileItem uploadFileName, String uploadPath, String fileName) {
	        String strDirectoytemp = null;
	        boolean flag = false;
	        try {
	            strDirectoytemp = uploadPath;

	            if (strDirectoytemp != null && !"".equals(strDirectoytemp) && strDirectoytemp.length() > 0) { // If directory is not exists it will create
	                File directorytemp = new File(strDirectoytemp);
	                if (!directorytemp.exists()) {
	                    directorytemp.mkdirs();
	                }

	                File fileToCreatetemp = new File(strDirectoytemp, fileName); // Copy the file into directory
	                FileOutputStream fileOutStreamtemp = new FileOutputStream(fileToCreatetemp); // Write the file content into buffer

	                if (uploadFileName.getSize() > 0) {
	                    fileOutStreamtemp.write(getBytes( uploadFileName.getInputStream()));
	                    fileOutStreamtemp.flush();
	                    fileOutStreamtemp.close();
	                    flag = true;
	                } else {
	                    flag = false;
	                }
	            } else {
	                flag = false;
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return flag;
	    }
	 public static byte[] getBytes(InputStream is) throws IOException {

		    int len;
		    int size = 1024;
		    byte[] buf;

		    if (is instanceof ByteArrayInputStream) {
		      size = is.available();
		      buf = new byte[size];
		      len = is.read(buf, 0, size);
		    } else {
		      ByteArrayOutputStream bos = new ByteArrayOutputStream();
		      buf = new byte[size];
		      while ((len = is.read(buf, 0, size)) != -1)
		        bos.write(buf, 0, len);
		      buf = bos.toByteArray();
		    }
		    return buf;
		  }
	 
	 
	 
	 public static boolean createDirectory(String dirPath)
		{
			try
			{
					File files = new File(dirPath);
					if (!files.exists()) 
					{
						if (files.mkdirs()) 
						{
							return true;
						} 
						else
						{
							return false;
						}
					}
					else
					{
						return true;
					}
			}
			catch(Exception e)
			{
				e.printStackTrace();
				return false;
			}
		}
	 
	 public static void deleteDirectory(File file)
		{
			try
			{ 

				if(file.exists())
				{
			    	if(file.isDirectory())
			    	{

			    		//directory is empty, then delete it
			    		if(file.list().length==0)
			    		{

			    		   file.delete();
			    		   log.info("Directory is deleted : " + file.getAbsolutePath());

			    		}
			    		else
			    		{

			    		   //list all the directory contents
			        	   String files[] = file.list();

			        	   for (String temp : files) 
			        	   {
			        	      //construct the file structure
			        	      File fileDelete = new File(file, temp);

			        	      //recursive delete
			        	      deleteDirectory(fileDelete);
			        	   }

			        	   //check the directory again, if empty then delete it
			        	   if(file.list().length==0)
			        	   {
			           	     file.delete();
			        	     log.info("Directory is deleted : " + file.getAbsolutePath());
			        	   }
			    		}

			    	}
			    	else
			    	{
			    		//if file, then delete it
			    		file.delete();
			    		 log.info("File is deleted : " + file.getAbsolutePath());
			    	}
				}
					 
			}
			catch(Exception e)
			{
				e.printStackTrace(); 
				log.error(e);
			}
		}
		
		public static boolean writeFileToDirecotry(FileItem myfile,String filename )
		{
			boolean status = false;

			InputStream inStream = null;
			OutputStream outStream = null;
			try
			{
				
			File checkFile = new File(filename);
			
				if(!checkFile.exists())
				{
					
					 byte[] buffer = new byte[1024];
			 		
					 
					 inStream = myfile.getInputStream();
			 	    outStream = new FileOutputStream(checkFile);
					 
			 	    int length;
			 	    //copy the file content in bytes 
				 	    while ((length = inStream.read(buffer)) > 0)
				 	    {
				 	    	outStream.write(buffer, 0, length);
				 	    }
			 	 
				 	    inStream.close();
				 	    outStream.close();
						status = true;
				}
				else
				{
					status = false;
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
				
				status = false;
			}
	 	    
			
			return status;
		}
		
		
		 public static boolean saveAadharPhoto(String aadharcardNo,String filename)
		 {
			String base64 = "";
			boolean status = false;
			AadhaarProfile aadharProfile = null;
	         
	         try
	         {
	             //aadharProfile = AadharDetails.getAadharDetails(aadharcardNo);
	        	 
	        	 aadharProfile = AadhaarUtility.getAadhaarProfileByUID(aadharcardNo);
	         
		             if (aadharProfile != null) 
		             {
		                 
		                 int invalidFlag = Integer.parseInt(aadharProfile.getStatus());
		
		                 if (invalidFlag == 100 || invalidFlag == 101)
		                 {
		                     if (aadharProfile.getBase64file() != null && !aadharProfile.getBase64file().equalsIgnoreCase("101")) 
		                     {
		                         base64 = aadharProfile.getBase64file();
		                     } 
		                     else
		                     {
		                         base64 = "-";
		                     }
		                 }
		
			             if (base64 != "" && !base64.equalsIgnoreCase("-")) 
			             {
			            	  byte[] bytearray = Base64.decode(base64);
	
			                  BufferedImage imag = ImageIO.read(new ByteArrayInputStream(bytearray));
			                  ImageIO.write(imag, "jpg", new File(filename));
			                  status = true;
			             } 
			             else
			             {
			            	 status = false;
			             }
		            }
		             else
		             {
		            	 status = false;
		             }
	          }
	         catch(Exception e)
	         {
	        	 status = false;
	         }
	         return status;
		 }

}
