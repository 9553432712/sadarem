/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.servicefactory;

import org.bf.disability.serviceimpl.AppletAuthorityServiceImpl;

/**
 *
 * @author 484898
 */
public class AppletAuthorityServiceFactory {

    public static AppletAuthorityServiceImpl appletAuthorityServiceImpl;

    public static AppletAuthorityServiceImpl getAppletAuthorityServiceImpl() {
        if (appletAuthorityServiceImpl == null) {
            appletAuthorityServiceImpl = new AppletAuthorityServiceImpl();
        }
        return appletAuthorityServiceImpl;
    }
}
