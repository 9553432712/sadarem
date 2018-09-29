package com.tcs.sadarem.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
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

public class EncriptDecrypt
{  
		static final private Logger log = Logger.getLogger(EncriptDecrypt.class);
	
	   private static final String algorithm = "AES/CBC/NoPadding";

	    private static final byte[] keyValue = new byte[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
	    private static final byte[] ivValue = new byte[] { 'f', 'e', 'd', 'c', 'b', 'a', '9', '8', '7', '6', '5', '4', '3', '2', '1', '0' };

	    private static final IvParameterSpec ivspec = new IvParameterSpec(ivValue);
	    private static final SecretKeySpec keyspec = new SecretKeySpec(keyValue, "AES");

	    final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();
	
	
	
	
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

	    public static String decrypt(String encryptedData) 
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

	    
	    
	    // Algorithm used
	    private final static String ALGORITHM = "DES";
	     
	    /**
	     * Encrypt data
	     * @param secretKey -   a secret key used for encryption
	     * @param data      -   data to encrypt
	     * @return  Encrypted data
	     * @throws Exception
	     */
	    public static String cipher(String secretKey, String data)
	    {
	    	String result="";
	    	try
	    	{
	        // Key has to be of length 8
	        if (secretKey == null || secretKey.length() != 8)
	        {
	            throw new Exception("Invalid key length - 8 bytes key needed!");
	        }
	         
	        SecretKey key = new SecretKeySpec(secretKey.getBytes(), ALGORITHM);
	        Cipher cipher = Cipher.getInstance(ALGORITHM);
	        cipher.init(Cipher.ENCRYPT_MODE, key);
	        result=toHex(cipher.doFinal(data.getBytes()));
	    	}
	    	catch(Exception e)
	    	{
	    		e.printStackTrace();
	    	}
	         
	        return result;
	    }
	     
	    /**
	     * Decrypt data
	     * @param secretKey -   a secret key used for decryption
	     * @param data      -   data to decrypt
	     * @return  Decrypted data
	     * @throws Exception
	     */
	    public static String decipher(String secretKey, String data) 
	    {
	    	String result="";
	    	try
	    	{
	        // Key has to be of length 8
	        if (secretKey == null || secretKey.length() != 8)
	            throw new Exception("Invalid key length - 8 bytes key needed!");
	         
	        SecretKey key = new SecretKeySpec(secretKey.getBytes(), ALGORITHM);
	        Cipher cipher = Cipher.getInstance(ALGORITHM);
	        cipher.init(Cipher.DECRYPT_MODE, key);
	        result =new String(cipher.doFinal(toByte(data)));
	    	}
	    	catch(Exception e)
	    	{
	    		e.printStackTrace();
	    	}
	    	
	        return result;
	    }
	     
	    // Helper methods
	     
	    private static byte[] toByte(String hexString)
	    {
	        int len = hexString.length()/2;
	         
	        byte[] result = new byte[len];
	         
	        for (int i = 0; i < len; i++)
	        {
	            result[i] = Integer.valueOf(hexString.substring(2*i, 2*i+2), 16).byteValue();
	        }
	        return result;
	    }
	 
	    public static String toHex(byte[] stringBytes) 
	    {
	        StringBuffer result = new StringBuffer(2*stringBytes.length);
	         
	        for (int i = 0; i < stringBytes.length; i++) 
	        {
	            result.append(HEX.charAt((stringBytes[i]>>4)&0x0f)).append(HEX.charAt(stringBytes[i]&0x0f));
	        }
	         
	        return result.toString();
	    }
	     
	    private final static String HEX = "0123456789ABCDEF";
	 
	    // Helper methods - end
	    
}         


