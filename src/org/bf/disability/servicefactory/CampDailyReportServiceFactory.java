/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bf.disability.servicefactory;

import org.bf.disability.serviceimpl.CampDailyReportServiceImpl;

/**
 *
 * @author SADAREM
 */
public class CampDailyReportServiceFactory {

    public static CampDailyReportServiceImpl campDailyReportServiceImpl;

    public static CampDailyReportServiceImpl getcampDailyReportServiceImpl(){

        if(campDailyReportServiceImpl == null)
            campDailyReportServiceImpl = new CampDailyReportServiceImpl();
        return campDailyReportServiceImpl;
    }

}
