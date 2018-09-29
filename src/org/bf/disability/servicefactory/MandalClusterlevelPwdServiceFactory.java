/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bf.disability.servicefactory;

import org.bf.disability.serviceimpl.MandalClusterlevelPwdServiceImpl;

/**
 *
 * @author 842412
 */
public class MandalClusterlevelPwdServiceFactory {
    public static MandalClusterlevelPwdServiceImpl mandalClusterlevelPwdServiceImpl;

    public static MandalClusterlevelPwdServiceImpl getMandalClusterlevelPwdServiceImpl() {
        if (mandalClusterlevelPwdServiceImpl == null) {
            mandalClusterlevelPwdServiceImpl = new MandalClusterlevelPwdServiceImpl();
        }
        return mandalClusterlevelPwdServiceImpl;
    }

}
