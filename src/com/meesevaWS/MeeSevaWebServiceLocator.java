/**
 * MeeSevaWebServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.meesevaWS;

public class MeeSevaWebServiceLocator extends org.apache.axis.client.Service implements com.meesevaWS.MeeSevaWebService {

    public MeeSevaWebServiceLocator() {
    }


    public MeeSevaWebServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public MeeSevaWebServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for MeeSevaWebServiceSoap
    private java.lang.String MeeSevaWebServiceSoap_address = "http://tgdept.meeseva.gov.in/msevapaymentgateway/meesevawebservice.asmx";

    public java.lang.String getMeeSevaWebServiceSoapAddress() {
        return MeeSevaWebServiceSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String MeeSevaWebServiceSoapWSDDServiceName = "MeeSevaWebServiceSoap";

    public java.lang.String getMeeSevaWebServiceSoapWSDDServiceName() {
        return MeeSevaWebServiceSoapWSDDServiceName;
    }

    public void setMeeSevaWebServiceSoapWSDDServiceName(java.lang.String name) {
        MeeSevaWebServiceSoapWSDDServiceName = name;
    }

    public com.meesevaWS.MeeSevaWebServiceSoap getMeeSevaWebServiceSoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(MeeSevaWebServiceSoap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getMeeSevaWebServiceSoap(endpoint);
    }

    public com.meesevaWS.MeeSevaWebServiceSoap getMeeSevaWebServiceSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.meesevaWS.MeeSevaWebServiceSoapStub _stub = new com.meesevaWS.MeeSevaWebServiceSoapStub(portAddress, this);
            _stub.setPortName(getMeeSevaWebServiceSoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setMeeSevaWebServiceSoapEndpointAddress(java.lang.String address) {
        MeeSevaWebServiceSoap_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.meesevaWS.MeeSevaWebServiceSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                com.meesevaWS.MeeSevaWebServiceSoapStub _stub = new com.meesevaWS.MeeSevaWebServiceSoapStub(new java.net.URL(MeeSevaWebServiceSoap_address), this);
                _stub.setPortName(getMeeSevaWebServiceSoapWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("MeeSevaWebServiceSoap".equals(inputPortName)) {
            return getMeeSevaWebServiceSoap();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://MsevaPaymentTG.org/", "MeeSevaWebService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://MsevaPaymentTG.org/", "MeeSevaWebServiceSoap"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("MeeSevaWebServiceSoap".equals(portName)) {
            setMeeSevaWebServiceSoapEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
