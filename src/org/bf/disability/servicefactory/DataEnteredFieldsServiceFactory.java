/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bf.disability.servicefactory;

import org.bf.disability.serviceimpl.DataEnteredFieldsServiceImpl;

/**
 *
 * @author t_bapinaidu
 */
public class DataEnteredFieldsServiceFactory {
    
  public static DataEnteredFieldsServiceImpl dataEnteredFieldsServiceImpl;
  public static DataEnteredFieldsServiceImpl getDataEnteredFieldsServiceImpl()
  {
       if(dataEnteredFieldsServiceImpl==null)
               dataEnteredFieldsServiceImpl = new DataEnteredFieldsServiceImpl();
           return dataEnteredFieldsServiceImpl;
   }
}
