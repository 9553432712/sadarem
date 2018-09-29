/*
 * CommonServiceFactory.java
 *
 * Created on September 13, 2008, 12:06 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.bf.disability.servicefactory;

import org.bf.disability.serviceimpl.CommonServiceImpl;

/**
 * This class is designed based on factory design pattern whose responsibility is
 * to create an object to the CommonServiceImpl class
 * @author SVS Ganesh
 * @version 1.0
 */
public class CommonServiceFactory {
    
  public static CommonServiceImpl commonserviceimpl;
  public static CommonServiceImpl getCommonServiceImpl(){
      if(commonserviceimpl==null)
          commonserviceimpl = new CommonServiceImpl();
      return commonserviceimpl;
  }
    
}
