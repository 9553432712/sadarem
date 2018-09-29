package org.bf.disability.common;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.Cipher;

import java.net.URLEncoder;
import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.*;
import java.io.*;
import sun.misc.*;

public class TripleDESEncryption {

    private byte[] key;
    private byte[] initializationVector;

    public TripleDESEncryption(byte[] key, byte[] initializationVector) {
        this.key = key;
        this.initializationVector = initializationVector;
    }

    public String encryptText(String plainText) throws Exception {
        //----  Use specified 3DES key and IV from other source --------------
        byte[] plaintext = plainText.getBytes();
        byte[] tdesKeyData = key;
        byte[] myIV = initializationVector;

        Cipher c3des = Cipher.getInstance("DESede/CBC/PKCS5Padding");
        SecretKeySpec myKey = new SecretKeySpec(tdesKeyData, "DESede");
        IvParameterSpec ivspec = new IvParameterSpec(myIV);
        c3des.init(Cipher.ENCRYPT_MODE, myKey, ivspec);
        byte[] cipherText = c3des.doFinal(plaintext);
        sun.misc.BASE64Encoder obj64 = new sun.misc.BASE64Encoder();
        return obj64.encode(cipherText);
    }

    public String decryptText(String encryptText) throws Exception {

        byte[] encData = new sun.misc.BASE64Decoder().decodeBuffer(encryptText);
        Cipher decipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
        byte[] tdesKeyData = key;
        SecretKeySpec myKey = new SecretKeySpec(tdesKeyData, "DESede");
        IvParameterSpec ivspec = new IvParameterSpec(initializationVector);
        decipher.init(Cipher.DECRYPT_MODE, myKey, ivspec);
        byte[] plainText = decipher.doFinal(encData);
        return new String(plainText);

    }

//    public static void main(String[] args) {
//        // TODO Auto-generated method stub
//        byte[] myIV = {(byte) 50, (byte) 51, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57};
//        byte[] tdesKeyData = {
//            (byte) 0xA2, (byte) 0x15, (byte) 0x37, (byte) 0x07, (byte) 0xCB, (byte) 0x62,
//            (byte) 0xC1, (byte) 0xD3, (byte) 0xF8, (byte) 0xF1, (byte) 0x97, (byte) 0xDF,
//            (byte) 0xD0, (byte) 0x13, (byte) 0x4F, (byte) 0x79, (byte) 0x01, (byte) 0x67,
//            (byte) 0x7A, (byte) 0x85, (byte) 0x94, (byte) 0x16, (byte) 0x31, (byte) 0x92};
//
//        try {
//            TripleDESEncryption objText = new TripleDESEncryption(tdesKeyData, myIV);
//
//            String values[] = "P1=ESBJVP00018292&P2=03&P3=03&P4=ESEVA-BJVP&P5=03BJVP00001&P6=OPR200&P7=ESEVA-BJVP".split("&");
//            for (String value : values) {
//                String paramValues[] = value.split("=");
//                if (paramValues != null && paramValues.length == 2 && paramValues[0].equals("P1")) {
//                   
//                }
//                if (paramValues != null && paramValues.length == 2 && paramValues[0].equals("P2")) {
//                }
//                if (paramValues != null && paramValues.length == 2 && paramValues[0].equals("P3")) {
//                }
//                if (paramValues != null && paramValues.length == 2 && paramValues[0].equals("P4")) {
//                }
//                if (paramValues != null && paramValues.length == 2 && paramValues[0].equals("P5")) {
//                }
//                if (paramValues != null && paramValues.length == 2 && paramValues[0].equals("P6")) {
//                }
//                if (paramValues != null && paramValues.length == 2 && paramValues[0].equals("P7")) {
//                }
//            }
//
//        } catch (Exception ex) {
//        }
//    }
}
