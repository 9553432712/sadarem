/*
 * HearingImpairmentServiceFactory.java
 *
 * Created on September 12, 2008, 5:45 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.bf.disability.servicefactory;
import org.bf.disability.serviceimpl.HearingImpairmentServiceImpl;
/**
 * This class is designed based on factory design pattern whose responsibility is
 * to create an object to the HearingImpairmentServiceImpl class
 * @author Sunima Dilla
 * @version 1.0
 */
public class HearingImpairmentServiceFactory {
    
    public static HearingImpairmentServiceImpl hearingimpairmentserviceimpl;
    public static HearingImpairmentServiceImpl getHearingServiceImpl(){
       if(hearingimpairmentserviceimpl==null)
               hearingimpairmentserviceimpl = new HearingImpairmentServiceImpl();
           return hearingimpairmentserviceimpl;
   }   
}
    
    

