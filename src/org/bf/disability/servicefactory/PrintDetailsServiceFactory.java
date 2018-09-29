/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.servicefactory;

import org.bf.disability.serviceimpl.PrintDetailsServiceImpl;

/**
 *
 * @author 747577
 */
public class PrintDetailsServiceFactory {

    public static PrintDetailsServiceImpl printDetailsServiceImpl;

    public static PrintDetailsServiceImpl getPrintDetailsServiceImpl() {
        if (printDetailsServiceImpl == null) {
            printDetailsServiceImpl = new PrintDetailsServiceImpl();
        }
        return printDetailsServiceImpl;
    }
}
