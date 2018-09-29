/*
 * AddHabitationServiceFactory.java
 *
 * Created on September 12, 2008, 1:16 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.bf.disability.servicefactory;

import org.bf.disability.serviceimpl.AddHabitationServiceImpl;


/**
 * This class is designed based on factory design pattern whose responsibility is
 * to create an object to the AddHabitationServiceImpl class
 * @author Pramod Kumar G
 * @version 1.0
 */
public class AddHabitationServiceFactory {
    
  public static AddHabitationServiceImpl addhabitationserviceimpl;
  public static AddHabitationServiceImpl getAddHabitationServiceImpl()
  {
       if(addhabitationserviceimpl==null)
               addhabitationserviceimpl = new AddHabitationServiceImpl();
           return addhabitationserviceimpl;
   }   
}
