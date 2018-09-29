/*
 * GetServicesServiceFactory.java
 *
 * Created on September 13, 2008, 11:07 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.bf.disability.servicefactory;

import org.bf.disability.serviceimpl.GetServicesServiceImpl;

/**
 * This class is designed based on factory design pattern whose responsibility is
 * to create an object to the GetServicesServiceImpl class
 * @author SVS Ganesh
 * @version 1.0
 */
public class GetServicesServiceFactory {
    
    /**
     * Creates a new instance of GetServicesServiceFactory
     */
   
    public static GetServicesServiceImpl getservicesseviceimpl;
    
     public static GetServicesServiceImpl getServicesServiceImpl(){
         if(getservicesseviceimpl==null)
             getservicesseviceimpl=new GetServicesServiceImpl();
         return getservicesseviceimpl;
     }
   
    
}
