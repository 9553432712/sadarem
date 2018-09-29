/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bf.disability.servicefactory;

import org.bf.disability.serviceimpl.ReportToCaptureDeadDetailsServiceImpl;

/**
 *
 * @author 310926
 */
public class ReportToCaptureDeadDetailsServiceFactory {
  public static ReportToCaptureDeadDetailsServiceImpl getReportToCaptureDeadDetailsServiceImpl;

    public static ReportToCaptureDeadDetailsServiceImpl getReportToCaptureDeadDetailsServiceImpl() {

        if (getReportToCaptureDeadDetailsServiceImpl == null) {
            getReportToCaptureDeadDetailsServiceImpl = new ReportToCaptureDeadDetailsServiceImpl();
        }

        return getReportToCaptureDeadDetailsServiceImpl;
    }
}
