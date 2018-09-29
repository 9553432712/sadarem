/**
 * UpdateApplicationDetailsResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.meesevaWS;

public class UpdateApplicationDetailsResponse  implements java.io.Serializable {
    private com.meesevaWS.UpdateApplicationDetailsResponseUpdateApplicationDetailsResult updateApplicationDetailsResult;

    public UpdateApplicationDetailsResponse() {
    }

    public UpdateApplicationDetailsResponse(
           com.meesevaWS.UpdateApplicationDetailsResponseUpdateApplicationDetailsResult updateApplicationDetailsResult) {
           this.updateApplicationDetailsResult = updateApplicationDetailsResult;
    }


    /**
     * Gets the updateApplicationDetailsResult value for this UpdateApplicationDetailsResponse.
     * 
     * @return updateApplicationDetailsResult
     */
    public com.meesevaWS.UpdateApplicationDetailsResponseUpdateApplicationDetailsResult getUpdateApplicationDetailsResult() {
        return updateApplicationDetailsResult;
    }


    /**
     * Sets the updateApplicationDetailsResult value for this UpdateApplicationDetailsResponse.
     * 
     * @param updateApplicationDetailsResult
     */
    public void setUpdateApplicationDetailsResult(com.meesevaWS.UpdateApplicationDetailsResponseUpdateApplicationDetailsResult updateApplicationDetailsResult) {
        this.updateApplicationDetailsResult = updateApplicationDetailsResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof UpdateApplicationDetailsResponse)) return false;
        UpdateApplicationDetailsResponse other = (UpdateApplicationDetailsResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.updateApplicationDetailsResult==null && other.getUpdateApplicationDetailsResult()==null) || 
             (this.updateApplicationDetailsResult!=null &&
              this.updateApplicationDetailsResult.equals(other.getUpdateApplicationDetailsResult())));
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
        if (getUpdateApplicationDetailsResult() != null) {
            _hashCode += getUpdateApplicationDetailsResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(UpdateApplicationDetailsResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://MsevaPaymentTG.org/", ">UpdateApplicationDetailsResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("updateApplicationDetailsResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://MsevaPaymentTG.org/", "UpdateApplicationDetailsResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://MsevaPaymentTG.org/", ">>UpdateApplicationDetailsResponse>UpdateApplicationDetailsResult"));
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
