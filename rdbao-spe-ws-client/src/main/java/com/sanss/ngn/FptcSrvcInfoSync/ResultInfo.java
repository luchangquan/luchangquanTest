/**
 * ResultInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sanss.ngn.FptcSrvcInfoSync;

public class ResultInfo  implements java.io.Serializable {
    private java.lang.String productOfferID;

    private java.lang.String productOfferName;

    private int OPResult;

    private java.lang.String OPDesc;

    public ResultInfo() {
    }

    public ResultInfo(
           java.lang.String productOfferID,
           java.lang.String productOfferName,
           int OPResult,
           java.lang.String OPDesc) {
           this.productOfferID = productOfferID;
           this.productOfferName = productOfferName;
           this.OPResult = OPResult;
           this.OPDesc = OPDesc;
    }


    /**
     * Gets the productOfferID value for this ResultInfo.
     * 
     * @return productOfferID
     */
    public java.lang.String getProductOfferID() {
        return productOfferID;
    }


    /**
     * Sets the productOfferID value for this ResultInfo.
     * 
     * @param productOfferID
     */
    public void setProductOfferID(java.lang.String productOfferID) {
        this.productOfferID = productOfferID;
    }


    /**
     * Gets the productOfferName value for this ResultInfo.
     * 
     * @return productOfferName
     */
    public java.lang.String getProductOfferName() {
        return productOfferName;
    }


    /**
     * Sets the productOfferName value for this ResultInfo.
     * 
     * @param productOfferName
     */
    public void setProductOfferName(java.lang.String productOfferName) {
        this.productOfferName = productOfferName;
    }


    /**
     * Gets the OPResult value for this ResultInfo.
     * 
     * @return OPResult
     */
    public int getOPResult() {
        return OPResult;
    }


    /**
     * Sets the OPResult value for this ResultInfo.
     * 
     * @param OPResult
     */
    public void setOPResult(int OPResult) {
        this.OPResult = OPResult;
    }


    /**
     * Gets the OPDesc value for this ResultInfo.
     * 
     * @return OPDesc
     */
    public java.lang.String getOPDesc() {
        return OPDesc;
    }


    /**
     * Sets the OPDesc value for this ResultInfo.
     * 
     * @param OPDesc
     */
    public void setOPDesc(java.lang.String OPDesc) {
        this.OPDesc = OPDesc;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultInfo)) return false;
        ResultInfo other = (ResultInfo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.productOfferID==null && other.getProductOfferID()==null) || 
             (this.productOfferID!=null &&
              this.productOfferID.equals(other.getProductOfferID()))) &&
            ((this.productOfferName==null && other.getProductOfferName()==null) || 
             (this.productOfferName!=null &&
              this.productOfferName.equals(other.getProductOfferName()))) &&
            this.OPResult == other.getOPResult() &&
            ((this.OPDesc==null && other.getOPDesc()==null) || 
             (this.OPDesc!=null &&
              this.OPDesc.equals(other.getOPDesc())));
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
        if (getProductOfferID() != null) {
            _hashCode += getProductOfferID().hashCode();
        }
        if (getProductOfferName() != null) {
            _hashCode += getProductOfferName().hashCode();
        }
        _hashCode += getOPResult();
        if (getOPDesc() != null) {
            _hashCode += getOPDesc().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ResultInfo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://ngn.sanss.com/FptcSrvcInfoSync/", "ResultInfo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("productOfferID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ProductOfferID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("productOfferName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ProductOfferName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("OPResult");
        elemField.setXmlName(new javax.xml.namespace.QName("", "OPResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("OPDesc");
        elemField.setXmlName(new javax.xml.namespace.QName("", "OPDesc"));
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
