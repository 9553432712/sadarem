/*
 * LowerExtremityServiceFactory.java
 *
 * Created on September 12, 2008, 1:20 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.bf.disability.servicefactory;


import org.bf.disability.serviceimpl.LowerExtremityServiceImpl;
/**
 * This class is designed based on factory design pattern whose responsibility is
 * to create an object to the LowerExtremityServiceImpl class
 * @author Bapinaidu
 * @version 1.0
 */
public class LowerExtremityServiceFactory {
    
    /** Creates a new instance of LowerExtremityServiceFactory */
    
    public static LowerExtremityServiceImpl lowerExtremityserviceimpl;
    public static LowerExtremityServiceImpl getLowerExtremityServiceImpl(){
        if(lowerExtremityserviceimpl==null)
            lowerExtremityserviceimpl=new LowerExtremityServiceImpl();
        return lowerExtremityserviceimpl;
       
    } 
}
