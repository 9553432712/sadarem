/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bf.disability.servicefactory;

import org.bf.disability.serviceimpl.NewpersonServiceimpl;

/**
 *
 * @author 695536
 */
public class NewpersonServicefactory {

    public static NewpersonServiceimpl newpersonServiceimpl;

    public static NewpersonServiceimpl getNewpersonServiceimpl() {
        if (newpersonServiceimpl == null) {
            newpersonServiceimpl = new NewpersonServiceimpl();
        }
        return newpersonServiceimpl;
    }

}
