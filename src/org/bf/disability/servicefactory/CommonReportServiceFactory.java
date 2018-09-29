/*
 * CommonReportServiceFactory.java
 *
 * Created on December 8, 2008, 10:23 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.bf.disability.servicefactory;

import org.bf.disability.serviceimpl.CommonReportServiceImpl;

/**
 *
 * @author svsganesh
 */
public class CommonReportServiceFactory {
    
    /** Creates a new instance of CommonReportServiceFactory */
    public CommonReportServiceFactory() {
    }
    public static CommonReportServiceImpl commonReportServiceImpl;
    public static CommonReportServiceImpl getCommonReportServiceImpl(){
        if(commonReportServiceImpl==null)
            commonReportServiceImpl = new CommonReportServiceImpl();
        return commonReportServiceImpl;
    }
}
