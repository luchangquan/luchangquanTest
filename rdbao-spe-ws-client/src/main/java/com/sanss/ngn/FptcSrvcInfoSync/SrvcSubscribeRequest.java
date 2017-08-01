/**
 * SrvcSubscribeRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sanss.ngn.FptcSrvcInfoSync;

public class SrvcSubscribeRequest  extends com.sanss.ngn.FptcSrvcInfoSync.BasicRequest  implements java.io.Serializable {
    private int actionType;

    private java.lang.String orderId;

    private int userIDType;

    private java.lang.String userNumber;

    private com.sanss.ngn.FptcSrvcInfoSync.SrvcInfo srvcInfo;

    public SrvcSubscribeRequest() {
    }

    public SrvcSubscribeRequest(
           java.lang.String streamingNo,
           java.lang.String srcSystemNo,
           java.lang.String signature,
           java.lang.String timeStamp,
           int actionType,
           java.lang.String orderId,
           int userIDType,
           java.lang.String userNumber,
           com.sanss.ngn.FptcSrvcInfoSync.SrvcInfo srvcInfo) {
        super(
            streamingNo,
            srcSystemNo,
            signature,
            timeStamp);
        this.actionType = actionType;
        this.orderId = orderId;
        this.userIDType = userIDType;
        this.userNumber = userNumber;
        this.srvcInfo = srvcInfo;
    }


    /**
     * Gets the actionType value for this SrvcSubscribeRequest.
     * 
     * @return actionType
     */
    public int getActionType() {
        return actionType;
    }


    /**
     * Sets the actionType value for this SrvcSubscribeRequest.
     * 
     * @param actionType
     */
    public void setActionType(int actionType) {
        this.actionType = actionType;
    }


    /**
     * Gets the orderId value for this SrvcSubscribeRequest.
     * 
     * @return orderId
     */
    public java.lang.String getOrderId() {
        return orderId;
    }


    /**
     * Sets the orderId value for this SrvcSubscribeRequest.
     * 
     * @param orderId
     */
    public void setOrderId(java.lang.String orderId) {
        this.orderId = orderId;
    }


    /**
     * Gets the userIDType value for this SrvcSubscribeRequest.
     * 
     * @return userIDType
     */
    public int getUserIDType() {
        return userIDType;
    }


    /**
     * Sets the userIDType value for this SrvcSubscribeRequest.
     * 
     * @param userIDType
     */
    public void setUserIDType(int userIDType) {
        this.userIDType = userIDType;
    }


    /**
     * Gets the userNumber value for this SrvcSubscribeRequest.
     * 
     * @return userNumber
     */
    public java.lang.String getUserNumber() {
        return userNumber;
    }


    /**
     * Sets the userNumber value for this SrvcSubscribeRequest.
     * 
     * @param userNumber
     */
    public void setUserNumber(java.lang.String userNumber) {
        this.userNumber = userNumber;
    }


    /**
     * Gets the srvcInfo value for this SrvcSubscribeRequest.
     * 
     * @return srvcInfo
     */
    public com.sanss.ngn.FptcSrvcInfoSync.SrvcInfo getSrvcInfo() {
        return srvcInfo;
    }


    /**
     * Sets the srvcInfo value for this SrvcSubscribeRequest.
     * 
     * @param srvcInfo
     */
    public void setSrvcInfo(com.sanss.ngn.FptcSrvcInfoSync.SrvcInfo srvcInfo) {
        this.srvcInfo = srvcInfo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SrvcSubscribeRequest)) return false;
        SrvcSubscribeRequest other = (SrvcSubscribeRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            this.actionType == other.getActionType() &&
            ((this.orderId==null && other.getOrderId()==null) || 
             (this.orderId!=null &&
              this.orderId.equals(other.getOrderId()))) &&
            this.userIDType == other.getUserIDType() &&
            ((this.userNumber==null && other.getUserNumber()==null) || 
             (this.userNumber!=null &&
              this.userNumber.equals(other.getUserNumber()))) &&
            ((this.srvcInfo==null && other.getSrvcInfo()==null) || 
             (this.srvcInfo!=null &&
              this.srvcInfo.equals(other.getSrvcInfo())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = super.hashCode();
        _hashCode += getActionType();
        if (getOrderId() != null) {
            _hashCode += getOrderId().hashCode();
        }
        _hashCode += getUserIDType();
        if (getUserNumber() != null) {
            _hashCode += getUserNumber().hashCode();
        }
        if (getSrvcInfo() != null) {
            _hashCode += getSrvcInfo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SrvcSubscribeRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://ngn.sanss.com/FptcSrvcInfoSync/", "SrvcSubscribeRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("actionType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ActionType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("orderId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "OrderId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userIDType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "UserIDType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "UserNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("srvcInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SrvcInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://ngn.sanss.com/FptcSrvcInfoSync/", "SrvcInfo"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
