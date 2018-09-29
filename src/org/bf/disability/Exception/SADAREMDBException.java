/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.Exception;

/**
This exception class encapsulates the application friendly exceptions and this
generic exception class is the parent of more specific exceptions that holds the
exception information of the service,db and ejb tiers respectively.

@version 1.0
 */
public class SADAREMDBException extends SADAREMException 
{

    private int errorCode;
    private String exceptionMessage;
    private String applicationError;
    private String className;
    private String methodName;

    public SADAREMDBException() 
    {
    	
    }

    public SADAREMDBException(int errCode, String exMsg, String appErr, String clName, String method) 
    {
       super( errCode,  exMsg,  appErr,  clName,  method);
    }



}
