/*
 * DataBackupServiceFactory.java
 *
 * Created on September 22, 2008, 2:17 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.bf.disability.servicefactory;

import org.bf.disability.serviceimpl.DataBackupServiceImpl;

/**
 * This class is designed based on factory design pattern whose responsibility is
 * to create an object to the DataBackupServiceImpl class
 * @author Pramod Kumar G
 * @version 1.0
 */
public class DataBackupServiceFactory 
{
  public static DataBackupServiceImpl dataBackupServiceImpl;
  public static DataBackupServiceImpl getDataBackupServiceImpl()
  {
       if(dataBackupServiceImpl==null)
               dataBackupServiceImpl = new DataBackupServiceImpl();
           return dataBackupServiceImpl;
   }   
}
