package com.meesevaWS;

public class MeeSevaWebServiceSoapProxy implements com.meesevaWS.MeeSevaWebServiceSoap {
  private String _endpoint = null;
  private com.meesevaWS.MeeSevaWebServiceSoap meeSevaWebServiceSoap = null;
  
  public MeeSevaWebServiceSoapProxy() {
    _initMeeSevaWebServiceSoapProxy();
  }
  
  public MeeSevaWebServiceSoapProxy(String endpoint) {
    _endpoint = endpoint;
    _initMeeSevaWebServiceSoapProxy();
  }
  
  private void _initMeeSevaWebServiceSoapProxy() {
    try {
      meeSevaWebServiceSoap = (new com.meesevaWS.MeeSevaWebServiceLocator()).getMeeSevaWebServiceSoap();
      if (meeSevaWebServiceSoap != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)meeSevaWebServiceSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)meeSevaWebServiceSoap)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (meeSevaWebServiceSoap != null)
      ((javax.xml.rpc.Stub)meeSevaWebServiceSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.meesevaWS.MeeSevaWebServiceSoap getMeeSevaWebServiceSoap() {
    if (meeSevaWebServiceSoap == null)
      _initMeeSevaWebServiceSoapProxy();
    return meeSevaWebServiceSoap;
  }
  
  public com.meesevaWS.GetPaymentGatewayResponseResponseGetPaymentGatewayResponseResult getPaymentGatewayResponse(java.lang.String[] arrPaymentDetails, java.lang.String[] arrAmount, java.lang.String[] arrTransParams, java.lang.String[] arrTransDetails, java.lang.String userid, java.lang.String password, java.lang.String checksum) throws java.rmi.RemoteException{
    if (meeSevaWebServiceSoap == null)
      _initMeeSevaWebServiceSoapProxy();
    return meeSevaWebServiceSoap.getPaymentGatewayResponse(arrPaymentDetails, arrAmount, arrTransParams, arrTransDetails, userid, password, checksum);
  }
  
  public com.meesevaWS.GetPaymentTransIdResponseGetPaymentTransIdResult getPaymentTransId(java.lang.String[] arrPaymentDetails, java.lang.String[] arrAmount, java.lang.String[] arrTransParams, java.lang.String[] arrTransDetails, java.lang.String userid, java.lang.String password, java.lang.String checksum) throws java.rmi.RemoteException{
    if (meeSevaWebServiceSoap == null)
      _initMeeSevaWebServiceSoapProxy();
    return meeSevaWebServiceSoap.getPaymentTransId(arrPaymentDetails, arrAmount, arrTransParams, arrTransDetails, userid, password, checksum);
  }
  
  public com.meesevaWS.UpdateApplicationDetailsResponseUpdateApplicationDetailsResult updateApplicationDetails(java.lang.String appno, java.lang.String status, java.lang.String remarks, java.lang.String updatedby, java.lang.String userid, java.lang.String password, java.lang.String deptFlag, java.lang.String checksum) throws java.rmi.RemoteException{
    if (meeSevaWebServiceSoap == null)
      _initMeeSevaWebServiceSoapProxy();
    return meeSevaWebServiceSoap.updateApplicationDetails(appno, status, remarks, updatedby, userid, password, deptFlag, checksum);
  }
  
  
}