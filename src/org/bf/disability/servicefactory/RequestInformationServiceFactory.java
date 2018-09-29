/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bf.disability.servicefactory;

import org.bf.disability.serviceimpl.RequestInformationServiceImpl;

/**
 *
 * @author 693461
 */
public class RequestInformationServiceFactory {

    public static RequestInformationServiceImpl requestInformationServiceImpl;

    public static  RequestInformationServiceImpl getRequestInformationServiceImpl(){

        if(requestInformationServiceImpl == null)
            requestInformationServiceImpl = new RequestInformationServiceImpl();

    return requestInformationServiceImpl;
    }


}
