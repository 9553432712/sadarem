/**
 * UpdateApplicationDetails.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.meesevaWS;

public class UpdateApplicationDetails  implements java.io.Serializable {
    private java.lang.String appno;

    private java.lang.String status;

    private java.lang.String remarks;

    private java.lang.String updatedby;

    private java.lang.String userid;

    private java.lang.String password;

    private java.lang.String deptFlag;

    private java.lang.String checksum;

    public UpdateApplicationDetails() {
    }

    public UpdateApplicationDetails(
           java.lang.String appno,
           java.lang.String status,
           java.lang.String remarks,
           java.lang.String updatedby,
           java.lang.String userid,
           java.lang.String password,
           java.lang.String deptFlag,
           java.lang.String checksum) {
           this.appno = appno;
           this.status = status;
           this.remarks = remarks;
           this.updatedby = updatedby;
           this.userid = userid;
           this.password = password;
           this.deptFlag = deptFlag;
           this.checksum = checksum;
    }


    /**
     * Gets the appno value for this UpdateApplicationDetails.
     * 
     * @return appno
     */
    public java.lang.String getAppno() {
        return appno;
    }


    /**
     * Sets the appno value for this UpdateApplicationDetails.
     * 
     * @param appno
     */
    public void setAppno(java.lang.String appno) {
        this.appno = appno;
    }


    /**
     * Gets the status value for this UpdateApplicationDetails.
     * 
     * @return status
     */
    public java.lang.String getStatus() {
        return status;
    }


    /**
     * Sets the status value for this UpdateApplicationDetails.
     * 
     * @param status
     */
    public void setStatus(java.lang.String status) {
        this.status = status;
    }


    /**
     * Gets the remarks value for this UpdateApplicationDetails.
     * 
     * @return remarks
     */
    public java.lang.String getRemarks() {
        return remarks;
    }


    /**
     * Sets the remarks value for this UpdateApplicationDetails.
     * 
     * @param remarks
     */
    public void setRemarks(java.lang.String remarks) {
        this.remarks = remarks;
    }


    /**
     * Gets the updatedby value for this UpdateApplicationDetails.
     * 
     * @return updatedby
     */
    public java.lang.String getUpdatedby() {
        return updatedby;
    }


    /**
     * Sets the updatedby value for this UpdateApplicationDetails.
     * 
     * @param updatedby
     */
    public void setUpdatedby(java.lang.String updatedby) {
        this.updatedby = updatedby;
    }


    /**
     * Gets the userid value for this UpdateApplicationDetails.
     * 
     * @return userid
     */
    public java.lang.String getUserid() {
        return userid;
    }


    /**
     * Sets the userid value for this UpdateApplicationDetails.
     * 
     * @param userid
     */
    public void setUserid(java.lang.String userid) {
        this.userid = userid;
    }


    /**
     * Gets the password value for this UpdateApplicationDetails.
     * 
     * @return password
     */
    public java.lang.String getPassword() {
        return password;
    }


    /**
     * Sets the password value for this UpdateApplicationDetails.
     * 
     * @param password
     */
    public void setPassword(java.lang.String password) {
        this.password = password;
    }


    /**
     * Gets the deptFlag value for this UpdateApplicationDetails.
     * 
     * @return deptFlag
     */
    public java.lang.String getDeptFlag() {
        return deptFlag;
    }


    /**
     * Sets the deptFlag value for this UpdateApplicationDetails.
     * 
     * @param deptFlag
     */
    public void setDeptFlag(java.lang.String deptFlag) {
        this.deptFlag = deptFlag;
    }


    /**
     * Gets the checksum value for this UpdateApplicationDetails.
     * 
     * @return checksum
     */
    public java.lang.String getChecksum() {
        return checksum;
    }


    /**
     * Sets the checksum value for this UpdateApplicationDetails.
     * 
     * @param checksum
     */
    public void setChecksum(java.lang.String checksum) {
        this.checksum = checksum;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof UpdateApplicationDetails)) return false;
        UpdateApplicationDetails other = (UpdateApplicationDetails) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.appno==null && other.getAppno()==null) || 
             (this.appno!=null &&
              this.appno.equals(other.getAppno()))) &&
            ((this.status==null && other.getStatus()==null) || 
             (this.status!=null &&
              this.status.equals(other.getStatus()))) &&
            ((this.remarks==null && other.getRemarks()==null) || 
             (this.remarks!=null &&
              this.remarks.equals(other.getRemarks()))) &&
            ((this.updatedby==null && other.getUpdatedby()==null) || 
             (this.updatedby!=null &&
              this.updatedby.equals(other.getUpdatedby()))) &&
            ((this.userid==null && other.getUserid()==null) || 
             (this.userid!=null &&
              this.userid.equals(other.getUserid()))) &&
            ((this.password==null && other.getPassword()==null) || 
             (this.password!=null &&
              this.password.equals(other.getPassword()))) &&
            ((this.deptFlag==null && other.getDeptFlag()==null) || 
             (this.deptFlag!=null &&
              this.deptFlag.equals(other.getDeptFlag()))) &&
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
        if (getAppno() != null) {
            _hashCode += getAppno().hashCode();
        }
        if (getStatus() != null) {
            _hashCode += getStatus().hashCode();
        }
        if (getRemarks() != null) {
            _hashCode += getRemarks().hashCode();
        }
        if (getUpdatedby() != null) {
            _hashCode += getUpdatedby().hashCode();
        }
        if (getUserid() != null) {
            _hashCode += getUserid().hashCode();
        }
        if (getPassword() != null) {
            _hashCode += getPassword().hashCode();
        }
        if (getDeptFlag() != null) {
            _hashCode += getDeptFlag().hashCode();
        }
        if (getChecksum() != null) {
            _hashCode += getChecksum().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(UpdateApplicationDetails.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://MsevaPaymentTG.org/", ">UpdateApplicationDetails"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("appno");
        elemField.setXmlName(new javax.xml.namespace.QName("http://MsevaPaymentTG.org/", "appno"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("status");
        elemField.setXmlName(new javax.xml.namespace.QName("http://MsevaPaymentTG.org/", "status"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("remarks");
        elemField.setXmlName(new javax.xml.namespace.QName("http://MsevaPaymentTG.org/", "remarks"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("updatedby");
        elemField.setXmlName(new javax.xml.namespace.QName("http://MsevaPaymentTG.org/", "updatedby"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
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
        elemField.setFieldName("deptFlag");
        elemField.setXmlName(new javax.xml.namespace.QName("http://MsevaPaymentTG.org/", "DeptFlag"));
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
