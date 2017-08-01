/**
 * SrvcModifyRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sanss.ngn.FptcSrvcInfoSync;

public class SrvcModifyRequest  extends com.sanss.ngn.FptcSrvcInfoSync.BasicRequest  implements java.io.Serializable {
    private java.lang.String orderId;

    private java.lang.String userNumber;

    private java.lang.String srvcID;

    private com.sanss.ngn.FptcSrvcInfoSync.Property[] propertyList;

    public SrvcModifyRequest() {
    }

    public SrvcModifyRequest(
           java.lang.String streamingNo,
           java.lang.String srcSystemNo,
           java.lang.String signature,
           java.lang.String timeStamp,
           java.lang.String orderId,
           java.lang.String userNumber,
           java.lang.String srvcID,
           com.sanss.ngn.FptcSrvcInfoSync.Property[] propertyList) {
        super(
            streamingNo,
            srcSystemNo,
            signature,
            timeStamp);
        this.orderId = orderId;
        this.userNumber = userNumber;
        this.srvcID = srvcID;
        this.propertyList = propertyList;
    }


    /**
     * Gets the orderId value for this SrvcModifyRequest.
     * 
     * @return orderId
     */
    public java.lang.String getOrderId() {
        return orderId;
    }


    /**
     * Sets the orderId value for this SrvcModifyRequest.
     * 
     * @param orderId
     */
    public void setOrderId(java.lang.String orderId) {
        this.orderId = orderId;
    }


    /**
     * Gets the userNumber value for this SrvcModifyRequest.
     * 
     * @return userNumber
     */
    public java.lang.String getUserNumber() {
        return userNumber;
    }


    /**
     * Sets the userNumber value for this SrvcModifyRequest.
     * 
     * @param userNumber
     */
    public void setUserNumber(java.lang.String userNumber) {
        this.userNumber = userNumber;
    }


    /**
     * Gets the srvcID value for this SrvcModifyRequest.
     * 
     * @return srvcID
     */
    public java.lang.String getSrvcID() {
        return srvcID;
    }


    /**
     * Sets the srvcID value for this SrvcModifyRequest.
     * 
     * @param srvcID
     */
    public void setSrvcID(java.lang.String srvcID) {
        this.srvcID = srvcID;
    }


    /**
     * Gets the propertyList value for this SrvcModifyRequest.
     * 
     * @return propertyList
     */
    public com.sanss.ngn.FptcSrvcInfoSync.Property[] getPropertyList() {
        return propertyList;
    }


    /**
     * Sets the propertyList value for this SrvcModifyRequest.
     * 
     * @param propertyList
     */
    public void setPropertyList(com.sanss.ngn.FptcSrvcInfoSync.Property[] propertyList) {
        this.propertyList = propertyList;
    }

    public com.sanss.ngn.FptcSrvcInfoSync.Property getPropertyList(int i) {
        return this.propertyList[i];
    }

    public void setPropertyList(int i, com.sanss.ngn.FptcSrvcInfoSync.Property _value) {
        this.propertyList[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SrvcModifyRequest)) return false;
        SrvcModifyRequest other = (SrvcModifyRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.orderId==null && other.getOrderId()==null) || 
             (this.orderId!=null &&
              this.orderId.equals(other.getOrderId()))) &&
            ((this.userNumber==null && other.getUserNumber()==null) || 
             (this.userNumber!=null &&
              this.userNumber.equals(other.getUserNumber()))) &&
            ((this.srvcID==null && other.getSrvcID()==null) || 
             (this.srvcID!=null &&
              this.srvcID.equals(other.getSrvcID()))) &&
            ((this.propertyList==null && other.getPropertyList()==null) || 
             (this.propertyList!=null &&
              java.util.Arrays.equals(this.propertyList, other.getPropertyList())));
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
        if (getOrderId() != null) {
            _hashCode += getOrderId().hashCode();
        }
        if (getUserNumber() != null) {
            _hashCode += getUserNumber().hashCode();
        }
        if (getSrvcID() != null) {
            _hashCode += getSrvcID().hashCode();
        }
        if (getPropertyList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getPropertyList());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getPropertyList(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SrvcModifyRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://ngn.sanss.com/FptcSrvcInfoSync/", "SrvcModifyRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("orderId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "OrderId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "UserNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("srvcID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SrvcID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("propertyList");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PropertyList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://ngn.sanss.com/FptcSrvcInfoSync/", "Property"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
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
