/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bf.disability.servicefactory;

import org.bf.disability.serviceimpl.PhysicalRequirementsDetailsServiceImpl;

/**
 *
 * @author 693461
 */
public class PhysicalRequirementsDetailsServiceFactory {

    public static PhysicalRequirementsDetailsServiceImpl physicalRequirementsDetailsServiceImpl;

    public static PhysicalRequirementsDetailsServiceImpl getPhysicalRequirementsDetailsServiceImpl(){

    if(physicalRequirementsDetailsServiceImpl==null){
      physicalRequirementsDetailsServiceImpl = new PhysicalRequirementsDetailsServiceImpl();
    }

    return physicalRequirementsDetailsServiceImpl;
    }



}
