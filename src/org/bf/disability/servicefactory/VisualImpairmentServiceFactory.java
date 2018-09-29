

/*
 * VisualImpairmentServiceFactory.java
 *
 * Created on September 12, 2008, 2:33 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.bf.disability.servicefactory;

import org.bf.disability.serviceimpl.VisualImpairmentServiceImpl;

/**
 * This class is designed based on factory design pattern whose responsibility is
 * to create an object to the VisualImpairmentServiceImpl class
 * @author Krishnaprasad K
 * @version 1.0
 */
public class VisualImpairmentServiceFactory 
{
    public static VisualImpairmentServiceImpl visualimparmentserviceimpl;
     public static VisualImpairmentServiceImpl getVisualImparmentImpl()
     {
         if(visualimparmentserviceimpl == null)
              visualimparmentserviceimpl = new VisualImpairmentServiceImpl();
         return visualimparmentserviceimpl;
     }
    
}
