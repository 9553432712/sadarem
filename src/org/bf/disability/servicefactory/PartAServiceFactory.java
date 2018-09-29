/*
 * PartAServiceFactory.java
 *
 * Created on September 8, 2008, 2:18 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.bf.disability.servicefactory;

import org.bf.disability.serviceimpl.PartAServiceImpl;

/**
 * This class is designed based on factory design pattern whose responsibility is
 * to create an object to the PartAServiceImpl class
 * @author SVS Ganesh
 * @version 1.0
 */
public class PartAServiceFactory {
    
  public static PartAServiceImpl partaserviceimpl;
  public static PartAServiceImpl getPartAServiceImpl(){
       if(partaserviceimpl==null)
               partaserviceimpl = new PartAServiceImpl();
           return partaserviceimpl;
   }   
}
