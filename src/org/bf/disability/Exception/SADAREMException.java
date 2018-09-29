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
public class SADAREMException extends Exception {

    private int errorCode;
    private String exceptionMessage;
    private String applicationError;
    private String className;
    private String methodName;

    public SADAREMException() {
    }

    public SADAREMException(int errCode, String exMsg, String appErr, String clName, String method) {
        this.errorCode = errCode;
        this.exceptionMessage = exMsg;
        this.applicationError = appErr;
        this.className = clName;
        this.methodName = method;
    }


    /**
    @return int
     */
    public int getErrorCode() {
        return this.errorCode;
    }

    /**
    @return java.lang.String
     */
    public String getExceptionMessage() {
        return this.exceptionMessage;
    }

    /**
    @return java.lang.String
     */
    public String getApplicationError() {
        return this.applicationError;
    }

    /**
    @return java.lang.String
     */
    public String getClassName() {
        return this.className;
    }

    /**
    @return java.lang.String
     */
    public String getmethodName() {
        return this.methodName;
    }

    /**
    @param errorCode
     */
    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    /**
    @param exceptionMessage
     */
    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    /**
    @param applicationError
     */
    public void setApplicationError(String applicationError) {
        this.applicationError = applicationError;
    }

    /**
    @param className
     */
    public void setClassName(String className) {
        this.className = className;
    }

    /**
    @param methodName
     */
    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
}
