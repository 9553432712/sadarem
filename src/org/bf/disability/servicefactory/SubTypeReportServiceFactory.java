/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bf.disability.servicefactory;

import org.bf.disability.serviceimpl.SubTypeReportServiceImpl;

/**
 *
 * @author 747577
 */
public class SubTypeReportServiceFactory {

    public static SubTypeReportServiceImpl subTypeReportServiceImpl;
    public static SubTypeReportServiceImpl getsubTypeReportServiceImpl()
  {
       if(subTypeReportServiceImpl==null)
               subTypeReportServiceImpl = new SubTypeReportServiceImpl();
           return subTypeReportServiceImpl;
   }
}
