/*
 * PersonCodeCheckServiceFactory.java
 *
 * Created on September 13, 2008, 10:57 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.bf.disability.servicefactory;

import org.bf.disability.serviceimpl.PersonCodeCheckServiceImpl;

/**
 * This class is designed based on factory design pattern whose responsibility is
 * to create an object to the PersonCodeCheckServiceImpl class
 * @author Deviprasad
 * @version 1.0
 */
public class PersonCodeCheckServiceFactory {
    
    public static PersonCodeCheckServiceImpl personCodeCheckServiceImpl;
    public static PersonCodeCheckServiceImpl getPersonCodeCheckServiceImpl(){
        if(personCodeCheckServiceImpl==null)
            personCodeCheckServiceImpl = new PersonCodeCheckServiceImpl();
        return personCodeCheckServiceImpl;
    }
    
}
