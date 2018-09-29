package com.tcs.sadarem.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;

import org.apache.log4j.Logger;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/** 
 * This is a simple applet which contains to functions  
 * Encript-> Converts String to Hexa
 * 
 * @author Mandali.Subbu (subramanyeswara.m@tcs.com) 
 */ 

public class PasswordEncriptDecrypt
{  
	static final private Logger log = Logger.getLogger(PasswordEncriptDecrypt.class);
	
	   private static final String algorithm = "AES/CBC/NoPadding";

	    private static final byte[] keyValue = new byte[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
	    private static final byte[] ivValue = new byte[] { 'f', 'e', 'd', 'c', 'b', 'a', '9', '8', '7', '6', '5', '4', '3', '2', '1', '0' };

	    private static final IvParameterSpec ivspec = new IvParameterSpec(ivValue);
	    private static final SecretKeySpec keyspec = new SecretKeySpec(keyValue, "AES");

	    final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();
	
	
	 public static String encryptText(String InstaReq) 
	   {

	
	     //logger.debug("Entry : encryptText()");
	     String encrInstaResp = null;
	     String reultStr 	="";
	     try 
			   {
	          //String lKey = "!i@*6@($%(";//NEW
	          //String lKey = "!i@*6@($%("; //orginal
	  	   
	  	   String lKey = "#@*&$(!9("; //modified
	
	         SecretKeySpec lKeySpec = new SecretKeySpec(lKey.getBytes("UTF8"),"Blowfish");
	         Cipher lCipher = Cipher.getInstance("Blowfish/ECB/PKCS5Padding");
	         lCipher.init(Cipher.ENCRYPT_MODE, lKeySpec);
	         byte[] lPassword = InstaReq.getBytes("UTF8");
	         byte[] lEncryptPassword = lCipher.doFinal(lPassword);
	         encrInstaResp = new BASE64Encoder().encode(lEncryptPassword);
	                                  /// Code Added for Removal of /n:- START
	         StringBuffer nString = new StringBuffer();;
	         for (int i = 0; i < encrInstaResp.length(); i++) 
				   {
			             int a = encrInstaResp.charAt(i);
			
			             //System.out.println(" The Value " + encrInstaResp.charAt(i)+ "   " + a);
			             if (a != 13 && a != 10 && !encrInstaResp.substring(i, i + 1).equals(" "))
			                 nString.append(encrInstaResp.charAt(i));
					}
			         //logger.debug(" nString.toString() >>>  " + nString.toString());
	         	reultStr= nString.toString();
			     /// Code Added for Removal of /n:- END
		     } 
		     catch (Exception lException) 
			 {
		         lException.getMessage();
		         reultStr =InstaReq;
		     }
     return reultStr;

	   }
	 
	 public static String decryptText(String InstaResp) 
	   {

		    // logger.debug("Entry : decryptText() ");
		     try {
		         //logger.debug("in side instaDecryption1111111111111111");
		         //logger.debug("the parameter is 0"+InstaResp );
		                   /*logger.debug.debug("INSTAINSURE:::PAYMENT::::instaDecryption--"
		                 + InstaResp);*/
		         //String lKey = "!i@*6@($%(";//NEW
		         
		  	   //String lKey = "!i@*6@($%("; //orginal
		  	   
		  	   String lKey = "#@*&$(!9("; // modified
		         SecretKeySpec lKeySpec = new SecretKeySpec(lKey.getBytes("UTF8"),"Blowfish");
		
		         Cipher lCipher = Cipher.getInstance("Blowfish/ECB/PKCS5Padding");
		
		         lCipher.init(Cipher.DECRYPT_MODE, lKeySpec);
		         String encoded2 = new String(new BASE64Decoder().decodeBuffer(InstaResp));
		
		         byte[] dec = new sun.misc.BASE64Decoder().decodeBuffer(InstaResp);
		
		         // Decrypt
		         byte[] utf8 = lCipher.doFinal(dec);
		         //logger.debug("finalllllllllllllly___________"+(new String(utf8, "UTF8")) );
		         // Decode using utf-8
		         return new String(utf8, "UTF8");
		
		     } 
			   catch (Exception lException)
				   {
		         log.debug("EXCEPTION IN instaDecryption method 2222222222222 " + lException.getMessage());
		                   return InstaResp;
		         }
	   }
	
	
	 public static String encrypt(String Data)
	    {
		 	String encryptedValue ="";
		 	try
		 	{
		 		Data=padString(Data);
		        Cipher c = Cipher.getInstance(algorithm);
		        c.init(Cipher.ENCRYPT_MODE, keyspec, ivspec);
		        byte[] encVal = c.doFinal(Data.getBytes());
		        encryptedValue = new BASE64Encoder().encode(encVal);
		 	}
		 	catch(Exception e)
		 	{
		 		encryptedValue = Data;
		 		log.debug("Exception in PasswordEncriptDecrypt @ encrypt method : " + e.getMessage());
		 	}
	        return encryptedValue;
	    }

	    public static String decrypt(String encryptedData) throws Exception 
	    {
		 	String decryptedValue ="";
		 	try
		 	{
		        Cipher c = Cipher.getInstance(algorithm);
		        c.init(Cipher.DECRYPT_MODE, keyspec, ivspec);
		        byte[] decordedValue = new BASE64Decoder().decodeBuffer(encryptedData);
		        byte[] decValue = c.doFinal(decordedValue);
		         decryptedValue = new String(decValue);
	 		}
		 	catch(Exception e)
		 	{
		 		decryptedValue = encryptedData;
		 		log.debug("Exception in PasswordEncriptDecrypt @ decrypt method : " + e.getMessage());
		 	}
		 	if(decryptedValue!=null && decryptedValue.length()>0)
		 	{
		 		decryptedValue=decryptedValue.trim();
		 	}
	        return decryptedValue;
	    }

	    public static String bytesToHex(byte[] bytes) 
	    {
	        char[] hexChars = new char[bytes.length * 2];
	        int v;
	        for ( int j = 0; j < bytes.length; j++ ) {
	            v = bytes[j] & 0xFF;
	            hexChars[j * 2] = hexArray[v >>> 4];
	            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
	        }
	        return new String(hexChars);
	    }

	    private static String padString(String source) 
	    {
	        char paddingChar = ' ';
	        int size = 16;
	        int x = source.length() % size;
	        int padLength = size - x;

	        for (int i = 0; i < padLength; i++)
	        {
	            source += paddingChar;
	        }
	        return source;
	      }
   }         


