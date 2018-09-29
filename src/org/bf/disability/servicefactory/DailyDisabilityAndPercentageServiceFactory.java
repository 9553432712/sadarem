/*
 * DailyDisabilityAndPercentageServiceFactory.java
 *
 * Created on November 25, 2008, 3:14 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.bf.disability.servicefactory;

import org.bf.disability.serviceimpl.DailyDisabilityAndPercentageServiceImpl;

/**
 * This class is designed based on factory design pattern whose responsibility is
 * to create an object to the DailyDisabilityAndPercentageServiceImpl class
 * @author SVS Ganesh
 * @version 1.0
 */
public class DailyDisabilityAndPercentageServiceFactory {
    
    /** Creates a new instance of DailyDisabilityAndPercentageServiceFactory */
    public DailyDisabilityAndPercentageServiceFactory() {
    }
   
     
  
  public static DailyDisabilityAndPercentageServiceImpl dailyDisabilityAndPercentageServiceImpl;
  public static DailyDisabilityAndPercentageServiceImpl getDailyDisabilityAndPercentageServiceImpl(){
      if(dailyDisabilityAndPercentageServiceImpl==null)
          dailyDisabilityAndPercentageServiceImpl = new DailyDisabilityAndPercentageServiceImpl();
      return dailyDisabilityAndPercentageServiceImpl;
  }
}
