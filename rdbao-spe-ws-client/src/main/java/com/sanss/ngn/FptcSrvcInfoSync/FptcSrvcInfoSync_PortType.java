/**
 * FptcSrvcInfoSync_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sanss.ngn.FptcSrvcInfoSync;

public interface FptcSrvcInfoSync_PortType extends java.rmi.Remote {
    public com.sanss.ngn.FptcSrvcInfoSync.UserInfoSyncResponse userInfoSync(com.sanss.ngn.FptcSrvcInfoSync.UserInfoSyncRequest inputParams) throws java.rmi.RemoteException;
    public com.sanss.ngn.FptcSrvcInfoSync.SrvcSubscribeResponse srvcSubscribe(com.sanss.ngn.FptcSrvcInfoSync.SrvcSubscribeRequest inputParams) throws java.rmi.RemoteException;
    public com.sanss.ngn.FptcSrvcInfoSync.SrvcModifyResponse srvcModify(com.sanss.ngn.FptcSrvcInfoSync.SrvcModifyRequest inputParams) throws java.rmi.RemoteException;
    public com.sanss.ngn.FptcSrvcInfoSync.PrsNumBindResponse prsNumBind(com.sanss.ngn.FptcSrvcInfoSync.PrsNumBindRequest inputParams) throws java.rmi.RemoteException;
}
