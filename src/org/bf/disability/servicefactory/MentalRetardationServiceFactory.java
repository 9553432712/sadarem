/*
 * MentalRetardationServiceFactory.java
 *
 * Created on September 12, 2008, 3:37 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.bf.disability.servicefactory;

import org.bf.disability.serviceimpl.MentalRetardationServiceImpl;

/**
 * This class is designed based on factory design pattern whose responsibility is
 * to create an object to the MentalRetardationServiceImpl class
 * @author Sunima Dilla
 * @version 1.0
 */
public class MentalRetardationServiceFactory {
    
    public static MentalRetardationServiceImpl mentalretardationserviceimpl;
    public static MentalRetardationServiceImpl getMentalRetardationServiceImpl(){
       if(mentalretardationserviceimpl==null)
               mentalretardationserviceimpl = new MentalRetardationServiceImpl();
           return mentalretardationserviceimpl;
   }   
}
    
    

