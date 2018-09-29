/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bf.disability.servicefactory;

import org.bf.disability.serviceimpl.ReAssessmentCampWiseServiceImpl;

/**
 *
 * @author SADAREM
 */
public class ReAssessementCampWiseReportServiceFactory {

    public static ReAssessmentCampWiseServiceImpl reAssessementCampWiseReportServiceImpl;

    public static ReAssessmentCampWiseServiceImpl getReAssessementCampWiseReportServiceImpl(){

        if(reAssessementCampWiseReportServiceImpl==null)
               reAssessementCampWiseReportServiceImpl = new ReAssessmentCampWiseServiceImpl();
           return reAssessementCampWiseReportServiceImpl;

       
    }

}
