/*
 * AddUserServiceFactory.java
 *
 * Created on September 12, 2008, 5:48 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.bf.disability.servicefactory;

import org.bf.disability.serviceimpl.AddUserServiceImpl;

/**
 * This class is designed based on factory design pattern whose responsibility is
 * to create an object to the AddUserSeerviceImpl class
 * @author Deviprasad
 * @version 1.0
 */
public class AddUserServiceFactory {
    
     public static AddUserServiceImpl addUserServiceImpl;
  public static AddUserServiceImpl getAddUserServiceImpl(){
       if(addUserServiceImpl==null)
               addUserServiceImpl = new AddUserServiceImpl();
           return addUserServiceImpl;
   }   
    
}
