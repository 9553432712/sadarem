/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.servicefactory;

import org.bf.disability.serviceimpl.CertificateGenerationImpl;

/**
 *
 * @author 728056
 */
public class CertificateGenerationServiceFoctory {

    public static CertificateGenerationImpl certificateGenerationImpl;

    public static CertificateGenerationImpl certificateGenerationImpl() {
        if (certificateGenerationImpl == null) {
            certificateGenerationImpl = new CertificateGenerationImpl();
        }

        return certificateGenerationImpl;
    }
}
