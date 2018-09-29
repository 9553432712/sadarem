/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bf.disability.servicefactory;

import org.bf.disability.serviceimpl.UserDetailsServiceImpl;

/**
 *
 * @author t_bapinaidu
 */
public class UserDetailsServiceFactory {

    public static UserDetailsServiceImpl userDetailsServiceImpl;

    public static UserDetailsServiceImpl getUserDetailsServiceImpl()
    {
        if(userDetailsServiceImpl == null)
            userDetailsServiceImpl = new UserDetailsServiceImpl();
        return userDetailsServiceImpl;
    }


}
