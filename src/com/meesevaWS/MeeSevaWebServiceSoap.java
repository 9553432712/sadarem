/**
 * MeeSevaWebServiceSoap.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.meesevaWS;

public interface MeeSevaWebServiceSoap extends java.rmi.Remote {
    public com.meesevaWS.GetPaymentGatewayResponseResponseGetPaymentGatewayResponseResult getPaymentGatewayResponse(java.lang.String[] arrPaymentDetails, java.lang.String[] arrAmount, java.lang.String[] arrTransParams, java.lang.String[] arrTransDetails, java.lang.String userid, java.lang.String password, java.lang.String checksum) throws java.rmi.RemoteException;
    public com.meesevaWS.GetPaymentTransIdResponseGetPaymentTransIdResult getPaymentTransId(java.lang.String[] arrPaymentDetails, java.lang.String[] arrAmount, java.lang.String[] arrTransParams, java.lang.String[] arrTransDetails, java.lang.String userid, java.lang.String password, java.lang.String checksum) throws java.rmi.RemoteException;
    public com.meesevaWS.UpdateApplicationDetailsResponseUpdateApplicationDetailsResult updateApplicationDetails(java.lang.String appno, java.lang.String status, java.lang.String remarks, java.lang.String updatedby, java.lang.String userid, java.lang.String password, java.lang.String deptFlag, java.lang.String checksum) throws java.rmi.RemoteException;
}
