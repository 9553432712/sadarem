/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.servicefactory;

import org.bf.disability.serviceimpl.ValidPersonDetailsServiceImpl;

/**
 *
 * @author 728056
 */
public class ValidPersonDetailsServiceFactory {

    public static ValidPersonDetailsServiceImpl detailsServiceImpl;

    public static ValidPersonDetailsServiceImpl getDetailsServiceImpl() {
        if (detailsServiceImpl == null) {
            detailsServiceImpl = new ValidPersonDetailsServiceImpl();

        }
        return detailsServiceImpl;
    }
}
