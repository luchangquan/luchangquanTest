/**
 * PrsNumBindRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sanss.ngn.FptcSrvcInfoSync;

public class PrsNumBindRequest  extends com.sanss.ngn.FptcSrvcInfoSync.BasicRequest  implements java.io.Serializable {
    private int actionType;

    private java.lang.String userNumber;

    private java.lang.String bizNumber;

    private java.lang.String productId;

    public PrsNumBindRequest() {
    }

    public PrsNumBindRequest(
           java.lang.String streamingNo,
           java.lang.String srcSystemNo,
           java.lang.String signature,
           java.lang.String timeStamp,
           int actionType,
           java.lang.String userNumber,
           java.lang.String bizNumber,
           java.lang.String productId) {
        super(
            streamingNo,
            srcSystemNo,
            signature,
            timeStamp);
        this.actionType = actionType;
        this.userNumber = userNumber;
        this.bizNumber = bizNumber;
        this.productId = productId;
    }


    /**
     * Gets the actionType value for this PrsNumBindRequest.
     * 
     * @return actionType
     */
    public int getActionType() {
        return actionType;
    }


    /**
     * Sets the actionType value for this PrsNumBindRequest.
     * 
     * @param actionType
     */
    public void setActionType(int actionType) {
        this.actionType = actionType;
    }


    /**
     * Gets the userNumber value for this PrsNumBindRequest.
     * 
     * @return userNumber
     */
    public java.lang.String getUserNumber() {
        return userNumber;
    }


    /**
     * Sets the userNumber value for this PrsNumBindRequest.
     * 
     * @param userNumber
     */
    public void setUserNumber(java.lang.String userNumber) {
        this.userNumber = userNumber;
    }


    /**
     * Gets the bizNumber value for this PrsNumBindRequest.
     * 
     * @return bizNumber
     */
    public java.lang.String getBizNumber() {
        return bizNumber;
    }


    /**
     * Sets the bizNumber value for this PrsNumBindRequest.
     * 
     * @param bizNumber
     */
    public void setBizNumber(java.lang.String bizNumber) {
        this.bizNumber = bizNumber;
    }


    /**
     * Gets the productId value for this PrsNumBindRequest.
     * 
     * @return productId
     */
    public java.lang.String getProductId() {
        return productId;
    }


    /**
     * Sets the productId value for this PrsNumBindRequest.
     * 
     * @param productId
     */
    public void setProductId(java.lang.String productId) {
        this.productId = productId;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PrsNumBindRequest)) return false;
        PrsNumBindRequest other = (PrsNumBindRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            this.actionType == other.getActionType() &&
            ((this.userNumber==null && other.getUserNumber()==null) || 
             (this.userNumber!=null &&
              this.userNumber.equals(other.getUserNumber()))) &&
            ((this.bizNumber==null && other.getBizNumber()==null) || 
             (this.bizNumber!=null &&
              this.bizNumber.equals(other.getBizNumber()))) &&
            ((this.productId==null && other.getProductId()==null) || 
             (this.productId!=null &&
              this.productId.equals(other.getProductId())));
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
        if (getUserNumber() != null) {
            _hashCode += getUserNumber().hashCode();
        }
        if (getBizNumber() != null) {
            _hashCode += getBizNumber().hashCode();
        }
        if (getProductId() != null) {
            _hashCode += getProductId().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PrsNumBindRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://ngn.sanss.com/FptcSrvcInfoSync/", "PrsNumBindRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("actionType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ActionType"));
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
        elemField.setFieldName("bizNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "BizNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("productId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ProductId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
