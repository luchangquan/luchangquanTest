/**
 * SrvcSubscribeResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sanss.ngn.FptcSrvcInfoSync;

public class SrvcSubscribeResponse  extends com.sanss.ngn.FptcSrvcInfoSync.BasicResponse  implements java.io.Serializable {
    private com.sanss.ngn.FptcSrvcInfoSync.ResultInfo[] resultInfo;

    public SrvcSubscribeResponse() {
    }

    public SrvcSubscribeResponse(
           java.lang.String streamingNo,
           int resultCode,
           java.lang.String description,
           com.sanss.ngn.FptcSrvcInfoSync.ResultInfo[] resultInfo) {
        super(
            streamingNo,
            resultCode,
            description);
        this.resultInfo = resultInfo;
    }


    /**
     * Gets the resultInfo value for this SrvcSubscribeResponse.
     * 
     * @return resultInfo
     */
    public com.sanss.ngn.FptcSrvcInfoSync.ResultInfo[] getResultInfo() {
        return resultInfo;
    }


    /**
     * Sets the resultInfo value for this SrvcSubscribeResponse.
     * 
     * @param resultInfo
     */
    public void setResultInfo(com.sanss.ngn.FptcSrvcInfoSync.ResultInfo[] resultInfo) {
        this.resultInfo = resultInfo;
    }

    public com.sanss.ngn.FptcSrvcInfoSync.ResultInfo getResultInfo(int i) {
        return this.resultInfo[i];
    }

    public void setResultInfo(int i, com.sanss.ngn.FptcSrvcInfoSync.ResultInfo _value) {
        this.resultInfo[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SrvcSubscribeResponse)) return false;
        SrvcSubscribeResponse other = (SrvcSubscribeResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.resultInfo==null && other.getResultInfo()==null) || 
             (this.resultInfo!=null &&
              java.util.Arrays.equals(this.resultInfo, other.getResultInfo())));
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
        if (getResultInfo() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getResultInfo());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getResultInfo(), i);
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
        new org.apache.axis.description.TypeDesc(SrvcSubscribeResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://ngn.sanss.com/FptcSrvcInfoSync/", "SrvcSubscribeResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ResultInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://ngn.sanss.com/FptcSrvcInfoSync/", "ResultInfo"));
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
