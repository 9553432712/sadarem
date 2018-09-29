/**
 * ErcDetails.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.rationCardWS;

public class ErcDetails  implements java.io.Serializable {
    private com.rationCardWS.RcFamilyDetails carddetails;

    private com.rationCardWS.RcMemberDetails[] memberdetails;

    private java.lang.String message;

    public ErcDetails() {
    }

    public ErcDetails(
           com.rationCardWS.RcFamilyDetails carddetails,
           com.rationCardWS.RcMemberDetails[] memberdetails,
           java.lang.String message) {
           this.carddetails = carddetails;
           this.memberdetails = memberdetails;
           this.message = message;
    }


    /**
     * Gets the carddetails value for this ErcDetails.
     * 
     * @return carddetails
     */
    public com.rationCardWS.RcFamilyDetails getCarddetails() {
        return carddetails;
    }


    /**
     * Sets the carddetails value for this ErcDetails.
     * 
     * @param carddetails
     */
    public void setCarddetails(com.rationCardWS.RcFamilyDetails carddetails) {
        this.carddetails = carddetails;
    }


    /**
     * Gets the memberdetails value for this ErcDetails.
     * 
     * @return memberdetails
     */
    public com.rationCardWS.RcMemberDetails[] getMemberdetails() {
        return memberdetails;
    }


    /**
     * Sets the memberdetails value for this ErcDetails.
     * 
     * @param memberdetails
     */
    public void setMemberdetails(com.rationCardWS.RcMemberDetails[] memberdetails) {
        this.memberdetails = memberdetails;
    }

    public com.rationCardWS.RcMemberDetails getMemberdetails(int i) {
        return this.memberdetails[i];
    }

    public void setMemberdetails(int i, com.rationCardWS.RcMemberDetails _value) {
        this.memberdetails[i] = _value;
    }


    /**
     * Gets the message value for this ErcDetails.
     * 
     * @return message
     */
    public java.lang.String getMessage() {
        return message;
    }


    /**
     * Sets the message value for this ErcDetails.
     * 
     * @param message
     */
    public void setMessage(java.lang.String message) {
        this.message = message;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ErcDetails)) return false;
        ErcDetails other = (ErcDetails) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.carddetails==null && other.getCarddetails()==null) || 
             (this.carddetails!=null &&
              this.carddetails.equals(other.getCarddetails()))) &&
            ((this.memberdetails==null && other.getMemberdetails()==null) || 
             (this.memberdetails!=null &&
              java.util.Arrays.equals(this.memberdetails, other.getMemberdetails()))) &&
            ((this.message==null && other.getMessage()==null) || 
             (this.message!=null &&
              this.message.equals(other.getMessage())));
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
        if (getCarddetails() != null) {
            _hashCode += getCarddetails().hashCode();
        }
        if (getMemberdetails() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getMemberdetails());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getMemberdetails(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getMessage() != null) {
            _hashCode += getMessage().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ErcDetails.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://service.fetch.rationcard/", "ercDetails"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("carddetails");
        elemField.setXmlName(new javax.xml.namespace.QName("", "carddetails"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://service.fetch.rationcard/", "rcFamilyDetails"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("memberdetails");
        elemField.setXmlName(new javax.xml.namespace.QName("", "memberdetails"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://service.fetch.rationcard/", "rcMemberDetails"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("message");
        elemField.setXmlName(new javax.xml.namespace.QName("", "message"));
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
