/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.servicefactory;

import org.bf.disability.serviceimpl.MeesvaSearchServiceImpl;

/**
 *
 * @author 693461
 */
public class MeesvaSearchServiceFactory {

    public static MeesvaSearchServiceImpl meesvaSearchServiceImpl;

    public static MeesvaSearchServiceImpl getMeesvaSearchServiceImpl() {
        if (meesvaSearchServiceImpl == null) {
            meesvaSearchServiceImpl = new MeesvaSearchServiceImpl();
        }
        return meesvaSearchServiceImpl;
    }
}
