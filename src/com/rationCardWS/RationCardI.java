/**
 * RationCardI.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.rationCardWS;

public interface RationCardI extends java.rmi.Remote {
    public com.rationCardWS.RcRespBean getePDSRationCardDetails(java.lang.String existingRCNum, java.lang.String password, java.lang.String hts) throws java.rmi.RemoteException, com.rationCardWS.NoSuchAlgorithmException, com.rationCardWS.UnsupportedEncodingException;
}
