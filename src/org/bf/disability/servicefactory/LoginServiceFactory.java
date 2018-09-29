/*
 * LoginServiceFactory.java
 *
 * Created on September 12, 2008, 6:10 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.bf.disability.servicefactory;

import org.bf.disability.serviceimpl.LoginServiceImpl;

/**
 * This class is designed based on factory design pattern whose responsibility is
 * to create an object to the LoginServiceImpl class
 * @author Deviprasad
 * @version 1.0
 */
public class LoginServiceFactory {
    
    public static LoginServiceImpl loginServiceImpl;
  public static LoginServiceImpl getLoginServiceImpl(){
       if(loginServiceImpl==null)
               loginServiceImpl = new LoginServiceImpl();
           return loginServiceImpl;
   }   
    
}
