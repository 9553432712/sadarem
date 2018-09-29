/*
 * TrunkServiceFactory.java
 *
 * Created on September 12, 2008, 3:24 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.bf.disability.servicefactory;

import org.bf.disability.serviceimpl.TrunkServiceImpl;

/**
 * This class is designed based on factory design pattern whose responsibility is
 * to create an object to the TrunkServiceImpl class
 * @author Bapinaidu
 * @version 1.0
 */
public class TrunkServiceFactory {
    
    /** Creates a new instance of TrunkServiceFactory */
    public TrunkServiceFactory() {
    }
   public static TrunkServiceImpl trunkserviceimpl;
    public static TrunkServiceImpl getTrunkServiceImpl(){
        if(trunkserviceimpl==null)
            trunkserviceimpl=new TrunkServiceImpl();
        return trunkserviceimpl;
       
    }  
}
