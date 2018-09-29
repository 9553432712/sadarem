/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.servicefactory;

import org.bf.disability.serviceimpl.UpdateSadaremDetailsServiceImpl;

/**
 *
 * @author 728056
 */
public class UpdateSadaremDetailsServiceFactory {

    public static UpdateSadaremDetailsServiceImpl detailsServiceImpl;

    public static UpdateSadaremDetailsServiceImpl getdetailsServiceImpl() {
        if (detailsServiceImpl == null) {
            detailsServiceImpl = new UpdateSadaremDetailsServiceImpl();
        }
        return detailsServiceImpl;
    }
}
