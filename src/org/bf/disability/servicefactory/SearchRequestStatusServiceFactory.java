/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bf.disability.servicefactory;

import org.bf.disability.serviceimpl.SearchRequestStatusServiceImpl;

/**
 *
 * @author 310926
 */
public class SearchRequestStatusServiceFactory {

     public static SearchRequestStatusServiceImpl searchRequestStatusServiceImpl;

    public static SearchRequestStatusServiceImpl getSearchRequestStatusServiceImpl(){

       if(searchRequestStatusServiceImpl ==null)
           searchRequestStatusServiceImpl = new SearchRequestStatusServiceImpl();

     return searchRequestStatusServiceImpl;
    }

}
