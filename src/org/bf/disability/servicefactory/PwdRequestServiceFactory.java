/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bf.disability.servicefactory;

import org.bf.disability.serviceimpl.pwdRequestServiceImpl;

/**
 *
 * @author 693461
 */
public class PwdRequestServiceFactory {
    public static pwdRequestServiceImpl pwdRequestServiceImpl;
    public static pwdRequestServiceImpl getPwdRequestServiceImpl(){
        if(pwdRequestServiceImpl==null){
            pwdRequestServiceImpl = new pwdRequestServiceImpl();
        }

    return pwdRequestServiceImpl;
    }


}
