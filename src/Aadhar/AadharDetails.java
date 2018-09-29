/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Aadhar;

import java.beans.ExceptionListener;
import java.beans.XMLDecoder;
import java.io.ByteArrayInputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.SQLException;

import org.bf.disability.Exception.SADAREMDBException;

import com.ecentric.servicemodels.AadhaarProfile;

/**
 *
 * @author apoisadmin
 */
public class AadharDetails 
{

    public static synchronized AadhaarProfile getAadharDetails(String aadharno) throws InvocationTargetException 
    {
        AadhaarProfile ap = null;
        XMLDecoder xmlDecoder = null;
        String xmlString = null;
        try {
            ServicesLocator l = new ServicesLocator();
            String endpoinaddr = l.getServicesSOAP11port_httpAddress();
            ServicesPortType proxy = null;
            try 
            {
                proxy = l.getServicesSOAP11port_http((new URL(endpoinaddr)));
            }
            catch (Exception e) 
            {
                // TODO Auto-generated catch block 548975603635
                e.printStackTrace();
            }
            ((ServicesSOAP11BindingStub) proxy).setUsername("srdhws");
            ((ServicesSOAP11BindingStub) proxy).setPassword("srdhws@123");
            ((ServicesSOAP11BindingStub) proxy).setTimeout(1000000);
            xmlString = proxy.getAadhaarInfo(aadharno, "");
            final ByteArrayInputStream is = new ByteArrayInputStream(xmlString.getBytes("UTF-8"));
            xmlDecoder = new XMLDecoder(is);
            xmlDecoder.setExceptionListener(new ExceptionListener()
            {

                public void exceptionThrown(final Exception e) 
                {
                    e.printStackTrace();
                }
            });

            ap = (AadhaarProfile) xmlDecoder.readObject();

        } catch (final Throwable e) {
            e.printStackTrace();
        } finally {
            try {
                if (xmlDecoder != null) {
                    xmlDecoder.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ap;
    }

    public String validateAadhar(String aadharCardNo) throws SADAREMDBException, SQLException
    {
        String msg = "";
        AadhaarProfile aadharProfile = null;
        try {

            if (aadharCardNo != null) {
                try {
                    aadharProfile = (AadhaarProfile)AadhaarUtility.getAadhaarProfileByUID(aadharCardNo);
                } catch (Exception e) {
                    msg = "Aadhar Webservice is down. Please try after some time";
                }
                if (aadharProfile != null) {
                    //validation Aadhar Number End

                    //System.out.println("Status: " + aadharProfile.getStatus());
                    int invalidFlag = Integer.parseInt(aadharProfile.getStatus());

                    if (invalidFlag == 100) {
                        if (aadharProfile.getDistrict() != null) {

                            if (aadharProfile.getStatecode().equals("2")) {
                                msg = "100";
                            } else if (aadharProfile.getStatecode().equals("1")) {
                                msg = "ap";
                            } else if (aadharProfile.getStatecode().equals("101")) {
                                msg = "101";
                            } else {
                                msg = "invalid";
                            }
                        }
                    } else if (invalidFlag == 101) {
                        msg = "101";
                    } else if (invalidFlag == 121) {
                        msg = "rejected";

                    } else {
                        msg = "invalid";
                    }
                } else {
                    msg = "down";
                }
            }
        } catch (Exception sADAREMException) {
            msg = "down";
        }
        //System.out.println("target: " + target);
        return msg;
    }
}


