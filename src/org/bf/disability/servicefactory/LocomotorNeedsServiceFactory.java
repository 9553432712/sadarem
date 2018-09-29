/*
 * LocomotorNeedsServiceFactory.java
 *
 * Created on September 12, 2008, 4:49 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.bf.disability.servicefactory;

import org.bf.disability.serviceimpl.LocomotorNeedsServiceImpl;

/**
 * This class is designed based on factory design pattern whose responsibility is
 * to create an object to the LocomotorNeedsServiceImpl class
 * @author Bapinaidu T
 * @version 1.0
 */
public class LocomotorNeedsServiceFactory {
    
    /** Creates a new instance of LocomotorNeedsServiceFactory */
  public static LocomotorNeedsServiceImpl locomotorneedsserviceimpl;
    public static LocomotorNeedsServiceImpl getLocomotorNeedsServiceImpl(){
        if(locomotorneedsserviceimpl==null)
            locomotorneedsserviceimpl=new LocomotorNeedsServiceImpl();
        return locomotorneedsserviceimpl;
       
    } 
    
}
