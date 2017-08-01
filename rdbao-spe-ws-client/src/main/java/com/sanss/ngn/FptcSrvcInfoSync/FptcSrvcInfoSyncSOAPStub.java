/**
 * FptcSrvcInfoSyncSOAPStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sanss.ngn.FptcSrvcInfoSync;

public class FptcSrvcInfoSyncSOAPStub extends org.apache.axis.client.Stub implements com.sanss.ngn.FptcSrvcInfoSync.FptcSrvcInfoSync_PortType {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[4];
        _initOperationDesc1();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("UserInfoSync");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ngn.sanss.com/FptcSrvcInfoSync/", "UserInfoSyncReq"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://ngn.sanss.com/FptcSrvcInfoSync/", "UserInfoSyncRequest"), com.sanss.ngn.FptcSrvcInfoSync.UserInfoSyncRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://ngn.sanss.com/FptcSrvcInfoSync/", "UserInfoSyncResponse"));
        oper.setReturnClass(com.sanss.ngn.FptcSrvcInfoSync.UserInfoSyncResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://ngn.sanss.com/FptcSrvcInfoSync/", "UserInfoSyncResp"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("SrvcSubscribe");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ngn.sanss.com/FptcSrvcInfoSync/", "SrvcSubscribeReq"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://ngn.sanss.com/FptcSrvcInfoSync/", "SrvcSubscribeRequest"), com.sanss.ngn.FptcSrvcInfoSync.SrvcSubscribeRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://ngn.sanss.com/FptcSrvcInfoSync/", "SrvcSubscribeResponse"));
        oper.setReturnClass(com.sanss.ngn.FptcSrvcInfoSync.SrvcSubscribeResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://ngn.sanss.com/FptcSrvcInfoSync/", "SrvcSubscribeResp"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("SrvcModify");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ngn.sanss.com/FptcSrvcInfoSync/", "SrvcModifyReq"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://ngn.sanss.com/FptcSrvcInfoSync/", "SrvcModifyRequest"), com.sanss.ngn.FptcSrvcInfoSync.SrvcModifyRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://ngn.sanss.com/FptcSrvcInfoSync/", "SrvcModifyResponse"));
        oper.setReturnClass(com.sanss.ngn.FptcSrvcInfoSync.SrvcModifyResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://ngn.sanss.com/FptcSrvcInfoSync/", "SrvcModifyResp"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("PrsNumBind");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ngn.sanss.com/FptcSrvcInfoSync/", "PrsNumBindReq"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://ngn.sanss.com/FptcSrvcInfoSync/", "PrsNumBindRequest"), com.sanss.ngn.FptcSrvcInfoSync.PrsNumBindRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://ngn.sanss.com/FptcSrvcInfoSync/", "PrsNumBindResponse"));
        oper.setReturnClass(com.sanss.ngn.FptcSrvcInfoSync.PrsNumBindResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://ngn.sanss.com/FptcSrvcInfoSync/", "PrsNumBindResp"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[3] = oper;

    }

    public FptcSrvcInfoSyncSOAPStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public FptcSrvcInfoSyncSOAPStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public FptcSrvcInfoSyncSOAPStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
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
            qName = new javax.xml.namespace.QName("http://ngn.sanss.com/FptcSrvcInfoSync/", "BasicRequest");
            cachedSerQNames.add(qName);
            cls = com.sanss.ngn.FptcSrvcInfoSync.BasicRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://ngn.sanss.com/FptcSrvcInfoSync/", "BasicResponse");
            cachedSerQNames.add(qName);
            cls = com.sanss.ngn.FptcSrvcInfoSync.BasicResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://ngn.sanss.com/FptcSrvcInfoSync/", "Property");
            cachedSerQNames.add(qName);
            cls = com.sanss.ngn.FptcSrvcInfoSync.Property.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://ngn.sanss.com/FptcSrvcInfoSync/", "PrsNumBindRequest");
            cachedSerQNames.add(qName);
            cls = com.sanss.ngn.FptcSrvcInfoSync.PrsNumBindRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://ngn.sanss.com/FptcSrvcInfoSync/", "PrsNumBindResponse");
            cachedSerQNames.add(qName);
            cls = com.sanss.ngn.FptcSrvcInfoSync.PrsNumBindResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://ngn.sanss.com/FptcSrvcInfoSync/", "ResultInfo");
            cachedSerQNames.add(qName);
            cls = com.sanss.ngn.FptcSrvcInfoSync.ResultInfo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://ngn.sanss.com/FptcSrvcInfoSync/", "SrvcInfo");
            cachedSerQNames.add(qName);
            cls = com.sanss.ngn.FptcSrvcInfoSync.SrvcInfo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://ngn.sanss.com/FptcSrvcInfoSync/", "SrvcModifyRequest");
            cachedSerQNames.add(qName);
            cls = com.sanss.ngn.FptcSrvcInfoSync.SrvcModifyRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://ngn.sanss.com/FptcSrvcInfoSync/", "SrvcModifyResponse");
            cachedSerQNames.add(qName);
            cls = com.sanss.ngn.FptcSrvcInfoSync.SrvcModifyResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://ngn.sanss.com/FptcSrvcInfoSync/", "SrvcSubscribeRequest");
            cachedSerQNames.add(qName);
            cls = com.sanss.ngn.FptcSrvcInfoSync.SrvcSubscribeRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://ngn.sanss.com/FptcSrvcInfoSync/", "SrvcSubscribeResponse");
            cachedSerQNames.add(qName);
            cls = com.sanss.ngn.FptcSrvcInfoSync.SrvcSubscribeResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://ngn.sanss.com/FptcSrvcInfoSync/", "UserInfoSyncRequest");
            cachedSerQNames.add(qName);
            cls = com.sanss.ngn.FptcSrvcInfoSync.UserInfoSyncRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://ngn.sanss.com/FptcSrvcInfoSync/", "UserInfoSyncResponse");
            cachedSerQNames.add(qName);
            cls = com.sanss.ngn.FptcSrvcInfoSync.UserInfoSyncResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://ngn.sanss.com/FptcSrvcInfoSync/", "VSubProdInfo");
            cachedSerQNames.add(qName);
            cls = com.sanss.ngn.FptcSrvcInfoSync.VSubProdInfo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

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

    public com.sanss.ngn.FptcSrvcInfoSync.UserInfoSyncResponse userInfoSync(com.sanss.ngn.FptcSrvcInfoSync.UserInfoSyncRequest inputParams) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://ngn.sanss.com/FptcSrvcInfoSync/UserInfoSync");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "UserInfoSync"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {inputParams});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.sanss.ngn.FptcSrvcInfoSync.UserInfoSyncResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.sanss.ngn.FptcSrvcInfoSync.UserInfoSyncResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.sanss.ngn.FptcSrvcInfoSync.UserInfoSyncResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.sanss.ngn.FptcSrvcInfoSync.SrvcSubscribeResponse srvcSubscribe(com.sanss.ngn.FptcSrvcInfoSync.SrvcSubscribeRequest inputParams) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://ngn.sanss.com/FptcSrvcInfoSync/SrvcSubscribe");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "SrvcSubscribe"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {inputParams});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.sanss.ngn.FptcSrvcInfoSync.SrvcSubscribeResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.sanss.ngn.FptcSrvcInfoSync.SrvcSubscribeResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.sanss.ngn.FptcSrvcInfoSync.SrvcSubscribeResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.sanss.ngn.FptcSrvcInfoSync.SrvcModifyResponse srvcModify(com.sanss.ngn.FptcSrvcInfoSync.SrvcModifyRequest inputParams) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://ngn.sanss.com/FptcSrvcInfoSync/SrvcModify");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "SrvcModify"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {inputParams});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.sanss.ngn.FptcSrvcInfoSync.SrvcModifyResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.sanss.ngn.FptcSrvcInfoSync.SrvcModifyResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.sanss.ngn.FptcSrvcInfoSync.SrvcModifyResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.sanss.ngn.FptcSrvcInfoSync.PrsNumBindResponse prsNumBind(com.sanss.ngn.FptcSrvcInfoSync.PrsNumBindRequest inputParams) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[3]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://ngn.sanss.com/FptcSrvcInfoSync/PrsNumBind");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "PrsNumBind"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {inputParams});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.sanss.ngn.FptcSrvcInfoSync.PrsNumBindResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.sanss.ngn.FptcSrvcInfoSync.PrsNumBindResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.sanss.ngn.FptcSrvcInfoSync.PrsNumBindResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

}
