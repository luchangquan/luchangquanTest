package com.sanss.ngn.FptcSrvcInfoSync;

public class FptcSrvcInfoSyncProxy implements com.sanss.ngn.FptcSrvcInfoSync.FptcSrvcInfoSync_PortType {
  private String _endpoint = null;
  private com.sanss.ngn.FptcSrvcInfoSync.FptcSrvcInfoSync_PortType fptcSrvcInfoSync_PortType = null;
  
  public FptcSrvcInfoSyncProxy() {
    _initFptcSrvcInfoSyncProxy();
  }
  
  public FptcSrvcInfoSyncProxy(String endpoint) {
    _endpoint = endpoint;
    _initFptcSrvcInfoSyncProxy();
  }
  
  private void _initFptcSrvcInfoSyncProxy() {
    try {
      fptcSrvcInfoSync_PortType = (new com.sanss.ngn.FptcSrvcInfoSync.FptcSrvcInfoSync_ServiceLocator()).getFptcSrvcInfoSyncSOAP();
      if (fptcSrvcInfoSync_PortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)fptcSrvcInfoSync_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)fptcSrvcInfoSync_PortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (fptcSrvcInfoSync_PortType != null)
      ((javax.xml.rpc.Stub)fptcSrvcInfoSync_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.sanss.ngn.FptcSrvcInfoSync.FptcSrvcInfoSync_PortType getFptcSrvcInfoSync_PortType() {
    if (fptcSrvcInfoSync_PortType == null)
      _initFptcSrvcInfoSyncProxy();
    return fptcSrvcInfoSync_PortType;
  }
  
  public com.sanss.ngn.FptcSrvcInfoSync.UserInfoSyncResponse userInfoSync(com.sanss.ngn.FptcSrvcInfoSync.UserInfoSyncRequest inputParams) throws java.rmi.RemoteException{
    if (fptcSrvcInfoSync_PortType == null)
      _initFptcSrvcInfoSyncProxy();
    return fptcSrvcInfoSync_PortType.userInfoSync(inputParams);
  }
  
  public com.sanss.ngn.FptcSrvcInfoSync.SrvcSubscribeResponse srvcSubscribe(com.sanss.ngn.FptcSrvcInfoSync.SrvcSubscribeRequest inputParams) throws java.rmi.RemoteException{
    if (fptcSrvcInfoSync_PortType == null)
      _initFptcSrvcInfoSyncProxy();
    return fptcSrvcInfoSync_PortType.srvcSubscribe(inputParams);
  }
  
  public com.sanss.ngn.FptcSrvcInfoSync.SrvcModifyResponse srvcModify(com.sanss.ngn.FptcSrvcInfoSync.SrvcModifyRequest inputParams) throws java.rmi.RemoteException{
    if (fptcSrvcInfoSync_PortType == null)
      _initFptcSrvcInfoSyncProxy();
    return fptcSrvcInfoSync_PortType.srvcModify(inputParams);
  }
  
  public com.sanss.ngn.FptcSrvcInfoSync.PrsNumBindResponse prsNumBind(com.sanss.ngn.FptcSrvcInfoSync.PrsNumBindRequest inputParams) throws java.rmi.RemoteException{
    if (fptcSrvcInfoSync_PortType == null)
      _initFptcSrvcInfoSyncProxy();
    return fptcSrvcInfoSync_PortType.prsNumBind(inputParams);
  }
  
  
}