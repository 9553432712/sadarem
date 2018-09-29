/*
 * TerritoryServiceFactory.java
 *
 * Created on September 12, 2008, 5:42 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.bf.disability.servicefactory;

import org.bf.disability.serviceimpl.TerritoryServiceImpl;
/**
 * This class is designed based on factory design pattern whose responsibility is
 * to create an object to the TerritoryServiceImpl class
 * @author SVS Ganesh
 * @version 1.0
 */
public class TerritoryServiceFactory {
    
   public static TerritoryServiceImpl territoryserviceimpl;
   public static TerritoryServiceImpl getTerritoryServiceImpl(){
       if(territoryserviceimpl==null)
           territoryserviceimpl = new TerritoryServiceImpl();
       return territoryserviceimpl;
   }
    
}
