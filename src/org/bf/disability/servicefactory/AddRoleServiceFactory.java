/*
 * AddRoleServiceFactory.java
 *
 * Created on September 12, 2008, 5:11 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.bf.disability.servicefactory;

import org.bf.disability.serviceimpl.AddRoleServiceImpl;

/**
 * This class is designed based on factory design pattern whose responsibility is
 * to create an object to the AddRoleServiceImpl class
 * @version 1.0
 */
public class AddRoleServiceFactory {
    
     public static AddRoleServiceImpl addRoleServiceImpl;
  public static AddRoleServiceImpl getAddRoleServiceImpl(){
       if(addRoleServiceImpl==null)
               addRoleServiceImpl = new AddRoleServiceImpl();
           return addRoleServiceImpl;
   }   
}
