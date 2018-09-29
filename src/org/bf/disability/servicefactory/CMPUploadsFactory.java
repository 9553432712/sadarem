/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.servicefactory;

import org.bf.disability.serviceimpl.CMPUploadsImpl;



/**
 *
 * @author 484898
 */
public class CMPUploadsFactory {

    public static CMPUploadsImpl cmpUploadsImpl;

    public static CMPUploadsImpl getCMPUploadsImpl() {
        if (cmpUploadsImpl == null) {
            cmpUploadsImpl = new CMPUploadsImpl();
        }
        return cmpUploadsImpl;
    }
}
