///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//package Aadhar;
//
//import com.ecentric.servicemodels.AadhaarProfile;
//import java.beans.XMLDecoder;
//import java.io.StringReader;
//import java.net.URL;
//import org.w3c.dom.CharacterData;
//import org.xml.sax.InputSource;
//import org.w3c.dom.Element;
//import org.w3c.dom.Node;
//
///**
// *
// * @author apoisadmin
// */
//public class NewClass {
//
//    public static void main(String[] args) {
//
//        try {
//            ServicesLocator l = new ServicesLocator();
//            String endpoinaddr = l.getServicesSOAP11port_httpAddress();
//            ServicesPortType proxy = null;
//            try {
//                proxy = l.getServicesSOAP11port_http((new URL(endpoinaddr)));
//            } catch (Exception e) {
//                // TODO Auto-generated catch block 548975603635
//                e.printStackTrace();
//            }
//            ((ServicesSOAP11BindingStub) proxy).setUsername("srdhws");
//            ((ServicesSOAP11BindingStub) proxy).setPassword("srdhws@123");
//            ((ServicesSOAP11BindingStub) proxy).setTimeout(1000000);
//            String s = proxy.getAadhaarInfo("936022723831", "");
//            InputSource is = new InputSource();
//            is.setCharacterStream(new StringReader(s));
//            XMLDecoder xmlDecoder = new XMLDecoder(is);
//            AadhaarProfile ap =(AadhaarProfile)xmlDecoder.readObject();
//            System.out.println(":uid: " + ap.getUid());
//            System.out.println(":name: " + ap.getName());
//            System.out.println(":dis: " + ap.getDistrict_name());
//            System.out.println(":mand: " + s);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static String getChardata(Element e) {
//        if (e != null && e.toString().length() > 0) {
//            Node child = e.getFirstChild();
//            if (child instanceof CharacterData) {
//                CharacterData cd = (CharacterData) child;
//                return cd.getData();
//            }
//        }
//        return "";
//    }
//}
