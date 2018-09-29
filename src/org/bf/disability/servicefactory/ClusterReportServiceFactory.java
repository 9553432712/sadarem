/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.servicefactory;

import org.bf.disability.serviceimpl.ClusterReportServiceImpl;

/**
 *
 * @author 747577
 */
public class ClusterReportServiceFactory {

    public static ClusterReportServiceImpl clusterReportServiceImpl;

    public static ClusterReportServiceImpl getClusterReportServiceImpl() {
        if (clusterReportServiceImpl == null) {
            clusterReportServiceImpl = new ClusterReportServiceImpl();
        }
        return clusterReportServiceImpl;
    }
}
