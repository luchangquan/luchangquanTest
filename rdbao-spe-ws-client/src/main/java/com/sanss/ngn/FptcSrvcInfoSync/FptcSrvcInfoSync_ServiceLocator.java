/**
 * FptcSrvcInfoSync_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sanss.ngn.FptcSrvcInfoSync;

public class FptcSrvcInfoSync_ServiceLocator extends org.apache.axis.client.Service implements com.sanss.ngn.FptcSrvcInfoSync.FptcSrvcInfoSync_Service {

    public FptcSrvcInfoSync_ServiceLocator() {
    }


    public FptcSrvcInfoSync_ServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public FptcSrvcInfoSync_ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for FptcSrvcInfoSyncSOAP
    private java.lang.String FptcSrvcInfoSyncSOAP_address = "http://101.95.49.32:80/FptcSrvcInfoSync/FptcSrvcInfoSyncSOAP";

    public java.lang.String getFptcSrvcInfoSyncSOAPAddress() {
        return FptcSrvcInfoSyncSOAP_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String FptcSrvcInfoSyncSOAPWSDDServiceName = "FptcSrvcInfoSyncSOAP";

    public java.lang.String getFptcSrvcInfoSyncSOAPWSDDServiceName() {
        return FptcSrvcInfoSyncSOAPWSDDServiceName;
    }

    public void setFptcSrvcInfoSyncSOAPWSDDServiceName(java.lang.String name) {
        FptcSrvcInfoSyncSOAPWSDDServiceName = name;
    }

    public com.sanss.ngn.FptcSrvcInfoSync.FptcSrvcInfoSync_PortType getFptcSrvcInfoSyncSOAP() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(FptcSrvcInfoSyncSOAP_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getFptcSrvcInfoSyncSOAP(endpoint);
    }

    public com.sanss.ngn.FptcSrvcInfoSync.FptcSrvcInfoSync_PortType getFptcSrvcInfoSyncSOAP(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.sanss.ngn.FptcSrvcInfoSync.FptcSrvcInfoSyncSOAPStub _stub = new com.sanss.ngn.FptcSrvcInfoSync.FptcSrvcInfoSyncSOAPStub(portAddress, this);
            _stub.setPortName(getFptcSrvcInfoSyncSOAPWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setFptcSrvcInfoSyncSOAPEndpointAddress(java.lang.String address) {
        FptcSrvcInfoSyncSOAP_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.sanss.ngn.FptcSrvcInfoSync.FptcSrvcInfoSync_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.sanss.ngn.FptcSrvcInfoSync.FptcSrvcInfoSyncSOAPStub _stub = new com.sanss.ngn.FptcSrvcInfoSync.FptcSrvcInfoSyncSOAPStub(new java.net.URL(FptcSrvcInfoSyncSOAP_address), this);
                _stub.setPortName(getFptcSrvcInfoSyncSOAPWSDDServiceName());
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
        if ("FptcSrvcInfoSyncSOAP".equals(inputPortName)) {
            return getFptcSrvcInfoSyncSOAP();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://ngn.sanss.com/FptcSrvcInfoSync/", "FptcSrvcInfoSync");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://ngn.sanss.com/FptcSrvcInfoSync/", "FptcSrvcInfoSyncSOAP"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("FptcSrvcInfoSyncSOAP".equals(portName)) {
            setFptcSrvcInfoSyncSOAPEndpointAddress(address);
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
