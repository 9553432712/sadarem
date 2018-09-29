

package org.bf.disability.servicefactory;

import org.bf.disability.serviceimpl.AmputationServiceImpl;
/**
 * This class is designed based on factory design pattern whose responsibility is
 * to create an object to the AmputationServiceImpl class
 * @author Deviprasad 
 * @version 1.0
 */

public class AmputationServiceFactory {
    
    
   
    
  public static AmputationServiceImpl amputationServiceImpl;
  public static AmputationServiceImpl getAmputationServiceImpl(){
       if(amputationServiceImpl==null)
               amputationServiceImpl = new AmputationServiceImpl();
           return amputationServiceImpl;
   }   
}
