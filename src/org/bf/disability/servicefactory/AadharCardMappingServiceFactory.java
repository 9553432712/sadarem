/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.servicefactory;

import org.bf.disability.serviceimpl.AadharCardMappingServiceImpl;

/**
 *
 * @author 693461
 */
public class AadharCardMappingServiceFactory {

    public static AadharCardMappingServiceImpl aadharCardMappingServiceImpl;

    public static AadharCardMappingServiceImpl getAddRoleDisplayServiceImpl() {
        if (aadharCardMappingServiceImpl == null) {
            aadharCardMappingServiceImpl = new AadharCardMappingServiceImpl();
        }
        return aadharCardMappingServiceImpl;
    }
}
