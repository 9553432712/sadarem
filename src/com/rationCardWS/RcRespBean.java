/**
 * RcRespBean.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.rationCardWS;

public class RcRespBean  implements java.io.Serializable {
    private com.rationCardWS.ErcDetails rationCardDetails;

    private java.lang.String respCode;

    private java.lang.String respMessage;

    public RcRespBean() {
    }

    public RcRespBean(
           com.rationCardWS.ErcDetails rationCardDetails,
           java.lang.String respCode,
           java.lang.String respMessage) {
           this.rationCardDetails = rationCardDetails;
           this.respCode = respCode;
           this.respMessage = respMessage;
    }


    /**
     * Gets the rationCardDetails value for this RcRespBean.
     * 
     * @return rationCardDetails
     */
    public com.rationCardWS.ErcDetails getRationCardDetails() {
        return rationCardDetails;
    }


    /**
     * Sets the rationCardDetails value for this RcRespBean.
     * 
     * @param rationCardDetails
     */
    public void setRationCardDetails(com.rationCardWS.ErcDetails rationCardDetails) {
        this.rationCardDetails = rationCardDetails;
    }


    /**
     * Gets the respCode value for this RcRespBean.
     * 
     * @return respCode
     */
    public java.lang.String getRespCode() {
        return respCode;
    }


    /**
     * Sets the respCode value for this RcRespBean.
     * 
     * @param respCode
     */
    public void setRespCode(java.lang.String respCode) {
        this.respCode = respCode;
    }


    /**
     * Gets the respMessage value for this RcRespBean.
     * 
     * @return respMessage
     */
    public java.lang.String getRespMessage() {
        return respMessage;
    }


    /**
     * Sets the respMessage value for this RcRespBean.
     * 
     * @param respMessage
     */
    public void setRespMessage(java.lang.String respMessage) {
        this.respMessage = respMessage;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RcRespBean)) return false;
        RcRespBean other = (RcRespBean) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.rationCardDetails==null && other.getRationCardDetails()==null) || 
             (this.rationCardDetails!=null &&
              this.rationCardDetails.equals(other.getRationCardDetails()))) &&
            ((this.respCode==null && other.getRespCode()==null) || 
             (this.respCode!=null &&
              this.respCode.equals(other.getRespCode()))) &&
            ((this.respMessage==null && other.getRespMessage()==null) || 
             (this.respMessage!=null &&
              this.respMessage.equals(other.getRespMessage())));
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
        if (getRationCardDetails() != null) {
            _hashCode += getRationCardDetails().hashCode();
        }
        if (getRespCode() != null) {
            _hashCode += getRespCode().hashCode();
        }
        if (getRespMessage() != null) {
            _hashCode += getRespMessage().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RcRespBean.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://service.fetch.rationcard/", "rcRespBean"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rationCardDetails");
        elemField.setXmlName(new javax.xml.namespace.QName("", "rationCardDetails"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://service.fetch.rationcard/", "ercDetails"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("respCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "respCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("respMessage");
        elemField.setXmlName(new javax.xml.namespace.QName("", "respMessage"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
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
