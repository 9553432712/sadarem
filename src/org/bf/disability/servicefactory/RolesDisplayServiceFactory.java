/*
 * RolesDisplayServiceFactory.java
 *
 * Created on September 13, 2008, 12:27 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.bf.disability.servicefactory;

import org.bf.disability.serviceimpl.PartAServiceImpl;
import org.bf.disability.serviceimpl.RolesDisplayServiceImpl;

/**
 * This class is designed based on factory design pattern whose responsibility is
 * to create an object to the RolesDisplayServiceImpl class
 * @author Deviprasad 
 * @version 1.0
 */
public class RolesDisplayServiceFactory 
{
    public static RolesDisplayServiceImpl rolesdisplayserviceimpl;
    public static RolesDisplayServiceImpl getRolesDisplayServiceImpl(){
    if(rolesdisplayserviceimpl==null)
               rolesdisplayserviceimpl = new RolesDisplayServiceImpl();
    return rolesdisplayserviceimpl;
   } 
}
