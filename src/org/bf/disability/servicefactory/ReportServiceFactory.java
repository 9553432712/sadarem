/*
 * ReportServiceFactory.java
 *
 * Created on September 12, 2008, 4:49 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.bf.disability.servicefactory;

import org.bf.disability.Exception.SADAREMException;
import org.bf.disability.serviceimpl.ReportServiceImpl;

/**
 * This class is designed based on factory design pattern whose responsibility is
 * to create an object to the ReportServiceImpl class
 * @author SVS Ganesh
 * @version 1.0
 */
public class ReportServiceFactory {
    
  
    public static ReportServiceImpl reportserviceimpl;
    public static ReportServiceImpl getReportServiceImpl() {
        if(reportserviceimpl==null)
            reportserviceimpl =new ReportServiceImpl();
        return reportserviceimpl;
    }
}
