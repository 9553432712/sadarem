/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bf.disability.servicefactory;

import org.bf.disability.serviceimpl.PensionForSearchReportServiceImpl;

/**
 *
 * @author 484898
 */
public class PensionForReportSearchServiceFactory {

    public static PensionForSearchReportServiceImpl pensionForSearchReportServiceImpl;
    public static PensionForSearchReportServiceImpl getpensionForSearchReportServiceImpl(){

        if(pensionForSearchReportServiceImpl == null)
                pensionForSearchReportServiceImpl = new PensionForSearchReportServiceImpl();

                return pensionForSearchReportServiceImpl;
    }
}
