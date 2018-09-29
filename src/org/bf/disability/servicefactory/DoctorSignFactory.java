/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.servicefactory;

import org.bf.disability.serviceimpl.DoctorSignImpl;

/**
 *
 * @author 484898
 */
public class DoctorSignFactory {

    private DoctorSignFactory() {
    }
    public static volatile DoctorSignImpl doctorSignImpl;

    public static DoctorSignImpl getDoctorSignImpl() {
        if (doctorSignImpl == null) {
            synchronized (DoctorSignFactory.class) {
                if (doctorSignImpl == null) {
                    doctorSignImpl = new DoctorSignImpl();
                }
            }
        }
        return doctorSignImpl;
    }
}
