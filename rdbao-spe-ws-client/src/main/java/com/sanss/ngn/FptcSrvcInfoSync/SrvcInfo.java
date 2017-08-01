/**
 * SrvcInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sanss.ngn.FptcSrvcInfoSync;

public class SrvcInfo  implements java.io.Serializable {
    private int srvcType;

    private java.lang.String srvcID;

    private com.sanss.ngn.FptcSrvcInfoSync.VSubProdInfo[] VSubProdInfo;

    private java.lang.String effDate;

    private java.lang.String expDate;

    private int isExperience;

    private int subscribeType;

    public SrvcInfo() {
    }

    public SrvcInfo(
           int srvcType,
           java.lang.String srvcID,
           com.sanss.ngn.FptcSrvcInfoSync.VSubProdInfo[] VSubProdInfo,
           java.lang.String effDate,
           java.lang.String expDate,
           int isExperience,
           int subscribeType) {
           this.srvcType = srvcType;
           this.srvcID = srvcID;
           this.VSubProdInfo = VSubProdInfo;
           this.effDate = effDate;
           this.expDate = expDate;
           this.isExperience = isExperience;
           this.subscribeType = subscribeType;
    }


    /**
     * Gets the srvcType value for this SrvcInfo.
     * 
     * @return srvcType
     */
    public int getSrvcType() {
        return srvcType;
    }


    /**
     * Sets the srvcType value for this SrvcInfo.
     * 
     * @param srvcType
     */
    public void setSrvcType(int srvcType) {
        this.srvcType = srvcType;
    }


    /**
     * Gets the srvcID value for this SrvcInfo.
     * 
     * @return srvcID
     */
    public java.lang.String getSrvcID() {
        return srvcID;
    }


    /**
     * Sets the srvcID value for this SrvcInfo.
     * 
     * @param srvcID
     */
    public void setSrvcID(java.lang.String srvcID) {
        this.srvcID = srvcID;
    }


    /**
     * Gets the VSubProdInfo value for this SrvcInfo.
     * 
     * @return VSubProdInfo
     */
    public com.sanss.ngn.FptcSrvcInfoSync.VSubProdInfo[] getVSubProdInfo() {
        return VSubProdInfo;
    }


    /**
     * Sets the VSubProdInfo value for this SrvcInfo.
     * 
     * @param VSubProdInfo
     */
    public void setVSubProdInfo(com.sanss.ngn.FptcSrvcInfoSync.VSubProdInfo[] VSubProdInfo) {
        this.VSubProdInfo = VSubProdInfo;
    }

    public com.sanss.ngn.FptcSrvcInfoSync.VSubProdInfo getVSubProdInfo(int i) {
        return this.VSubProdInfo[i];
    }

    public void setVSubProdInfo(int i, com.sanss.ngn.FptcSrvcInfoSync.VSubProdInfo _value) {
        this.VSubProdInfo[i] = _value;
    }


    /**
     * Gets the effDate value for this SrvcInfo.
     * 
     * @return effDate
     */
    public java.lang.String getEffDate() {
        return effDate;
    }


    /**
     * Sets the effDate value for this SrvcInfo.
     * 
     * @param effDate
     */
    public void setEffDate(java.lang.String effDate) {
        this.effDate = effDate;
    }


    /**
     * Gets the expDate value for this SrvcInfo.
     * 
     * @return expDate
     */
    public java.lang.String getExpDate() {
        return expDate;
    }


    /**
     * Sets the expDate value for this SrvcInfo.
     * 
     * @param expDate
     */
    public void setExpDate(java.lang.String expDate) {
        this.expDate = expDate;
    }


    /**
     * Gets the isExperience value for this SrvcInfo.
     * 
     * @return isExperience
     */
    public int getIsExperience() {
        return isExperience;
    }


    /**
     * Sets the isExperience value for this SrvcInfo.
     * 
     * @param isExperience
     */
    public void setIsExperience(int isExperience) {
        this.isExperience = isExperience;
    }


    /**
     * Gets the subscribeType value for this SrvcInfo.
     * 
     * @return subscribeType
     */
    public int getSubscribeType() {
        return subscribeType;
    }


    /**
     * Sets the subscribeType value for this SrvcInfo.
     * 
     * @param subscribeType
     */
    public void setSubscribeType(int subscribeType) {
        this.subscribeType = subscribeType;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SrvcInfo)) return false;
        SrvcInfo other = (SrvcInfo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.srvcType == other.getSrvcType() &&
            ((this.srvcID==null && other.getSrvcID()==null) || 
             (this.srvcID!=null &&
              this.srvcID.equals(other.getSrvcID()))) &&
            ((this.VSubProdInfo==null && other.getVSubProdInfo()==null) || 
             (this.VSubProdInfo!=null &&
              java.util.Arrays.equals(this.VSubProdInfo, other.getVSubProdInfo()))) &&
            ((this.effDate==null && other.getEffDate()==null) || 
             (this.effDate!=null &&
              this.effDate.equals(other.getEffDate()))) &&
            ((this.expDate==null && other.getExpDate()==null) || 
             (this.expDate!=null &&
              this.expDate.equals(other.getExpDate()))) &&
            this.isExperience == other.getIsExperience() &&
            this.subscribeType == other.getSubscribeType();
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        _hashCode += getSrvcType();
        if (getSrvcID() != null) {
            _hashCode += getSrvcID().hashCode();
        }
        if (getVSubProdInfo() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getVSubProdInfo());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getVSubProdInfo(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getEffDate() != null) {
            _hashCode += getEffDate().hashCode();
        }
        if (getExpDate() != null) {
            _hashCode += getExpDate().hashCode();
        }
        _hashCode += getIsExperience();
        _hashCode += getSubscribeType();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SrvcInfo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://ngn.sanss.com/FptcSrvcInfoSync/", "SrvcInfo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("srvcType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SrvcType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("srvcID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SrvcID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("VSubProdInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "VSubProdInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://ngn.sanss.com/FptcSrvcInfoSync/", "VSubProdInfo"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("effDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "EffDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("expDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ExpDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isExperience");
        elemField.setXmlName(new javax.xml.namespace.QName("", "IsExperience"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("subscribeType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SubscribeType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
