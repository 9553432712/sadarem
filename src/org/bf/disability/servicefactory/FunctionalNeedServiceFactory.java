/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author 509865
 */
package org.bf.disability.servicefactory;

import org.bf.disability.serviceimpl.FunctionalNeedServiceImpl;

/**
 * This class is designed based on factory design pattern whose responsibility is
 * to create an object to the FunctionalNeedServiceImpl class
 * @author sivakumar
 * @version 1.0
 */
public class FunctionalNeedServiceFactory {

    public static FunctionalNeedServiceImpl functionalNeedServiceImpl;

    public static FunctionalNeedServiceImpl getFunctionalNeedServiceImpl() {
        if (functionalNeedServiceImpl == null) {
            functionalNeedServiceImpl = new FunctionalNeedServiceImpl();
        }
        return functionalNeedServiceImpl;
    }
}
