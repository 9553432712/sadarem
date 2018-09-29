/*
 * AddRoleDisplayServiceFactory.java
 *
 * Created on September 12, 2008, 5:29 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.bf.disability.servicefactory;

import org.bf.disability.serviceimpl.AddRoleDisplayServiceImpl;


/**
 * This class is designed based on factory design pattern whose responsibility is
 * to create an object to the AddRoleDisplayServiceImpl class
 * @author Deviprasad
 * @version 1.0
 */
public class AddRoleDisplayServiceFactory {
    
    public static AddRoleDisplayServiceImpl addRoleDisplayServiceImpl;
  public static AddRoleDisplayServiceImpl getAddRoleDisplayServiceImpl(){
       if(addRoleDisplayServiceImpl==null)
               addRoleDisplayServiceImpl = new AddRoleDisplayServiceImpl();
           return addRoleDisplayServiceImpl;
   }   
}
