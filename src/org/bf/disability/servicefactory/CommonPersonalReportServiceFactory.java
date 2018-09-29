/*
 * CommonPersonalReportServiceFactory.java
 *
 * Created on November 26, 2008, 1:57 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.bf.disability.servicefactory;

import org.bf.disability.serviceimpl.CommonPersonalReportServiceImpl;

/**
 * This class is designed based on factory design pattern whose responsibility is
 * to create an object to the CommonPersonalReportServiceImpl class
 * @author SVS Ganesh
 * @version 1.0
 */
public class CommonPersonalReportServiceFactory {
    
    /** Creates a new instance of CommonPersonalReportServiceFactory */
    public CommonPersonalReportServiceFactory() {
    }
    public static CommonPersonalReportServiceImpl commonPersonalReportServiceImpl;
    public static CommonPersonalReportServiceImpl getCommonPersonalReportServiceImpl(){
        if(commonPersonalReportServiceImpl==null)
            commonPersonalReportServiceImpl = new CommonPersonalReportServiceImpl();
        return commonPersonalReportServiceImpl;
    }
}
