/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.servicefactory;

import org.bf.disability.serviceimpl.DashboardReportsServiceImpl;

/**
 *
 * @author apoisadmin
 */
public class DashboardReportsServiceFactory {

    public static DashboardReportsServiceImpl dashboardReportsServiceImpl;

    public static DashboardReportsServiceImpl getDashboardReportsServiceImpl() {
        if (dashboardReportsServiceImpl == null) {
            dashboardReportsServiceImpl = new DashboardReportsServiceImpl();
        }
        return dashboardReportsServiceImpl;
    }
}
