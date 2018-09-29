/**
 * GetPaymentTransId.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.meesevaWS;

public class GetPaymentTransId  implements java.io.Serializable {
    private java.lang.String[] arrPaymentDetails;

    private java.lang.String[] arrAmount;

    private java.lang.String[] arrTransParams;

    private java.lang.String[] arrTransDetails;

    private java.lang.String userid;

    private java.lang.String password;

    private java.lang.String checksum;

    public GetPaymentTransId() {
    }

    public GetPaymentTransId(
           java.lang.String[] arrPaymentDetails,
           java.lang.String[] arrAmount,
           java.lang.String[] arrTransParams,
           java.lang.String[] arrTransDetails,
           java.lang.String userid,
           java.lang.String password,
           java.lang.String checksum) {
           this.arrPaymentDetails = arrPaymentDetails;
           this.arrAmount = arrAmount;
           this.arrTransParams = arrTransParams;
           this.arrTransDetails = arrTransDetails;
           this.userid = userid;
           this.password = password;
           this.checksum = checksum;
    }


    /**
     * Gets the arrPaymentDetails value for this GetPaymentTransId.
     * 
     * @return arrPaymentDetails
     */
    public java.lang.String[] getArrPaymentDetails() {
        return arrPaymentDetails;
    }


    /**
     * Sets the arrPaymentDetails value for this GetPaymentTransId.
     * 
     * @param arrPaymentDetails
     */
    public void setArrPaymentDetails(java.lang.String[] arrPaymentDetails) {
        this.arrPaymentDetails = arrPaymentDetails;
    }


    /**
     * Gets the arrAmount value for this GetPaymentTransId.
     * 
     * @return arrAmount
     */
    public java.lang.String[] getArrAmount() {
        return arrAmount;
    }


    /**
     * Sets the arrAmount value for this GetPaymentTransId.
     * 
     * @param arrAmount
     */
    public void setArrAmount(java.lang.String[] arrAmount) {
        this.arrAmount = arrAmount;
    }


    /**
     * Gets the arrTransParams value for this GetPaymentTransId.
     * 
     * @return arrTransParams
     */
    public java.lang.String[] getArrTransParams() {
        return arrTransParams;
    }


    /**
     * Sets the arrTransParams value for this GetPaymentTransId.
     * 
     * @param arrTransParams
     */
    public void setArrTransParams(java.lang.String[] arrTransParams) {
        this.arrTransParams = arrTransParams;
    }


    /**
     * Gets the arrTransDetails value for this GetPaymentTransId.
     * 
     * @return arrTransDetails
     */
    public java.lang.String[] getArrTransDetails() {
        return arrTransDetails;
    }


    /**
     * Sets the arrTransDetails value for this GetPaymentTransId.
     * 
     * @param arrTransDetails
     */
    public void setArrTransDetails(java.lang.String[] arrTransDetails) {
        this.arrTransDetails = arrTransDetails;
    }


    /**
     * Gets the userid value for this GetPaymentTransId.
     * 
     * @return userid
     */
    public java.lang.String getUserid() {
        return userid;
    }


    /**
     * Sets the userid value for this GetPaymentTransId.
     * 
     * @param userid
     */
    public void setUserid(java.lang.String userid) {
        this.userid = userid;
    }


    /**
     * Gets the password value for this GetPaymentTransId.
     * 
     * @return password
     */
    public java.lang.String getPassword() {
        return password;
    }


    /**
     * Sets the password value for this GetPaymentTransId.
     * 
     * @param password
     */
    public void setPassword(java.lang.String password) {
        this.password = password;
    }


    /**
     * Gets the checksum value for this GetPaymentTransId.
     * 
     * @return checksum
     */
    public java.lang.String getChecksum() {
        return checksum;
    }


    /**
     * Sets the checksum value for this GetPaymentTransId.
     * 
     * @param checksum
     */
    public void setChecksum(java.lang.String checksum) {
        this.checksum = checksum;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetPaymentTransId)) return false;
        GetPaymentTransId other = (GetPaymentTransId) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.arrPaymentDetails==null && other.getArrPaymentDetails()==null) || 
             (this.arrPaymentDetails!=null &&
              java.util.Arrays.equals(this.arrPaymentDetails, other.getArrPaymentDetails()))) &&
            ((this.arrAmount==null && other.getArrAmount()==null) || 
             (this.arrAmount!=null &&
              java.util.Arrays.equals(this.arrAmount, other.getArrAmount()))) &&
            ((this.arrTransParams==null && other.getArrTransParams()==null) || 
             (this.arrTransParams!=null &&
              java.util.Arrays.equals(this.arrTransParams, other.getArrTransParams()))) &&
            ((this.arrTransDetails==null && other.getArrTransDetails()==null) || 
             (this.arrTransDetails!=null &&
              java.util.Arrays.equals(this.arrTransDetails, other.getArrTransDetails()))) &&
            ((this.userid==null && other.getUserid()==null) || 
             (this.userid!=null &&
              this.userid.equals(other.getUserid()))) &&
            ((this.password==null && other.getPassword()==null) || 
             (this.password!=null &&
              this.password.equals(other.getPassword()))) &&
            ((this.checksum==null && other.getChecksum()==null) || 
             (this.checksum!=null &&
              this.checksum.equals(other.getChecksum())));
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
        if (getArrPaymentDetails() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getArrPaymentDetails());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getArrPaymentDetails(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getArrAmount() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getArrAmount());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getArrAmount(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getArrTransParams() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getArrTransParams());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getArrTransParams(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getArrTransDetails() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getArrTransDetails());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getArrTransDetails(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getUserid() != null) {
            _hashCode += getUserid().hashCode();
        }
        if (getPassword() != null) {
            _hashCode += getPassword().hashCode();
        }
        if (getChecksum() != null) {
            _hashCode += getChecksum().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetPaymentTransId.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://MsevaPaymentTG.org/", ">GetPaymentTransId"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("arrPaymentDetails");
        elemField.setXmlName(new javax.xml.namespace.QName("http://MsevaPaymentTG.org/", "arrPaymentDetails"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://MsevaPaymentTG.org/", "string"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("arrAmount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://MsevaPaymentTG.org/", "arrAmount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://MsevaPaymentTG.org/", "string"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("arrTransParams");
        elemField.setXmlName(new javax.xml.namespace.QName("http://MsevaPaymentTG.org/", "arrTransParams"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://MsevaPaymentTG.org/", "string"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("arrTransDetails");
        elemField.setXmlName(new javax.xml.namespace.QName("http://MsevaPaymentTG.org/", "arrTransDetails"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://MsevaPaymentTG.org/", "string"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userid");
        elemField.setXmlName(new javax.xml.namespace.QName("http://MsevaPaymentTG.org/", "userid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("password");
        elemField.setXmlName(new javax.xml.namespace.QName("http://MsevaPaymentTG.org/", "password"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("checksum");
        elemField.setXmlName(new javax.xml.namespace.QName("http://MsevaPaymentTG.org/", "checksum"));
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
