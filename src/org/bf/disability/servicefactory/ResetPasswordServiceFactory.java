/*
 * ResetPasswordServiceFactory.java
 *
 * Created on September 13, 2008, 11:41 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.bf.disability.servicefactory;

import org.bf.disability.serviceimpl.PersonCodeCheckServiceImpl;
import org.bf.disability.serviceimpl.ResetPasswordServiceImpl;

/**
 * This class is designed based on factory design pattern whose responsibility is
 * to create an object to the ResetPasswordServiceImpl class
 * @author Deviprasad
 * @version 1.0
 */
public class ResetPasswordServiceFactory {
    
    public static ResetPasswordServiceImpl resetPasswordServiceImpl;
    public static ResetPasswordServiceImpl getResetPasswordServiceImpl(){
        if(resetPasswordServiceImpl==null)
            resetPasswordServiceImpl = new ResetPasswordServiceImpl();
        return resetPasswordServiceImpl;
    }
    
}
