/**
 * RationCardFetchServiceServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.rationCardWS;

public class RationCardFetchServiceServiceLocator extends org.apache.axis.client.Service implements com.rationCardWS.RationCardFetchServiceService {

    public RationCardFetchServiceServiceLocator() {
    }


    public RationCardFetchServiceServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public RationCardFetchServiceServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for RationCardFetchServicePort
    private java.lang.String RationCardFetchServicePort_address = "http://10.10.5.97/RationCardFetchTG/rationcardtg";

    public java.lang.String getRationCardFetchServicePortAddress() {
        return RationCardFetchServicePort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String RationCardFetchServicePortWSDDServiceName = "RationCardFetchServicePort";

    public java.lang.String getRationCardFetchServicePortWSDDServiceName() {
        return RationCardFetchServicePortWSDDServiceName;
    }

    public void setRationCardFetchServicePortWSDDServiceName(java.lang.String name) {
        RationCardFetchServicePortWSDDServiceName = name;
    }

    public com.rationCardWS.RationCardI getRationCardFetchServicePort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(RationCardFetchServicePort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getRationCardFetchServicePort(endpoint);
    }

    public com.rationCardWS.RationCardI getRationCardFetchServicePort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.rationCardWS.RationCardIBindingStub _stub = new com.rationCardWS.RationCardIBindingStub(portAddress, this);
            _stub.setPortName(getRationCardFetchServicePortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setRationCardFetchServicePortEndpointAddress(java.lang.String address) {
        RationCardFetchServicePort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.rationCardWS.RationCardI.class.isAssignableFrom(serviceEndpointInterface)) {
                com.rationCardWS.RationCardIBindingStub _stub = new com.rationCardWS.RationCardIBindingStub(new java.net.URL(RationCardFetchServicePort_address), this);
                _stub.setPortName(getRationCardFetchServicePortWSDDServiceName());
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
        if ("RationCardFetchServicePort".equals(inputPortName)) {
            return getRationCardFetchServicePort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://service.fetch.rationcard/", "RationCardFetchServiceService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://service.fetch.rationcard/", "RationCardFetchServicePort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("RationCardFetchServicePort".equals(portName)) {
            setRationCardFetchServicePortEndpointAddress(address);
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
