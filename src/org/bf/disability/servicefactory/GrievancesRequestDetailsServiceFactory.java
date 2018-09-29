/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.servicefactory;

import org.bf.disability.serviceimpl.GrievancesRequestDetailsServiceImpl;

/**
 *
 * @author 728056
 */
public class GrievancesRequestDetailsServiceFactory {

    public static GrievancesRequestDetailsServiceImpl detailsServiceImpl;

    public static GrievancesRequestDetailsServiceImpl getdetailsServiceImpl() {
        if (detailsServiceImpl == null) {
            detailsServiceImpl = new GrievancesRequestDetailsServiceImpl();
        }
        return detailsServiceImpl;
    }
}
