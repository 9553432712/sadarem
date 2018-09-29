/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.servicefactory;

import org.bf.disability.serviceimpl.MandalWiseClusterReportServiceImpl;

/**
 *
 * @author 695536
 */
public class MandalWiseClusterReportServicefactory {

    public static MandalWiseClusterReportServiceImpl mandalWiseClusterReportImpl;

    public static MandalWiseClusterReportServiceImpl getMandalWiseClusterReportImpl() {
        if (mandalWiseClusterReportImpl == null) {
            mandalWiseClusterReportImpl = new MandalWiseClusterReportServiceImpl();
        }
        return mandalWiseClusterReportImpl;

    }
}
