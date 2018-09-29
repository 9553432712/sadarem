/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.servicefactory;

import org.bf.disability.serviceimpl.NoPhotosDataServiceImpl;

/**
 *
 * @author 728056
 */
public class NoPhotosDataServiceFactory {

    public static NoPhotosDataServiceImpl noPhotosDataServiceImpl;

    public static NoPhotosDataServiceImpl getPhotosDataServiceImpl() {
        if (noPhotosDataServiceImpl == null) {
            noPhotosDataServiceImpl = new NoPhotosDataServiceImpl();
        }
        return noPhotosDataServiceImpl;
    }
}
