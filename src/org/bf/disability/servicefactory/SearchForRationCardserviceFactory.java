/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.servicefactory;

import org.bf.disability.serviceimpl.SearchForRationCardserviceImpl;

/**
 *org.bf.disability
 * @author 525483
 */
public class SearchForRationCardserviceFactory {

    public static SearchForRationCardserviceImpl searchForRationCardserviceImpl;

    public static SearchForRationCardserviceImpl getSearchForRationCardserviceImpl() {
        if (searchForRationCardserviceImpl == null) {
            searchForRationCardserviceImpl = new SearchForRationCardserviceImpl();
        }
        return searchForRationCardserviceImpl;
    }
}
