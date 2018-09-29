/*
 * UpperExterimityServiceFactory.java
 *
 * Created on September 12, 2008, 12:40 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.bf.disability.servicefactory;

import org.bf.disability.serviceimpl.UpperExterimityServiceImpl;

/**
 * This class is designed based on factory design pattern whose responsibility is
 * to create an object to the UpperExtremityServiceImpl class
 * @author SVS Ganesh
 * @version 1.0
 */
public class UpperExterimityServiceFactory {
    
    /** Creates a new instance of UpperExterimityServiceFactory */
   
  public static UpperExterimityServiceImpl upperexterimityserviceimpl;
  public static UpperExterimityServiceImpl getUpperExterimityServiceImpl(){
      if(upperexterimityserviceimpl==null)
          upperexterimityserviceimpl = new UpperExterimityServiceImpl();
      return upperexterimityserviceimpl;
  }
}
