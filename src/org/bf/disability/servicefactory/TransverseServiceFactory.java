/*
 * TransverseServiceFactory.java
 *
 * Created on September 12, 2008, 12:55 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.bf.disability.servicefactory;

import org.bf.disability.serviceimpl.TransverseServiceImpl;

/**
 * This class is designed based on factory design pattern whose responsibility is
 * to create an object to the TransverseServiceImpl class
 * @author Pramod Kumar G
 * @version 1.0
 */
public class TransverseServiceFactory {
    
    
  public static TransverseServiceImpl transverseserviceimpl;
  public static TransverseServiceImpl getTransverseServiceImpl()
  {
       if(transverseserviceimpl==null)
               transverseserviceimpl = new TransverseServiceImpl();
           return transverseserviceimpl;
   }   
}
