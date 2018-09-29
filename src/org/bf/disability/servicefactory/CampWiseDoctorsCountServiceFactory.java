/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.servicefactory;

import org.bf.disability.serviceimpl.CampWiseDoctorsCountServiceImpl;



/**
 *
 * @author 693461
 */
public class CampWiseDoctorsCountServiceFactory {

    public static CampWiseDoctorsCountServiceImpl campWiseDoctorsCountServiceImpl;

    public static CampWiseDoctorsCountServiceImpl getCampWiseDoctorsCountServiceImpl() {

        if (campWiseDoctorsCountServiceImpl == null) {
            campWiseDoctorsCountServiceImpl = new CampWiseDoctorsCountServiceImpl();
        }


        return campWiseDoctorsCountServiceImpl;

    }
}
