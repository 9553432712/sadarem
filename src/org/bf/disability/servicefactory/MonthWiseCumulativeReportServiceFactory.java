/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bf.disability.servicefactory;

import org.bf.disability.serviceimpl.MonthWiseCumulativeReportServiceImpl;

/**
 *
 * @author 747577
 */
public class MonthWiseCumulativeReportServiceFactory {

    public static MonthWiseCumulativeReportServiceImpl monthWiseCumulativeReportServiceImpl;
    public static MonthWiseCumulativeReportServiceImpl getMonthWiseCumulativeServiceImpl()
  {
       if(monthWiseCumulativeReportServiceImpl==null)
               monthWiseCumulativeReportServiceImpl = new MonthWiseCumulativeReportServiceImpl();
           return monthWiseCumulativeReportServiceImpl;
   }
}
