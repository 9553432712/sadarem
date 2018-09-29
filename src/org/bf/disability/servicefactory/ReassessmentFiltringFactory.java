/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.servicefactory;

import org.bf.disability.serviceimpl.ReassessmentFiltringImpl;

/**
 *
 * @author 484898
 */
public class ReassessmentFiltringFactory {

    public static ReassessmentFiltringImpl reassessmentFiltringImpl;

    /** Method for creating the singleton Object */
    public static ReassessmentFiltringImpl getReassessmentFiltringImpl() {
        if (reassessmentFiltringImpl == null) {
            reassessmentFiltringImpl = new ReassessmentFiltringImpl();
        }
        return reassessmentFiltringImpl;
    }
}
