/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.servicefactory;

import org.bf.disability.serviceimpl.PercentageWiseReportServiceImpl;

/**
 *
 * @author 842412
 */
public class PercentageWiseReportServiceFactory {

    public static PercentageWiseReportServiceImpl percentageWiseReportServiceImpl;

    public static PercentageWiseReportServiceImpl getPercentageWiseReportServiceImpl() {
        if (percentageWiseReportServiceImpl == null) {
            percentageWiseReportServiceImpl = new PercentageWiseReportServiceImpl();
        }
        return percentageWiseReportServiceImpl;
    }
}
