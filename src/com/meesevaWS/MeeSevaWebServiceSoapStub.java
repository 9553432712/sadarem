/**
 * MeeSevaWebServiceSoapStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.meesevaWS;

public class MeeSevaWebServiceSoapStub extends org.apache.axis.client.Stub implements com.meesevaWS.MeeSevaWebServiceSoap {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[3];
        _initOperationDesc1();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetPaymentGatewayResponse");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://MsevaPaymentTG.org/", "arrPaymentDetails"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://MsevaPaymentTG.org/", "ArrayOfString"), java.lang.String[].class, false, false);
        param.setItemQName(new javax.xml.namespace.QName("http://MsevaPaymentTG.org/", "string"));
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://MsevaPaymentTG.org/", "arrAmount"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://MsevaPaymentTG.org/", "ArrayOfString"), java.lang.String[].class, false, false);
        param.setItemQName(new javax.xml.namespace.QName("http://MsevaPaymentTG.org/", "string"));
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://MsevaPaymentTG.org/", "arrTransParams"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://MsevaPaymentTG.org/", "ArrayOfString"), java.lang.String[].class, false, false);
        param.setItemQName(new javax.xml.namespace.QName("http://MsevaPaymentTG.org/", "string"));
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://MsevaPaymentTG.org/", "arrTransDetails"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://MsevaPaymentTG.org/", "ArrayOfString"), java.lang.String[].class, false, false);
        param.setItemQName(new javax.xml.namespace.QName("http://MsevaPaymentTG.org/", "string"));
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://MsevaPaymentTG.org/", "userid"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://MsevaPaymentTG.org/", "password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://MsevaPaymentTG.org/", "checksum"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://MsevaPaymentTG.org/", ">>GetPaymentGatewayResponseResponse>GetPaymentGatewayResponseResult"));
        oper.setReturnClass(com.meesevaWS.GetPaymentGatewayResponseResponseGetPaymentGatewayResponseResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://MsevaPaymentTG.org/", "GetPaymentGatewayResponseResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetPaymentTransId");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://MsevaPaymentTG.org/", "arrPaymentDetails"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://MsevaPaymentTG.org/", "ArrayOfString"), java.lang.String[].class, false, false);
        param.setItemQName(new javax.xml.namespace.QName("http://MsevaPaymentTG.org/", "string"));
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://MsevaPaymentTG.org/", "arrAmount"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://MsevaPaymentTG.org/", "ArrayOfString"), java.lang.String[].class, false, false);
        param.setItemQName(new javax.xml.namespace.QName("http://MsevaPaymentTG.org/", "string"));
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://MsevaPaymentTG.org/", "arrTransParams"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://MsevaPaymentTG.org/", "ArrayOfString"), java.lang.String[].class, false, false);
        param.setItemQName(new javax.xml.namespace.QName("http://MsevaPaymentTG.org/", "string"));
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://MsevaPaymentTG.org/", "arrTransDetails"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://MsevaPaymentTG.org/", "ArrayOfString"), java.lang.String[].class, false, false);
        param.setItemQName(new javax.xml.namespace.QName("http://MsevaPaymentTG.org/", "string"));
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://MsevaPaymentTG.org/", "userid"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://MsevaPaymentTG.org/", "password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://MsevaPaymentTG.org/", "checksum"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://MsevaPaymentTG.org/", ">>GetPaymentTransIdResponse>GetPaymentTransIdResult"));
        oper.setReturnClass(com.meesevaWS.GetPaymentTransIdResponseGetPaymentTransIdResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://MsevaPaymentTG.org/", "GetPaymentTransIdResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("UpdateApplicationDetails");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://MsevaPaymentTG.org/", "appno"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://MsevaPaymentTG.org/", "status"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://MsevaPaymentTG.org/", "remarks"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://MsevaPaymentTG.org/", "updatedby"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://MsevaPaymentTG.org/", "userid"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://MsevaPaymentTG.org/", "password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://MsevaPaymentTG.org/", "DeptFlag"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://MsevaPaymentTG.org/", "checksum"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://MsevaPaymentTG.org/", ">>UpdateApplicationDetailsResponse>UpdateApplicationDetailsResult"));
        oper.setReturnClass(com.meesevaWS.UpdateApplicationDetailsResponseUpdateApplicationDetailsResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://MsevaPaymentTG.org/", "UpdateApplicationDetailsResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[2] = oper;

    }

    public MeeSevaWebServiceSoapStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public MeeSevaWebServiceSoapStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public MeeSevaWebServiceSoapStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
        if (service == null) {
            super.service = new org.apache.axis.client.Service();
        } else {
            super.service = service;
        }
        ((org.apache.axis.client.Service)super.service).setTypeMappingVersion("1.2");
            java.lang.Class cls;
            javax.xml.namespace.QName qName;
            javax.xml.namespace.QName qName2;
            java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
            java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
            java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
            java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
            java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
            java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
            java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
            java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
            java.lang.Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
            java.lang.Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
            qName = new javax.xml.namespace.QName("http://MsevaPaymentTG.org/", ">>GetPaymentGatewayResponseResponse>GetPaymentGatewayResponseResult");
            cachedSerQNames.add(qName);
            cls = com.meesevaWS.GetPaymentGatewayResponseResponseGetPaymentGatewayResponseResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://MsevaPaymentTG.org/", ">>GetPaymentTransIdResponse>GetPaymentTransIdResult");
            cachedSerQNames.add(qName);
            cls = com.meesevaWS.GetPaymentTransIdResponseGetPaymentTransIdResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://MsevaPaymentTG.org/", ">>UpdateApplicationDetailsResponse>UpdateApplicationDetailsResult");
            cachedSerQNames.add(qName);
            cls = com.meesevaWS.UpdateApplicationDetailsResponseUpdateApplicationDetailsResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://MsevaPaymentTG.org/", ">GetPaymentTransId");
            cachedSerQNames.add(qName);
            cls = com.meesevaWS.GetPaymentTransId.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://MsevaPaymentTG.org/", ">GetPaymentTransIdResponse");
            cachedSerQNames.add(qName);
            cls = com.meesevaWS.GetPaymentTransIdResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://MsevaPaymentTG.org/", ">UpdateApplicationDetails");
            cachedSerQNames.add(qName);
            cls = com.meesevaWS.UpdateApplicationDetails.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://MsevaPaymentTG.org/", ">UpdateApplicationDetailsResponse");
            cachedSerQNames.add(qName);
            cls = com.meesevaWS.UpdateApplicationDetailsResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://MsevaPaymentTG.org/", "ArrayOfString");
            cachedSerQNames.add(qName);
            cls = java.lang.String[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string");
            qName2 = new javax.xml.namespace.QName("http://MsevaPaymentTG.org/", "string");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

    }

    protected org.apache.axis.client.Call createCall() throws java.rmi.RemoteException {
        try {
            org.apache.axis.client.Call _call = super._createCall();
            if (super.maintainSessionSet) {
                _call.setMaintainSession(super.maintainSession);
            }
            if (super.cachedUsername != null) {
                _call.setUsername(super.cachedUsername);
            }
            if (super.cachedPassword != null) {
                _call.setPassword(super.cachedPassword);
            }
            if (super.cachedEndpoint != null) {
                _call.setTargetEndpointAddress(super.cachedEndpoint);
            }
            if (super.cachedTimeout != null) {
                _call.setTimeout(super.cachedTimeout);
            }
            if (super.cachedPortName != null) {
                _call.setPortName(super.cachedPortName);
            }
            java.util.Enumeration keys = super.cachedProperties.keys();
            while (keys.hasMoreElements()) {
                java.lang.String key = (java.lang.String) keys.nextElement();
                _call.setProperty(key, super.cachedProperties.get(key));
            }
            // All the type mapping information is registered
            // when the first call is made.
            // The type mapping information is actually registered in
            // the TypeMappingRegistry of the service, which
            // is the reason why registration is only needed for the first call.
            synchronized (this) {
                if (firstCall()) {
                    // must set encoding style before registering serializers
                    _call.setEncodingStyle(null);
                    for (int i = 0; i < cachedSerFactories.size(); ++i) {
                        java.lang.Class cls = (java.lang.Class) cachedSerClasses.get(i);
                        javax.xml.namespace.QName qName =
                                (javax.xml.namespace.QName) cachedSerQNames.get(i);
                        java.lang.Object x = cachedSerFactories.get(i);
                        if (x instanceof Class) {
                            java.lang.Class sf = (java.lang.Class)
                                 cachedSerFactories.get(i);
                            java.lang.Class df = (java.lang.Class)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                        else if (x instanceof javax.xml.rpc.encoding.SerializerFactory) {
                            org.apache.axis.encoding.SerializerFactory sf = (org.apache.axis.encoding.SerializerFactory)
                                 cachedSerFactories.get(i);
                            org.apache.axis.encoding.DeserializerFactory df = (org.apache.axis.encoding.DeserializerFactory)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                    }
                }
            }
            return _call;
        }
        catch (java.lang.Throwable _t) {
            throw new org.apache.axis.AxisFault("Failure trying to get the Call object", _t);
        }
    }

    public com.meesevaWS.GetPaymentGatewayResponseResponseGetPaymentGatewayResponseResult getPaymentGatewayResponse(java.lang.String[] arrPaymentDetails, java.lang.String[] arrAmount, java.lang.String[] arrTransParams, java.lang.String[] arrTransDetails, java.lang.String userid, java.lang.String password, java.lang.String checksum) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://MsevaPaymentTG.org/GetPaymentGatewayResponse");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://MsevaPaymentTG.org/", "GetPaymentGatewayResponse"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {arrPaymentDetails, arrAmount, arrTransParams, arrTransDetails, userid, password, checksum});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.meesevaWS.GetPaymentGatewayResponseResponseGetPaymentGatewayResponseResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.meesevaWS.GetPaymentGatewayResponseResponseGetPaymentGatewayResponseResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.meesevaWS.GetPaymentGatewayResponseResponseGetPaymentGatewayResponseResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.meesevaWS.GetPaymentTransIdResponseGetPaymentTransIdResult getPaymentTransId(java.lang.String[] arrPaymentDetails, java.lang.String[] arrAmount, java.lang.String[] arrTransParams, java.lang.String[] arrTransDetails, java.lang.String userid, java.lang.String password, java.lang.String checksum) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://MsevaPaymentTG.org/GetPaymentTransId");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://MsevaPaymentTG.org/", "GetPaymentTransId"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {arrPaymentDetails, arrAmount, arrTransParams, arrTransDetails, userid, password, checksum});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.meesevaWS.GetPaymentTransIdResponseGetPaymentTransIdResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.meesevaWS.GetPaymentTransIdResponseGetPaymentTransIdResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.meesevaWS.GetPaymentTransIdResponseGetPaymentTransIdResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.meesevaWS.UpdateApplicationDetailsResponseUpdateApplicationDetailsResult updateApplicationDetails(java.lang.String appno, java.lang.String status, java.lang.String remarks, java.lang.String updatedby, java.lang.String userid, java.lang.String password, java.lang.String deptFlag, java.lang.String checksum) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://MsevaPaymentTG.org/UpdateApplicationDetails");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://MsevaPaymentTG.org/", "UpdateApplicationDetails"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {appno, status, remarks, updatedby, userid, password, deptFlag, checksum});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.meesevaWS.UpdateApplicationDetailsResponseUpdateApplicationDetailsResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.meesevaWS.UpdateApplicationDetailsResponseUpdateApplicationDetailsResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.meesevaWS.UpdateApplicationDetailsResponseUpdateApplicationDetailsResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

}
