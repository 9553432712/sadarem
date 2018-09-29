package com.rationCardWS;

public class RationCardIProxy implements com.rationCardWS.RationCardI {
  private String _endpoint = null;
  private com.rationCardWS.RationCardI rationCardI = null;
  
  public RationCardIProxy() {
    _initRationCardIProxy();
  }
  
  public RationCardIProxy(String endpoint) {
    _endpoint = endpoint;
    _initRationCardIProxy();
  }
  
  private void _initRationCardIProxy() {
    try {
      rationCardI = (new com.rationCardWS.RationCardFetchServiceServiceLocator()).getRationCardFetchServicePort();
      if (rationCardI != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)rationCardI)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)rationCardI)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (rationCardI != null)
      ((javax.xml.rpc.Stub)rationCardI)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.rationCardWS.RationCardI getRationCardI() {
    if (rationCardI == null)
      _initRationCardIProxy();
    return rationCardI;
  }
  
  public com.rationCardWS.RcRespBean getePDSRationCardDetails(java.lang.String existingRCNum, java.lang.String password, java.lang.String hts) throws java.rmi.RemoteException, com.rationCardWS.NoSuchAlgorithmException, com.rationCardWS.UnsupportedEncodingException{
    if (rationCardI == null)
      _initRationCardIProxy();
    return rationCardI.getePDSRationCardDetails(existingRCNum, password, hts);
  }
  
  
}