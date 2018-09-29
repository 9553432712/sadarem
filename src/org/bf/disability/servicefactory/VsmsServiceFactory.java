/*
 * VsmsServiceFactory.java
 *
 * Created on October 16, 2008, 2:43 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.bf.disability.servicefactory;

import org.bf.disability.serviceimpl.VsmsScreeningTestServiceImpl;

/**
 * This class is designed based on factory design pattern whose responsibility is
 * to create an object to the VsmsScreeningTestServiceImpl class
 * @author Sunima Dilla
 * @version 1.0
 */
public class VsmsServiceFactory {
    
    public static VsmsScreeningTestServiceImpl vsmsscreeningtestserviceimpl;
    public static VsmsScreeningTestServiceImpl getVsmsScreeningTestServiceImpl(){
       if(vsmsscreeningtestserviceimpl==null)
               vsmsscreeningtestserviceimpl = new VsmsScreeningTestServiceImpl();
           return vsmsscreeningtestserviceimpl;
   }   

    
}
