/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.servicefactory;

import org.bf.disability.serviceimpl.UploadGosandActsServiceImpl;



/**
 *
 * @author 695048
 */
public class UploadGosandActsServiceFactory {

    public static UploadGosandActsServiceImpl getGosandActsServiceImpl;

    public static UploadGosandActsServiceImpl getGosandActsServiceImpl() {

        if (getGosandActsServiceImpl == null) {
            getGosandActsServiceImpl = new UploadGosandActsServiceImpl();
        }

        return getGosandActsServiceImpl;
    }
}
