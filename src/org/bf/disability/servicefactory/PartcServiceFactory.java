/*
 * PartcServiceFactory.java
 *
 * Created on September 13, 2008, 11:03 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.bf.disability.servicefactory;

import org.bf.disability.serviceimpl.PartcServiceImpl;

/**
 *
 * @author bapinaidu.t
 */
public class PartcServiceFactory {
    
    /** Creates a new instance of PartcServiceFactory */
     public static PartcServiceImpl partcserviceimpl;
    public static PartcServiceImpl getPartcServiceImpl(){
        if(partcserviceimpl==null)
            partcserviceimpl=new PartcServiceImpl();
        return partcserviceimpl;
       
    } 
}
