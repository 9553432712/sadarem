/*
 * StatusForPWDReportServiceFactory.java
 *
 * Created on November 25, 2008, 4:07 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.bf.disability.servicefactory;

import org.bf.disability.serviceimpl.StatusForPWDReportServiceImpl;

/**
 * This class is designed based on factory design pattern whose responsibility is
 * to create an object to the StatusForPWDReportServiceImpl class
 * @author Raghavendra
 * @version 1.0
 */
public class StatusForPWDReportServiceFactory {
    
    /** Creates a new instance of StatusForPWDReportServiceFactory */
    public StatusForPWDReportServiceFactory() {
    }
   public static StatusForPWDReportServiceImpl statusForPWDReportServiceImpl ;
   public static StatusForPWDReportServiceImpl getStatusForPWDReportServiceImpl(){
       if(statusForPWDReportServiceImpl==null)
          statusForPWDReportServiceImpl = new StatusForPWDReportServiceImpl();
          return statusForPWDReportServiceImpl;
       
   }
}
