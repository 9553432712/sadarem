/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bf.disability.servicefactory;

import org.bf.disability.serviceimpl.PwdsDisabilityPensionServiceImpl;

/**
 *
 * @author 842412
 */
public class PwdsDisabilityPensionServiceFactory {
    public static PwdsDisabilityPensionServiceImpl pwdsDisabilityPensionServiceImpl;

    public static PwdsDisabilityPensionServiceImpl getPwdsDisabilityPensionServiceImpl() {
        if (pwdsDisabilityPensionServiceImpl == null) {
            pwdsDisabilityPensionServiceImpl = new PwdsDisabilityPensionServiceImpl();
        }
        return pwdsDisabilityPensionServiceImpl;
    }

}
