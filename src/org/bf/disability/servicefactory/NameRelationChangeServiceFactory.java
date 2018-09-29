/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bf.disability.servicefactory;

import org.bf.disability.service.NameRelationChangeService;
import org.bf.disability.serviceimpl.NameRelationChangeServiceImpl;

/**
 *
 * @author 693461
 */
public class NameRelationChangeServiceFactory {

    public static NameRelationChangeServiceImpl nameRelationChangeServiceImpl;
    public static NameRelationChangeServiceImpl getNameRelationChangeServiceImpl()
  {
       if(nameRelationChangeServiceImpl==null)
               nameRelationChangeServiceImpl = new NameRelationChangeServiceImpl();
           return nameRelationChangeServiceImpl;
   }
  
}
